package implementationOfUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExceFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateCampaignWithStatus {

	public static void main(String[] args) throws IOException 
	{
		PropertiesFileUtility putil=new PropertiesFileUtility();
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		String BROWSER = putil.toGetDataFromPropertiesFile("Browser");
		String URL=putil.toGetDataFromPropertiesFile("Url");
		String USERNAME=putil.toGetDataFromPropertiesFile("Username");
		String PASSWORD=putil.toGetDataFromPropertiesFile("Password");
		
		
		
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
		wutil.waitPageForLoad(driver);	
		
		//login
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.getUN().sendKeys(USERNAME);
		lp.getPW().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		
//		driver.findElement(By.id("username")).sendKeys(USERNAME);
//		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
//		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		String campname = eutil.toGetDataFromExceFile("Campaign", 1, 2);
		String target = eutil.toGetDataFromExceFile("Campaign", 1, 3);
		
		//Random class
		
		
		//create campaign
		HomePage hp=new HomePage(driver);
		wutil.waitForVisibilityOfElement(driver, hp.getCreateCampaignBtn());
		hp.getCreateCampaignBtn().click();
//		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCampName().sendKeys(campname+ jutil.toGetRandomNumber());
		cp.getCampStatus().sendKeys("In progress");
//		driver.findElement(By.name("campaignName")).sendKeys(campname + jutil.toGetRandomNumber());
//		driver.findElement(By.name("campaignStatus")).sendKeys("In progress");
		WebElement target1 = cp.getTarget();//driver.findElement(By.name("targetSize"));
		target1.clear();
		target1.sendKeys(target);
		cp.getCreateCampSubmitBtn().click();;
		//driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//validation
		WebElement toastmsg = cp.getToastmsg();//driver.findElement(By.xpath("//div[@role='alert']"));
        wutil.waitForVisibilityOfElement(driver, toastmsg);
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		
		if(msg.contains(campname))
		{
			System.out.println("campaign created");
		}
		
		else {
			System.out.println("campaign not created");
		}
		cp.getCreateCampSubmitBtn().click();
		//driver.findElement(By.xpath("//button[@aria-label='close']")).click();		
	
		
		//logout
		WebElement icon = hp.getUserIcon();//driver.findElement(By.xpath("//div[@class='user-icon']"));
		wutil.mouseHover(driver, icon);
//		Actions act=new Actions(driver);
//		act.moveToElement(icon).perform();
		WebElement logout = hp.getLogoutBtn();//driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		wutil.mouseHover(driver, logout);
		wutil.clickOnWebelement(driver, logout);
		//act.moveToElement(logout).click().perform();
		
		driver.quit();
		

	}

	

}
