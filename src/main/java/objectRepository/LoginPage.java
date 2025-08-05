package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//autohealing
	@FindAll({@FindBy(id="username"),@FindBy(name="username")})
	private WebElement UN;
	
	@FindBy(id="inputPassword")
	private WebElement PW;
	
	@FindBy(xpath="//button[text()='Sign In']")
	private WebElement loginBtn;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUN() {
		return UN;
	}

	public WebElement getPW() {
		return PW;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	
	

}
