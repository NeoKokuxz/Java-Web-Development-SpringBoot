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
## Named Queries
> Named queries can help us organize our queries by class. They also allow us to compiler-check our queries for validity at build time. Any named queries that reference invalid entities will throw exceptions, helping ensure we don’t commit invalid query strings. To use them, declare them at the top of the Entity class to which they refer. Remember that their names are global across the whole persistence unit, so they should all have unique names.
### Static Query String Constants
- NOTE*** both annotation should be use at class level (above class declaration)
- @NamedQuery Annotation
```java
@NamedQuery(
       name = "Outfit.findByHat",
       query = "select o from Outfit o where o.hat = :hat")
TypedQuery<Outfit> query = entityManager.createNameQuery("Outfit.findByHat", Outfit.class); 
```
- @NamedQueries Annotation
```java
@NamedQueries{
       @NamedQuery(
              name = "Outfit.findByHat",
              query = "select o from Outfit o where o.hat = :hat")
       TypedQuery<Outfit> query = entityManager.createNameQuery("Outfit.findByHat", Outfit.class); 
       @NamedQuery(
              name = "Outfit.findBySock",
              query = "select o from Outfit o where o.sock = :sock")
       TypedQuery<Outfit> query = entityManager.createNameQuery("Outfit.findBySock", Outfit.class); 
}

```
#### Constant Query String
```java
private static final String FIND_PERSON_BY_COMPOSERS = "select p from Person p where p.favoriteComposer like :favoriteComposer"
```
#### Query Fragments
```java
private static final String SELECT_FIND_PERSON = "select p from Person p" ;
private static final String WHERE_COMPOSER = "where p.favoriteComposer like :favoriteComposer";
private static final String WHERE_PANTS = "where p.outfit.shoes = :shoes"
```

#### Criteria Interface
> Allows you to use Java to construct your query
##### Criteria Builder
- OG Query
```java
SELECT h FROM Humanoid h
WHERE :outfit MEMBER OF h.outfits
```
- Criteria Built Query
```java
List<Humanoid> findHumanoidByOutfitCriteria(Outfit o) {
   CriteriaBuilder cb = entityManager.getCriteriaBuilder();
   CriteriaQuery<Humanoid> criteria = cb.createQuery(Humanoid.class);
   Root<Humanoid> root = criteria.from(Humanoid.class);

   criteria.select(root).where(cb.isMember(o, root.get("outfits")));
   return entityManager.createQuery(criteria).getResultList();
}
```

