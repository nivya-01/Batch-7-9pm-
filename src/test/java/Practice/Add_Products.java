package Practice;

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

public class Add_Products {

	public static void main(String[] args) throws IOException 
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		Properties prop=new Properties();
		prop.load(fis);
	
		String BROWSER= prop.getProperty("Browser");
		String URL=prop.getProperty("Url");
		String USERNAME=prop.getProperty("Username");
		String PASSWORD=prop.getProperty("Password");
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//login
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\add_products.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Product");
		Row r = sh.getRow(1);
		Cell c1 = r.getCell(0);
		String prodname = c1.getStringCellValue();
		Cell c2 = r.getCell(1);
		String qty = c2.getStringCellValue();
		Cell c3 = r.getCell(2);
		String price = c3.getStringCellValue();
		
		//Random class
		Random rand=new Random();
		int randcount = rand.nextInt(1000);		
		
		//add products
		WebElement prod = driver.findElement(By.linkText("Products"));
		Actions act= new Actions(driver);
		act.moveToElement(prod).click().perform();
		
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		
		driver.findElement(By.name("productName")).sendKeys(prodname+randcount);
		
		WebElement qty1 = driver.findElement(By.name("quantity"));
		qty1.clear();
		qty1.sendKeys(qty);
		
		WebElement pr = driver.findElement(By.name("price"));
		pr.clear();
		pr.sendKeys(price);
		
		WebElement prod_category = driver.findElement(By.name("productCategory"));
		Select s=new Select(prod_category);
		s.selectByValue("Electronics");
		
		WebElement vendor = driver.findElement(By.name("vendorId"));
		Select v=new Select(vendor);
		v.selectByValue("VID_002");
		
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		
		//validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		
		if(msg.contains(prodname))
		{
			System.out.println("Product details added");
		}
		
		else {
			System.out.println("Product details not added");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();	
		
		//logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act1=new Actions(driver);
		act1.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act1.moveToElement(logout).click().perform();
		
		driver.quit();		
	}

}
