package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	//Pages
	private SignUpPage signUpPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private ResultPage resultPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test //Pass
	public void testHomeWithoutLoin() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test //Pass
	public void testLoginPage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test //Pass
	public void testSignUpPage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage = new SignUpPage(driver);
		Thread.sleep(5000);
		signUpPage.preSignup();
	}

	@Test //Pass
	public void testHomePageWithLogin() throws InterruptedException {
		signup();
		login();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test //Pass
	public void testHomePageAfterLogout() throws InterruptedException{
		signup();
		login();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		homePage = new HomePage(driver);
		homePage.logout();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testNoteFunctionality() throws InterruptedException{
		signup();
		login();
		//Add Note - Home
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		homePage.showNoteModal();
		Thread.sleep(1000);
		homePage.addNote();
		Thread.sleep(1000);
		//Result Page
		driver.get("http://localhost:" + this.port + "/result");
		resultPage = new ResultPage(driver);
		resultPage.scClick();
		Thread.sleep(2000); //Pass

		//Back to Note tab in home
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		Thread.sleep(1000); //Pass

		//Check Assertion for note title
		WebElement title = driver.findElement(By.id("t-noteTitle"));
		Assertions.assertEquals("Day 1 - Java nano", title.getText());
		Thread.sleep(500);
		//Check Assertion for note desc
		WebElement desc = driver.findElement(By.id("t-noteDesc"));
		Assertions.assertEquals("Learn Spring MVC", desc.getText());
		Thread.sleep(500); //Pass

		//Edit
		homePage.editNoteBtn();
		Thread.sleep(1000);
		homePage.editNote();
		Thread.sleep(1000);
		driver.get("http://localhost:" + this.port + "/result");
		resultPage = new ResultPage(driver);
		resultPage.scClick();
		Thread.sleep(1000); //Pass

		//Back to Note tab in home
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		Thread.sleep(1000); //Pass

		//Check Assertion for note title
		title = driver.findElement(By.id("t-noteTitle"));
		Assertions.assertEquals("New Title", title.getText());
		Thread.sleep(500);
		//Check Assertion for note desc
		desc = driver.findElement(By.id("t-noteDesc"));
		Assertions.assertEquals("New Desc", desc.getText());
		Thread.sleep(500); //Pass

		//Delete
		homePage.deleteNote();
		Thread.sleep(5000); //Pass

	}

	@Test
	public void testCredentialFunctionality() throws InterruptedException{
		signup();
		login();
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.addCredential();
	}

	private void signup() throws InterruptedException{
		//Sign up
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage = new SignUpPage(driver);
		Thread.sleep(1000);
		signUpPage.preSignup();
		Thread.sleep(1000);
	}

	private void login() throws InterruptedException{
		driver.get("http://localhost:" + this.port + "/login");
		loginPage = new LoginPage(driver);
		Thread.sleep(1000);
		loginPage.preLogin();
		Thread.sleep(1000);
	}
}
