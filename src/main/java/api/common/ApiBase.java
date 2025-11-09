package api.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.specification.RequestSpecification;
import utils.ConfigLoader;

//import android.BasePage;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.json.Cookie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.ConfigLoader;
import utils.JsonUtil;
import utils.LogHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;


public class ApiBase {
	
	private static final LogHandler LOG = new LogHandler(ApiBase.class);
    static volatile ConfigLoader loader;
    static volatile String channel;
    static volatile SoftAssert softAssert = new SoftAssert();
    static volatile JsonUtil jsonUtil = new JsonUtil();
    static volatile JSONObject jsonObject = null;
    static final String apiTestDataFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator + "config" + File.separator + "api-testdata.json";
    static final String apiFilePath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator;

    static {
        loader = ConfigLoader.getInstance();
        channel = ConfigLoader.getChannelName();
    }

    static volatile CookieFilter filter;
    static volatile Response response;
    static volatile RequestSpecification requestSpecification;
    static volatile RequestSpecification requestSpecificationModified;
    static volatile String messageStatusCode;
    static volatile String messageResponse;
    static volatile String accessToken="", auth0AccessToken="", idToken="", decodeToken="";
    static volatile String auth0UserName="", auth0Password="", state="", passwordState="", code="";

    static volatile String uberAccessToken;



    public synchronized String getTestData(String input) throws IOException {
        LOG.testLog("Reading the test data file from ::" + apiTestDataFilePath);
        System.out.println(apiTestDataFilePath);
        JSONObject jsonObject = (JSONObject) JsonUtil.getJsonData(apiTestDataFilePath).get(input);
        String testData = String.valueOf(jsonObject);
        LOG.testLog(testData);
        return testData;
    }

    public synchronized String setValue(String inputFeildValue) {
        switch (inputFeildValue) {
            case "":
                return "";
            case "NULL":
                return null;
            case "BLANK":
                return "";
            default:
                return inputFeildValue;
        }
    }

    public synchronized String getModifiedTestData(String input, String inputFeildName, String inputFeildValue) throws IOException {
        LOG.testLog("Reading the test data file from ::" + apiTestDataFilePath);
        JSONObject jsonObject = (JSONObject) JsonUtil.getJsonData(apiTestDataFilePath).get(input);
        jsonObject.put(inputFeildName, setValue(inputFeildValue));
        String testData = String.valueOf(jsonObject);
        LOG.testLog(testData);
        return testData;
    }

