package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "login-btn")
    private WebElement loginBtn;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    //Constructor to load WebDriver
    public LoginPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Helper Methods
    public void preLogin(){
        this.inputUsername.sendKeys("Naoki123");
        this.inputPassword.sendKeys("123123");
        loginBtn.submit();
    }

    public void loginWith(String username, String password){
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        loginBtn.submit();
    }

}