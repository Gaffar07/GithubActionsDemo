package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import groovyjarjarantlr4.v4.parse.ANTLRParser.elementOptions_return;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	// public static WebDriver driver = null;
	protected static ConfigLoader loader = ConfigLoader.getInstance();

	/** Log4j Logger. */
	private static final Logger LOG = LogManager.getLogger(DriverFactory.class);

	public static WebDriver initializeWebDriver() throws IOException {
		String executionEnv = System.getProperty("executionEnv", loader.getProperty("executionEnv"));
		String platformName = System.getProperty("platformName", loader.getProperty("platformName"));
		String browserName = System.getProperty("browserName", loader.getBrowser());
		String browserVersion = System.getProperty("browserVersion", loader.getProperty("browserVersion"));
		switch (executionEnv) {
			case "SauceLab":
				URL url;

				String SAUCE_REMOTE_URL = "https://" + loader.getUsername() + ":" + loader.getAccessKey()
						+ loader.getUrl();
				LOG.info("Connecting to " + SAUCE_REMOTE_URL);
				if (browserName.equals("chrome")) {
					url = new URL(SAUCE_REMOTE_URL);
/*					DesiredCapabilities caps = DesiredCapabilities.chrome();
					caps.setCapability("platformName", loader.getPlatformName());
					caps.setCapability("browserName", browserName);
					caps.setCapability("browserVersion", browserVersion);
					caps.setCapability("name", "ShakeShack");
					caps.setCapability("extendedDebugging", "true");
					caps.setCapability("screen-resolution", "1280x1024");
					caps.setCapability("sessionCreationRetry", loader.getProperty("sessionCreationRetry"));
					driver.set(new RemoteWebDriver(url, caps));*/

					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setCapability("platformName", platformName);
					chromeOptions.setCapability("browserVersion", browserVersion);
					// chromeOptions.setHeadless(true);

					MutableCapabilities sauceOptions = new MutableCapabilities();
					sauceOptions.setCapability("name", "ACME Test");
					sauceOptions.setCapability("build", "web-script-execution");
					sauceOptions.setCapability("screen-resolution", "1280x1024");
					chromeOptions.setCapability("sauce:options", sauceOptions);
					driver.set(new RemoteWebDriver(url, chromeOptions));
					
					
				} else {
					SafariOptions safariOptions = new SafariOptions();
					safariOptions.setCapability("platformName", "macOS 13");
					safariOptions.setCapability("browserVersion", "latest");

					MutableCapabilities sauceOptions = new MutableCapabilities();
					sauceOptions.setCapability("build", "Mac-Safari-Test");
					sauceOptions.setCapability("name", "Test on macOS+Safari");
					sauceOptions.setCapability("screen-resolution", "1280x960");
					// sauceOptions.setCapability("sauce:geolocation", "40.7128,-74.0060");  // not working to bypass location alert
					// sauceOptions.setCapability("timeZone", "Los_Angeles");
					safariOptions.setCapability("sauce:options", sauceOptions);

					driver.set(new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), safariOptions));
				}
				break;

			case "Local":
				if (browserName.equals("chrome")) {
					ChromeOptions options = new ChromeOptions();
					//options.setBinary("/usr/bin/google-chrome"); // critical in Docker 
					if(loader.getProperty("docker").equals("true")|| loader.getProperty("jenkins").equals("true")){
					
						options.addArguments("--headless=new"); 
						options.addArguments("--no-sandbox"); 
						options.addArguments("--disable-dev-shm-usage"); 
						options.addArguments("--disable-gpu"); 
						options.addArguments("--remote-allow-origins=*"); 
						//options.setExperimentalOption("prefs", prefs);
						
						
					}
					
					WebDriverManager.chromedriver().setup();
//					String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//							+ File.separator + "resources" + File.separator + "chromedriver.exe";
					if (platformName.contains("mac"))
						System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
					else
//						System.setProperty("webdriver.chrome.driver", path);
//					Map<String, Object> prefs = new HashMap<String, Object>();

					// Use File.separator as it will work on any OS
					//prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "target");

					// Adding cpabilities to ChromeOptions
					

					
					
					// options.setHeadless(true);

					System.out.println("Initializing Chrome Driver");
					driver.set(new ChromeDriver(options));
					// driver = new ChromeDriver(options);
					// } else if (browserName.equals("firefox")) {
					// WebDriverManager.firefoxdriver().setup();
					// driver.set(new FirefoxDriver());
					// } else if (browserName.equals("edge")) {
					// WebDriverManager.iedriver().setup();
					// driver.set(new EdgeDriver());
				} else {
					System.out.println("Initializing Safari Driver");
					// driver = new SafariDriver();
					driver.set(new SafariDriver());
				}
				break;
		}
		String testUrl = System.getProperty("url", loader.getProperty("url"));
		if(browserName.equals("safari"))
			driver.get().get(testUrl);  // failing if we add it somewhere else for SafariDriver
		driver.get().manage().window().maximize();
		driver.get().manage().deleteAllCookies();
		driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver.get();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void tearDown() {
		getDriver().quit();
		driver.remove();
	}

	/** Appium Driver Section */
	private final static ThreadLocal<AppiumDriver<MobileElement>> mobileDriver = new ThreadLocal<>();

	public static synchronized ThreadLocal<AppiumDriver<MobileElement>> initializeMobileDriver() {
		ConfigLoader loader = ConfigLoader.getInstance();
		try {
			// String browser = loader.getExecutionEnv();
			String executionEnv = System.getProperty("executionEnv", loader.getProperty("executionEnv"));
			String app = ConfigLoader.TEST_APP_NAME;
			switch (executionEnv) {
				case "SauceLab": {
					URL url;

					String SAUCE_REMOTE_URL = "https://" + loader.getUsername() + ":" + loader.getAccessKey()
							+ loader.getUrl();
					LOG.info("Connecting to " + SAUCE_REMOTE_URL);
					url = new URL(SAUCE_REMOTE_URL);

					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability("platformName", loader.getPlatformName());
					caps.setCapability("platformVersion", loader.getPlatformName());
					caps.setCapability("deviceName", loader.getDeviceName());
					if (app.equalsIgnoreCase("mobileweb")) {
						caps.setCapability("appPackage", "com.android.chrome");
						caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
					} else
						caps.setCapability("app", loader.getApp());
					caps.setCapability("fullReset", true);
					caps.setCapability("idleTimeout", "90");
					caps.setCapability("newCommandTimeout", "90");
					caps.setCapability("sessionCreationRetry", loader.getProperty("sessionCreationRetry"));

					HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
					sauceOptions.put("appiumVersion", "latest");
					sauceOptions.put("vitals", true);
					sauceOptions.put("name", "ShakeShack-"+app);
					sauceOptions.put("build", app+"-script-execution");
					caps.setCapability("sauce:options", sauceOptions);
					if (app.equalsIgnoreCase("ios") || app.equalsIgnoreCase("kiosk")) {
						caps.setCapability("appium:automationName", "xcuitest");
						mobileDriver.set(new IOSDriver<>(url, caps));
					} else {
						caps.setCapability("appium:automationName", "UiAutomator2");
						mobileDriver.set(new AndroidDriver<>(url, caps));
					}
				}
					break;
				case "Local": {
					String isRealDevice = loader.getProperty("iosRealDevice");
					String appFileName = null;
					String localHost = "http://127.0.0.1:4723";
					if (app.equalsIgnoreCase("iOS") && isRealDevice.contains("false"))
						appFileName = "iosSimulator.app";
					else if (app.equalsIgnoreCase("iOS") && isRealDevice.contains("true"))
						appFileName = "iosRealDevice.ipa";
					else
						appFileName = "android.apk";
					String pathToApk = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
							+ File.separator + "resources" + File.separator + "Apk" + File.separator + appFileName;
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability("platformName", loader.getPlatformName());
					caps.setCapability("platformVersion", loader.getSimulatorPlatformVersion());
					caps.setCapability("deviceName", loader.getSimulatorDeviceName());
					if (app.equalsIgnoreCase("ios") && isRealDevice.contains("false")) {
						caps.setCapability("appium:udid", loader.getProperty("simulatorUDID"));
					} else if (app.equalsIgnoreCase("ios") && isRealDevice.contains("true")) {
						caps.setCapability("appium:udid", loader.getProperty("realDeviceUDID"));
					} else
						caps.setCapability("appium:automationName", "UiAutomator2");
					if (app.equalsIgnoreCase("mobileweb")) {
						caps.setCapability("appPackage", "com.android.chrome");
						caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
					} else{
//						caps.setCapability("app", pathToApk);
						 caps.setCapability("appPackage", "com.shakeshack.android.nonprod");
						 caps.setCapability("appActivity", "com.shakeshack.android.application.MainActivity");
					}
					caps.setCapability("fullReset", "false");
					if (app.equalsIgnoreCase("ios") || app.equalsIgnoreCase("kiosk")) {
						caps.setCapability("appium:automationName", "xcuitest");
						mobileDriver.set(new IOSDriver<>(new URL(localHost), caps));
					} else {
						caps.setCapability("appium:automationName", "UiAutomator2");
						mobileDriver.set(new AndroidDriver<>(new URL(localHost), caps));
					}
					break;
				}
			}
		} catch (Exception e) {
			LOG.info("Driver initialization failed: " + e.getMessage());
		} finally {
			getMobileDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return mobileDriver;
	}

	public static AppiumDriver<MobileElement> getMobileDriver() {
		return mobileDriver.get();
	}

	public static void quitMobileDriver() {
		getMobileDriver().quit();
		mobileDriver.remove();
	}

}
