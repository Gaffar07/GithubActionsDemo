package web;

import java.io.File;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utils.DriverFactory;
import utils.JsonUtil;
import utils.Utils;


import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.Gson;

import utils.DriverFactory;

public class Locations  extends BasePage{
	
	/**
	 * Log4j Logger.
	 */
	private final Logger LOG = LogManager.getLogger(Locations.class);

	public Locations(WebDriver driver) {
		super(driver);
	}

	static HashMap<String, HashMap<String, List<String>>> testDataMap = null;
	static List<String> timeZone = new ArrayList<>();;
	static final String locationTestDataFilePath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator + "config" + File.separator
			+ "location.json";

	@FindBy(xpath = "//*[@role='option']")
	WebElement commonStoreLocator;
	@FindBy(xpath = "//div[@role='listbox']/div")
	WebElement commonStoreStatus;
	@FindBy(css = "div.location-title")
	WebElement selectedRegionName;
	@FindBy(css = "div.location-title > .clear")
	WebElement clearRegion;

	String stateListXpath = "//*[@role='option']";
	List<WebElement> regionList = new ArrayList<>();

	public void getRegionList() {
		if (waitForElementToAppear(clearRegion)) {
			clearRegion.click();
		}
		waitForElementToAppear(commonStoreLocator);
		regionList = driver.findElements(By.xpath(stateListXpath));
		LOG.info("Region count: " + regionList.size());
	}

	String currState;

	public void getStatewiseStoreStatus() {
		int stateCount = regionList.size();
		for (int i = 1; i <= stateCount; i++) {
			String currStateXpath = "(//*[@role='option'])[" + i + "]";
			WebElement currStateEle = driver.findElement(By.xpath(currStateXpath));
			currState = currStateEle.getText().split("\n")[0];
			timeZone.forEach((data) -> {
				if (testDataMap.get("locationTimeZoneWise").get(data).contains(currState)) {
					scrollToElement(currStateEle);
					waitUntilSpinnerDisappears();
					currStateEle.click();
					LOG.info("Clicked on state " + currState);
					waitUntilSpinnerDisappears();
					this.getStoreStatusForSelectedRegion(currState);
					clearRegion.click();
					LOG.info("Clicked on state clear selected region");
					waitUntilSpinnerDisappears();
				}
			});
		}
	}

	List<List<Object>> listOfClosedStores = new ArrayList<>();
	List<Object> stateListWithClosedStores = new ArrayList<>();

	static List<Object> stateList = new ArrayList<>();
	static List<Object> totalStoreList = new ArrayList<>();
	static List<Object> availableStoreList = new ArrayList<>();
	static List<Object> unavailableStoreList = new ArrayList<>();

	public void getStoreStatusForSelectedRegion(String currStateName) {
		List<WebElement> storeList = driver.findElements(By.xpath("//div[@role='listbox']/div"));
		int currTotalStore = storeList.size();
		int currActiveStore = 0, currInactiveStore = 0;

		List<Object> currUnavailableStores = new ArrayList<>();

		for (int i = 1; i <= currTotalStore; i++) {
			String storeStatusXpath = "(//*[@role='option']/div[4])[" + i + "]";
			String storeNameXpath = "(//*[@role='option']/div[2])[" + i + "]";

			String storeStatus = driver.findElement(By.xpath(storeStatusXpath)).getText();
			String storeName = driver.findElement(By.xpath(storeNameXpath)).getText();

			if (storeStatus.contains("unavailable")) {
				currUnavailableStores.add(storeName);
				currInactiveStore++;
			} else {
				currActiveStore++;
			}
		}

		stateList.add(currStateName);
		totalStoreList.add(currTotalStore);
		availableStoreList.add(currActiveStore);
		unavailableStoreList.add(currInactiveStore);
		LOG.info("Unavailable store size: " + currUnavailableStores.size());
		if (currUnavailableStores.size() > 0) {
			stateListWithClosedStores.add(currState);
			listOfClosedStores.add(currUnavailableStores);
		}
		LOG.info("Total store: " + currTotalStore + ", Active stores: " + currActiveStore + ", Inactive stores: "
				+ currInactiveStore);
	}

	public void generateExtentReport() {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/LocationReport.html");
		extent.attachReporter(spark);

		StoreReportClass storeObject = new StoreReportClass(stateList, totalStoreList, availableStoreList,
				unavailableStoreList);
		extent.createTest("State-wise Store Details").generateLog(Status.PASS,
				MarkupHelper.toTable(storeObject, "table-sm"));

		ClosedStoreClass closedObject = new ClosedStoreClass(stateListWithClosedStores, listOfClosedStores);
		extent.createTest("Closed Stores").generateLog(Status.PASS, MarkupHelper.toTable(closedObject, "table-sm"));
		extent.flush();
	}

