package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(linkText="Campaigns")
	private WebElement campaign;
	
	@FindBy(linkText = "Products")
	private WebElement products;
	
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement createCampaignBtn;
	
	@FindBy(className ="user-icon")
	private WebElement userIcon;
	
	@FindBy(xpath="//div[text()='Logout ']")
	private WebElement logoutBtn;

	public WebElement getCampaign() {
		return campaign;
	}

	public WebElement getProducts() {
		return products;
	}

	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
	

}
