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
