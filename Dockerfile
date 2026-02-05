FROM quay.io/wildfly/wildfly:latest-jdk17

# Kopieren der notwendigen Dateien
COPY postgresql-42.7.9.jar /tmp/
COPY datasource-config.cli /tmp/
COPY target/online-shop.war /opt/jboss/wildfly/standalone/deployments/

# Konfiguration ausführen & Management User anlegen
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/tmp/datasource-config.cli && \
    /opt/jboss/wildfly/bin/add-user.sh admin admin123 --silent && \
    rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history

# Startbefehl mit Bindung an alle IPs
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]