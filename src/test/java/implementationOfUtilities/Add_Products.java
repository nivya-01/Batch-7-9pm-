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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExceFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

public class Add_Products {

	public static void main(String[] args) throws IOException 
	{
		PropertiesFileUtility putil=new PropertiesFileUtility();
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		String BROWSER = putil.toGetDataFromPropertiesFile("Browser");
		String URL = putil.toGetDataFromPropertiesFile("Url");
		String USERNAME = putil.toGetDataFromPropertiesFile("Username");
		String PASSWORD = putil.toGetDataFromPropertiesFile("Password");
	
		
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
		
		String prodname = eutil.toGetDataFromExcelFileForProducts("Product", 1, 0);
		String quant = eutil.toGetDataFromExcelFileForProducts("Product", 1, 1);
		String price = eutil.toGetDataFromExcelFileForProducts("Product", 1, 2);
		
		
		
		//add products
		HomePage hp=new HomePage(driver);
		
		hp.getProducts().click();//driver.findElement(By.linkText("Products"));
//		wutil.mouseHover(driver, prod);
//		wutil.clickOnWebelement(driver, prod);
		
		ProductPage pp=new ProductPage(driver);
		pp.getAddproduct().click();
		//driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		pp.getProdname().sendKeys(prodname+jutil.toGetRandomNumber());
		//driver.findElement(By.name("productName")).sendKeys(prodname+jutil.toGetRandomNumber());
		
		WebElement qty1 = pp.getProdquantity();//driver.findElement(By.name("quantity"));
		qty1.clear();
		qty1.sendKeys(quant);
		
		WebElement pr = pp.getProdprice();//driver.findElement(By.name("price"));
		pr.clear();
		pr.sendKeys(price);
		
		WebElement prod_category = pp.getProdcat();//driver.findElement(By.name("productCategory"));
		wutil.selectByIndex(prod_category, 3);
		
		WebElement vendor = pp.getProdvendorid();//driver.findElement(By.name("vendorId"));
		wutil.selectByValue(vendor, "VID_002");
		
		pp.getAddbutton().click();
		//driver.findElement(By.xpath("//button[text()='Add']")).click();
		
//		//validation
//		WebElement toastmsg = //driver.findElement(By.xpath("//div[@role='alert']"));
//		wutil.waitForVisibilityOfElement(driver, toastmsg);
//		String msg = toastmsg.getText();
//		
//		if(msg.contains(prodname))
//		{
//			System.out.println("Product details added");
//		}
//		
//		else {
//			System.out.println("Product details not added");
//		}
//		driver.findElement(By.xpath("//button[@aria-label='close']")).click();	
		
		//logout
		WebElement icon = hp.getUserIcon();//driver.findElement(By.xpath("//div[@class='user-icon']"));
		wutil.mouseHover(driver, icon);
		WebElement logout = hp.getLogoutBtn();//driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
        wutil.mouseHover(driver, logout);
        wutil.clickOnWebelement(driver, logout);
		
		driver.quit();		
	}


}
