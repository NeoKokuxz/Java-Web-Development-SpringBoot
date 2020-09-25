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

## JDBCTemplate
- Spring utility class for managing JDBC connections and transactions
- Like the EntityManager, but for JDBC
- Automatically constructed and injected by Spring
- JdbcTemplate functions like the EntityManager: it provides connections, executes queries, and manages transactions.
```java
NamedParameterJdbcTemplate allows you to use named parameters in your query string.

-- with standard JdbcTemplate
select * from person where id = ? and age >= ?

-- with NamedParameterJdbcTemplate
select * from person where id = :id and age >= :minAge
```

### queryForObject
> To query for a single object, you can use the jdbcTemplate.queryForObject method. This example takes three parameters:

- Query String
- A map of parameter names to parameter values
- A RowMapper that returns the instance of the object for which we’re querying

```java
@Autowired
NamedParameterJdbcTemplate jdbcTemplate;

private static final String SELECT_PERSON_BY_ID = 
   "SELECT * FROM person " + 
   "WHERE id = :id";

public PersonData getPersonById(Long id){
   return jdbcTemplate.queryForObject(
           SELECT_PERSON_BY_ID,
           new MapSqlParameterSource().addValue("id", id),
           new BeanPropertyRowMapper<>(PersonData.class));
}
```

### update
> The jdbcTemplate.update method allows you to execute SQL statements that modify the database, such as INSERT, UPDATE, or DELETE.

> Instead of taking a RowMapper, it takes an optional KeyHolder object that can contain the id of the new row.
```java
private static final String INSERT_PERSON = 
   "INSERT INTO person (name, age, favorite_composer) " + 
   "VALUES(:name, :age, :favoriteComposer)";

public Long addPerson(PersonData personData) {
   KeyHolder key = new GeneratedKeyHolder();
   jdbcTemplate.update(
           INSERT_PERSON,
           new BeanPropertySqlParameterSource(personData),
           key);
   return key.getKey().longValue();
}
```
### SimpleJdbcInsert
> The SimpleJdbcInsert class allows us to do inserts without writing a query, which can make it easier to maintain our code if the composition of our tables change.

```java
// does the same as the previous addPerson method
public Long addPersonInsert(PersonData personData) {
   SimpleJdbcInsert sji = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
           .withTableName("person")
           .usingGeneratedKeyColumns("id");
   return sji.executeAndReturnKey(new 
  BeanPropertySqlParameterSource(personData)).longValue();
}
```
