package Hooks;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigLoader;
import utils.DriverFactory;

//bs code
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.util.Base64;

public class Hooks {
	
	/** Log4j Logger. */
	private static final Logger LOG = LogManager.getLogger(Hooks.class);
	private static ConfigLoader loader = ConfigLoader.getInstance();
	String appName = ConfigLoader.TEST_APP_NAME;

	public String testTitle, testrailTitle;
	public int runId, tcId, caseId, statusId, testRunStatus;
	public JSONArray runsDetails, testcaseDetails;
	public JSONObject jsonObj;
	public Map<String, String> postheader = new HashMap<String, String>();
	public static String[] testRailId, scriptTitle;
	public Response Runs_id, test_id, status;
	public static final String get_milestones = "/api/v2/get_milestones/1";
	public static final String get_projectruns = "/api/v2/get_runs/1";
	public static final String get_testruns = "/api/v2/get_tests/";
	public static final String post_teststatus = "/api/v2/add_result_for_case/";
	public static final String passed = "{\"status_id\": 1, \"comment\": \"Passed-Result updated by automation scripts\"}";
	public static final String retest = "{\"status_id\": 4, \"comment\": \"Failed-Result updated by automation scripts\"}";
	public int PASS = 1, RETEST = 4, count = 0;
	public String[] multiTcs = new String[10];
	public boolean isFound = false;
	public boolean multiFlag = false;
	public boolean milestoneFlag = false;
	public boolean runFlag = false;
	public static String trUname = loader.getProperty("testrailUname");
	public static String trPwd = loader.getProperty("testrailPwd");
	public static String milestoneId = System.getProperty("milestone", loader.getProperty("milestone"));
	public static String runName = System.getProperty("runname", loader.getProperty("runName"));

	@Before("not @TableTents")
	public void setup(Scenario scenario) throws Exception {
		LOG.info("Thread is :"+String.valueOf(Thread.currentThread().getId()));
		System.out.println("Thread is :"+String.valueOf(Thread.currentThread().getId()));
		testTitle = scenario.getName();
		LOG.info("Starting scenario: " + scenario.getName());
		if (appName.equalsIgnoreCase("web") || appName.equalsIgnoreCase("ssma")) {
			LOG.info("Initializing Web driver");
			DriverFactory.initializeWebDriver();
		}
//		else {
//			LOG.info("Initializing Appium driver");
//			DriverFactory.initializeMobileDriver();
//		}
	}

