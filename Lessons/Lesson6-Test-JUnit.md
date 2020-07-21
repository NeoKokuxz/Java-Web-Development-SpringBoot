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
  
# JUnit API
Assertion
- assertNull()
  - Expect null value when given input inside ()
- assertThrows
  - Throws expection when there's conditional exception
```java
assertThrows(ArithmeticException.class ()-> { //condition code here} );
```
- assertEquals
  - First prama = expected value , 2nd prama = actual value
```java
assertEquals(expected: 0, example.getValue() ); //Compare the value difference
```

  
# Web Driver
In order for Selenium to assume control of a browser, it needs a program to interface with the specific browser's API. This program is called a web driver, and there are different web drivers for each major browser.
```java
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebElement inputField = driver.findElement(By.name("q"));
        inputField.sendKeys("selenium");
        inputField.submit();
        List<WebElement> results = driver.findElements(By.cssSelector("div.g a"));
        for (WebElement element : results) {
            String link = element.getAttribute("href");
            System.out.println(link);
        }
        Thread.sleep(5000);
        driver.quit();
    }
```

# Counter Page

```java
public class CounterPage {

    //Web Elements using @FindBy() ***
    @FindBy(id = "count-display")
    private WebElement countDisplay;

    @FindBy(id = "increment-button")
    private WebElement incrementButton;

    @FindBy(id = "reset-value-field")
    private WebElement resetValueField;

    @FindBy(id = "reset-button")
    private WebElement resetButton;

    //Pass in driver in constructor, this reference to this page object
    public CounterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Methods that perform actions on the page
    public int getDisplayedCount() {
        return Integer.parseInt(countDisplay.getText());
    }

    public void incrementCount() {
        incrementButton.click();
    }

    public void resetCount(int value) {
        resetValueField.clear();
        resetValueField.sendKeys(String.valueOf(value));
        resetButton.click();
    }
}
```

# Selenium Page Factory
Page Factory pattern to initialize web elements which are defined in Page Objects.
```java
public HompePage(WebDriver driver) {           
  this.driver = driver; 
  PageFactory.initElements(driver, this);
}
```
- This is shorthand to tell Selenium to use the given driver to initialize the @FindBy-annotated fields in the class. In principle, we could do this somewhere else, but as we'll see in the next video, initializing a Page Object in its constructor like this is pretty flexible and clean.

# @SpringBootTest
- Server has to run before the test starts
- Explanation below inside code with //
```java

//This tells JUnit to run the application before any tests are executed in a random port instead of 8080
//If mutiple instance running on port 8080, might cause problems.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTestingApplicationTests {

    //This will assign the number of current port to the Integer port and for later usage
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private CounterPage counter;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    //navigate to the /chat url and 
    @BeforeEach
    public void beforeEach() {
        //driver.get method will get the port that test will be on
        driver.get("http://localhost:" + port + "/counter");
        counter = new CounterPage(driver);
    }

    @Test
    public void testIncrement() {
        int prevValue = counter.getDisplayedCount();
        counter.incrementCount();
        assertEquals(prevValue + 1, counter.getDisplayedCount());
    }

    @Test
    public void testIncrementTenTimes() {
        int prevValue = counter.getDisplayedCount();
        for (int i = 0; i < 10; i++) {
            assertEquals(prevValue + i, counter.getDisplayedCount());
            counter.incrementCount();
        }
    }

    @Test
    public void testReset() {
        counter.resetCount(10);
        assertEquals(10, counter.getDisplayedCount());
        counter.resetCount(0);
        assertEquals(0, counter.getDisplayedCount());
    }

}
```
# Wait
Time to wait for web element loading in the page
```java
//Create new WebDriver wait instance
//Pass in existing webdriver as augment and 1000millisec wait time
WebDriverWait wait = new WebDriverWait(driver, 1000);
//wait.until the driver attempt to find the specific element by id
//This method will block the test progress until find the valid elenment or time out. 
//webDriver can be replaced by any driver defined
WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("page-load-marker")));
```

# Glossary
Glossary
- Test Driven Development: a software development methodology that emphasizes writing tests before the code to be tested. This gives developers a roadmap to success - once all the tests are passing, the feature is complete!
- User Story: User stories are short sentences derived from feature requirements in the format of As a user, I can in order to . These are used to create tests to verify the components of a feature.
- Unit Tests: A unit test only validates the smallest unit of a computational process. That might mean a test of a single method, or a single component in an application.
- Invariants: An invariant is a law of computation, something that shouldn't change despite changing circumstances. For example, adding 0 to a number should always result in the original number, and dividing by 0 should always result in an error.
- Integration Tests: Integration tests are intended to validate the operation of multiple application components as they interact with each other - or integrate with one another.
- Assertion: an assertion, in the context of JUnit, is a method we can call to check our assumptions about the behavior of the unit under test. If our assumptions are correct, the assertion silently returns and the test method continues. If they're false, the assertion throws a special exception class that JUnit uses to build the final failure report, and the test method halts execution.
- Interactive Reporting: When we run tests in an IDE, we can usually inspect the results of each test individually. If an assertion fails or an unexpected exception is triggered, the stack trace and circumstances will be shown in the details for each test, and clickable links in the results help you navigate to problem areas in your code.
- Interactive Debugging: When a pernicious problem persists, it can often be helpful to step through the code's execution line-by-line to inspect both the control flow and the values in memory used by the program. This is called debugging, and while it's technically possible to do outside of an IDE, IDEs like IntelliJ provide many useful tools for making the process as painless as possible.
- Code Coverage Reports: When we run code in an IDE like IntelliJ, we can choose to have the IDE track which lines of our code were visited, and how many times. This can be wildly useful when trying to track down why a branch of a condition isn't being reached, as well as when determining how much the entire code base is covered by the currently-implemented tests.
- Web Driver: In order for Selenium to assume control of a browser, it needs a program to interface with the specific browser's API. This program is called a web driver, and there are different web drivers for each major browser.
- Page Object: a special POJO variant that can be defined for use with Selenium. A Page Object should have @FindBy-annotated fields that represent the key HTML elements under test, and should have helper methods that define high-level utilities and user actions on the page under test.

