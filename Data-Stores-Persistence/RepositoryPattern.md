# Repository Pattern
- @Component
  - @Controller
  - @Service
  - @Repository
  
## @Repository
> Spring will translate persistence exception into Spring exceptions. This annotation identifies components in Spring that interact with the database.

### Repository Pattern
> is a way of thinking about database as a collection of objects.

- Simple Repository Interface
```java
public interface HumanoidRepository {
   Humanoid save(Humanoid h);
   Humanoid findById(Long id);
   void delete(Humanoid h);
}
```
- Sample Implementation
```java
@Repository
public class HumanoidRepositoryImpl implements HumanoidRepository {
   @Autowired
   EntityManager entityManager;

   @Override
   public Humanoid save(Humanoid h) {
       if(h.getId() == null || h.getId() <= 0) {
           entityManager.persist(h);
       } else {
           h = entityManager.merge(h);
       }
       return h;
   }

   @Override
   public Humanoid findById(Long id) {
       return entityManager.find(Humanoid.class, id);
   }

   @Override
   public void delete(Humanoid h) {
       if (entityManager.contains(h)) {
           entityManager.remove(h);
       } else {
           entityManager.remove(entityManager.merge(h));
       }
   }

}
```

### Repository Management
- Unique Interface, Unique Implementation
  - More customizable, able to limit methods per Entity.
  - Lots of very similar interfaces and classes.
- Generic Interface, Unique Implementation
  - Fewer Interfaces, but implement unused methods.
- Generic Interface, Generic Implementation
  - Most work up front.
  - Least redundant code.

## Spring Data JPA
- JPA: Defines the API for using objects to map to database tables
- Hibernate: Implements the JPA API
- Spring Data JPA provides code and code-generation tools to make it easier to use JPA. 

### Referencing Associations, Providing JPQL, and using Named Queries
```java
@Repository
public interface OutfitRepository extends CrudRepository<Outfit, Long> {
   //finds a single outfit by attribute
   Outfit findByHat(String hat);
   //you can use Operators like And/Or, Lessthan/greaterthan, null/notnull
   Outfit findByHatAndShoes(String hat, String shoes);
}
```
```java
@Repository
public interface HumanoidRepository extends JpaRepository<Humanoid, Long> {
   //you can reference associations and attributes by chaining
   //attribute names. Here we reference Humanoid.outfits.hat
   List<Humanoid> findAllByOutfitsHat(String hat);

   //you can provide specific JPQL Queries
   @Query("select h from Humanoid h where :outfit member of h.outfits ")
   List<Humanoid> findAllByOutfit(@Param("outfit") Outfit outfit);

   //does the same as above
   List<Humanoid> findAllByOutfitsContaining(Outfit outfit);

   //automatically uses query named Humanoid.findAllNamedQuery
   List<Humanoid> findAllNamedQuery(Outfit outfit);

}
```