	@After(order = 0)
	public void quitBrowser() {
		if (appName.equalsIgnoreCase("web") || appName.equalsIgnoreCase("ssma"))
			DriverFactory.tearDown();
		// else
		// 	DriverFactory.quitMobileDriver();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		LOG.info("Test Result: " + scenario.getStatus());

		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath;
			if (appName.equalsIgnoreCase("web"))
				sourcePath = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			else
				sourcePath = ((TakesScreenshot) DriverFactory.getMobileDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			
			
			//trying to check browsrstack stratus 
			
			try {
				SessionId sessionId = ((RemoteWebDriver) DriverFactory.getDriver()).getSessionId();
	            //SessionId sessionId = driver.getSessionId();
	            String status = scenario.isFailed() ? "failed" : "passed";
	            String name = scenario.getName();

	            String username = System.getenv("gaffar_mbBXWM");
	            String accessKey = System.getenv("BSK81M5auB99VarhQE5B");

	            URL url = new URL("https://api.browserstack.com/automate/sessions/" + sessionId + ".json");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setDoOutput(true);
	            conn.setRequestMethod("PUT");

	            String auth = username + ":" + accessKey;
	            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
	            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
	            conn.setRequestProperty("Content-Type", "application/json");

	            String payload = "{\"status\":\"" + status + "\", \"name\":\"" + name + "\"}";
	            try (OutputStream os = conn.getOutputStream()) {
	                os.write(payload.getBytes());
	                os.flush();
	            }

	            conn.getResponseCode(); // Optional: Check response code
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
			
			
			
		
		}
	

	// @AfterAll
	// public static void tearDown() {
	// 	try {
	// 		if(!new Hooks().appName.equalsIgnoreCase("web"))
	// 			DriverFactory.quitMobileDriver();
	// 		LOG.info("Driver session is closed");
	// 	} catch(Exception ex) {
	// 		LOG.info("Could not find driver session to quit");
	// 	}
	// }

	@After(order = 2)
	public void updateTestRailStatus(Scenario scenario) {
		LOG.info("Milestone id is - " + milestoneId);
		LOG.info("Runname  is - " + runName);
		RestAssured.baseURI = loader.getProperty("testrailURL");

		if (milestoneId.isEmpty() || runName.isEmpty()) {
			LOG.error("Either milestoneId or runName is Empty.Please update correct values");
			return;
		}
		// Validate milestone is active in testrail
		Response milestone_id = RestAssured.given().auth().preemptive().basic(trUname, trPwd)
				.queryParam(get_milestones, "").queryParam("is_completed", 0).get();
		LOG.info("Get milestones response is " + milestone_id.getStatusLine());
		jsonObj = new JSONObject(milestone_id.getBody().asString());
		JSONArray milestoneDetails = jsonObj.getJSONArray("milestones");
		for (int i = 0; i < milestoneDetails.length(); i++) {
			if ((milestoneDetails.getJSONObject(i).getInt("id")) == (Integer.parseInt(milestoneId))) {
				milestoneFlag = true;
			}
		}
		if (!milestoneFlag) {
			LOG.error("Given milestoneId is not found on TestRail");
			return;
		} else {
			testTitle = scenario.getName();
			LOG.info("Scenario title - " + testTitle);
			String[] scriptTitle = testTitle.split("-");
			LOG.info("Script id is - " + scriptTitle[0]);
			if (scriptTitle[0].contains("&")) {
				multiFlag = true;
				multiTcs = scriptTitle[0].split("&");
			} else {
				multiTcs[0] = scriptTitle[0];
			}
//			LOG.info("Multi tcs length is - " + multiTcs.length);
			LOG.info("Scenario status - " + scenario.getStatus());
			postheader.put("Content-Type", "application/json");

			// Step1-https://341600.testrail.io/index.php?/api/v2/get_runs/1&milestone_id=222;
			Response Runs_id = RestAssured.given().auth().preemptive().basic(trUname, trPwd)
					.queryParam(get_projectruns, "").queryParam("milestone_id", milestoneId).get();
			LOG.info("Get runs response is " + Runs_id.getStatusLine());
			jsonObj = new JSONObject(Runs_id.getBody().asString());
			JSONArray runsDetails = jsonObj.getJSONArray("runs");
			for (int i = 0; i < runsDetails.length(); i++) {
				String actualRunName = runsDetails.getJSONObject(i).getString("name");
				if (actualRunName.equals(runName)) {
					runId = runsDetails.getJSONObject(i).getInt("id");
					LOG.info("Run id is - " + runId);
					runFlag = true;
				}
			}
			Assert.assertTrue(runFlag, runName + ": does not exist in milestone");

			// Step2-https://341600.testrail.io/index.php?/api/v2/get_tests/2605
			while (count < 3) {
				if (count == 0)
					test_id = RestAssured.given().auth().preemptive().basic(trUname, trPwd)
							.queryParam(get_testruns + runId, "").get();
				else if (count == 1)
					test_id = RestAssured.given().auth().preemptive().basic(trUname, trPwd)
							.queryParam(get_testruns + runId, "").queryParam("limit", "250").queryParam("offset", "250")
							.get();
				else
					test_id = RestAssured.given().auth().preemptive().basic(trUname, trPwd)
							.queryParam(get_testruns + runId, "").queryParam("limit", "250").queryParam("offset", "500")
							.when().get();

				LOG.info("Get tests response is " + test_id.getStatusLine());
				jsonObj = new JSONObject(test_id.getBody().asString());
				testcaseDetails = jsonObj.getJSONArray("tests");
				if (multiFlag) {
					for (int k = 0; k < multiTcs.length; k++) {
						for (int i = 0; i < testcaseDetails.length(); i++) {
							testrailTitle = testcaseDetails.getJSONObject(i).getString("title");
							testRailId = testrailTitle.split("-");
//							LOG.info("TestRail id is" + testRailId[0]);
							if (testRailId[0].equalsIgnoreCase(multiTcs[k])) {
								getTestCaseId(scenario, i);
								break;
							}
						}
						count++;
						if (!isFound & count == 1) {
							LOG.info(scriptTitle[0] + ": Could not find TC on testrail in 0-250 response limit");
						}
						if (!isFound & count == 2) {
							LOG.info(scriptTitle[0] + ": Could not find TC on testrail in 250-500 response limit");
						}
						if (!isFound & count == 3) {
							LOG.info(scriptTitle[0] + ": Could not find TC on testrail in 500-750 response limit");
						}
					}
				} else {
					for (int i = 0; i < testcaseDetails.length(); i++) {
						testrailTitle = testcaseDetails.getJSONObject(i).getString("title");
						testRailId = testrailTitle.split("-");
						if (testRailId[0].equalsIgnoreCase(scriptTitle[0])) {
							getTestCaseId(scenario, i);
							break;
						}
					}
					count++;
					if (!isFound & count == 1) {
						LOG.info(scriptTitle[0] + ": Could not find TC on testrail in 0-250 response limit");

					}
					if (!isFound & count == 2) {
						LOG.info(scriptTitle[0] + ": Could not find TC on testrail in 250-500 response limit");
					}
					if (!isFound & count == 3) {
						LOG.info(scriptTitle[0] + ": Could not find TC on testrail in 500-750 response limit");
					}
				}
			}
		}
	}

	public void getTestCaseId(Scenario scenario, int i) {
		statusId = testcaseDetails.getJSONObject(i).getInt("status_id");
		tcId = testcaseDetails.getJSONObject(i).getInt("id");
		caseId = testcaseDetails.getJSONObject(i).getInt("case_id");
		LOG.info("Testcase id is - " + tcId);
		LOG.info("Case id is - " + caseId);
		LOG.info("Before post, status id is - " + statusId);
		isFound = true;
		count++;
		if (isFound && !(statusId == 1)) {
			markScenario(scenario);
		}
	}

//	Step3-https://341600.testrail.io/index.php?/api/v2/add_result_for_case/2605/36899-POST     //		runid/caseid
	public void markScenario(Scenario scenario) {
		if (!scenario.isFailed()) {
			status = RestAssured.given().auth().preemptive().basic(trUname, trPwd).headers(postheader).body(passed)
					.queryParam(post_teststatus + runId + "/" + caseId).post();
			LOG.info("Mark status response is " + status.getStatusLine());
			jsonObj = new JSONObject(status.getBody().asString());
			Assert.assertTrue(jsonObj.getInt("status_id") == PASS);

		} else {
			status = RestAssured.given().auth().preemptive().basic(trUname, trPwd).headers(postheader).body(retest)
					.queryParam(post_teststatus + runId + "/" + caseId).post();
			LOG.info("Mark status response is " + status.getStatusLine());
			jsonObj = new JSONObject(status.getBody().asString());
			Assert.assertTrue(jsonObj.getInt("status_id") == RETEST);
		}
	}


}
