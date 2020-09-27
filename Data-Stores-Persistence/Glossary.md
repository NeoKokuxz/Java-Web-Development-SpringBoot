## Multitier Architecture
- Refers to a form of design that separates various functions of the application into their own layers.

## Java Persistence API (JPA)
- A specification describing how to manage relational data.

## Hibernate
- An implementation of the JPA Specification. You can access Hibernate’s documentation page here.

## POJO or "Plain Old Java Object"
- A Java object that contains data, but no methods to describe behavior.

## Entity Types
- Java classes that describe a collection of data.
- Contain Data only, no behavior
- Represented by a table in the database

## Value Types
- The data inside an Entity.
- Primitives like int, boolean, char
- Classes that only represent a single piece of data, like BigInteger or LocalDate
- Represented by a column in the database

## Basic Types
- Basic Types map a single database column to a single, non-aggregated Java type.

## Serialization
- Transforming your data into a format that can be stored and reconstructed later.

## First Normal form
- Each attribute of a table contains only atomic values.

## Atomic
- Representing a single piece of data; indivisible.

## Embeddables
- Add their attributes as columns in the containing Entity’s table.

## Entities
- Become new tables which relate to a containing entity by a Join Column.

## Unidirectional
- Association specified on one side of the relationship only.
  - Doesn't retrieve data you won’t use.
  - Should use Set collection type for most efficient SQL.
- Bidirectional
  - Association specified on both sides of the relationship. Use mappedBy on the containing Entity side.
  - Access both sides of relationship with a single query.
  - Hibernate recommends for @OneToMany, because it allows the foreign key constraint to exist only on the table of the contained object.

## OneToOne
- Single Entity on each side of the relationship.

## OneToMany and ManyToOne
- List of Entities on one side, single Entity on the other.

## ManyToMany
- Lists of Entities on both sides.

## Inheritance
- Inheritance is a way to share data and associations across multiple related classes.

## Single Table Inheritance
- The default inheritance strategy used by Hibernate. All the fields of the parent and children classes are stored in the same table. Allows the fastest polymorphic queries because no tables need to be joined to access all subclasses. Cannot support Not Null column constraints because columns must be able to contain null for sibling classes.

## Polymorphic Query
- A query for the parent class that returns elements of all subclass types.

## Joined Inheritance
- Creates a table for the parent class and each subclass. The subclass tables only have fields unique to their class. Supports polymorphic queries by UNIONing subclass tables. Uses the least space of the solutions that allow Not Null columns.

## Table Per Class Inheritance
- Creates a table for the parent class and each subclass. The subclass tables have all fields from the parent class as well as fields unique to their class. Supports polymorphic queries by UNIONing subclass tables, but does not require any UNION to access superclass fields on non-polymorphic queries.

## Mapped Superclass
- This is selected by using the @MappedSuperclass annotation on the parent class instead of @Entity. It creates a table per class just like TABLE_PER_CLASS, but there is no superclass table. It does not support polymorphic queries, but never requires UNIONS to query subclasses.

## Data Transfer Objects (DTOs)
- Data structures designed to represent the needs of the front end.

## JSONView Annotations
- Annotation that filters which Entity data is visible to the Presentation layer.

## Persistence Context
- Describes the relationship between all the Entity instances in our program and their representations in the underlying database.

## Instance
- A specific copy of an Entity in program memory.

## Persistence Context Entity States
- Transient: not associated with the persistence context. Often has not yet had an ID assigned.
- Managed: persistent. Managed by the current persistence context. Changes to the entity will be reflected in the backing database.
- Detached: previously managed. Occurs to all managed entities when persistence context ends.
- Removed: scheduled to be removed from the database. Java object still exists and has ID.

## Entity Manager
- Class that manages the persistence state of Entities in the Persistence Context

## Entity Manager Operations
- Persist: Takes an Entity not yet managed. The Entity becomes managed and will be saved to the database.
- Find: Looks up an id in the database and returns a managed Entity.
- Merge: Updates an Entity that is in the detached state. Returns an instance of that Entity that is now managed. If Entity was not found in the database to update, persists Entity as a new row.
- Remove: Detaches an entity and deletes it from the database.

## Lazy Loading
- A way to prevent associated Entities from being retrieved until they are referenced.

## FetchType.EAGER
- Always retrieve the associated values as part of the Entity retrieval. Default value for @ManyToOne and @OneToOne.

## FetchType.LAZY
- Wait to retrieve associated values until they are referenced. Default value for @OneToMany and @ManyToMany.

## CascadeType
- Specifies which persistence operations should apply to associated entities when executed on the containing entity.

## CRUD
- Short for 'Create', 'Read', 'Update', 'Delete', the four main categories of basic database operations.

## JPQL
- Java Persistence Query Language. A query language very similar to SQL that can be used to reference Entities and their attributes directly.

## Named Queries
- Query strings that are defined in a class-level annotation that are validated on application launch.

## Projections
- Results of queries that are loaded into objects other than Entities.

## Repository Pattern
- A way of thinking about your database as a collection of objects. Exposes methods similar to Collection interfaces like Map or Set.

## @Repository Annotation
- A specialization of the Spring @Component annotation. Marks a class for component scanning by Spring and also indicates that persistence exceptions thrown by the class should be translated into Spring exceptions.

## Spring Data JPA Repository
- An interface specifying default repository behavior. Extending these interfaces allows Spring Data to automatically generate implementations based on interface method names.

## Flushing
> The process of synchronizing the state of the persistence context with the underlying database. Triggered by:
- Transaction Ends
- Query overlaps with queued Entity actions
- Native SQL Query executes without registering affected Entities

## Transaction
- A set of operations that either succeed or fail as a group.

## Level 1 Cache
- An intermediate layer into which changes can be written and objects retrieved quickly. The Persistence Context functions as a Level 1 Cache, because it does not write changes to the database until Flushing occurs.

## Lesson Outline
- Initialization with SQL
- Data Object vs. Entities
- Data Access Objects (DAOs)
- JdbcTemplate
- Performance

## Definitions

### SQL Initialization Scripts
> Spring attempts to execute two scripts by default when the application starts:

- schema.sql - Create or update the schema.
- data.sql - Initialize or modify the data in your tables.

### Platform-specific initialization
> You may provide additional initialization scripts using the naming pattern:

- schema-${platform}.sql //schema-test.sql
- data-${platform}.sql //data-test.sql

### Data Object
> A Java class that maps to a specific database.

### Data Access Object
> A design pattern for the data layer that presents a programmatic interface to one or more related tables. Whereas a Repository presents a collection of Entities that you modify to update the database, a DAO will often provide methods that expose various actions, along with standard CRUD operations.

### JdbcTemplate
> Provides connections, executes queries, and manages transactions.

### NamedParameterJdbcTemplate
> A JdbcTemplate that allows you to use named parameters in your query strings.

### RowMapper
> Class that takes a single row for a query result set and returns an object representing that row.

### ResultSetExtractor
> Class that takes an entire result set and returns one object representing all the results.

### spring.jpa.properties.hibernate.generate_statistics
> Property that enables a feature causing Hibernate to print all transaction statistics when a transaction ends.