    public synchronized String createFile() {
        String text = "Welcome to API Testing \nHappy Testing!";
        DateFormat date_format_obj = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
        Date date_obj = new Date();
        LOG.testLog("Current date and time: " + date_format_obj.format(date_obj));
        String date = date_format_obj.format(date_obj);

        String fileName = apiFilePath + "APItestfile_" + date + ".docx";
        Path filePath = Path.of(fileName);
        try {
            Files.writeString(filePath, text);
            LOG.testLog("File Content ::" + Files.readString(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public synchronized void deleteFile(File file) {
        try {
            file.deleteOnExit();
            LOG.testLog("File deleted successfully :: " + file);
        } catch (Exception e) {
            LOG.testLog("Failed to delete the file :: " + file);
        }
    }

    public synchronized String isoDateTimeFormate() {
        Instant instant = Instant.now();
        LocalDateTime localDateTime = instant.atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDate = localDateTime.format(formatter);
        return formattedDate + ":00Z";
    }

    public synchronized String isoDateTimeFormateAddedTime(String type, int value) {
        Instant instant;
        if (type.equalsIgnoreCase("hour"))
            instant = Instant.now().plus(Duration.ofHours(value));
        else if (type.equalsIgnoreCase("minute"))
            instant = Instant.now().plus(Duration.ofMinutes(value));
        else
            instant = Instant.now().plus(Duration.ofSeconds(value));
        LocalDateTime localDateTime = instant.atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDate = localDateTime.format(formatter);
        return formattedDate + ":00Z";
    }

    public synchronized String isoDateTimeFormatewithoutZ() {
        Instant instant = Instant.now();
        LocalDateTime localDateTime = instant.atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDate = localDateTime.format(formatter);
        return formattedDate + ":00";
    }

    public synchronized JSONObject getJsonDataBody(String jSonBody, Object application) throws IOException {
//		LOG.testLog("Reading the test data of ::"+application +"  from ::"+ jSonBody);
        JSONObject jsonObject = (JSONObject) JsonUtil.getJsonBody(jSonBody).get(application);
        return jsonObject;
    }

    public synchronized Map testDataMapping(String inputFields, String inputData) {
        Map<String, String> testData = new HashMap<String, String>();
        String[] inputFieldsArray = inputFields.split(",");
        String[] inputDataArray = inputData.split(",");
        for (int i = 0; i < inputFieldsArray.length; i++) {
            try {
                testData.put(inputFieldsArray[i], setValue(inputDataArray[i].replace(";", ",")));
            } catch (Exception e) {
                System.out.println("runing exception");
                testData.put(inputFieldsArray[i], setValue(" "));
            }
        }
        return testData;
    }

    public synchronized JSONArray getJsonArrayDataBody(String jSonBody) throws IOException {

        JSONArray jsonArray = (JSONArray) JsonUtil.getJsonArray(jSonBody);
        return jsonArray;
    }
    public synchronized RequestSpecification getAuth0SpecRequest() {
        return given().redirects().follow(false).filter(filter)
                .config(RestAssured.config().encoderConfig(encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }
    public synchronized void clearAuth0Cookies() {
        filter= new CookieFilter();;
    }

    public synchronized void setDecodeToken() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idToken", idToken);
        jsonObject.put("accessToken", auth0AccessToken);
        System.out.println("JSON file created: " + jsonObject);
        decodeToken = jsonObject.toJSONString();
        System.out.println("decodeToken: " + decodeToken);
    }

    public synchronized void setEncodeToken() {
        accessToken = Base64.getEncoder().encodeToString(decodeToken.getBytes());
        System.out.println("accessToken: " + accessToken);
    }
    public synchronized void reponseDataValidation(String inputData, Object result ){
        LOG.testLog("!! Verifying the reponse based on the input data !!");
        Gson gson = new Gson();
        String jsonString = gson.toJson(result);
        org.json.JSONObject resultJsonObject = new org.json.JSONObject(jsonString);
        String[] data = inputData.split(",");
        List<String> dataList = Arrays.asList(data);
        dataList.stream().forEach((d) -> {
            String field = d.split(":")[0];
            String value = d.split(":")[1];
            try{
                String responseData=String.valueOf(resultJsonObject.get(field));
                Assert.assertEquals(responseData,value,field+" data is not matching with the expected ::"+value+" as actual is :"+responseData);
                LOG.testLog("AssertPass: Expected Data in the feild: "+field +" is matched with actual value :"+responseData);
            }catch (Exception e){
                LOG.testLog("AssertFail:Expected field "+field+"is not availble is response");
//                Assert.fail("Expected field "+field+"is not availble is response");
            }});
    }
    public synchronized String inputDataToggleHaspMap(String inputData){
        HashMap<String,Object> result=new HashMap<>();
        String[] data = inputData.split(",");
        List<String> dataList = Arrays.asList(data);
        dataList.stream().forEach((d) -> {
            String field = d.split(":")[0];
            String value = d.split(":")[1];
            result.put(field, value);
        });
        Gson gson = new Gson();
        String jsonString = gson.toJson(result);
//        org.json.JSONObject resultJsonObject = new org.json.JSONObject(jsonString);
        return jsonString;
    }
    synchronized long epochTimeToMilli() {
        return Instant.now().toEpochMilli();
//        return Instant.now().getEpochSecond();
    }


}
