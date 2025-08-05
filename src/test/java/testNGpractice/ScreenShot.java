package testNGpractice;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class ScreenShot 
{
	@Test
	public void takeScreenShot() throws IOException
	{
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		Date d=new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts= (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm=new File("./Screenshots/takeSS"+newDate+".png");
		FileHandler.copy(temp, perm);
	}

}
