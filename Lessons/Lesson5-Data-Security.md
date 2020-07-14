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
