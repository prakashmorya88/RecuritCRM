package org.TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.PagesCRM.PagesDashboard;
import org.PagesCRM.PagesLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseUtils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TCs_DashBoard
{
	WebDriver driver;
	PagesDashboard d_page;
	PagesLoginPage l_page;
	ConfigReader cr;
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		d_page = new PagesDashboard(driver);
		l_page = new PagesLoginPage(driver);
		cr = new ConfigReader();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(cr.getPropData("url"));
		l_page.enterUsername(cr.getPropData("username"));
		l_page.enterPassword(cr.getPropData("password"));
		l_page.clickOnLoginButton();
	}
	
	@Test (priority=1)
	public void verifyDashBoardPageTitle() throws InterruptedException
	{
		Thread.sleep(5000);				//CHANGE with Explicit Wait
		Assert.assertEquals(driver.getTitle(), "Dashboard | Recruit CRM");
	}
	
	@Test (priority=2)
	public void verifyVisibilityOfLogo()
	{
		Assert.assertTrue(d_page.logoIsDisplayed(), "Logo not Visible");
	}

	@Test (priority=3)
	public void verifyAddNewTaskFunctionality()
	{
		d_page.clickOnAddFunction();
		d_page.clickOnTaskIcon();
		d_page.enterTitle("Remind me to call Parshuram for Interview");
		
		WebElement timePicker = driver.findElement(By.id("sTest-datePicker-time-AddTaskStartingDate"));
		Select sel = new Select(timePicker);
		
		Assert.assertEquals(sel.getFirstSelectedOption().getText(), "11:30 PM", "MisMatch in Default Option");
		
		Assert.assertEquals(sel.getOptions().size(), 96, "Mismatch in all options");
			
		d_page.clickOnAddButton();
		WebElement confirmationMessage = driver.findElement(By.xpath("//div [contains(text(),'Task Added')]"));
		Assert.assertTrue(confirmationMessage.isDisplayed(), "Task Not Created");
		
	}
	
	@Test (priority=4)
	public void verifyBrokenLinks()
	{
		List <WebElement> allUrls = driver.findElements(By.tagName("a"));
		int totalBrokenLinks = 0;
		for(WebElement i : allUrls)
		{
			try 
			{
				String link = i.getAttribute("href");
				URL url = new URL(link);
				HttpURLConnection huc = (HttpURLConnection)url.openConnection();
				huc.setRequestMethod("HEAD");
				huc.connect();
				
				int resCode = huc.getResponseCode();
				
				if(resCode>=400)
				{
					System.out.println(link +" : is a Broken Link");
					totalBrokenLinks++;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
		Assert.assertEquals(totalBrokenLinks, 0, "Broken Link Found");		
	}
	
	@Test (priority=5)
	public void verifyProfileImageUploadFunctionality() throws AWTException
	{
		d_page.clickOndropDownIcon();
		d_page.clickOnProfileIcon();
		d_page.clickOnphotoUploadIcon();
		
		String filePath = "C:\\FileUpload\\Result.png";
		StringSelection ss = new StringSelection(filePath);
		Toolkit .getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div [contains(text(),'Updated')]")));

		WebElement confirmationMsg = driver.findElement(By.xpath("//div [contains(text(),'Updated')]"));
		Assert.assertTrue(confirmationMsg.isDisplayed(), "Profile Photo Not Uploaded");
		
	}
	
//	@Test
	public void verifyCheckBoxFunctionality() throws InterruptedException
	{
		Thread.sleep(5000);
		d_page.clickOndropDownCheckBox();
		Thread.sleep(5000);
		d_page.clickOnOpenCheckBox();
		Thread.sleep(5000);
		Assert.assertTrue(d_page.openCheckBoxIsSelected(), "Open CheckBox is Not Selected");
		
	}
		
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
