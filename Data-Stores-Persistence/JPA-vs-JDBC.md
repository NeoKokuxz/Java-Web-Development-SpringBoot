# JPA vs. JDBC
## JPA
- Convenient
- Table relationships defined in Java
- SQL is generated

## JDBC
- Can be faster
- Table relationships defined in Database
- SQL is written

## Hibernate Statistics
- Enable Statistics and log them to console
```
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=DEBUG
```
- Note:
```
For Hibernate 5.4.5 and later you can also use this property to cause any single query longer than the specified duration to print a message to the log.

hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS=25
```

## Comparing Performance - Simple Example
```java

public void setFavoriteComposerModifyEntity(Long personId, String favoriteComposer) {
   personRepository.getOne(personId).setFavoriteComposer(favoriteComposer);
}

private static final String SET_FAVORITE_COMPOSER = 
    "update Person p set p.favoriteComposer = :favoriteComposer where p.id =:id";
public void setFavoriteComposerJpql(Long personId, String favoriteComposer){
   entityManager.createQuery(SET_FAVORITE_COMPOSER)
           .setParameter("favoriteComposer", favoriteComposer)
           .setParameter("id", personId)
           .executeUpdate();
}

private static final String SET_FAVORITE_COMPOSER_NATIVE = 
    "update humanoid h set h.favorite_composer = :favoriteComposer where h.id = :id";
public void setFavoriteComposerNative(Long personId, String favoriteComposer){
   entityManager.createNativeQuery(SET_FAVORITE_COMPOSER_NATIVE)
           .setParameter("favoriteComposer", favoriteComposer)
           .setParameter("id", personId)
           .executeUpdate();
}
```
> The first solution executes 2 JDBC Statements, one to look up, another to modify. If favoriteComposer is unchanged, then it only executes 1 JDBC Statement.

> The second and third solutions both execute 1 JDBC Statement. However, the native SQL example requires knowing the Person is stored in a table called humanoid, while the JPQL does not. Their performance is otherwise similar.

> In practice, only about a third of the time in each Session is preparing and executing statements, and the performance difference between these situations is nominal, so it could be hard to justify not using the first example.

## Comparing Performance - Complex Example
> Let’s consider a more expensive operation - updating every outfit a Person owns.
```java

public void setShoeColorModifyEntity(Long personId, String shoeColor) {
   personRepository.findById(personId).get().getOutfits().stream()
           .forEach(o -> o.setShoes(shoeColor));
}

private static final String SET_SHOE_COLOR_NATIVE =
       "update Outfit o " +
               "set o.shoes = :color " +
               "where o.id in " +
               "(select ho.outfits_id " +
               "from humanoid_outfits ho " +
               "where ho.humanoid_id = :id)";
public void setShoeColorNative(Long humanoidId, String color){
   entityManager.createNativeQuery(SET_SHOE_COLOR_NATIVE)
           .setParameter("color", color)
           .setParameter("id", humanoidId)
           .executeUpdate();
}
```
-  this case, the first example is vastly simpler. It doesn’t require knowledge of the database structure, is easy to understand, and can be easily modified. However, it executes 1 JDBC Statement for every single outfit a person owns, plus 1 JDBC statement to find a person and 1 JDBC statement to write them. If you modify the shoe color of someone with 30 pairs of shoes, you will prepare and execute 32 JDBC statements.

- The second example is messy, but only requires a single JDBC statement. However, it’s faster than the first example, taking about a third the time.

- The question is whether that kind of performance improvement is worth the complexity. If it was a 30x performance for cases with 30 outfits, perhaps so, but I think it would be an error to perform this optimization for a 2-3x improvement before you know it’s going to be a problem.

- Note: This is not a robust performance testing setup, it is only meant to demonstrate the statistics provided by hibernate for discussion.

- Note 2: MySQL prohibits subselects containing the same table you modify in an update statement. As such, you cannot use the following JPQL, because it is converted into a SQL statement containing a subselect of Outfit.
```sql
update Outfit o 
set o.shoes = :color 
where o.id in 
(select o2.id from Humanoid h 
join h.outfits o2 
where h.id = :id)
```
