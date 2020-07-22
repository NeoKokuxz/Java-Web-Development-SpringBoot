# ORM
- Object-Relational-Mapping

# Database
- Data shared across multiple user sessions, like a product inventory
- Persistent data that should remain accessible after being logged out, like user profile of shopping cart. 

# Structured Data
- Intuitively, Most data can be stored in a similar format to the data objects that represent it in Java, with attributes matching column names.
- Differing, some data must be stored differently for security reasons, such as encrypted passwords, other data may require a different format for efficient storage, such as large files. 

# Security
- User-specific data
- General accessible(unsecured) data
- Vary by domain

## Mapping
Drawing a relationship between a field in a java class and a column in a SQL table.

## MyBatis Mappers
```java
@Mapper
public interface UserMapper {
  @Select("SELECT * FROM USERS WHERE username = #{username}")
  User getUser(String username);
  
   @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
           "VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
   @Options(useGeneratedKeys = true, keyProperty = "userId")
   int insert(User user);
}
```

```java
@Mapper
public interface DeliveryMapper {
   @Select("SELECT * FROM Delivery WHERE id = #{id}")
   Delivery findDelivery(Integer id);

   @Insert("INSERT INTO Delivery (orderId, time) VALUES(#{orderId}, #{time})")
   @Options(useGeneratedKeys = true, keyProperty = "id")
   Integer insert(Delivery delivery);

   @Delete("DELETE FROM Delivery WHERE id = #{id}")
   void delete(Integer id);
}

```

## User Credentials
- User class
  - Class object that holds user data
- User Mapper
  - Map object with the SQL queries
- User Service
  - User service will create a new user, it will call the hash service to convert the password to hashed value before saving it. 
  - Salt: random data that is combined with the input string when hasing so that the resultant hased values are unique for each row. This will prevent users with same password get the same hashed value.
- Hash Service
  - Provide methods to convert password into hashed value.
- AuthenticationService
  - Rehash user input data and submit to the database to compare if there's a match.
  - it implements the authenticationProvider interface, and supports method will define which autentication method we are using.
```java
@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserMapper userMapper;
    private HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
```

# Spring Security
- @EnableWebSecurity
  - This is used to let Spring know that this class is configuring sprint security.
- If the class extends WebSecurityConfigurerAdapter
  - This class is added to spring web security configuration
  - configure method
- configure method
  - One with AuthenticationManagerBuilder add the authenticationService class to the Provider inside builder.
  - Two tells spring which pages are protected by the spring security. 
```java
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }
```
## Key Terms
- Onion Pattern
  - Tiered Architecture, Multi-tiered Architecture or n-tiered Architecture. This is design pattern that separates areas of application into controller, service, and data layer and more. 
  - Flow from controller tier and pass them to service tier which they will reach data access bean.
  
- Encryption
  - Modifying data before storing it. 
  - Hashing
  - Salt

# Glossary
- ORM: Object-Relational Mapping. A general term describing a set of technology that can be used to automatically convert data between database representation and application representation.
- JDBC: Java Database Connectivity API, which is a specification for making SQL requests from Java.
- MyBatis: A thin ORM over JDBC that automatically generates code to execute SQL statements over JDBC and maps the results to Java objects.
- Mapping: Drawing a relationship between a field in a Java class and a column in a SQL table.
- One to One: A relationship between two objects in which one entity is on each side of the relationship.
- Many to Many: A relationship between two objects in which multiple copies of each entity can be related to multiple copies of the other entity.
- @Select, @Insert, @Update, @Delete: Annotations representing SQL statements to be executed. Each annotation takes a string for a SQL statement of the corresponding type. For example, a @Select annotation takes a string for a SQL SELECT statement.
- @Options: Annotation providing access to switches and configuration options for JDBC statements.
- Onion Pattern: Sometimes also called Tiered Architecture, Multi-tiered Architecture, or n-tiered Architecture. This is a design pattern that separates areas of the application into controller, service, and data layers (and sometimes more). User flows originate from the controller tier, which passes them to the service tier, which then reaches a data access bean.
- Encryption: Modifying data before storing it, with the intention of using another algorithm to return the data to its original form once it needs to be used.
- Hashing: Modifying data before storing with the intention of never returning it to its original form. The modified data will be compared to other modified data only.
- Salt: random data that is combined with the input string when hashing so that the resultant hashed values are unique for each row. This means that two users with the same password would not have the same hash in the database.
