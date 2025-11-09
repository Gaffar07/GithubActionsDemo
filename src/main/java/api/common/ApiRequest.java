package api.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.Gson;

import api.pojo.acmetest.acmetestresponse;
import api.pojo.acmetest.registerUser;
import api.pojo.getUser.getUserResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import utils.ConfigLoader;
import utils.LogHandler;

public class ApiRequest extends ApiBase{
	private static final LogHandler LOG = new LogHandler(ApiRequest.class);
    public static volatile String oloid;
    static volatile String FirstName;
    public static volatile int retryCounter = 0;
    public static volatile int retryCounterMax = 3;
    public static volatile double total, subtotal, totalAmountCouponCode, tip, giftCardBalance, refundAmount, ActualRefundAmount, ActualRefundAmountGC;
   // public static volatile String newUserStartingString = loader.getProperty("newUserStartingString");
    public static volatile String giftcardNumber;
    static Gson gson = new Gson();
    static volatile long retryTime=3000;
    static volatile RequestSpecification getAuthorizationClientIdClientSecret, getAuthorization;
    RequestAppSpec requestAppSpec=new RequestAppSpec();
    ResponseAppSpec responseAppSpec=new ResponseAppSpec();
    
    
    public ApiRequest() {
    	super();
    }

    public synchronized void assertionResult(String field, String assertType, String actual, String expected, String message) {
        switch (assertType) {
            case "true":
                double actualValue = Double.parseDouble(actual);
                double expectedValue = Double.parseDouble(expected);
                if (actualValue > expectedValue) {
                    LOG.testLog("AssertPass: " + field + "actual value is " + actualValue);
                    Assert.assertTrue(true);
                } else {
                    LOG.testLog("AssertFail: " + field + "actual value is not " + actualValue);
                    if (retryCounter < retryCounterMax) {
                        throw new IllegalStateException();
                    } else {
                        LOG.testLog("AssertFail: " + message);
                        Assert.fail();
                    }
                }
                break;
            case "notnull":
                if (actual.isEmpty()) {
                    if (retryCounter < retryCounterMax) {
                        throw new IllegalStateException();
                    } else {
                        LOG.testLog("AssertFail: " + field + " Message is empty");
                        Assert.fail();
                    }
                } else {
                    LOG.testLog("AssertPass: " + field + " is not empty");
                    Assert.assertTrue(true);
                }
                break;
            case "equal":
                if (actual.equalsIgnoreCase(expected)) {
                    LOG.testLog("AssertPass: " + field + " :: " + actual + " response is matched with expected " + expected);
                    Assert.assertTrue(true);
                } else {
                    LOG.testLog("AssertFail: " + field + " :: " + actual + " response is not matched with expected " + expected);
                    if (retryCounter < retryCounterMax) {
                        throw new IllegalStateException();
                    } else {
                        Assert.fail();
                    }
                }
                break;
            case "contain":
                if (actual.contains(expected)) {
                    LOG.testLog("AssertPass: " + field + " :: " + actual + " response contains as expected : " + expected);
                    Assert.assertTrue(true);
                } else {
                    LOG.testLog("AssertFail: " + field + " :: " + actual + " response does not contain as expected : " + expected);
                    if (retryCounter < retryCounterMax) {
                        throw new IllegalStateException();
                    } else {
                        Assert.fail();
                    }
                }
                break;
            case "fail":
                LOG.testLog("AssertFail: " + field + " : " + message);
                Assert.fail();
                break;
            case "pass":
                LOG.testLog("AssertPass: " + field + " : " + message);
                Assert.assertTrue(true);
                break;
            default:
                break;
        }
//        } catch  (IllegalStateException|NullPointerException|AssertionError e) {
//            LOG.testLog("AssertFail: " + field + " Message is NULL");
//            Assert.fail();
//        }
    }

    private synchronized static void printRequestLog(RequestSpecification requestSpecification, Response response) {
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
    }

