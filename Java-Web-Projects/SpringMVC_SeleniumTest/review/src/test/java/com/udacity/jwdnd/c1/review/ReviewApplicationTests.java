package com.udacity.jwdnd.c1.review;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

	@LocalServerPort
	private Integer port;

	private static WebDriver driver;
	private ChatPage chatPage;
	private LoginPage loginPage;
	private SignupPage signupPage;

	//Init driver and set up chrome to test
	@BeforeAll
	public static void beforeAll(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	//Quit driver after tests
	@AfterAll
	public static void afterAll() throws InterruptedException {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(1000);

		driver.get("http://localhost:" + port +  "/signup");
		signupPage = new SignupPage(driver);
		signupPage.presetTest();

		Thread.sleep(1000);

		driver.get("http://localhost:" + port +  "/login");
		loginPage = new LoginPage(driver);
		loginPage.preLogin();

		Thread.sleep(1000);

		driver.get("http://localhost:" + port +  "/chat");
		chatPage = new ChatPage(driver);
		chatPage.sendMessage();

		Thread.sleep(5000);
	}

}
