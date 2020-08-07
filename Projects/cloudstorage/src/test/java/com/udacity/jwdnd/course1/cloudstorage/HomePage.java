package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    //Log out
    @FindBy(id = "logout-btn")
    private WebElement logOutBtn;

    //--------------------------------------------
    //Note elements
    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "t-noteTitle")
    private WebElement noteTitle;

    @FindBy(id = "t-noteDesc")
    private WebElement noteDesc;

    //Note Modal
    @FindBy(id = "note-title")
    private WebElement addNoteTitle;

    @FindBy(id = "note-description")
    private WebElement addNoteDesc;

    //Note Actions
    @FindBy(id = "deleteNote")
    private WebElement deleteNoteBtn;

    @FindBy(id = "addNote-btn")
    private WebElement addNoteBtn;

    @FindBy(id = "editNote-btn")
    private WebElement editNoteBtn;

    @FindBy(id = "saveNote-btn")
    private WebElement saveNoteBtn;

    @FindBy(id = "closeNote-btn")
    private WebElement closeNoteModalBtn;

    //--------------------------------------------
    //Credential elements
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "c-url-display")
    private WebElement cURL;

    @FindBy(id = "c-username-display")
    private WebElement cUsername;

    @FindBy(id = "c-password-display")
    private WebElement cPassword;

    //Credential Actions
    @FindBy(id = "addCredential-btn")
    private WebElement addCredentialBtn;

    @FindBy(id = "editCredential-btn")
    private WebElement editCredentialBtn;

    @FindBy(id = "deleteCredential-btn")
    private WebElement deleteCredentialBtn;

    @FindBy(id = "credentialSubmit")
    private WebElement submitCredentialBtn;

    //Credential Modal
    @FindBy(id = "credential-url")
    private WebElement credentialURL;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword ;

    //--------------------------------------------
    //Constructor
    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //--------------------------------------------
    //Helper Method
    // - Note
    public void addNote(){
        addNoteTitle.sendKeys("Day 1 - Java nano");
        addNoteDesc.sendKeys("Learn Spring MVC");
        saveNoteBtn.click();
    }

    public void clickNoteTab(){
        noteTab.click();
    }

    public void addNoteWith(String title, String desc){
        addNoteTitle.sendKeys(title);
        addNoteDesc.sendKeys(desc);
        saveNoteBtn.click();
    }

    public void editNote(){
        addNoteTitle.clear();
        addNoteTitle.sendKeys("New Title");
        addNoteDesc.clear();
        addNoteDesc.sendKeys("New Desc");
        saveNoteBtn.click();
    }

    public void deleteNote(){
        deleteNoteBtn.click();
    }

    // - Credential
    public void addCredential(){
        addCredentialBtn.click();
        credentialURL.sendKeys("Github");
        credentialUsername.sendKeys("Naoki");
        credentialPassword.sendKeys("Naoki123123123");
        submitCredentialBtn.submit();
    }

    public void addCredentialWith(String url, String username, String password){
        addCredentialBtn.click();
        credentialURL.sendKeys(url);
        credentialUsername.sendKeys(username);
        credentialPassword.sendKeys(password);
        submitCredentialBtn.submit();
    }

    public void logout(){
        logOutBtn.click();
    }

    public void showNoteModal(){
        addNoteBtn.click();
    }

    public void editNoteBtn(){
        editNoteBtn.click();
    }









































}
