# Unit Test & Integration Test

## Testing Dependency

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

```
### Spring Boot Starter Test
- JUnit
- AssertJ
- Hamcrest
- And other useful libraries

### Unit Testing
- JUnit
- Mockito
- MockMVC - Aka Spring Test
- @MockBean annotation
- @WebMvcTest
        - Controller layer unit testing
        - Only scan controllers with @Controllers and @RESTControllers
        
> JUnit is a popular unit testing framework that allows you to test individual units of source code.
> Mockito is a great mocking framework which provices data for JUnit tests.
> @MockBean works well with the Mockito library.
> @WebMvcTest is used for controller layer unit testing.

```java
@RunWith(SpringRunner.class)
//This is the controller that we want to test
@WebMvcTest(LocationController.class)
public class LocationControllerUnitTest {

    //Test Mvc controller without start a full HTTP server
    @Autowired
    private MockMvc mockMvc;

    //Mock the Bean to LocationService
    @MockBean
    LocationService locationService;

    @Test
    public void getAllLocations() throws Exception {
        //This simulates HTTP request to REST controller
        mockMvc.perform(get("/location/")) //Ger Request - Path
                //Below are expectations such as HTTP responses received from controller class
                .andExpect(status().isOk()) //Return ok
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) //Return Application_JSON_UTF8
                .andExpect(content().json("[]"));

        //Verify the time of mock method has been called
        //At this case only once and retrieveLocation method to be called
        verify(locationService, times(1)).retrieveLocations();
    }

    @Test
    public void getLocation() throws Exception {
        mockMvc.perform(get("/location/1"))
                .andExpect(status().isOk());

        verify(locationService, times(1)).retrieveLocation(1);
    }
}
```
