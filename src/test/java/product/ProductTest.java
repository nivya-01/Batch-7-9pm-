package product;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import genericUtility.ExceFileUtility;
import genericUtility.JavaUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.ProductPage;

@Listeners(listenerUtility.ListenerImplementation.class)//to capture run time events
public class ProductTest extends BaseClass
{
	@Test(groups = "smoke")
	public void toAddProducts() throws EncryptedDocumentException, IOException
	{
		ExceFileUtility eutil=new ExceFileUtility();
		JavaUtility jutil=new JavaUtility();
		
		String prodname = eutil.toGetDataFromExcelFileForProducts("Product", 1, 0);
		String quant = eutil.toGetDataFromExcelFileForProducts("Product", 1, 1);
		String price = eutil.toGetDataFromExcelFileForProducts("Product", 1, 2);
				
		//add products
		HomePage hp=new HomePage(driver);
		
		hp.getProducts().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getAddproduct().click();
		pp.getProdname().sendKeys(prodname+jutil.toGetRandomNumber());
		
		WebElement qty1 = pp.getProdquantity();
		qty1.clear();
		qty1.sendKeys(quant);
		
		WebElement pr = pp.getProdprice();
		pr.clear();
		pr.sendKeys(price);
		
		WebElement prod_category = pp.getProdcat();
		wutil.selectByIndex(prod_category, 3);
		
		WebElement vendor = pp.getProdvendorid();
		wutil.selectByValue(vendor, "VID_002");
		
		pp.getAddbutton().click();
		

		//validation
		CampaignPage cp=new CampaignPage(driver);
		WebElement toastmsg = cp.getToastmsg(); 
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(prodname));
		//assertEquals(msg, prodname); for listener
//		if(msg.contains(prodname))
//		{
//			System.out.println("product added");
//		}
//		
//		else {
//			System.out.println("product not added");
//		}
		cp.getClosemsg().click();
		
		
	}

}
