package api.common;

import java.util.HashMap;
import java.util.Map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiHeader extends ApiBase {

	
	 public Map<String, String> basicHeader(String access_token, String channel) {
	        Map<String, String> basic_header = new HashMap<String, String>();
	        basic_header.put("Authorization", "Bearer " + access_token);
	        basic_header.put("content-Type", "application/json");
	        basic_header.put("Connection", "keep-alive");
	        basic_header.put("Channel", channel);
	        return basic_header;
	    }

	    public Map<String, String> modifedHeader(String access_token, String channel, String moreDetails) {
	        Map<String, String> modified_header = new HashMap<String, String>();
	        modified_header.put("Authorization", "Bearer " + access_token);
	        modified_header.put("content-Type", "application/json");
	        modified_header.put("Connection", "keep-alive");
	        modified_header.put("Channel", channel);
	        String header;
	        String value;
	        String[] inputFieldsArray = moreDetails.split(",");
	        for (int i = 0; i < inputFieldsArray.length; i++) {
	            header = inputFieldsArray[i].split(":")[0];
	            value = inputFieldsArray[i].split(":")[1];
	            if (value.equalsIgnoreCase("BLANK"))
	                value = "";
	            modified_header.put(header, value);
	        }
	        return modified_header;
	    }

	    public Map<String, String> formParam() {
	        Map<String, String> form_param = new HashMap<String, String>();
	        form_param.put("grant_type", "password");
	        form_param.put("password", loader.getProperty("adminPassword"));
	        form_param.put("username", loader.getProperty("adminUser"));
	        return form_param;
	    }

	    public Map<String, String> formKioskParam() {
	        Map<String, String> form_param = new HashMap<String, String>();
	        form_param.put("grant_type", "password");
	        form_param.put("password", loader.getProperty("kiosk_password"));
	        form_param.put("username", loader.getProperty("kiosk_username"));
	        return form_param;
	    }

	    public Map<String, String> giftCardHeader(String access_token, String channel, String sessionId) {
	        System.out.println("access token is" + access_token);
	        System.out.println("channel is" + channel);
	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Authorization", "Bearer " + access_token);
	        header.put("content-Type", "application/json");
	        header.put("Connection", "keep-alive");
	        header.put("Channel", channel);
	        header.put("Kount-Session-ID", sessionId);
	        return header;
	    }

	    public Map<String, String> giftCardParams(String paramName, String paramValue, String access_token,
	                                              String sessionId) {
	        Map<String, String> header = new HashMap<String, String>();
	        String[] paramNameArray = paramName.split(",");
	        String[] paramValueArray = paramValue.split(",");
	        for (int i = 0; i < paramNameArray.length; i++) {
	            header.put(paramNameArray[i], paramValueArray[i]);
	        }
	        header.put("Authorization", "Bearer " + access_token);
	        header.put("content-Type", "application/json");
	        header.put("Connection", "keep-alive");
	        header.put("Kount-Session-ID", sessionId);
	        return header;
	    }

	    public Map<String, String> giftCardHeaderParams(String paramName, String paramValue, String access_token) {
	        System.out.println("access token is" + access_token);
	        Map<String, String> header = new HashMap<String, String>();
	        String[] paramNameArray = paramName.split(",");
	        String[] paramValueArray = paramValue.split(",");
	        for (int i = 0; i < paramNameArray.length; i++) {
	            header.put(paramNameArray[i], paramValueArray[i]);
	        }
	        header.put("Authorization", "Bearer " + access_token);
	        header.put("content-Type", "application/json");
	        header.put("Connection", "keep-alive");
	        return header;
	    }

	    public String invalidAuthToken(String access_token) {
	        String invalidToken = "Bearer " + access_token;
	        return invalidToken;
	    }

	    public Map<String, String> addCardTokenHeader(String access_token, String sessionId) {

	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Authorization", "Bearer " + access_token);
	        header.put("content-Type", "application/json");
	        header.put("Connection", "keep-alive");
	        header.put("Kount-Session-ID", sessionId);
	        return header;
	    }

	    public Map<String, String> walletHeader(String access_token, String perm_access_token) {

	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Authorization", "Bearer " + access_token);
	        header.put("content-Type", "application/json");
	        header.put("Connection", "keep-alive");
	        header.put("access-token", perm_access_token);
	        return header;
	    }

	    public Map<String, String> deleteWalletHeader(String access_token, String perm_access_token) {

	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Authorization", "Bearer " + access_token);
	        header.put("Connection", "keep-alive");
	        header.put("access-token", perm_access_token);
	        return header;
	    }

	    public Map<String, String> getFreedompaySession(String access_token) {
	        Map<String, String> get_freedompay_session = new HashMap<String, String>();
	        get_freedompay_session.put("Authorization", "Bearer " + access_token);
	        get_freedompay_session.put("Content-Type", "application/json");
	        get_freedompay_session.put("Connection", "keep-alive");
	        return get_freedompay_session;
	    }

	    public Map<String, String> getPaymentToken(String sessionKey) {
	        Map<String, String> get_freedompay_session = new HashMap<String, String>();
	        get_freedompay_session.put("Authorization", "Bearer " + sessionKey);
	        get_freedompay_session.put("Content-Type", "application/json");
	        get_freedompay_session.put("Accept", "application/json");
	        get_freedompay_session.put("Connection", "keep-alive");
	        return get_freedompay_session;
	    }

	    public Map<String, String> paymentKeyToken(String hpc_session_key) {
	        Map<String, String> header = new HashMap<String, String>();
	        header.put("Authorization", "Bearer " + hpc_session_key);
	        header.put("content-Type", "application/json");
	        header.put("Connection", "keep-alive");
	        return header;
	    }

	    public Map<String, String> geoLocation(String lat, String lng) {
	        Map<String, String> geo_location = new HashMap<String, String>();
	        geo_location.put("lat", lat);
	        geo_location.put("lng", lng);
	        geo_location.put("language", "en");
	        geo_location.put("resultType", "street_address");
	        geo_location.put("locationType", "ROOFTOP");
	        return geo_location;
	    }

	    public Map getInputParam(String input) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("input", input);
	        return param;
	    }

	    public Map<String, String> formParam(String username) {
	        Map<String, String> form_param = new HashMap<String, String>();
	        form_param.put("grant_type", "password");
	        form_param.put("password", loader.getProperty("currentPassword"));
	        form_param.put("username", username);
	        return form_param;
	    }

	    public Map<String, String> formParam(String username, String password) {
	        Map<String, String> form_param = new HashMap<String, String>();
	        form_param.put("grant_type", "password");
	        form_param.put("password", password);
	        form_param.put("username", username);
	        return form_param;
	    }

	    public Map<String, String> orderHeader(String access_token, String channel) {
	        Map<String, String> order_header = new HashMap<String, String>();
	        order_header.put("Authorization", "Bearer " + access_token);
	        order_header.put("Content-Type", "application/json");
	        order_header.put("Accept", "application/json");
	        order_header.put("Channel", channel);
	        order_header.put("User-Agent", "postman-" + channel + "-agent");
	        order_header.put("X-Device-Id", "Postman-device-" + channel);
	        return order_header;

	    }

	    public Map<String, String> generateTokenViaHPC(String access_token, String session_ID) {
	        Map<String, String> Generate_token = new HashMap<String, String>();
	        Generate_token.put("Authorization", "Bearer " + access_token);
	        Generate_token.put("Content-Type", "application/json");
	        Generate_token.put("Accept", "application/json");
	        Generate_token.put("Connection", "keep-alive");
	        Generate_token.put("Kount-Session-ID", session_ID);
	        return Generate_token;

	    }

	    public Map getLocationParam(Integer input) {
	        Map<String, Integer> param = new HashMap<String, Integer>();
	        param.put("location", input);
	        return param;
	    }

	    public Map getLocationParam(String input) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("location", input);
	        return param;
	    }

	    public Map<String, String> eligibilityParam(String deliveryMode, String E2Etotal) {
	        Map<String, String> eligibility_param = new HashMap<String, String>();
	        eligibility_param.put("deliveryMode", deliveryMode);
	        eligibility_param.put("orderTotal", E2Etotal);
	        return eligibility_param;
	    }

	    public Map<String, String> checkElegibilityHeader(String access_token, String channel) {
	        Map<String, String> check_elgibility_header = new HashMap<String, String>();
	        check_elgibility_header.put("Authorization", "Bearer " + access_token);
	        check_elgibility_header.put("Content-Type", "application/json");
	        check_elgibility_header.put("Connection", "keep-alive");
	        check_elgibility_header.put("Channel", channel);
	        check_elgibility_header.put("X-Device-Id", "Postman-device-" + channel);

	        return check_elgibility_header;
	    }

	    public Map<String, String> cancelOrder() {
	        Map<String, String> cancle_order = new HashMap<String, String>();
	        cancle_order.put("grant_type", "client_credentials");
	        cancle_order.put("client_id", "DzPkjAdjfbl4kYDIYkAvArRQykURkBhT");
	        cancle_order.put("client_secret", "Nf7e8-trQX3SJ41cjhZ1let21ipbbAt7npgexvBX");
	        cancle_order.put("scope", "eats.deliveries");
	        return cancle_order;
	    }

	    public Map<String, String> runs() {
	        Map<String, String> runs = new HashMap<String, String>();
	        runs.put("Content-Type", "application/json");
	        runs.put("Accept", "application/json");
	        runs.put("Connection", "keep-alive");
	        return runs;
	    }

	    ////// Query parameters
	    public Map<String, String> exeArnParam(String executionArn) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("executionArn", executionArn);
	        return param;
	    }

	    public Map<String, String> getDeliveryParam() {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("input", loader.getProperty("deliveryAddress"));
	        return param;
	    }

	    public Map<String, String> getDeliveryParamWithInput(String input) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("input", input);
	        return param;
	    }

	    public Map<String, String> paramForLocation(String channel) {
	        Map<String, String> param_for_location = new HashMap<String, String>();
	        param_for_location.put("channel", channel);
	        param_for_location.put("includePrivate", "true");
	        param_for_location.put("includeAdmin", "true");
	        return param_for_location;
	    }

	    public Map<String, String> geoLocation() {
	        Map<String, String> geo_location = new HashMap<String, String>();
	        geo_location.put("lat", "37.76999");
	        geo_location.put("lng", "-122.44696");
	        geo_location.put("language", "en");
	        geo_location.put("resultType", "street_address");
	        geo_location.put("locationType", "ROOFTOP");
	        return geo_location;
	    }

	    public Map<String, String> paramForCrossSell(String channel) {
	        Map<String, String> param_for_cross_sell = new HashMap<String, String>();
	        param_for_cross_sell.put("platform", channel);
	        param_for_cross_sell.put("day", "mon");
	        return param_for_cross_sell;
	    }

	    public Map<String, String> crossSell(String access_token) {
	        Map<String, String> cross_sell = new HashMap<String, String>();
	        cross_sell.put("Authorization", "Bearer " + access_token);
	        cross_sell.put("Content-Type", "application/json");
	        cross_sell.put("Accept", "application/json");
	        cross_sell.put("Connection", "keep-alive");
	        return cross_sell;
	    }

	    public Map<String, Double> refundEligibilityParam(Double orderTotal) {
	        Map<String, Double> eligibility_param = new HashMap<String, Double>();
	        eligibility_param.put("orderTotal", orderTotal);
	        return eligibility_param;
	    }

	    public Map<String, String> refundEligibilityParamInvalid(String orderTotal) {
	        Map<String, String> eligibility_param = new HashMap<String, String>();
	        eligibility_param.put("orderTotal", orderTotal);
	        return eligibility_param;
	    }

	    public Map<String, String> allLocations(String channel) {
	        Map<String, String> all_locations = new HashMap<String, String>();
	        all_locations.put("includePrivate", "true");
	        all_locations.put("all", "true");
	        all_locations.put("channel", channel);
	        return all_locations;
	    }

	    public Map<String, String> locationsParam(String paramName, String paramValue) {
	        Map<String, String> locations = new HashMap<String, String>();
	        String[] paramNameArray = paramName.split(",");
	        String[] paramValueArray = paramValue.split(",");
	        for (int i = 0; i < paramNameArray.length; i++) {
	            try {
	                locations.put(paramNameArray[i], setValue(paramValueArray[i].replace(";", ",")));
	            } catch (Exception e) {
	                locations.put(paramNameArray[i], setValue(" "));
	            }
	        }
//			locations.put(paramName, paramValue);
	        return locations;
	    }

	    public Map<String, String> allRegions() {
	        Map<String, String> all_regions = new HashMap<String, String>();
	        all_regions.put("includePrivate", "true");
	        all_regions.put("isAlpha", "true");
	        all_regions.put("input", "new");
	        return all_regions;
	    }

	    public Map<String, String> getPossible(String channel) {
	        Map<String, String> get_possible = new HashMap<String, String>();
	        get_possible.put("channel", channel);
	        get_possible.put("lat", "39.743392");
	        get_possible.put("lng", "-104.992264");
	        get_possible.put("includePrivate", "true");
	        get_possible.put("radius", "52");
	        return get_possible;
	    }

	    public Map<String, String> runsQuery() {
	        Map<String, String> runs_query = new HashMap<String, String>();
	        runs_query.put("/api/v2/get_runs/1", "");
	        runs_query.put("milestone_id", "222");
	        return runs_query;
	    }

	    public Map<String, Object> getUserId(int userid) {
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("userIds", userid);
	        return param;
	    }

	    public Map<String, Object> getMpid(Object mpid) {
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("mpid", mpid);
	        return param;
	    }

	    public Map<String, Object> putSubscriptionHeader(Object mpid, boolean emailSubscription, boolean pushSubscription) {
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("mPid", mpid);
	        param.put("emailSubscribe", emailSubscription);
	        param.put("pushSubscribe", pushSubscription);
	        return param;
	    }

	    public Map<String, Integer> getProductChainId(int id) {
	        Map<String, Integer> param = new HashMap<String, Integer>();
	        param.put("product_chain_id", id);
	        return param;
	    }

	    public Map<String, String> getProductChainIdasString(String id) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("product_chain_id", id);
	        return param;
	    }

	    public Map<String, Integer> getCategoriesCategoryId(int categoryOloId, int LocationOloId) {
	        Map<String, Integer> param = new HashMap<String, Integer>();
	        param.put("category_olo_ids", categoryOloId);
	        param.put("location_olo_id", LocationOloId);
	        return param;
	    }

	    public Map<String, String> getCategoriesCategoryId(String categoryOloId, String LocationOloId) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("category_olo_ids", categoryOloId);
	        param.put("location_olo_id", LocationOloId);
	        System.out.print(param);
	        return param;
	    }

	    public Map<String, String> dataHeader() {
	        Map<String, String> data_header = new HashMap<String, String>();
	        data_header.put("Content-Type", "application/json");
	        data_header.put("Accept", "application/json");
	        return data_header;

	    }

	    public Map<String, String> deliveryHeader(String access_token) {
	        Map<String, String> delivery_header = new HashMap<String, String>();
	        delivery_header.put("Authorization", "Bearer " + access_token);
	        delivery_header.put("Content-Type", "application/json");
	        delivery_header.put("Accept", "application/json");
	        return delivery_header;

	    }

	    public Map getOrderList(Object locationId, Object placedAfterDate, String inputData) {
	        Map<String, Object> order = new HashMap<String, Object>();
	        order.put("locationId", locationId);
	        order.put("placedAfter", placedAfterDate);
	        try {
	            String[] data = inputData.split(",");
	            List<String> dataList = Arrays.stream(data).collect(Collectors.toList());
	            dataList.forEach((dl) -> {
	                order.put(dl.split(":")[0], setValue(dl.split(":")[1]));
	            });
	        } catch (Exception e) {
	        }
	        return order;
	    }

	    public Map getOrderListModified(Object locationId, Object placedAfterDate, String inputData) {
	        Map<String, Object> order = new HashMap<String, Object>();
	        order.put("locationId", locationId);
	        order.put("placedAfter", placedAfterDate);
	        try {
	            String[] data = inputData.split(",");
	            List<String> dataList = Arrays.stream(data).collect(Collectors.toList());
	            dataList.forEach((dl) -> {
	                order.replace(dl.split(":")[0], setValue(dl.split(":")[1]));
	            });
	        } catch (Exception e) {
	        }
	        return order;
	    }

	    public Map getEmailParam(String email) {
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("email", email);
	        return param;
	    }

	    public Map<String, String> kioskHeaders(String access_token) {
	        Map<String, String> basic_header = new HashMap<String, String>();
	        basic_header.put("Authorization", "Bearer " + access_token);
	        basic_header.put("content-Type", "application/json");
	        basic_header.put("Connection", "keep-alive");
	        return basic_header;
	    }

	    public Map<String, String> autherizationHeader() {
	        Map<String, String> autherization_Header = new HashMap<String, String>();
	        autherization_Header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
	        autherization_Header.put("Accept-Encoding", "gzip, deflate, br");
	        return autherization_Header;
	    }

	    public Map<String, String> autherizationForm() {
	        Map<String, String> autherization_Form = new HashMap<String, String>();
	        autherization_Form.put("authority", loader.getProperty("autherizationAuthority"));
	        autherization_Form.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
	        autherization_Form.put("Accept-Encoding", "gzip, deflate, br");
	        return autherization_Form;
	    }

	    public Map<String, String> autherizationQuery() {
	        Map<String, String> autherization_Query = new HashMap<String, String>();
	        autherization_Query.put("client_id", loader.getProperty("auth0_client_id"));
	        autherization_Query.put("scope", loader.getProperty("auth0_scope"));
	        autherization_Query.put("redirect_uri", loader.getProperty("auth0_redirect_uri"));
	        autherization_Query.put("audience", loader.getProperty("auth0_audience"));
	        autherization_Query.put("ext-channel", channel.toLowerCase());
	        autherization_Query.put("response_type", "code");
	        autherization_Query.put("response_mode", "query");
	        autherization_Query.put("code_challenge_method", loader.getProperty("auth0_code_challenge_method"));
	        autherization_Query.put("auth0Client", loader.getProperty("auth0_auth0Client"));
	        return autherization_Query;
	    }

	    public Map<String, String> auth0StateQuery(String state) {
	        Map<String, String> auth0State_Query = new HashMap<String, String>();
	        auth0State_Query.put("state", state);
	        return auth0State_Query;
	    }

	    public Map<String, String> auth0LoginQueryParam() {
	        Map<String, String> auth0Login_QueryParam = new HashMap<String, String>();
	        auth0Login_QueryParam.put("state", state);
	        return auth0Login_QueryParam;
	    }

	    public Map<String, String> auth0LoginFormParam() {
	        Map<String, String> auth0Login_FormParam = new HashMap<String, String>();
	        auth0Login_FormParam.put("state", state);
	        auth0Login_FormParam.put("username", auth0UserName);
	        auth0Login_FormParam.put("js-available", "true");
	        auth0Login_FormParam.put("webauthn-available", "true");
	        auth0Login_FormParam.put("is-brave", "false");
	        auth0Login_FormParam.put("webauthn-platform-available", "true");
	        auth0Login_FormParam.put("action", "default");
	        return auth0Login_FormParam;
	    }

	    public Map<String, String> auth0FormHeader() {
	        Map<String, String> auth0_FormHeader = new HashMap<String, String>();
	        auth0_FormHeader.put("Content-Type", "application/x-www-form-urlencoded");
	        return auth0_FormHeader;
	    }

	    public Map<String, String> auth0PasswordFormParam() {
	        Map<String, String> auth0_PasswordFormParam = new HashMap<String, String>();
	        auth0_PasswordFormParam.put("password", auth0Password);
	        return auth0_PasswordFormParam;
	    }

	    public Map<String, String> auth0CodeQuery(String code) {
	        Map<String, String> auth0State_Query = new HashMap<String, String>();
	        auth0State_Query.put("code", code);
	        return auth0State_Query;
	    }

	    public Map<String, String> auth0AutherizationFormParam() {
	        Map<String, String> auth0Autherization_FormParam = new HashMap<String, String>();
	        auth0Autherization_FormParam.put("code", code);
	        auth0Autherization_FormParam.put("client_id", loader.getProperty("auth0_client_id")); //stg:vvgBxwhfqstrE1VQ2JPb6EguhhmYZ0VL
	        auth0Autherization_FormParam.put("grant_type", "authorization_code");
	        auth0Autherization_FormParam.put("redirect_uri", loader.getProperty("auth0_redirect_uri"));
	        return auth0Autherization_FormParam;
	    }

	    public Map<String, String> uberAuth0Form() {
	        Map<String, String> auth0Autherization_FormParam = new HashMap<String, String>();
	        auth0Autherization_FormParam.put("client_id", loader.getProperty("uber_clientId"));
	        auth0Autherization_FormParam.put("client_secret", loader.getProperty("uber_clientSecret"));
	        auth0Autherization_FormParam.put("grant_type", "client_credentials");
	        auth0Autherization_FormParam.put("scope", "eats.deliveries");
	        return auth0Autherization_FormParam;
	    }

	    public Map<String, String> uberAuthFormHeader() {
	        Map<String, String> auth0_FormHeader = new HashMap<String, String>();
	        auth0_FormHeader.put("Content-Type", "application/x-www-form-urlencoded");
	        auth0_FormHeader.put("Host", "auth.uber.com");
	        auth0_FormHeader.put("Accept-Encoding", "gzip, deflate, br");
	        return auth0_FormHeader;
	    }
	    public Map<String, String> closuerQueryParam(){
	        Map<String, String> closuer_QueryParam = new HashMap<String, String>();
	        closuer_QueryParam.put("Content-Type", "application/x-www-form-urlencoded");
	        closuer_QueryParam.put("Host", "auth.uber.com");
	        closuer_QueryParam.put("Accept-Encoding", "gzip, deflate, br");
	        return closuer_QueryParam;
	    }
	    public Map<String, String> getToggleLocationQueryParam(){
	        Map<String, String> getToggle_QueryParam = new HashMap<String, String>();
	        getToggle_QueryParam.put("location", loader.getProperty("toggleLocationId"));
	        return getToggle_QueryParam;
	    }
	    public Map<String, Long> getDetailToggleLocationQueryParam(){
	        Map<String, Long> getToggle_QueryParam = new HashMap<String, Long>();
	        getToggle_QueryParam.put("cache_bust", epochTimeToMilli());
	        return getToggle_QueryParam;
	    }
	    public Map<String, String> updateToggleRequest() {
	        Map<String, String> updateToggleRequest_Header = new HashMap<String, String>();
	        updateToggleRequest_Header.put("Content-Type", "application/json");
	        return updateToggleRequest_Header;
	    }
	    public Map<String, Object> getLogToggleQueryParam(){
	        Map<String, Object> getLogToggle_QueryParam = new HashMap<String, Object>();
	        getLogToggle_QueryParam.put("page", 1);
	        getLogToggle_QueryParam.put("location", loader.getProperty("toggleLocationId"));
	        getLogToggle_QueryParam.put("search", "");
	        getLogToggle_QueryParam.put("cache_bust", epochTimeToMilli());
	        return getLogToggle_QueryParam;
	    }
	    public Map<String, String> formWorkDayParam() {
	        Map<String, String> form_param = new HashMap<String, String>();
	        form_param.put("grant_type", "refresh_token");
	        form_param.put("refresh_token", loader.getProperty("WorkDayRefreshToken"));
	        return form_param;
	    }
}
