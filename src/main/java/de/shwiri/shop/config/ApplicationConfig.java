package de.shwiri.shop.config;

import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.enterprise.context.ApplicationScoped;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:jboss/datasources/ExampleDS", // Dein JNDI-Name
        callerQuery = "select password_hash from users where email = ?", // Spalte & Tabelle anpassen
        groupsQuery = "select role_name from user_roles where email = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Iterations=3072",
                "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA256",
                "Pbkdf2PasswordHash.SaltSizeBytes=32"
        }
)
@ApplicationScoped
public class ApplicationConfig {
}
