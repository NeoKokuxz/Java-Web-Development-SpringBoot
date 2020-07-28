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
```java
spring.application.name=eureka-server 
server.port=8761

#don't register itself as a client
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=ON
logging.level.com.netflix.discovery=ON
```
