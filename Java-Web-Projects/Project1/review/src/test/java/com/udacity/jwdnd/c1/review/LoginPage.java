package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement loginBtn;

    //Constructor
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Helper Methods
    public void preLogin(){
        this.inputUsername.sendKeys("123");
        this.inputPassword.sendKeys("123");
        loginBtn.submit();
    }

    public void login(String username, String password){
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        loginBtn.submit();
    }
}
