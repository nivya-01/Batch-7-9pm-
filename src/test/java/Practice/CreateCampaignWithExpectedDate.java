package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateCampaignWithExpectedDate {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException
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
		
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\create_campaign.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Campaign");
		Row r = sh.getRow(1);
		Cell c = r.getCell(2);
		String campname = c.getStringCellValue();
		Cell targetsize = r.getCell(3);
		String target = targetsize.getStringCellValue();
		
		//Random class
		Random rand=new Random();
		int randcount = rand.nextInt(1000);
		
		
		//date after 30 days
		Date date=new Date();//it gives today's date
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String datereq = sim.format(cal.getTime());
			
		
		//create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname+randcount);
		driver.findElement(By.name("campaignStatus")).sendKeys("In progress");
		WebElement target1 = driver.findElement(By.name("targetSize"));
		target1.clear();
		target1.sendKeys(target);
		
		WebElement expectedClosedate = driver.findElement(By.name("expectedCloseDate"));
		Actions act1=new Actions(driver);
		act1.click(expectedClosedate).sendKeys(datereq).perform();
		
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		
		if(msg.contains(campname))
		{
			System.out.println("campaign created");
		}
		
		else {
			System.out.println("campaign not created");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();		
	
		
		//logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act=new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		
		driver.quit();

	}

}
