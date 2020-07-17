# JUnit
Java test runner where every test is a plain java method
- Junit needs @Test for each method, and each method have to be public and void
- it uses assertions that provided by JUnit
- Repeat for each test
  - @BeforeALL
  - @BeforeEach
  - @Test
  - @AFterEach
  - @AfterAll
  
```java
public class ExampleTests {

    private List<Integer> testList;

    @BeforeEach
    public void beforeEach() {
        testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
    }

    @AfterEach
    public void afterEach() {
        testList = null;
    }

    @Test
    public void testAddZero() {
        int a = 10;
        int b = 0;
        int c = a + b;
        assertEquals(a, c);
    }

    @Test
    public void testDivideZero() {
        int a = 10;
        int b = 0;
        assertThrows(ArithmeticException.class, () -> {
            int c = a / b;
        });
    }

    @Test
    public void testListContains() {
        assertTrue(testList.contains(1));
        testList.remove(0);
        assertFalse(testList.contains(1));
    }

}
```
  
# Selenium/WebDriver

# Junit and Selenium

# Test-Driven Development
Written test before development

- Cycle
  - new feature ideas
  - user stories
  - falling tests
  - feature development
  - passing tests
  - repeat...

- User Stories
  - As a user I can _action__ in order to __goal_

- Unit Test
  - Unit tests should be used to test invariants - condition doesn't change
  - Test app behavior with inputs including edee cases
  - usually only need access to platform runtime

- Integration Test
  - should be used to test user actions and process flows
  - Check app state updates throughout execution 
  - may need access to other environment
