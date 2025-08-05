package testNGpractice;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ConfigurationAnnotations {
  @Test
  public void demo() 
  {
	  System.out.println("TC executed");
  }
  @BeforeMethod
  public void beforeMethod() 
  {
	  System.out.println("login");
  }

  @AfterMethod
  public void afterMethod() 
  {
	  System.out.println("logout");
  }

  @BeforeClass
  public void beforeClass() 
  {
	  System.out.println("launching the browser");
  }

  @AfterClass
  public void afterClass() 
  {
	  System.out.println("close the browser");
  }

  @BeforeTest
  public void beforeTest()
  {
	  System.out.println("pre conditions");
  }

  @AfterTest
  public void afterTest() 
  {
	  System.out.println("post conditions");
  }

  @BeforeSuite
  public void beforeSuite() 
  {
	  System.out.println("DB connectivity open");
  }

  @AfterSuite
  public void afterSuite() 
  {
	  System.out.println("DB connectivity close");
  }

}
