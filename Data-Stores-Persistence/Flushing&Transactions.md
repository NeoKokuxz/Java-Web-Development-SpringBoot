# Flushing & Transactions

## Definitions
- Flushing 
> The process of synchronizing the state of the persistence context with the underlying database

- Transaction
> A set of operations that either succeed or fail as a group

- Level 1 cache 
> The persistence context function as a Level 1 cache, because it does not write changes to the database until Flushing occurs.

## Transaction-Per-Request
- Advantages
  - Allows Persistence Context to function as cache
  - Minimize number of transactions created
  - Avoids locking resources across requests
  
- Implementation 
  - Annotate service methods that interact with repositories with @Transactional
  - Don't begin transactions in the controller layer
  
```java
Transactional example
@Transactional
public void createOutfitForPerson(Outfit outfit, Long personId) {
   outfitRepository.save(outfit);

   //getOne throws EntityNotFoundException if personId doesn't exist!
   humanoidRepository.getOne(personId).getOutfits().add(outfit);
}
```

## Flushing
- Triggers:
  - When transaction ends
  - Query overlaps with the queued entity actions
  - Native query that has not registered which entities it affects

