package implementationofBaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import genericUtility.ExceFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateCampaignWithStatus extends BaseClass
{
	@Test
	public void toCreateCampaignWithStatus() throws EncryptedDocumentException, IOException
	{
		
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
		
		String campname = eutil.toGetDataFromExceFile("Campaign", 1, 2);
		String target = eutil.toGetDataFromExceFile("Campaign", 1, 3);
		
		
		//create campaign
		HomePage hp=new HomePage(driver);
		wutil.waitForVisibilityOfElement(driver, hp.getCreateCampaignBtn());
		hp.getCreateCampaignBtn().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCampName().sendKeys(campname+ jutil.toGetRandomNumber());
		cp.getCampStatus().sendKeys("In progress");
		WebElement target1 = cp.getTarget();
		target1.clear();
		target1.sendKeys(target);
		cp.getCreateCampSubmitBtn().click();;
		
		//validation
		WebElement toastmsg = cp.getToastmsg();
        wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		
		if(msg.contains(campname))
		{
			System.out.println("campaign created");
		}
		
		else {
			System.out.println("campaign not created");
		}
		cp.getCreateCampSubmitBtn().click();
				
	}

}
