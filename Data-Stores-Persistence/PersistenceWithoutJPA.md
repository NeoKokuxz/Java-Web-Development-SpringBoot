# Persistence Without JPA

### SQL Initialization Scripts
- Spring attempts to execute two scripts by default when the application starts:

  - schema.sql - Create or update the schema.
  - data.sql - Initialize or modify the data in your tables.
- The default directory for these files is src/main/resources

### Properties
> Still controlled by the same property
```
spring.datasource.initialization-mode=[always|embedded|never]
```
- Disable hibernate initialization to avoid conflicts
```
spring.jpa.hibernate.ddl-auto=none
```
