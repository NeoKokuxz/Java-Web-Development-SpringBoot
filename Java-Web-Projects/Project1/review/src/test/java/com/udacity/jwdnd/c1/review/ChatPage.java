package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {

    @FindBy(id="messageText")
    private WebElement text;

    @FindBy(id="messageType")
    private WebElement type;

    @FindBy(id="submit-btn")
    private WebElement submitBtn;

    //Constructor
    public ChatPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Helper Method
    public void sendMessage(){
        text.sendKeys("Text Message Test 123");
        submitBtn.submit();
    }
}
