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
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

public class AddProducts extends BaseClass
{
	@Test
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
		
	}

}
