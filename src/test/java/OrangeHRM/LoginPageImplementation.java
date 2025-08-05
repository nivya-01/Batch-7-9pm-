package OrangeHRM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import genericUtilityOrangeHRM.ExcelFileUtility;
import genericUtilityOrangeHRM.PropertiesFileUtility;
import genericUtilityOrangeHRM.WebDriverUtility;
import objectRepositoryOrangeHRM.LoginPage;

public class LoginPageImplementation {

	public static void main(String[] args) throws IOException
	{
		PropertiesFileUtility putil=new PropertiesFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		String BROWSER = putil.toGetDataFromPropertyFile("Browser");
		String URL = putil.toGetDataFromPropertyFile("Url");
		String USERNAME = eutil.toGetDataFromExcelFile("Login", 1, 0);
		String PASSWORD = eutil.toGetDataFromExcelFile("Login", 1, 1);
		
		System.out.println(BROWSER);
		System.out.println(URL);
		
		WebDriver driver=null;
		if(BROWSER.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		else if(BROWSER.equals("Chrome"))
		{
			driver=new ChromeDriver();
			
		}
		else if(BROWSER.equals("Firefox"))
		{
			driver=new FirefoxDriver();
		}	
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.getUsername().sendKeys(USERNAME);
		lp.getPassword().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		WebElement dropdown = lp.getDropdown();
		wutil.mouseHover(driver, dropdown);
		wutil.clickOnWebelement(driver, dropdown);
		WebElement logout = lp.getLogoutbtn();
		wutil.clickOnWebelement(driver, logout);
		
		driver.quit();
		
	
		
	}

}
