# Web Services and APIs

# Web Services
share data between two disparate systems or way to retrieve data to display in your application.

## Client
The Client makes a request for data

## Server
Server will respond to the client's request

## Standard Web Protocol HTTP
- Format
  - JSON
    - JavaScript Object Notation
    - Key - Value Pairs
  - XML
    - Extensible Markup Language
    
## Reusability & Usability
this concept allows organizations to use web services provided by third parties. This reduces development time and delivers more powerful applications.

# APIs & Microservices
More lightweight than web service 
- APIs
  - SOAP
  - RESTful APIs
- Microservices
  - Similar to API
  - Fully contained
  - individual component

## SOAP 
Simple Object Access Protocol 

# RESTful APIs
REST stands for REpresentational State Transfer
-Data & Functionality are the resouces in API and identified through something called the URI
  - URI - Uniform Resource Identifier
  - manipulate with a fixed set of operations
    - GET, retrieve the resources 
    - POST, send in the resources
    - PUT, update the resources
    - DELETE, delete the resources
    
## Entity
This marks a Java class as an entity, which means it will be persisted to the database. Typically, an entity maps to a database table and the table contains rows that represent that given entity.
- @Entity - defined by this annotation
```java
@Entity
public class Location {
    //Id is unique identifier (primary key)
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO) //Type can be: AUTO/TABLE/SEQUENCE/IDENTITY
    private Long id;

    private String name;
    private String address;

    //Constructor
    public Location(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
 }
```

## RestController
This marks a class as a REST API.
- @RestController is a convenience annotation that combines @Controller and @ResponseBody.

## GetMapping
This annotation handles HTTP GET requests and acts as a shortcut for @RequestMapping (method = RequestMethod.GET).
```java
    @GetMapping("/location")
    public ResponseEntity<List<Location>> getAllLocation(){
        List<Location> list = locationService.retrieveLocations();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
```

# GraphQL APIs
- Web application
- Query language for APIs (Describes how to ask for data
- Flexibility
- graphql-spring-boot-starter - configure a GraphQL Servlet that you can access at /graphql
- graphql-java-tools - A helper library to parse the GraphQL schema.
- Schema
  - Define data points
  - Data type
  - Relationships
  - Operations
  - Queries & mutations
  - Note*** There can only be one root Query and one root Mutation type in a schema file. ***
```GraphQL
type Location {
 id: ID!
 name: String!
 address: String!
}

type Query {
 findAllLocations: [Location]!
}

type Mutation {
 newLocation(name: String!, address: String) : Location!
 deleteLocation(id:ID!) : Boolean
 updateLocationName(newName: String!, id:ID!) : Location!
}
```

- Queries
> A query allows for the retrieving of data. Each query will have a specific object that it returns and based on the object returned, you can add or remove fields to match the exact data you need to fit your specific use case.
- Mutations
> GraphQL has the ability to update the data stored on the server, by means of mutations. Mutations, such as, create, update, or delete will change the data, unlike a query.
  ```java
  {
  findAllApplications {
    id
    owner
    address
  }
}
mutation {
  newLocation(
    name: "MBJ Airport",
    address: "Montego Bay, Jamaica airport location") {
      id 
      name
      address
    }
}
mutation {
  deleteLocation(id:1)
}
```
## Resolver
- GrahphQLQueryResolver
  - This allows spring to automatically detect and call the right method in response to one of the GraphQLQueries inside the schema
  
- GraphQLMutationResolver

## Custom Exception
- Java class has to extends RuntimeException
  - Specific to GraphQL, implements GraphQLError

```java
public class LocationNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public LocationNotFoundException(String message, Long invalidLocationId) {
        super(message);
        extensions.put("invalidLocationId", invalidLocationId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}

```
  
## GraphQL Query 
type: "query"
Name: "{findAllDogNames}"
Return: {name id breed origin} //Order can be any

```GraphQL
{
    "query":"{findAllDogNames {name id breed origin} }"
}
```

## GraphiQL
When server is up and running, type http://localhost:8080/graphiql to access the web application
- Mutation is the root type to create, update and delete.
### GraphiQL query:
```GraphQL
#Create
mutation{
  #newLocation method 
  # and return data in the {} after method
  newLocation(name: "new Location name", address: "new Address location"){
    id
    name
    address
  }
}
```
```GraphQL
#Update
mutation{
  #Method - Note: if id doesn't exist will cause error
  updateLocationName(newName: "new Name here", id: 123){
    id
    name
    addree
  } #THis is the return body
}
```
```GraphQL
#Delete
mutation{
  #method
  deleteLocation(id:1) #This will return true or false depends on operation successful or not
}
```

## GraphQL Query By Postman
```GraphQL
#Create 
{
  "query":"mutation {newDogBreed(id: 886, name: \"SB dog\", breed: \"Husky\") {id name breed} }"
}

#Read
{
  "query" : "{findDogBreeds {id name breed} }"
}

#Update
{
  "query":"mutation {updateDogName(newName: \"SB two\", id: 2) {id name breed} }"
}

#Delete
{
  "query":"mutation {deleteDogBreed(breed: \"Husky\") }"
}

```