    private synchronized static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        LOG.testLog("Request Details:: ");
        LOG.testLog("Endpoint URL is :" + queryableRequestSpecification.getBaseUri() + queryableRequestSpecification.getBasePath());
        LOG.testLog("Method is :" + queryableRequestSpecification.getMethod());
        LOG.testLog("Query Param is :" + queryableRequestSpecification.getQueryParams());
        LogHandler.logHeaders(queryableRequestSpecification.getHeaders().asList());
        LOG.testLog("Request body is :");
        String requestBody = queryableRequestSpecification.getBody();
        if (requestBody == null)
            LOG.testLog("There is no Request Body");
        else
            LogHandler.logBody(requestBody);
    }

    private synchronized static void printResponseLogInReport(Response response) {
        LOG.testLog("Response Details:: ");
        LOG.testLog("Response status is " + response.getStatusCode());
        LOG.testLog("Response Headers are ");
        LogHandler.logHeaders(response.getHeaders().asList());
        LOG.testLog("Response body is ");
        LogHandler.logBody(response.getBody().prettyPrint());
    }
    
    private synchronized long waitingTime(int retryCounter) {
        int threadNum;
        try {
            threadNum = Integer.parseInt(Thread.currentThread().getName().split("-")[2]);
        } catch (Exception e) {
            threadNum = 1;
        }
        LOG.testLog("waiting time is " + threadNum * retryTime + retryCounter * 100);
        return threadNum * retryTime + retryCounter * 100L;
    }



    
    
	 public synchronized void getBearerTokenAdmin() {
	        LOG.testLog("!! Getting the Bearer Token !!");
	        try {
	        	registerUser user=new registerUser(loader.getProperty("Email"), loader.getProperty("password"));
	            requestSpecification = RestAssured.given()
	                    .spec(requestAppSpec.getBearerTokenSpec())
	                    .when().body(user);
	            response = requestSpecification.post();
	            System.out.println(response.getStatusCode());
	            acmetestresponse acmetestresponse = response.then()
	                    .spec(responseAppSpec.commonResponseSpec()).extract().response().as(acmetestresponse.class);
	            printRequestLog(requestSpecification, response);
	            accessToken = acmetestresponse.getToken();
	            assertionResult("accessToken", "notnull", accessToken, "", "");
	            LOG.testLog("Info: Access token is " + accessToken);
	        } catch (IllegalStateException | NullPointerException | AssertionError e) {
	            try {
	                if (retryCounter < retryCounterMax) {
	                    retryCounter++;
	                    retryTime = waitingTime(retryCounter);
	                    Thread.sleep(retryTime);
	                    getBearerTokenAdmin();
	                } else {
	                    Assert.fail("AssertFail :: After retrying maxiumum time getting the assertion error");
	                }
	            } catch (InterruptedException ex) {
	                throw new RuntimeException(ex);
	            }
	        }
	    }
	 
	 
	 public synchronized void getUser() {
	        LOG.testLog("!! Getting the Session ID !! ");
	        try {
	            requestSpecification = RestAssured.given()
	                    .spec(requestAppSpec.getUserSpec(loader.getProperty("createSession"))).when();
	            response = requestSpecification.post();
	            getUserResponse getuserresponse = response.then()
	                    .spec(responseAppSpec.commonResponseSpec()).extract().response().as(getUserResponse.class);
	            FirstName = getuserresponse.getData().get(0).getFirstName();
	            System.out.println("token fetched from api request class is : "+accessToken);
	            messageStatusCode = String.valueOf(response.getStatusCode());
	           // checkRetryFlag(messageStatusCode);
	        } catch (IllegalStateException | NullPointerException | AssertionError e) {
	            try {
	                LOG.testLog("Retrying :" + retryCounter);
	                if (retryCounter < retryCounterMax) {
	                    retryCounter++;
	                    retryTime = waitingTime(retryCounter);
	                    Thread.sleep(retryTime);
	                    getUser();
	                } else {
	                    Assert.fail("AssertFail :: After retrying maxiumum time getting the assertion error");
	                }
	            } catch (InterruptedException ex) {
	                throw new RuntimeException(ex);
	            }
	        }
	        LOG.testLog("Info: sessionId is " + FirstName);
	        printRequestLog(requestSpecification, response);
	    }
	 
	 public synchronized String getFileIdFromSauceLabs(String channel, String appDescription, String appVersion) {
	        LOG.testLog("!! Getting file id from SauceLabs ::" + channel + " -> " + appDescription + " -> " + appVersion + " !!");
	        Map<String, List<String>> requestMap = new HashMap<>();
	        requestMap.put("kind", List.of(channel.toLowerCase()));
	        requestMap.put("q", List.of(appDescription, appVersion));
	        String sauceLabResponse = RestAssured.given().spec(requestAppSpec.sauceLabsRequestSpec(requestMap)).when().get()
	                .then().extract().response().asString();
	        JSONObject response = new JSONObject(sauceLabResponse);
	        JSONArray items = new JSONArray(response.getJSONArray("items"));

	        String fileid = null;
	        for (int i = 0; i < items.length(); i++) {
	            JSONObject item = items.getJSONObject(i);
	            if (((String) item.get("description")).equalsIgnoreCase(appDescription)) {
	                JSONObject metadata = (JSONObject) item.get("metadata");
	                if ((((String) metadata.get("version")).equalsIgnoreCase(appVersion))) {
	                    fileid = (String) item.get("id");
	                    break;
	                }
	            }
	        }
	        System.out.println("Fetched file id from SauceLabs: " + fileid);
	        loader.setProperty("buildid", fileid);
	        return fileid;
	    }


}
