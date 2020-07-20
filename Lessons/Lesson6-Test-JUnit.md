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


