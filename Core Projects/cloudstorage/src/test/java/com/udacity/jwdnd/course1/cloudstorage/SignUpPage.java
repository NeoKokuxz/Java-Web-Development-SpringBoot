package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    @FindBy(id = "inputFirstName" )
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName" )
    private WebElement inputLastName;

    @FindBy(id = "inputUsername" )
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-btn")
    private WebElement submitBtn;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    //Constructor
    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Helper Methods
    public void preSignup(){
        this.inputFirstName.sendKeys("Naoki");
        this.inputLastName.sendKeys("Kokuxz");
        this.inputUsername.sendKeys("Naoki123");
        this.inputPassword.sendKeys("123123");
        submitBtn.click();
    }

    public void signupWith(String fname, String lname, String username, String password) {
        this.inputFirstName.sendKeys(fname);
        this.inputLastName.sendKeys(lname);
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        submitBtn.click();
    }


}
