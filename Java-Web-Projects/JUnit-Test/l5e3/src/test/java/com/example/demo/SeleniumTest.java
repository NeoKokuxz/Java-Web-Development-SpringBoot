package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/animal");
        WebElement inputField = driver.findElement(By.id("animalText"));
        inputField.sendKeys("Hello123123123123123");
        inputField = driver.findElement(By.id("adjective"));
        inputField.sendKeys("World123123123123123");
        for(int i = 0; i < 5; i++){
            inputField.submit();
            List<WebElement> trainingResults = driver.findElements(By.className("trainingMessage"));
            System.out.println("trainingResults.size() = " + trainingResults.size());
            break;
        }

        WebElement conclusionResult = driver.findElement(By.className("conclusionMessage"));
        System.out.println("conclusionResult.getText() = " + conclusionResult.getText());

        Thread.sleep(5000);
        driver.quit();
    }
}
