package campaign;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseTest.BaseClass;
import genericUtility.ExceFileUtility;
import genericUtility.JavaUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;

@Listeners(listenerUtility.ListenerImplementation.class)
public class CampaignTest extends BaseClass
{
	@Test(groups = "smoke")
	public void toCreateCampaignWithMadatoryFieldsTest() throws IOException
	{
		
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
		
		String campname = eutil.toGetDataFromExceFile("Campaign", 1, 2);
		String target = eutil.toGetDataFromExceFile("Campaign", 1, 3);
		
		HomePage hp=new HomePage(driver);
		hp.getCreateCampaignBtn().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCampName().sendKeys(campname);
		WebElement target1 = cp.getTarget();  
		target1.clear();
		target1.sendKeys(target);
		cp.getCreateCampSubmitBtn().click();

		
		//validation
		WebElement toastmsg = cp.getToastmsg(); 
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
//		if(msg.contains(campname))
//		{
//			System.out.println("campaign created");
//		}
//		
//		else {
//			System.out.println("campaign not created");
//		}
		cp.getClosemsg().click();
		
	}
	
	@Test(groups = "smoke")
	public void toCreateCampaignWithStatusTest() throws EncryptedDocumentException, IOException
	{
		
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
		
		String campname = eutil.toGetDataFromExceFile("Campaign", 1, 2);
		String target = eutil.toGetDataFromExceFile("Campaign", 1, 3);
		
		HomePage hp=new HomePage(driver);
		hp.getCreateCampaignBtn().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCampName().sendKeys(campname);
		cp.getCampStatus().sendKeys("In progress");
		WebElement target1 = cp.getTarget();  
		target1.clear();
		target1.sendKeys(target);
		cp.getCreateCampSubmitBtn().click();

		
		//validation
		WebElement toastmsg = cp.getToastmsg(); 
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
//		if(msg.contains(campname))
//		{
//			System.out.println("campaign created");
//		}
//		
//		else {
//			System.out.println("campaign not created");
//		}
		cp.getClosemsg().click();
		
	}
	
	@Test(groups = "regression")
	public void toCreateCampaignWithExpectedDateTest() throws IOException
	{
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
			
		String campname = eutil.toGetDataFromExceFile("Campaign", 1, 2);
		String target = eutil.toGetDataFromExceFile("Campaign", 1, 3);
		
		String datereq = jutil.toGetExpectedDate(30);
	
		
		//create campaign
		HomePage hp=new HomePage(driver);
		hp.getCreateCampaignBtn().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCampName().sendKeys(campname);
		cp.getCampStatus().sendKeys("In progress");
		WebElement target1 = cp.getTarget();
		target1.clear();
		target1.sendKeys(target);
		
		WebElement expectedClosedate = cp.getExpCloseDate();
		wutil.passInput(driver, expectedClosedate, datereq);
		
		cp.getCreateCampSubmitBtn().click();
		
		//validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		//assertEquals(msg, campname);//for listener
//		if(msg.contains(campname))
//		{
//			System.out.println("campaign created");
//		}
//		
//		else {
//			System.out.println("campaign not created");
//		}
		cp.getClosemsg().click();
	
	}




}
