package api.common;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.ConfigLoader;

public class RequestAppSpec {
	
	public volatile RequestSpecification getBearerTokenSpec, commonSSMASpec, getSpec, commonOLOSpec, refundSpec, userProfileSpec,
    getSettings, allergens, productsSpec, categoriesSpec, getOrderSListSPec, giftCardSpec,
    profileSpec, deliverySSMASpec, communSpec, kitchenServiceSpec, FPSpec, postDeliverySpec, offerSpec, patchOfferSpec, ssmaKioskSpec, auth0BaseUriSpec,
    auth0BaseHeaderSpec, auth0AutherizationHeaderSpec, auth0AutherizationQuerySpec, auth0FormSpec, auth0AutherizationSpec, auth0QuerySpec, auth0Spec,
    auth0LoginSpec, auth0PasswordSpec;
public static volatile ApiHeader header = new ApiHeader();
public static volatile ConfigLoader loader;
public static volatile String channel;
public static volatile String testEnv;
ApiRequest apiRequest;
RequestSpecification given = RestAssured.given();

/**
* Declared in static block to read loader file
*/
static {
loader = ConfigLoader.getInstance();
channel = ConfigLoader.getChannelName();
testEnv = ConfigLoader.getTestConfigFileName();
}

String REQRESURl = loader.getProperty("REQRESURl");
String OLOURL = loader.getProperty("OLOUrl");
String oloWebHook = loader.getProperty("olo_webhook_url");
String orderUrl = loader.getProperty("orderUrl");
String refundUrl = loader.getProperty("refundUrl");
String userProfilesUrl = loader.getProperty("userProfiles");
String commonUrl = loader.getProperty("commonUrl");
String menuUrl = loader.getProperty("menuUrl");
String getUserUrl = loader.getProperty("getUserUrl");
String locationUrl = loader.getProperty("locationUrl");
String profileUrl = loader.getProperty("profileUrl");
String commonsUrl = loader.getProperty("commonsUrl");
String giftCardUrl = loader.getProperty("giftCardUrl");
String kitchenServiceUrl = loader.getProperty("kitchenServiceUrl");
String paymentUrl = loader.getProperty("paymentUrl");
String paymentTokenUrl = loader.getProperty("paymentTokenUrl");
String centerlizedLoginUrl = loader.getProperty("centerlizedLoginUrl");
String kitchenServiceDevUrl = loader.getProperty("kitchenServiceDevUrl");
String configServiceUrl = loader.getProperty("configServiceUrl");
String deliveryUrl = loader.getProperty("deliveryUrl");
String offersUrl = loader.getProperty("offersUrl");
String auth0LoginUrl = loader.getProperty("auth0LoginUrl");
String auth0RedirectUri = loader.getProperty("auth0_redirect_uri");
String sausesauceLabsUrl = loader.getProperty("sausesauceLabsUrl");
String uberAuthUrl = loader.getProperty("uber_auth_Url");
String uberUrl = loader.getProperty("uber_Url");
String adminAppUrl = loader.getProperty("adminAppUrl");
String boomiApiUrl = loader.getProperty("boomiApiUrl");



public RequestSpecification getBearerTokenSpec() {
return getBearerTokenSpec = new RequestSpecBuilder().setBaseUri(REQRESURl)
        .setBasePath(loader.getProperty("getBearerToken")).build();
}

public RequestSpecification getBearerTokenSpec(String username) {
return getBearerTokenSpec = new RequestSpecBuilder().addFormParams(header.formParam(username))
        .setBaseUri(REQRESURl).setBasePath(loader.getProperty("getBearerToken")).build();
}

public RequestSpecification getKioskBearerTokenSpec() {
return getBearerTokenSpec = new RequestSpecBuilder().addFormParams(header.formKioskParam()).setBaseUri(REQRESURl)
        .setBasePath(loader.getProperty("getBearerToken")).build();
}

public String getURL(String path, String variableToEvaluate) {
    String[] actual = loader.getProperty(path).split("\\+");
    String decodedRequest=null;
    String request=null;
    try {
        request = actual[0] + variableToEvaluate + actual[2];
    }catch(ArrayIndexOutOfBoundsException ae){
        request = actual[0] + variableToEvaluate;
    }
    decodedRequest = URLDecoder.decode(request, StandardCharsets.UTF_8);
    return decodedRequest;
}


public RequestSpecification sauceLabsRequestSpec(Map queryMap) {
    return auth0LoginSpec = sauceLabsBaseRequestSpec().auth().basic(loader.getProperty("saucelabsUserName"),
            loader.getProperty("saucelabsPassword")).queryParams(queryMap);
}


public RequestSpecification sauceLabsBaseRequestSpec() {
    return auth0BaseUriSpec = new RequestSpecBuilder().setBaseUri(sausesauceLabsUrl)
            .build();
}

//======================================================================================================
/***** locations ***********/
public RequestSpecification getLocations() {
return productsSpec = new RequestSpecBuilder().addHeader("content-Type", "application/json")
        .setBaseUri(locationUrl).build();
}

public RequestSpecification getStringLocationsSpec(String basePath) {
return getSpec = getLocations().basePath(basePath);
}


public RequestSpecification getUser() {
return productsSpec = new RequestSpecBuilder().addHeader("content-Type", "application/json")
        .setBaseUri(getUserUrl).build();
}

public RequestSpecification getUserSpec(String basePath) {
return getSpec = getUser().basePath(basePath);
}





//=======================================================================================================


}
