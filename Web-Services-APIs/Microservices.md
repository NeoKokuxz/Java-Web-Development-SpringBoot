# Microservices

# MSA
- Agile framework
- Flexible due to language support and communication
- Easy to write, test, deploy, and share
- Scale to load and demand
- Resilient systems 

## Micro - Scope of the interface
- single purpose
- simple interface
- modular and independent
- isolated persistence
- system of record

## Layers
- Presentation Layer
- Business Process Layer/Service Layer
- Data Access Layer

# Eureka Server

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
  public static void main(String[] args) {
    SpringApplication.run(EurekaApplication.class, args);
  }
}
```
- Properties
```Eureka
spring.application.name=eureka-server 
server.port=8761

#don't register itself as a client
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=ON
logging.level.com.netflix.discovery=ON
```

## Spring Data Rest
https://spring.io/projects/spring-data-rest
- Locates All Spring Data Repository
- Create endpoints that matches entity names
- Append S to the entity name in the endpoint
- Exposes CRUD operations
- No Need of controller or service layer
```java
public interface ItemRepository extends JpaRepository<Item, Integer> {...}
```
Code above expose the repo resources at http://localhost:8080/items/
> Summary:
> At application startup, Spring Data Rest finds all of the spring data repositories
> Then, Spring Data Rest creates an endpoint that matches the entity name
> Next, Spring Data Rest appends an S to the entity name in the endpoint
> Lastly, Spring Data Rest exposes CRUD (Create, Read, Update, and Delete) operations as RESTful APIs over HTTP

```java
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

