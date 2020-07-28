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
