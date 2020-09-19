# Hibernate & Initialization

## spring.datasource.initialization-mode values
- Embedded: Default. Initialization performed on embedded databases only.
- Always: Initialization for both embedded and external databases.
- Never: No initialization for either embedded or external databases.

## spring.jpa.hibernate.ddl-auto
> This property allows you to customize Hibernate’s initialization behavior.

- Create: Drop all tables for defined Entities, then create them.
- Create-drop: Create tables, drop them when application stops.
- Update: Attempt to migrate previous version of tables to match current Entities.
- Validate: Throw an exception if tables or columns are missing.
- None: Do not initialize tables.

```java
//Example:
spring.jpa.hibernate.ddl-auto=create / create-drop / update / validate / none
//For in memory database like H2, it's default to create and for external like MySQL it's none
```

### show-sql
- Spring offers a useful command to print all generated sql commands to the console:
``` 
 spring.jpa.show-sql=true
```
- There’s also a hibernate property for formatting the sql output that makes it easier to read:
```
spring.jpa.properties.hibernate.format_sql=true
```
