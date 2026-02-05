package de.shwiri.shop.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import java.sql.Connection;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class LiquibaseConfig {

    @Inject
    private Logger logger;

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    private DataSource dataSource;

    @PostConstruct public void updateDatabase() {
        // Deaktiviert die Sicherheitsprüfung für XSD-Lookups
        System.setProperty("liquibase.secureParsing", "false");

        try (Connection connection = dataSource.getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);

            liquibase.update("");
            logger.log(Level.INFO, "Liquibase Migration erfolgreich abgeschlossen.");
        } catch (Exception e) {
            logger.log(Level.FINER, "Liquibase Migration fehlgeschlagen\n{0}", e.getMessage());
            throw new RuntimeException("Liquibase Migration fehlgeschlagen", e);
        }
    }
}
