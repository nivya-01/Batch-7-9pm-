package testNGpractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Enabled 
{
	@Test(invocationCount = 0)
	public void add()
	{
		Reporter.log("add", true);
	}
	//skips the from test execution
	@Test(enabled = false)
	public void sub()
	{
		Reporter.log("sub", true);
	}
	
	@Test
	public void mul()
	{
		Reporter.log("mul", true);
	}

}
