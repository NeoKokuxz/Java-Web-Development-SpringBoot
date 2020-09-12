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
