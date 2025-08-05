package testNGpractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Priority 
{
	@Test(priority = 1)
	public void add()
	{
		Reporter.log("add", true);
	}
	
	@Test(priority = 2)
	public void sub()
	{
		Reporter.log("sub", true);
	}
	
	@Test
	public void mul()
	{
		Reporter.log("mul", true);
	}
	
	@Test(priority = -1)
	public void div()
	{
		Reporter.log("div", true);
	}

}
