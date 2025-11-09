package utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.common.ApiRequest;

public class ConfigLoader {
	
	private final Properties properties;
	private static ConfigLoader configLoader;
	private static String channelName;
	public static String TEST_APP_NAME;
	public static String TEST_ENV;

	private ConfigLoader(String testConfigFileName) { // no other class will be able to instantiate this class,
		// singleton class
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + testConfigFileName
				+ ".properties";
		properties = propertyUtils.propertyLoader(filePath);
		
	}

	/**
	 * Handling test app name from test-config.yml file
	 *
	 * @Returns env name as web/android/ios/kiosk
	 */
	public static String getTestTEST_APP_NAME() {
		String testConfigPath = System.getProperty("user.dir") + File.separator + "test-config.yml";
		try {
			Scanner inFile = new Scanner(new File(testConfigPath));
			while (inFile.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile.nextLine());
				String str = sb.toString();
				if (str.contains("app")) {
					TEST_APP_NAME = str.split("=")[1].trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return TEST_APP_NAME;
	}

	/**
	 * Handling test environment name from YAML file
	 *
	 * @Returns env name as dev/stg/stg-green
	 */
	public static String getTestConfigFileName() {
		String ymlFilePath = System.getProperty("user.dir") + File.separator + "test-config.yml";
		try {
			Scanner inFile = new Scanner(new File(ymlFilePath));
			while (inFile.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile.nextLine());
				String str = sb.toString();
				if (str.contains("test_env")) {
					TEST_ENV = str.split("=")[1].trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TEST_ENV;
	}

	/**
	 * creating or getting the ConfigLoader instance, it points to the config file
	 * related to test env in Pipeline
	 */
	public static ConfigLoader getInstance() {
		TEST_APP_NAME = System.getProperty("app", getTestTEST_APP_NAME()).toLowerCase();
		TEST_ENV = System.getProperty("testenv", getTestConfigFileName());
		if (configLoader == null) {
			// configLoader = new ConfigLoader(TEST_APP_NAME + File.separator + TEST_ENV);
			configLoader = new ConfigLoader(TEST_ENV);
		}
		return configLoader;
	}

	public String getStoreName() {
		String prop = System.getProperty("store", properties.getProperty("storeName"));
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property storeName is not specified in the config file.");
	}

	public String getProductName() {
		String prop = System.getProperty("product", properties.getProperty("productName"));
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property product is not specified in the config file.");
	}

	public String getAlternateProductName() {
		String prop = System.getProperty("alternateproduct", properties.getProperty("productName1"));
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property productName is not specified in the config file.");
	}

	public String getBrowser() {
		String prop = properties.getProperty("browserName", "chrome");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property browser is not specified in the config file.");
	}

	public String getExecutionEnv() {
		String prop = properties.getProperty("executionEnv", "SauceLabs");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property executionEnv is not specified in the config file.");
	}

	public String getUsername() {
		String prop = properties.getProperty("sauceLabs_username", "shahidhussain");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property username is not specified in the config file.");
	}

	public String getAccessKey() {
		String prop = properties.getProperty("sauceLabs_accessKey", "d564f03a-6a2d-4e95-acda-551772352b3d");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property accessKey is not specified in the config file.");
	}

	public String getUrl() {
		String prop = properties.getProperty("sauceLabs_url", "@hub-cloud.browserstack.com/wd/hub");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property browserUrl is not specified in the config file.");
	}

	public String getPlatformName() {
		String prop = null;
		if (TEST_APP_NAME.equalsIgnoreCase("web") || TEST_APP_NAME.equalsIgnoreCase("ssma"))
			prop = properties.getProperty("platformName", "Windows 11");
		else if (TEST_APP_NAME.equalsIgnoreCase("android") || TEST_APP_NAME.equalsIgnoreCase("mobileweb"))
			prop = properties.getProperty("androidPlatformName", "android");
		else
			prop = properties.getProperty("iOSPlatformName", "iOS");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property platformName is not specified in the config file.");
	}

	public String getPlatformVersion() {
		String prop = null;
		if (TEST_APP_NAME.equalsIgnoreCase("web") || TEST_APP_NAME.equalsIgnoreCase("ssma"))
			prop = properties.getProperty("platformVersion", "102");
		else if (TEST_APP_NAME.equalsIgnoreCase("kiosk"))
			prop = properties.getProperty("kioskPlatformVersion", "16.*");
		else if (TEST_APP_NAME.equalsIgnoreCase("ios"))
			prop = properties.getProperty("iOSPlatformVersion", "16.*");
		else
			prop = properties.getProperty("androidPlatformVersion", "14.*");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property platformVersion is not specified in the config file.");
	}

	public String getDeviceName() {
		String prop = null;
		if (TEST_APP_NAME.equalsIgnoreCase("kiosk"))
			prop = properties.getProperty("kioskDeviceName", "iPad.*");
		else if (TEST_APP_NAME.equalsIgnoreCase("ios"))
			prop = properties.getProperty("iOSDeviceName", "iPhone.*");
		else
			prop = properties.getProperty("androidDeviceName", "Google_Pixel_8_shakeshack_us1");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property deviceName is not specified in the config file.");
	}

	public String readAppDetailsAndGetFileIdFromSauceLabs() {
		try {
			String appDescription = System.getProperty("appdescription");
			String appVersion = System.getProperty("appversion");
			if (appVersion == null || appDescription.length() == 0 || appVersion.length() == 0)
				return null;
			return new ApiRequest().getFileIdFromSauceLabs(TEST_APP_NAME.toLowerCase(), appDescription, appVersion);
		} catch (Exception e) {
			return null;
		}
	}

	public String getApp() {
		String prop;
		// reading file id from sacelabs when execution is triggered through ci/cd
		// pipeline
		prop = readAppDetailsAndGetFileIdFromSauceLabs();
		if (prop != null)
			return "storage:" + prop;

		try {
			// using file id supplied as env variables through mvn command by manual trigger
			String buildid = System.getProperty("buildid");
			if (buildid.length() != 0)
				return "storage:" + buildid;
		} catch (Exception e) {
			// reading default file id from properties file if file id is not supplied as
			// env variables
			if (TEST_APP_NAME.equalsIgnoreCase("kiosk"))
				prop = "storage:" + properties.getProperty("kioskbuildid");
			else if (TEST_APP_NAME.equalsIgnoreCase("ios"))
			prop = "storage:" + properties.getProperty("iosbuildid");
			else
				prop = "storage:" + properties.getProperty("androidbuildid");
		}

		return prop;
	}
	
	

	public String getKioskUsername() {
		if (getStoreName().equalsIgnoreCase("DV")) 
			return "kiosk-test-03";
		else
			return "kiosk-test-dtl";
		// String prop = properties.getProperty("kiosk_username", "kiosk-test-03");
		// if (prop != null)
		// 	return prop;
		// else
		// 	throw new RuntimeException("property kiosk_username is not specified in the config file.");
	}

	public String getKioskPassword() {
		String prop = properties.getProperty("kiosk_password", "test");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property kiosk_password is not specified in the config file.");
	}

	public String getShakeShackEmailId() {
		String prop = properties.getProperty("shakeshack_email", "dramachandiran@shakeshack.com");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property shakeshack_email is not specified in the config file.");
	}

	public String getAppSuiteUnit() {
		String prop = properties.getProperty("AppSuiteUnit", "105");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property AppSuiteUnit is not specified in the config file.");
	}

	public String getCVV() {
		String prop = properties.getProperty("cvv", "111");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property cvv is not specified in the config.properties file.");
	}

	public String getProperty(String value) {
		String prop = properties.getProperty(value);
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property " + value + " is not specified in the config file.");
	}

	public void setProperty(String status, String value) {
		properties.setProperty(status, value);
	}

	public String getRandomEmail() {
		return new Utils().generateRandomEmail();
	}

	public String getRandomEmail(String prefix) {
		return new Utils().generateRandomEmail(prefix);
	}

	public String getRandomName() {
		return new Utils().generateRandomName();
	}

	public String getCompoundFirstName() {
		String prop = properties.getProperty("compound_firstname", "minds");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property compound firstname is not specified in the config.properties file.");
	}

	public String getCompoundLastName() {
		String prop = properties.getProperty("compound_lastname", "minds");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property compound firstname is not specified in the config.properties file.");
	}

	/**
	 * Handling channel name from YAML file
	 *
	 * @Returns channel name as IOS/Android/Web
	 */
	public static String getChannelName() {
		String ymlFilePath = System.getProperty("user.dir") + File.separator + "test-config.yml";
		try {
			Scanner inFile = new Scanner(new File(ymlFilePath));
			while (inFile.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile.nextLine());
				String str = sb.toString();
				if (str.contains("channel")) {
					channelName = str.split("=")[1].trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channelName;
	}

	public static String setChannelName(String newChannelName) {
		String ymlFilePath = System.getProperty("user.dir") + File.separator + "test-config.yml";
		try {
			Scanner inFile = new Scanner(new File(ymlFilePath));
			while (inFile.hasNext()) {
				StringBuilder sb = new StringBuilder();
				sb.append(inFile.nextLine());
				String str = sb.toString();
				if (str.contains("channel")) {
					channelName = str.split("=")[1].trim();
					str.replaceAll(channelName, newChannelName);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channelName;
	}

	public String getSimulatorPlatformVersion() {
		String isRealDevice = properties.getProperty("iosRealDevice");
		String prop = null;
		if (TEST_APP_NAME.equalsIgnoreCase("ios") && isRealDevice.contains("false"))
			prop = properties.getProperty("simulatorPlatformVersion", "16.*");
		else if (TEST_APP_NAME.equalsIgnoreCase("ios") && isRealDevice.contains("true"))
			prop = properties.getProperty("realDevicePlatformVersion", "16.*");
		else
			prop = properties.getProperty("emulatorPlatformVersion", "11.0");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property platformVersion is not specified in the config file.");
	}

	public String getSimulatorDeviceName() {
		String isRealDevice = properties.getProperty("iosRealDevice");
		String prop = null;
		if (TEST_APP_NAME.equalsIgnoreCase("ios") && isRealDevice.contains("false"))
			prop = properties.getProperty("simulatorName", "iPhone.*");
		else if (TEST_APP_NAME.equalsIgnoreCase("ios") && isRealDevice.contains("true"))
			prop = properties.getProperty("realDeviceName", "iPhone.*");
		else
			prop = properties.getProperty("emulatorName", "pixel_3a_xl");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property deviceName is not specified in the config file.");
	}

}
