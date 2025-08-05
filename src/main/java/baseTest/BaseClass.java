package baseTest;

import org.testng.annotations.Test;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.HomePage;
import objectRepository.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass 
{
	public WebDriver driver=null;
	public static WebDriver ldriver=null;// for listener
	public PropertiesFileUtility putil=new PropertiesFileUtility();
	public WebDriverUtility wutil=new WebDriverUtility();

  @BeforeMethod(groups = {"smoke","regression"})
  public void beforeMethod() throws IOException 
  {
	  System.out.println("login");
	  String URL = putil.toGetDataFromPropertiesFile("Url");
	  String USERNAME = putil.toGetDataFromPropertiesFile("Username");
	  String PASSWORD = putil.toGetDataFromPropertiesFile("Password");
	  driver.manage().window().maximize();
	  wutil.waitPageForLoad(driver);
	  driver.get(URL);
	  LoginPage lp=new LoginPage(driver);
	  lp.getUN().sendKeys(USERNAME);
	  lp.getPW().sendKeys(PASSWORD);
	  lp.getLoginBtn().click();
	  
  }

  @AfterMethod(groups = {"smoke","regression"})
  public void afterMethod() 
  {
	  HomePage hp=new HomePage(driver);
	  System.out.println("logout");
	  hp.getUserIcon().click();
	  hp.getLogoutBtn().click();
  }
  //@Parameters("BROWSER")
  @BeforeClass(groups = {"smoke","regression"})
  public void beforeClass() throws IOException //public void beforeClass(String browser) throws IOException 
  {
	  //String BROWSER=browser;
	  System.out.println("Launching the browser");
	  String BROWSER = putil.toGetDataFromPropertiesFile("Browser");
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
	  ldriver=driver;
  }

  @AfterClass(groups = {"smoke","regression"})
  public void afterClass() 
  {
	  System.out.println("Close the browser");
	  driver.quit();
	  
  }

  @BeforeTest(groups = {"smoke","regression"})
  public void beforeTest() 
  {
	  System.out.println("Preconditions");
  }

  @AfterTest(groups = {"smoke","regression"})
  public void afterTest() 
  {
	  System.out.println("Post conditions");
  }

  @BeforeSuite(groups = {"smoke","regression"})
  public void beforeSuite() 
  {
	  System.out.println("DB connectivity open");
  }

  @AfterSuite(groups = {"smoke","regression"})
  public void afterSuite() 
  {
	  System.out.println("DB connectivity close");
  }

}
