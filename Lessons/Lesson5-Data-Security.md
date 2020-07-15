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


