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

