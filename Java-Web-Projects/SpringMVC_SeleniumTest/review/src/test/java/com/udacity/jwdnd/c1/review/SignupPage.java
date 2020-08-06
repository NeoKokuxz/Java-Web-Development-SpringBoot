package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    //Find web elements
    @FindBy(id = "inputFirstName")
    private WebElement fName;

    @FindBy(id= "inputLastName")
    private WebElement lName;

    @FindBy(id="inputUsername")
    private WebElement username;

    @FindBy(id= "inputPassword")
    private WebElement password;

    @FindBy(id="submit-button")
    private WebElement submit;

    @FindBy(id="login-link")
    private WebElement loginLink;

    @FindBy(id="success-msg")
    private WebElement successMsg;

    @FindBy(id="error-msg")
    private WebElement errorMsg;

    //Constructor to initial the page object
    public SignupPage(WebDriver driver){
        //Get the selenium element driver and this page to init method
        PageFactory.initElements(driver, this);
    }

    //Helper Method
    public void presetTest(){
        fName.sendKeys("Hello");
        lName.sendKeys("World");
        username.sendKeys("123");
        password.sendKeys("123");
        this.submit.click();
    }

    public void register(String fName, String lName, String userName, String password){
        this.fName.sendKeys(fName);
        this.lName.sendKeys(lName);
        this.username.sendKeys(userName);
        this.password.sendKeys(password);
        this.submit.click();
    }

    public void reset(){
        fName.clear();
        lName.clear();
        password.clear();
        username.clear();
    }

    public void login(){
        loginLink.click();
    }
}
