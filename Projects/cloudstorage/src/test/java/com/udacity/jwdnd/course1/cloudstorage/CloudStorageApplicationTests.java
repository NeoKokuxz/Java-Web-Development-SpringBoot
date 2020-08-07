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
		Thread.sleep(1000);
		signup();
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

	@Test //Pass
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

		//Continue to home - Result
		WebElement next = driver.findElement(By.id("sc-btn"));
		next.click();
		Thread.sleep(1000); //Pass

		//Check Assertion for note title
		WebElement title = driver.findElement(By.id("t-noteTitle"));
		Assertions.assertEquals("Day 1 - Java nano", title.getText());
		Thread.sleep(500);
		//Check Assertion for note desc
		WebElement desc = driver.findElement(By.id("t-noteDesc"));
		Assertions.assertEquals("Learn Spring MVC", desc.getText());
		Thread.sleep(500); //Pass

		//Edit Note - Home
		homePage.editNoteBtn();
		Thread.sleep(1000);
		homePage.editNote();
		Thread.sleep(1000); //Pass
		next = driver.findElement(By.id("sc-btn"));
		next.click();
		Thread.sleep(1000); //Pass

		//Check Assertion for edited note
		title = driver.findElement(By.id("t-noteTitle"));
		Assertions.assertEquals("New Title", title.getText());
		Thread.sleep(500);
		//Check Assertion for note desc
		desc = driver.findElement(By.id("t-noteDesc"));
		Assertions.assertEquals("New Desc", desc.getText());
		Thread.sleep(500); //Pass

		//Delete Note - Home
		homePage.deleteNote();
		Thread.sleep(1000); //Pass

		//Continue to home - Result
		next = driver.findElement(By.id("sc-btn"));
		next.click();
		Thread.sleep(2000); //Pass

	}

	@Test //Pass
	public void testCredentialFunctionality() throws InterruptedException{
		signup();
		login();

		//Add Credential - Home
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		homePage.showCredentialModal();
		Thread.sleep(1000);
		homePage.addCredential();
		Thread.sleep(1000); //Pass

		//Continue to home - Result
		WebElement next = driver.findElement(By.id("sc-btn"));
		next.click();
		Thread.sleep(1000); //Pass

		//Check Assertion for credential URL, Username, Password
		WebElement url = driver.findElement(By.id("c-url-display"));
		Assertions.assertEquals("Github", url.getText());
		Thread.sleep(500);
		WebElement username = driver.findElement(By.id("c-username-display"));
		Assertions.assertEquals("Naoki", username.getText());
		Thread.sleep(500);
		WebElement password = driver.findElement(By.id("c-password-display"));
		Assertions.assertEquals("Naoki123123123", password.getText());
		Thread.sleep(500); //Pass

		//Edit Note - Home
		homePage.editCredentialBtn();
		Thread.sleep(1000);
		homePage.editCredential();
		Thread.sleep(1000);
		next = driver.findElement(By.id("sc-btn"));
		next.click();
		Thread.sleep(1000); //Pass

		//Check Assertion for edited credential URL, Username, Password
		url = driver.findElement(By.id("c-url-display"));
		Assertions.assertEquals("New URL", url.getText());
		Thread.sleep(500);
		username = driver.findElement(By.id("c-username-display"));
		Assertions.assertEquals("New Username", username.getText());
		Thread.sleep(500);
		password = driver.findElement(By.id("c-password-display"));
		Assertions.assertEquals("New Password", password.getText());
		Thread.sleep(500); //Pass

		//Delete Credential - Home
		homePage.deleteCredentialBtn();
		Thread.sleep(1000); //Pass

		//Continue to home - Result
		next = driver.findElement(By.id("sc-btn"));
		next.click();
		Thread.sleep(2000); //Pass
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
