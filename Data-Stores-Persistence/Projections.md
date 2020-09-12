# Projections
> Projections allow to return non-Entity data from queries
- Good to  combine unrelated Entities or Return Value types
- Query directly into Data Object that match the needs to front end

## Projecting into a Value Type
```java
private static final String LIST_FAVORITE_COMPOSERS = "select distinct p.favoriteComposer from Person p";

List<String> listFavoriteComposers() {
   TypedQuery<String> query = entityManager.createQuery(LIST_FAVORITE_COMPOSERS, String.class);
   return query.getResultList();
}
```

## Projecting into non-Entity Object
```java 
public class PersonComposerDTO {
   private String name;
   private String composer;

   public PersonComposerDTO(String name, String composer) {
       this.name = name;
       this.composer = composer;
   }
   /* getters and setters */
}
```
```java
private static final String GET_PERSON_AND_COMPOSER =
       "select new com.udacity.jdnd.course3.controller.PersonComposerDTO(p.name, p.favoriteComposer) " +
       "from Person p " +
       "where p.id = :id";

PersonComposerDTO getPersonComposer(Long id) {
   TypedQuery<PersonComposerDTO> query = entityManager.createQuery(GET_PERSON_AND_COMPOSER, PersonComposerDTO.class);
   query.setParameter("id", id);
   return query.getSingleResult();
}
```
