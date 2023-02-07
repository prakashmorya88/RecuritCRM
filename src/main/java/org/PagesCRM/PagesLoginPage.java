package org.PagesCRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagesLoginPage 
{
	WebDriver driver;
	
	//Login Variables
	@FindBy (id = "sTestEmail") private WebElement usernameField;
	@FindBy (id = "sTestPassword") private WebElement passwordField;
	@FindBy (id = "sTestLoginBtn") private WebElement loginButton;
	@FindBy (xpath = "//div[contains(text(),'Login Successful')]") private WebElement loginSuccessfulMessage;
	@FindBy (xpath = "//div[contains(text(),'Failed to Login')]") private WebElement errorMessage;

	//Forgot Password Variables
	@FindBy (partialLinkText = "Forgot") private WebElement forgotPassword;
	@FindBy (id = "sTest-emailIdForgotPassTxt") private WebElement emailIdForgotPassTxt;
	@FindBy (id = "sTest-requestPasswordBtn") private WebElement requestPasswordBtn;
	@FindBy (xpath = "//div [contains(text(),'Password Changed')]") private WebElement passwordChangedConfirmationMessage;

	
	//This is use to initialize the Variables
	public PagesLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver = driver;
	}
	
	//Public getter methods for Login functionality
	public void enterUsername(String username)
	{
		usernameField.sendKeys(username);
	}
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	public void clickOnLoginButton()
	{
		loginButton.click();
	}
	
	public boolean loginSuccessfulMessageIsDisplayed()
	{
		return loginSuccessfulMessage.isDisplayed();

	}
	public boolean errorMessageIsDisplayed()
	{
		return errorMessage.isDisplayed();

	}
	
	//Forgot Password Functionality Tests
	public void clickOnForgotPasswordLink()
	{
		forgotPassword.click();
	}
	public void enterEmailId(String emailID)
	{
		emailIdForgotPassTxt.sendKeys(emailID);
	}
	public void clickOnRequestPasswordButton()
	{
		requestPasswordBtn.click();
	}
	public boolean passwordChangedConfirmationMessageIsDisplayed()
	{
		return passwordChangedConfirmationMessage.isDisplayed();
	}
	
	
}