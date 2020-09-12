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

#### @Embeddable
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
#### @IdClass
```java
public class PersonPK implements Serializable {
   private int heightCm;
   private String sockColor;

   @Override
   public boolean equals(Object o) {...}

   @Override
   public int hashCode() {...}
   /* getters and setters*/
}
```
```java
@Entity
@IdClass(PersonPK.class)
public class Person {
   @Id
   private int heightCm;
   @Id
   private String sockColor;

   public PersonPK getId() {
       PersonPK id = new PersonPK();
       id.setHeightCm(heightCm);
       id.setSockColor(sockColor);
       return id;
   }

   public void setId(PersonPK id) {
       this.heightCm = id.getHeightCm();
       this.sockColor = id.getSockColor();
   }
   /* getters and setters */
}
```

#### @Embeddle Vs. @IdClass
- @Embeddable
    - Key fields are more obvious
        - person.id.sockColor
    - Object-oriented querying by id object type
    - Preferred when PK has meaning to your application
    - Preferred when PK is reused elsewhere is the app
    
- @IdClass
    - Allows use of unmodifiable classes as keys
    - Sometimes used to support legacy or 3rd-party code
    - Key fields not distinguishable 
        - person.sockColor
    - Must query by specific attributes
    - Allows generation of values within the PK
    - Support Auto generated ID

#### Unidirectional Association / Bi-directional Association

- Types of Entity Associations
    - OneToOne: Single Entity on each side of the relationship.
    - OneToMany and ManyToOne: List of Entities on one side, single Entity on the other.
    - ManyToMany: Lists of Entities on both sides.

- @OneToOne
- @JoinColumn(name = "outter_column")

- Bi-directional Association
    - Turn the outter class into @Entity
    - Provide @OneToOne to the main class
    - Mapped by attribute on the child side of the relationship
    - child side is the primary entity class
    - This allows the contained entity class table to control theforeign key constraint 
    
- Code:
    - Unidirectional
```java
@Entity
public class Person {
   @Id
   @GeneratedValue
   private Long id;

   @OneToMany
   private List<Outfit> outfits;

   /* rest of class */
}

@Entity
public class Outfit {
   @Id
   @GeneratedValue
   private Long id;

   /* rest of class */
}
```
 - Bidirectional
```java
@Entity
public class Person {
   @Id
   @GeneratedValue
   private Long id;

   @OneToMany(mappedBy = "person")
   private List<Outfit> outfits;

   /* rest of class */
}

@Entity
public class Outfit {
   @Id
   @GeneratedValue
   private Long id;

   @ManyToOne
   private Person person;

   /* rest of class */
}
```

- @JoinTable
```java
@Entity
public class Person {
   @Id
   @GeneratedValue
   private Long id;

   @ManyToMany
   @JoinTable(
      name = "person_outfit",
      joinColumns = { @JoinColumn(name = "person_id")},
      inverseJoinColumns = { @JoinColumn(name = "outfit_id")}
   )
   private List<Outfit> outfits;

   /* rest of class */
}
```
    
## Entity Relationships
- Value Type
    - Become single columns in their containing Enetity table
- Embeddable
    - Objects add their attributes as columns to their containing Entity table
- Entities
    - Become new tables which are ferenced to their containing Entity table through a Join Column

### Association Types
- OneToOne 
    - Single Entity on each side of the relationship
- OneToMany or ManyToOne
    - List of Entities on one side and single on other side
- ManyToMany
    - List of Entities on both side    

### @ElementCollection
> You can use the @ElementCollection annotation to denote an association between a single Entity and a list of values that are not themselves Entities. This annotation lets you persist Lists of Embeddables or enums, for example. These embeddables will be stored in a separate table, along with the id of the Entity in which they are contained.
```java
@Entity
public class Person {
   @Id
   @GeneratedValue
   private Long id;

   @ElementCollection
   private List<Outfit> outfits;

   /* rest of class */
}

@Embeddable
public class Outfit {
   private String hat;
   private String gloves;
   private String shoes;
   private String legs;
   private String top;
}
```

### Inheritance
- Composition: Objects are related because one of them "has-a" refrence to the other one
- Inheritance: Objects are related because one of them "is-a" type of other one. 

```java
@Entity
public abstract class Humanoid {
   @Id
   @GeneratedValue
   Long id;

   @OneToMany(mappedBy = "humanoid")
   List<Outfit> outfits;

   /* getters and setters */
}

@Entity
public class Person extends Humanoid {

   @Type(type="nstring")
   private String name;
   private int age;
   @Column(name="composer", length=512)
   private String favoriteComposer;

   /* getters and setters */
}

@Entity
public class CreepyDepartmentStoreMannequin extends Humanoid {

   private boolean hasAHead;
   private MannequinShape mannequinShape;

   enum MannequinShape {
       LITHE, MUSCULUR, UNASSUMING;
   }

    /* getters and setters */
}
```
- JOINED
> Creates a table for the parent class and each subclass. The subclass tables only have fields unique to their class. Supports polymorphic queries by UNIONing subclass tables. Uses the least space of the solutions that allow Not Null columns.

- TABLE_PER_CLASS
> Creates a table for the parent class and each subclass. The subclass tables have all fields from the parent class as well as fields unique to their class. Supports polymorphic queries by UNIONing subclass tables, but does not require any UNION to access superclass fields on non-polymorphic queries.

- @MappedSuperclass
> This is selected by using the @MappedSuperclass annotation on the parent class instead of @Entity. It creates a table per class just like TABLE_PER_CLASS, but there is no superclass table. It does not support polymorphic queries, but never requires UNIONS to query subclasses.

## Multitier Architecture Advantages
- Maintenance
    - Centralizes access to your data source
    - Reduces time needed to make changes to Entity interactions
    - Reduces amount of code each developer needs to understand
- Performance
    - Allows application layers to easily be separated into modules
    - Reduces application size
    - Enables scaling of independent components
    - Supports future architecture deployment
- Security
    - Able to secure each tier with different permissions
    - Reduces redundant authentication in other tiers

