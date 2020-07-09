# Spring
## Data Types
Data Types are simple data containers, created on-demand.

```Java
public class UserProfile{
  private String name;
  private int age;
  private String location;
  
  //Constructor
  public UserProfile(){}
  
  //Getters && Setters
}
```

## Components
Components are Persistent Services

```java
public class UserService {
  public UserProfile getUserProfile(String username){
    //Fetch user data
    UserProfile result = new UserProfile();
    
    //Set the UserProfile fields
    
    return result;
  }
}
```

## Spring implements IoC
- Spring scans components.
- Spring initializes and stores in application context.
- Closed System
  - Beans in the application Context are only aware of other beans in the same context. 
- Spring components can only depend on other spring components

## Application Context
This context is ultimately very similar to a Map or a python dictionary, and it can be queried at runtime to find specific components when needed. This is a closed system, so components instantiated outside of Spring won't automatically be injected with dependencies like those instantiated by Spring. Mind the new keyword!
- Personal: It's a giant data structure that holds all application component instances. Can be queried to gain access to a specified component at runtime, this is how spring use to resolve dependencies. 
- Beans are Spring's name for generic application components, it includes any value Spring has stored in the application context.

## @SpringBootApplication
- This is equivalent as
  - @Configuration
  - @EnableAutoConfiguration
  - @ComponentScan

## @Bean
- Generic application components, always either:
  - Object
  - Primitive value

## @Configuration

## @EnableAutoConfiguration
- This allows Spring to auto match dependencies. 
- If mutiple beans that satisfy a specific dependency, needs to be marked as @Primary

## @Primary
- Indicates a universally-preferred bean for it's type

## @Qualifier
- on beans and dependencies that refernces them to gain a finer level of control

## @ComponentScan
- Provides another layer of automation - automatic component scanning throughout entire code base. 

## @Service
A collection of library methods that manage one aspect of an application's business logic

## @Repository
Inner Layer, act like interface to specific set of stored or persistent data. 

## @Controller
Controller for receive web requests.

# Configuration Files
- .properties
- .yaml
- .xml

# Component Annotations
- @Configuration
- @Component
- @Service

# Component and Services
- main config with @ComponentScan and service class with @Component
- @Component is shorthand of @Bean declaration 
- @Component default only look in same package or sub-package

# Onion Architecture
An architectural pattern in which an application is separated into nested layers. In order for a request to be processed by the application, it must first travel through an outer layer of external interfaces and controllers, then through a middle layer of services and business logic, and finally through a persistence layer of data access objects. The separation of these layers emphasizes clean separation of concerns.





  
