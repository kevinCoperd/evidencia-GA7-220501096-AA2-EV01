# Módulo JDBC - mokevnet

## Descripción
CRUD con JDBC para la entidad Producto.

## Requisitos
- Java 11+
- MySQL
- Maven

## Setup rápido
1. Crear BD: mysql -u root -p < sql/schema.sql
2. Configurar src/main/resources/application.properties
3. mvn clean package
4. java -jar target/mokevnet-1.0-SNAPSHOT-jar-with-dependencies.jar
