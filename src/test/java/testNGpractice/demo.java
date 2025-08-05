package testNGpractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class demo 
{
	@Test	
	public void add()
	{
		Reporter.log("add", true);
		
	}
	
	public void sub() 
	{
		Reporter.log("sub", true);
		
	}
	@Test
	public void div()
	{
		Reporter.log("div", true);
	}
	
	@Test
	public void mul()
	{
		Reporter.log("mul", true);
	}

}
