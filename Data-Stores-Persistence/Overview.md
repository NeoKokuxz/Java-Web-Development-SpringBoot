# Entity Design Overview

## JPA - Java Persistence API
> A specification describing how to manage relational data

### Hibernate
> An implementation of JPA specification

### Entity Design
- Value vs. Entity Types
- BasicTypes in Java and JDBC
- Identifiers - unique entity
- Relationships
- Inheritance

#### Entity Definitions
- Entity Type
> A java object that contains a collection of data and fedines as relationalship with the database

- POJO - Plain Old Java Object
> Plain Old Java Object is Java object that contains data but no methods that describe behavior

- Value Type
> A piece of data inside an Entity that represents a single value

#### @Entity & Hibernate
The class that with @Entity annotation will informs Hibernate about this class should be stored in a database <br>
the table name are often associate with the table name or custome table name using @Table(name="CustomName") at class level <br>
the column name can be custom using @Column(name="ColumnName", length=512) annotation for data  <br>
Note***: They must provide a public or protected no argument constructor **** <br>
Converting Type: String name can be custom with @Type(type="nstring") instead of default varchar for name value <br>
@Nationalized be can use instead of type annotation <br>

- Hebernate recommonded using Long or Interger as identifier

### Composite identifier
- Started class with @Embeddable annotation
- The class implements Serializable interface
- Override the equals and hashcode method. 
```java
@Embeddable
public class PersonPK implements Serializable {
    private Long flowerId;
    private String personName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonPK personPK = (PersonPK) o;
        return flowerId == personPK.flowerId &&
                personName.equals(personPK.personName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowerId, personName);
    }
}
```
```java
@Entity
public class Delivery {
//    @Id * No more 
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @EmbeddedId
    PersonPK id;
```





