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
