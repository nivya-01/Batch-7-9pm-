package testNGpractice;

import static org.testng.Assert.assertEquals;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion 
{
	@Test
	public void titleValidation()
	{
		String expTitle="Facebook – log in or sign ";
		WebDriver driver=new EdgeDriver();
		driver.get("https://www.facebook.com/");
		String actTitle = driver.getTitle();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(expTitle, actTitle);
		//assertEquals(expTitle, actTitle);
		System.out.println("passed");
		soft.assertAll();
	}

}
