package testNGpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ThreadPoolSize {
//invocation count should be mandatory
	@Test(invocationCount = 7, threadPoolSize = 3)
	public void login() throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		Thread.sleep(2000);
		driver.quit();
	}
	
}
