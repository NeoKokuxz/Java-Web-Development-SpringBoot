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
