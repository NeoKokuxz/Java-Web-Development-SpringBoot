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

### Data Access Object Pattern (DAO)

- Repository: Present database as collection of entities
- Data Access Object (DAO): Programmatic interface to one ore more related tables



### DAO vs. Repository
> The Data Access Object (DAO) pattern is an alternative to the Repository pattern. A DAO is a programmatic interface to a table or collection of related tables. Whereas a Repository presents a collection of Entities that you modify to update the database, a DAO will often provide methods that expose various actions, along with standard CRUD operations.

### Data Object Projection Scope
> Data Object scope can vary depending on the needs of the application. It is more common to project directly into DTOs from a DAO, but remember that @JSONView still works with Data Objects, so it is often still worth adhering to a similar set of boundaries to what you would observe with Entities.

### DAO pattern in Hibernate
> While the Repository pattern is common with Hibernate, because most modifications can be done simply by editing Entities, there is nothing that prevents us from using the DAO pattern in either a Hibernate or SQL-based persistence model.

