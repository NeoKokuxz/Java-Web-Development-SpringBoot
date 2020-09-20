# Configuring Unit Test DB

## Create a new application.properties
- under: root/src/test/resources

### Using H2 in memory database
```
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:db
spring.datasource.username=sa
spring.datasource.password=sa
```

## @SpringBootTest
> This annotation goes on your unit test class. creates an entire Spring ApplicationContext when running unit tests. It is used if you need to test controller or service classes, or perform integration tests spanning multiple layers.

## DataJpaTest
> This annotation provides an alternate way to test your data layer without providing an application.properties file. It disables Spring autoconfiguration and automatically uses an in-memory database if available. It only loads Entities and Spring Data JPA repositories, but not your Services or Controllers.

## TestEntityManager
> TestEntityManager is a class provided by Spring Boot that provides useful methods for persisting test data inside persistence unit tests. It is still available in @DataJpaTests despite the rest of the app not being wired up.

## @AutoConfigureTestDatabase
> This annotation can be used with either @SpringBootTest or @DataJpaTest. You can use it to customize Spring’s behavior for replacing the normal datasource. For example, the following annotation could be used in conjunction with @DataJpaTest to indicate that Spring should NOT replace the datasource with an in-memory datasource.
```java
@AutoConfigureTestDatabase(replace=Replace.NONE)
```