	public void preSteupProdRun() {
		readingLocationJsonFile();
		selectTimeZone();
		if (timeZone.isEmpty()) {
			DriverFactory.getDriver().quit();
			LOG.info(currentETime + " - Current time is not eligible for Store status check");
			Assert.assertFalse(true, "Current time does not fall in given range");
		}
		timeZone.forEach((x) -> LOG.info("Selected time zone is:: " + x));
	}

	public void selectTimeZone() {
		testDataMap.get("timeRun").forEach((k, v) -> {
			testDataMap.get("timeRun").get(k).forEach((timeValue) -> {
				if (timeDifferanceCalculator(timeValue)) {
					timeZone.add(k);
				}
			});
		});
	}

	private ZonedDateTime currentETime;

	public boolean timeDifferanceCalculator(String timeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		LocalTime givenTime = LocalTime.parse(timeString, formatter);
		ZonedDateTime currentSystemTime = ZonedDateTime.now(); // "Asia/Kolkata"
		currentETime = currentSystemTime.withZoneSameInstant(ZoneId.of("America/New_York"));
		Duration duration = Duration.between(givenTime, currentETime);
		long differance = duration.toMinutes();
		if (differance > -15 & differance < 15) {
			return true;
		} else {
			return false;
		}

	}

	public void readingLocationJsonFile() {
		LOG.info("Reading the file");
		Gson gson = new Gson();
		JSONObject jsonObject = (JSONObject) JsonUtil.getJsonData(locationTestDataFilePath);
		String jsonString = String.valueOf(jsonObject);
		Type type = new TypeToken<HashMap<String, HashMap<String, List<String>>>>() {
		}.getType();
		testDataMap = gson.fromJson(jsonString, type);
		testDataMap.forEach((key, value) -> {
			System.out.println("Key: " + key + ", Value: " + value);
		});
	}

	public void sendChatMessageToSlack() {
		String composedMessage = "";
		composedMessage += "[!!!]Store Status Information in EST time - " + currentETime + "\n";

		if (totalStoreList.isEmpty()) {
			composedMessage += "Location details was not loaded in app\n";
		} else {
			composedMessage += "Store status for Timezone " + timeZone.toString() + "\n";
			for (Object stateName : stateList) {
				composedMessage += "State: " + stateName + ", Total: " + totalStoreList.get(stateList.indexOf(stateName))
						+ ", Active: " + availableStoreList.get(stateList.indexOf(stateName)) + ", Inactive: "
						+ unavailableStoreList.get(stateList.indexOf(stateName)) + "\n";

				if ((int) unavailableStoreList.get(stateList.indexOf(stateName)) > 0) {
					composedMessage += "Closed Stores: " + listOfClosedStores.get(stateListWithClosedStores.indexOf(stateName)) + "\n";
				}
			}
		}
		Utils.sendSlackChatNotificationUsingBolt(composedMessage);
	}

	public void sendChatMessageToSlackOld() {
		Utils.sendSlackChatNotificationUsingBolt(
				"[!!!]Store Status Information (close/open) in EST time - " + currentETime);
		if (totalStoreList.isEmpty()) {
			Utils.sendSlackChatNotificationUsingBolt("Location details was not loaded in app");
		} else {
			Utils.sendSlackChatNotificationUsingBolt("Running script for Timezone " + timeZone.toString());
			stateList.forEach((stateName) -> {
				Utils.sendSlackChatNotificationUsingBolt(
						"State: " + stateName + ", Total: " + totalStoreList.get(stateList.indexOf(stateName))
								+ ", Active: " + availableStoreList.get(stateList.indexOf(stateName)) + ", Inactive: "
								+ unavailableStoreList.get(stateList.indexOf(stateName)));

				if ((int) unavailableStoreList.get(stateList.indexOf(stateName)) > 0) {
					Utils.sendSlackChatNotificationUsingBolt(
							"Closed Stores: " + listOfClosedStores.get(stateListWithClosedStores.indexOf(stateName)));
				}
			});
		}
	}
}

class StoreReportClass {
	public List<Object> state;
	public List<Object> total;
	public List<Object> available;
	public List<Object> unavailable;

	public StoreReportClass(List<Object> region, List<Object> total, List<Object> available, List<Object> unavailable) {
		this.state = region;
		this.total = total;
		this.available = available;
		this.unavailable = unavailable;
	}
}

class ClosedStoreClass {
	public List<Object> state;
	public List<List<Object>> storesname;

	public ClosedStoreClass(List<Object> closedStateList, List<List<Object>> listOfClosedStores) {
		this.state = closedStateList;
		this.storesname = listOfClosedStores;
	}

}
