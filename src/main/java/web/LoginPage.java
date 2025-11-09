package web;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPage extends BasePage {
	
	/*  Log4j implementation*/
	
	private final Logger LOG=LogManager.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='email']")
	WebElement LoginField;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordField;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement LoginButton;
	
	@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
	WebElement LoginSuccess;
	
	

	Actions a = new Actions(driver);
	HomePage hp = new HomePage(driver);
	SoftAssert softAssert = new SoftAssert();

	public void launchApp() {
		String url = System.getProperty("url", Loader.getProperty("url"));
		driver.get(url);
		LOG.info("Opened Test URL: " + url);
//		acceptCookiesIfDisplayed();
		//closeOneTrustCookiesIfDisplayed();
	}

	public void clickOnSignIn() {
		if (checkIfElementIsVisible(LoginButton))
			scrollToElement(LoginButton);
		else
			driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(LoginButton)).click();
		LOG.info("Clicked on Sign in");
	}

	public void enterUsername_adminapp() {
		// wait.until(ExpectedConditions.visibilityOf(createAccountTxt));
		String userName = Loader.getProperty("admin_username");
		wait.until(ExpectedConditions.visibilityOf(LoginField)).sendKeys(userName);
		LOG.info("Entered valid username: " + userName);
	}
	
	public void enterUsername() {
		// wait.until(ExpectedConditions.visibilityOf(createAccountTxt));
		String userName = Loader.getProperty("username");
		wait.until(ExpectedConditions.visibilityOf(LoginField)).sendKeys(userName);
		LOG.info("Entered valid username: " + userName);
	}


	public void enterUsername(String emailId) {
		//wait.until(ExpectedConditions.visibilityOf(createAccountTxt));
		wait.until(ExpectedConditions.visibilityOf(LoginField)).sendKeys(emailId);
		LOG.info("Entered email id as: " + emailId);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
		LOG.info("Entered valid password");
	}

	public void enterPassword() {
		String password = Loader.getProperty("admin_password");
		wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
		LOG.info("Entered valid password");
	}

	public void clickOnSignInButton() {
//		wait.until(ExpectedConditions.elementToBeClickable(formSignInBtn)).click();
		scrollToElement(LoginButton);
//		wait.until(ExpectedConditions.visibilityOf(formSignInBtn));
		wait.until(ExpectedConditions.elementToBeClickable(LoginButton)).click();
		LOG.info("Clicked on Sign in button");
		waitUntilLoadingBarDisappears();
		LOG.info("Loading disappeared");
		wait.until(ExpectedConditions.elementToBeClickable(new HomePage(driver).siteLogo));
		LOG.info("Logo is clickable");
		waitUntilSpinnerDisappears(); // required since its failing due to page loading
	}

	public void verifyLoginSuccess() {
		wait.until(ExpectedConditions.visibilityOf(LoginSuccess));
		Assert.assertTrue(LoginSuccess.isDisplayed(), "User is not Logged in");
		LOG.info("User is Logged in ");
	}

//	public void verifyLoginStatusWithProductsOnTray() {
//		Assert.assertTrue(userName.isDisplayed(), " User is not Logged in");
//		Log.info("User is Logged in with products on Tray");
//		wait.until(ExpectedConditions.visibilityOf(userName)).click();
//
//		softAssert.assertTrue(accountHeader.isDisplayed(), "Account page is not displayed");
//		Log.info("Account page is displayed");
//		wait.until(ExpectedConditions.visibilityOf(closeIcon)).click();
//		Log.info("Clicked on Close Icon");
//		clickElementUsingJavaScriptExecutor(cartIcon);
//		Assert.assertTrue(userName.isDisplayed(), "Logged user name is not displayed");
//	}
//
//	public void clickCreateOneNow() {
//
//		wait.until(ExpectedConditions.visibilityOf(createOneNowOption)).click();
//		Log.info("Clicked on Create One Now!");
//	}

	public void enterinvalidPassword() {
		String invalidpassword = Loader.getProperty("invalidPassword");
		wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(invalidpassword);
		LOG.info("Entered invalid password");
	}


	public void signin() {
		clickOnSignIn();
		enterUsername();
		enterPassword();
		clickOnSignInButton();
	}

	
	

}
