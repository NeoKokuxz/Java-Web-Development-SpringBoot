# JPQL
> JPQL allows us to write queries for the EntityManager that return objects directly. Their syntax is very similar to SQL, but we reference Entities instead of tables.

## SQL VS JPQL
- SQL
```
SELECT * 
FROM   person p /* Table name */ 
WHERE  p.favoritecomposer LIKE '%Sibelius%' /* Column */
```
- JPQL
```
SELECT p 
FROM   person p /* Entity */ 
WHERE  p.favoritecomposer LIKE '%Sibelius%' /* Attribute */
```
### Creating a JPQL Query
> To create a query, inject an entityManager into your class and then use the createQuery method. This method returns different types of Query objects depending on your parameters. TypedQuery is recommended for clarity.

```java
private static final String FIND_PERSON_BY_COMPOSER =
       "select p from Person p " +
       "where p.favoriteComposer like :favoriteComposer";

public Person findPersonByFavoriteComposer(String favoriteComposer){
   TypedQuery<Person> query = entityManager.createQuery(FIND_PERSON_BY_COMPOSER, Person.class);
   query.setParameter("favoriteComposer", favoriteComposer);
   return query.getSingleResult();
}
```
### Referencing Associated Entities
> In SQL, you will often join tables together to search for results by related Entities. In JPQL, you can reference the value of associated Entities by accessing them directly as Entity attributes in the query.

```java
private static final String FIND_HUMANOID_BY_OUTFIT =
       "select h from Humanoid h " +
       "where :outfit member of h.outfits";

List<Humanoid> findHumanoidByOutfit(Outfit o){
   TypedQuery<Humanoid> query = entityManager.createQuery(FIND_HUMANOID_BY_OUTFIT, Humanoid.class);
   query.setParameter("outfit", o);
   return query.getResultList();
}
```
