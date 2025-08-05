package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage
{
	WebDriver driver;
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath="//span[text()='Add Product']")
	private WebElement addproduct;
	
	@FindBy (name="productName")
	private WebElement prodname;
	
	@FindBy (name="productCategory")
	private WebElement prodcat;
	
	@FindBy (name="quantity")
	private WebElement prodquantity;
	
	@FindBy (name="price")
	private WebElement prodprice;
	
	@FindBy (name="vendorId")
	private WebElement prodvendorid;
	
	@FindBy (xpath="//button[text()='Add']")
	private WebElement addbutton;
	
	public WebElement getAddproduct() {
		return addproduct;
	}

	public WebElement getProdname() {
		return prodname;
	}

	public WebElement getProdcat() {
		return prodcat;
	}

	public WebElement getProdquantity() {
		return prodquantity;
	}

	public WebElement getProdprice() {
		return prodprice;
	}

	public WebElement getProdvendorid() {
		return prodvendorid;
	}

	public WebElement getAddbutton() {
		return addbutton;
	}
	
	

}
