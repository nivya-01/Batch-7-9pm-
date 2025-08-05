package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Create_Contact {

	public static void main(String[] args) 
	{
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//login
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Electronics Sale");
		driver.findElement(By.name("campaignStatus")).sendKeys("In progress");
		WebElement target = driver.findElement(By.name("targetSize"));
		target.clear();
		target.sendKeys("10");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		//validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		
		if(msg.contains("Electronics Sale"))
		{
			System.out.println("campaign created");
		}
		
		else {
			System.out.println("campaign not created");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();		
	
		
		//create contact
		WebElement contact = driver.findElement(By.linkText("Contacts"));
		Actions act1=new Actions(driver);
		act1.moveToElement(contact).click().perform();
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		driver.findElement(By.name("organizationName")).sendKeys("abc company");
		driver.findElement(By.name("title")).sendKeys("QA Analyst");
		driver.findElement(By.name("contactName")).sendKeys("Aadhi");
		driver.findElement(By.name("mobile")).sendKeys("1234567890");
		
		driver.findElement(By.xpath("//svg[@aria-hidden='true']")).click();
		
//		WebElement plus = driver.findElement(By.xpath("//button[@type,'button']"));
//		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait1.until(ExpectedConditions.elementToBeClickable(plus));
//		plus.click();
//	
		
		WebElement cam_name = driver.findElement(By.id("search-criteria"));
		Select s=new Select(cam_name);
		s.selectByValue("campaignName");
		
		driver.findElement(By.id("search-input")).sendKeys("Electronics Sale");
		
		
		
		

	}

}
