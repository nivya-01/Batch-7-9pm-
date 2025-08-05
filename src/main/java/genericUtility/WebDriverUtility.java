package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	public void waitPageForLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	public void waitForVisibilityOfElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameorId)
	{
		driver.switchTo().frame(nameorId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void switchToAlertAndSendkeys(WebDriver driver, String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	public String switchToAlertAndGetText(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	public void selectByIndex(WebElement element,int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	
	public void selectByValue(WebElement element, String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	
	public void selectByIndex(WebElement element, String text)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	
	public void mouseHover(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void clickOnWebelement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).click().perform();
	}
	
	public void doubleClickOnWebelement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).doubleClick().perform();
	}
	
	public void passInput(WebDriver driver, WebElement element, String text)
	{
		Actions act=new Actions(driver);
		act.click(element).sendKeys(text).perform();
	}
	
	public void rightClickOnWebelement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
		
	}
	
	public void switchToWindow(WebDriver driver)
	{
		Set<String> allWindId = driver.getWindowHandles();
		for(String id : allWindId)
		{
			driver.switchTo().window(id);
		}
	}
	
	public void takeScreenshot(WebDriver driver,String fileName) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm=new File("./errorshots/"+fileName+".png");
		FileHandler.copy(temp, perm);
		
	}
	
	public void toScrollBy(WebDriver driver, int x,int y)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}
	



}
