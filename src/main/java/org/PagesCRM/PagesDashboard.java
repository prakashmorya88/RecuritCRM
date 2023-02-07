package org.PagesCRM;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagesDashboard 
{
	WebDriver driver;
	//Profile Photo Upload Variables
	@FindBy (id = "sTest-dpLinkInAppBtn") private WebElement dropDownIcon;
	@FindBy(xpath = "//div [@class=\"userDropdownOptions p-t-0\"]/div[1] ") private WebElement profileIcon;
	@FindBy (xpath = "//img [@class=\"details-page-profile\"]") private WebElement photoUploadIcon;
	
	//Click on Job Status drop down and Select the checkBoxes Variables
	@FindBy (xpath = "//span [contains(text(),'Showing')]") private WebElement jobStatusDropDown;
	@FindBy (xpath = "(//span [text()='Open'])[1]") private WebElement openCheckBox;
	@FindBy (xpath = "(//span [text()='On Hold'])[1]") private WebElement onHoldCheckBox;
	@FindBy (xpath = "(//span [text()='Canceled'])[1]") private WebElement canceledCheckBox;
	@FindBy (xpath = "(//span [text()='Closed'])[1]") private WebElement closedCheckBox;
	
	//For the CheckBox of Tasks
	@FindBy (xpath = "//label[@id=\"sTest-taskListCheckBox\"]") private WebElement checkBoxTasks; 
	
	//To add new Tasks and verify drop down functionality
	@FindBy (xpath = "//div [@class=\"navbar-end\"]/div[1]") private WebElement addFunction;
	@FindBy (id = "sTest-addTaskInAppBtn") private WebElement taskIcon;
	@FindBy (id = "sTest-TaskTitle") private WebElement titleInputField;
	@FindBy (id = "sTest-addTaskSubmitBtnTxt") private WebElement taskAddButton;
	
	//To verify Logo
	@FindBy(id = "Group_344") private WebElement logo;
	
	
	//This is use to initialize the Variables
	public PagesDashboard(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver = driver;
	}
	
	//Profile Photo Upload Methods
	public void clickOndropDownIcon()
	{
		dropDownIcon.click();
	}
	public void clickOnProfileIcon()
	{
		profileIcon.click();
	}
	public void clickOnphotoUploadIcon()
	{
		photoUploadIcon.click();
	}
	
	//Click on Job Status drop down and Select the checkBoxes and Verify them
	public void clickOndropDownCheckBox()
	{
		jobStatusDropDown.click();
	}
	public void clickOnOpenCheckBox()
	{
		openCheckBox.click();
	}
	public void clickOnOnHoldCheckBox()
	{
		onHoldCheckBox.click();
	}
	public boolean openCheckBoxIsSelected()
	{
		return openCheckBox.isSelected();
	}
	public boolean onHoldCheckBoxCheckBoxIsSelected()
	{
		return onHoldCheckBox.isSelected();
	}
	
	//Add Tasks and verify DropDown Functionality
	public void clickOnAddFunction()
	{
		addFunction.click();
	}
	public void clickOnTaskIcon()
	{
		taskIcon.click();
	}
	public void enterTitle(String titleOfTask)
	{
		titleInputField.sendKeys(titleOfTask);
	}
	public void clickOnAddButton()
	{
		taskAddButton.click();
		
	}
	//Logo Verify
	
	public boolean logoIsDisplayed()
	{
		return logo.isDisplayed();
	}
	
}
