package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage 
{
	WebDriver driver;
	public CampaignPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (name="campaignName")
	private WebElement campName;
	
	@FindBy(name="campaignStatus")
	private WebElement campStatus;
	
	@FindBy(name="targetSize")
	private WebElement target;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expCloseDate;
	
	@FindBy (xpath="//button[text()='Create Campaign']")
	private WebElement createCampSubmitBtn;
	
	@FindBy (xpath="//div[@role='alert']")
	private WebElement toastmsg;
	
	@FindBy(xpath="//button[@aria-label='close']")
	private WebElement closemsg;

	public WebElement getCampName() {
		return campName;
	}

	public WebElement getCampStatus() {
		return campStatus;
	}

	public WebElement getTarget() {
		return target;
	}

	public WebElement getExpCloseDate() {
		return expCloseDate;
	}

	public WebElement getCreateCampSubmitBtn() {
		return createCampSubmitBtn;
	}

	public WebElement getToastmsg() {
		return toastmsg;
	}

	public WebElement getClosemsg() {
		return closemsg;
	}
	
	

}
