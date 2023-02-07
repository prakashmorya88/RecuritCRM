package org.TestCases;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.PagesCRM.PagesLoginPage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseUtils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TCs_LoginPage
{
	WebDriver driver;
	PagesLoginPage l_page;
	ConfigReader cr;
	
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		l_page = new PagesLoginPage(driver);
		cr = new ConfigReader();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(cr.getPropData("url"));
	}
	
	
	@Test (dataProvider="dataInput", priority=1)
	public void verifyLoginPageCredentialsTests(String userName, String passWord, String data)
	{	
		l_page.enterUsername(userName);
		l_page.enterPassword(passWord);
		l_page.clickOnLoginButton();

		
		if(data.equals("valid"))
		{
			Assert.assertTrue(l_page.loginSuccessfulMessageIsDisplayed(), "Login is not Successful");
		}
		else if(data.equals("validInvalid"))
		{
			Assert.assertTrue(l_page.errorMessageIsDisplayed(), "validInvalid");
		}
		else if(data.equals("invalidValid"))
		{
			Assert.assertTrue(l_page.errorMessageIsDisplayed(), "invalidValid");
		}
		else if(data.equals("invalidInvalid"))
		{
			Assert.assertTrue(l_page.errorMessageIsDisplayed(), "invalidInvalid");
		}
		
	}
	@DataProvider
	public Object[][] dataInput() throws IOException
	{
		FileInputStream fis = new FileInputStream("./excelFiles/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s1 = wb.getSheet("RecruitCRM");
		
		Object[][]ar = new Object[s1.getLastRowNum()][s1.getRow(0).getLastCellNum()];
				
		for(int i=1,a=0; i<=s1.getLastRowNum(); i++,a++)							
		{
			for(int k=0; k<s1.getRow(0).getLastCellNum(); k++)
			{
				ar[a][k] = s1.getRow(i).getCell(k).getStringCellValue();
				
			}
		}
		
		return ar;
	}
	
	@Test (priority=2)
	public void verifyLoginPageTitle()
	{
		Assert.assertEquals(driver.getTitle(), "Recruit CRM");
	}
	
	@Test (priority=3)
	public void verifyLoginPageUrl()
	{
		Assert.assertEquals(driver.getCurrentUrl(), "https://app.recruitcrm.io/");
	}
	
	@Test(priority=4)
	public void verifyForgotPasswordFunctionality()
	{
		l_page.clickOnForgotPasswordLink();
		l_page.enterEmailId(cr.getPropData("username"));
		l_page.clickOnRequestPasswordButton();
		
		Assert.assertTrue(l_page.passwordChangedConfirmationMessageIsDisplayed(), "Confirmation msg not displayed");
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}





/*try
{
	WebElement errorMessage = driver.findElement(By.xpath("//div [@role=\"alert\"]"));
	Assert.assertEquals(errorMessage.getText(),"Failed to Login : Please check your Email ID & Password, if you still can’t login, email us at support@recruitcrm.io");
}
catch(Exception e)
{
	WebElement dashBoardIcon = driver.findElement(By.id("sTest-dashboradIcon"));
	Assert.assertTrue(dashBoardIcon.isDisplayed(), "Login Not Successful");
}*/
