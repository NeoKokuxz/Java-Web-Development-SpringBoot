# Java Persistence API 

## Persistence Context 
> Describes the relationship between all the Entity instances in our program and their representations in the underlying database

### Instance
> A specific copy of an Entity in program memory

#### Transient
> not associated with the persistence context. Oftern has not yet had an ID assigned

#### Managed: 
> Persistent, Managed by the current persistence context. Changes to the entity will be reflected in the backing database.

#### Detached
> Previously managed. Occurs to all managed entities when persistence context ends.

#### Removed
> Scheduled to be removed from the database. Java object still exists and has ID. 

## Entity Manager using @PersistenceContext
- Persist: Takes an Entity not yet managed. The Entity becomes managed and will be saved to the database.
- Find: Looks up an id in the database and returns a managed Entity.
- Merge: Updates an Entity that is in the detached state. Returns an instance of that Entity that is now managed. If Entity was not found in the database to update, persists Entity as a new row.
- Remove: Detaches an entity and deletes it from the database.

```java
@PersistenceContext
EntityManager entityManager;

public void persistExample(Person p) {
   entityManager.persist(p); //write p to the database
   p.setFavoriteComposer("Johann Strauss II"); //will update database
}

public void findExample(Long id) {
   Person p = entityManager.find(Person.class, id); //retrieve an instance by its key
   p.setFavoriteComposer("Sir Malcolm Arnold"); // will update database
}

public void getReferenceExample(Long personId, Long outfitId) {
   Person p = entityManager.find(Person.class, personId);
   Outfit outfitReference = entityManager.getReference(Outfit.class, outfitId);
   p.getOutfits().add(outfitReference);
}

public void mergeExample(Person detachedPerson){
   detachedPerson.setFavoriteComposer("Rimsky Korsakov");
   Person managedPerson = entityManager.merge(detachedPerson);
   detachedPerson.setFavoriteComposer("Antonio Salieri"); //will have no effect on database
   managedPerson.setFavoriteComposer("C.P.E. Bach"); //will overwrite Korsakov
}

public void deleteExample(Long id) {
   Person p = entityManager.find(Person.class, id); //retrieve an instance by its key
   entityManager.remove(p); //will delete row from database
}
```

## Default FetchTypes
### FetchType.LAZY
> Always retrieve the associated values as part of the Entity retrieval. This means the initial query for the entity retrieves this data.
   - @OneToMany
   - @ManyToMany
### FetchType.EAGER
> Wait to retrieve associated values until they are referenced. Lazy-loaded attributes are Hibernate proxy objects whose specific values are retrieved from the database only if they’re accessed. The initial query for the entity will NOT retrieve this data.
   - @ManyToOne
   - @OneToOne
  
```java
@Entity
public class Person {

   @Id
   @GeneratedValue
   Long id;

   @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
   List<Outfit> outfits;

   private String name;
   private int age;
   private String favoriteComposer;

   /* rest of class */
}
```
#### Default Value
> An easy way to remember this is that both associations mapping to Many objects default to Lazy, because it’s more costly to retrieve lots of objects from the database. Associations mapping to One object default to Eager, because there’s usually less information.

### CascadeType
> CascadeType allows us to modify Entity associations so that persistence operations on one Entity will cascade to other Entities associated with it.
- Each cascadeType will Cascade operations of that specific type
- Propagates corresponding EntityManager methods to any assoicated Entities

```java
@Entity
public class Person {

   @Id
   @GeneratedValue
   Long id;

   @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
   List<Outfit> outfits;

   private String name;
   private int age;
   private String favoriteComposer;

   /* rest of class */
```

