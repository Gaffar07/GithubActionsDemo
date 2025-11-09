package web;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.types.resources.comparators.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.ConfigLoader;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected ConfigLoader Loader;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Loader=ConfigLoader.getInstance();
		wait=new WebDriverWait(driver,Integer.parseInt(Loader.getProperty("dynamicwaitInsecs")));
	}
	
	private String screenShotName;
	
	
	/*log4j implementation*/
	
	private final Logger LOG=LogManager.getLogger(BasePage.class);
	
	By spinner = By.xpath("//*[@class='sc-jIRcFI dDmreh' or @class='sc-bhNKFk']");
	
	By loadingBar=By.xpath(".loading");
	
	
	
	
	public String getDateAndTimeStamp() {
		
		return new Date().toString().replace(":", "-").replace(" ", "-");
	}
	
	
	public String getCurrentDate() {
		
		Date d=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("dd-MM-yyyy");
		return sd.format(d);
	}
	
	
	public String getScreenshotpath() {
		
		return (System.getProperty("user.dir")+File.separator+"reports"+File.separator+getCurrentDate()+File.separator+"images"+File.separator+screenShotName).toString();
	}
	
	
	public void captureSceenshot(String result) throws IOException {
		LOG.info("Test Result: "+result);
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenShotName=getDateAndTimeStamp()+"_"+result+".png";
		File destPath=new File(getScreenshotpath());
		FileUtils.copyFile(srcfile, destPath);
	}
	
	
	public String getTimestamp() {
		
		
		return new Date().toString().replace(":", "-").replace(" ", "-");
	}
	
	
	public boolean scrollToElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView", element);
		return true;
	}
	
	
	public void waitUntillSpinnerDisappear() {
		LOG.info("waiting for spinner to disappear");
		
		try {
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void waitUntilSpinnerDisappears() {
		LOG.info("[!!!] Waiting for spinner to disappear");
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		} catch (Exception e) {
		}
	}
	
	public void waitUntilLoadingBarDisappears() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		LOG.info("[!!!] Waiting for Loading progress to disappear");
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBar));
		} catch (Exception e) {
		}
	}

	
	public void waitUtillLoadingBarDisappear() {
		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		LOG.info("waiting for loading progress bar to disappear");
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBar));
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	public void waitUntilProcessingOrderDisappears() {
//		int time = Integer.parseInt(Loader.getProperty("processingOrderWaitInSecs"));
//		WebDriverWait animatedWait = new WebDriverWait(driver, time);
//		try {
//			Thread.sleep(8000); // added to avoid unwanted failure on SauceLabs
//			LOG.info("Waiting until processing order animation disappears or max " + time + "secs");
//			animatedWait.until(ExpectedConditions.invisibilityOfElementLocated(processingOrderImg));
//		} catch (Exception e) {
//		}
//	}

	public boolean checkIfElementIsVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean checkIfElementIsClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void clickElementUsingJavaScriptExecutor(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollToTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
	}

	public void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,100)");
	}

	public void switchBrowserWindow(String parentWindow) {
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
			}
		}
	}

	public void moveToElement(WebElement target) {
		Actions a = new Actions(driver);
		a.moveToElement(target);
	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,100)");
	}

	public void selectFromDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectFromDropdownByValue(WebElement element, String element1) {
		Select select = new Select(element);
		select.selectByValue(element1);
	}

	public void selectFromDropdownByVisiableText(WebElement element, String element1) {
		Select select = new Select(element);
		select.selectByVisibleText(element1);
	}

	public void scrolldown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,50)");
	}

	public void valuesInDropdown(WebElement element) {
		Select select = new Select(element);
		List<WebElement> values = select.getOptions();
		for (int i = 0; i < values.size(); i++) {
			String allValues = values.get(i).getText();
			System.out.println(allValues);
		}
	}

	public boolean isDisplayed(WebElement element) {

		boolean dsp = element.isDisplayed();
		return dsp;
	}

	/** Converting String amount to decimal and limiting to 2 decimal point */
	public float roundTwoDecimals(float f) {
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		String amountRoundedOff = decimalFormat.format(f);
		return Float.valueOf(amountRoundedOff).floatValue();
	}

	public String getColor(WebElement element) throws Exception {
		try {
//            long waitTime = (long) Double.parseDouble("20");
//            WebDriverWait wait = new WebDriverWait(driver(), waitTime);
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
//                    element);
			String textcolor = element.getCssValue("color").trim();
			LOG.info("Text color is: " + textcolor);

			String color_hex[];
			color_hex = textcolor.replace("rgba(", "").split(",");
			String actual_hex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
					Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
			LOG.info("Text color in Hex is: " + actual_hex);
			return actual_hex;
		} catch (Exception e) {
			throw new Exception("Not able to get text", e);
		}
	}

//	public void acceptCookiesIfDisplayed() {
//		try {
//			if (wait.until(ExpectedConditions.visibilityOf(new LoginPage(driver).cookiesXPath)) != null) {
//				new LoginPage(driver).cookiesXPath.click();
//				LOG.info("Cookies accepted.");
////				if (checkIfElementIsVisible(new LoginPage(driver).covidUpdateCloseBtn)) {
//				if ((new LoginPage(driver).covidUpdateCloseBtn).isDisplayed()) {
//					new LoginPage(driver).covidUpdateCloseBtn.click();
//					LOG.info("Closed Covid update information");
//				}
//			}
//		} catch (Exception e) {
////			LOG.info("Cookies not found" + e.getMessage());
//		}
//	}

//	public void closeOneTrustCookiesIfDisplayed() {
//		try {
//			if (wait.until(ExpectedConditions.visibilityOf(new LoginPage(driver).oneTrustCookiesCloseIcon)) != null) {
//				new LoginPage(driver).oneTrustCookiesCloseIcon.click();
//				LOG.info("One trust Cookies closed.");
//				if (wait.until(ExpectedConditions.visibilityOf(new LoginPage(driver).covidUpdateCloseBtn)).isDisplayed()) {
//					new LoginPage(driver).covidUpdateCloseBtn.click();
//					LOG.info("Closed Covid update information");
//				}
//			}
//		} catch (Exception e) {
////			LOG.info("One trust cookies not found" + e.getMessage());
//		}
//	}

	public boolean scrollToElementAndClickByXpath(String element, WebDriver items) {
		JavascriptExecutor js = (JavascriptExecutor) items;
		js.executeScript("arguments[0].scrollIntoView(true);",
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element))));
		return true;
	}

	/** Waiting for element to be visible */
	public boolean waitForElementToAppear(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean scrollToElementBy(By ele) {
		WebElement element = driver.findElement(ele);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		return true;
	}
	public boolean waitForElementToClickable(WebElement element) {
		try {	
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/** Getting element's attribute by "value" */
	public String getAttributeByValue(WebElement element) {
		String textValue = element.getAttribute("value");
		return textValue;
	}

	/** Converting String amount to decimal and limiting to 2 decimal point */
	
//	public void closeOneTrustCookiesIfDisplayedForSauceLabs() {
//		try {
//			if (wait.until(ExpectedConditions.visibilityOf(new LoginPage(driver).oneTrustCookiesCloseIcon)) != null) {
//				new LoginPage(driver).oneTrustCookiesCloseIcon.click();
//				LOG.info("One trust Cookies closed for SauceLabs.");
//			}
//		} catch (Exception e) {
//		}
//	}

	
	
	
}
