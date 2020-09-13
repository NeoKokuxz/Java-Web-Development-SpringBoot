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

