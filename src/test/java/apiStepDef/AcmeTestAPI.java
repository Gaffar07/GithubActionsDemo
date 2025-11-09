package apiStepDef;

import api.common.ApiRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import utils.ConfigLoader;
//import utils.SSMAUtils;
import utils.LogHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AcmeTestAPI {
	
	 ApiRequest request = new ApiRequest();
	    //SOAPRequest soapRequest = new SOAPRequest();
	    static ConfigLoader loader;
	    static String channel;
	    private static final LogHandler LOG=new LogHandler(AcmeTestAPI.class);
	    //public static SSMAUtils ssmaUtils = new SSMAUtils();
    /**
     * Declared in static block to read properties file
     */
    static {
        loader = ConfigLoader.getInstance();
        channel = ConfigLoader.getTestConfigFileName();
    }

//	@Given("I place pickup order with only CC")
//	public void iPlaceAnOnlyCCOrder() throws InterruptedException {
////		request.getBearerToken();
////		request.SSMAGetMe();
//		request.OLOProxyRequest();
//		// request.createBasket();
//		request.createOrder();
//		// request.basketValidate();
//		request.orderPickupWithCC();
//		request.orderValidation();
//	}

    @Given("Admin User is logged into application")
    public void Admin_user_logged_into_application() {
        //request.OLOProxyRequest();
//        request.pickExisitngUserFromList();
//        request.email = loader.getProperty("adminUser");
//        request.password = loader.getProperty("adminPassword");
//        request.OLOProxyRequestExisitngUser();
    }

   

    @And("User submits the pickup order with GC")
    public void user_submits_the_pickup_order_GC() {
        //request.orderPickupWithGC();
    }

    @When("user validates the basket")
    public void user_validates_basket() {
        //request.basketValidate();
    }

    @Given("user validates the basket with invalid {string}")
    public void user_validates_the_basket_with_invalid(String basketID) {
        //request.basketValidateWithInvalidbasketID(basketID);
    }

    @When("User creates a basket")
    public void user_creates_a_basket() {
        //request.createBasket();
    }

    @When("user creates a basket with invalid details {string} and {string}")
    public void user_creates_a_basket_with_invalid_details_and(String section, String value) {
        //request.createBasketWithInvalidDetails(section, value);
    }

    @When("user sets the delivery mode {string}")
    public void user_sets_the_delivery_mode(String mode) {
       // request.setDeliveryMode(mode);
    }

    @Given("user sets the delivery mode with invalid details {string} and {string}")
    public void and_user_sets_the_delivery_mode_with_invalid_details_and(String section, String value) {
        //request.setDeliveryModeWithInvalidDetails(section, value);
    }

    @When("user sets the vehicle {string} with value {string}")
    public void user_sets_the_vehicle(String field, String value) {
        //request.setCustomField(field, value);
    }

    @When("user sets the vehicle {string} with invalid value {string}")
    public void user_sets_the_vehicle_with_invalid_value(String field, String value) {
        //request.setCustomFieldWithInvalidValues(field, value);
    }

    @When("User adds a product")
    public void user_adds_a_product() {
        //request.addsProduct();
    }

    @Then("User adds a product with invalid basket {string}")
    public void user_adds_a_product_with_invalid_basket(String basketId) {
        //request.addsProductWithInvalidOlaBasketId(basketId);
    }

    @And("user adds coupon code")
    public void user_adds_coupon() {
       // request.orderAddsCoupon();
    }

    @When("user adds a Coupon with invalid details {string} and {string}")
    public void user_adds_a_coupon_with_invalid_details_and(String section, String value) {
        //request.addCouponWithInvalidDetails(section, value);
    }

    @And("user adds tip")
    public void user_adds_tip() {
        //request.orderAddsTip();
    }

    @When("user adds a tip with invalid details {string} and {string}")
    public void user_adds_a_tip_with_invalid_details_and(String section, String value) {
        //request.addTipWithInvalidBasketID(section, value);
    }

    @And("User submits the delivery order")
    public void user_submits_the_delivery_order() {
        //request.orderDeliveryWithCC();
    }

    @And("User submits the delivery order with GC")
    public void user_submits_the_delivery_order_with_GC() {
       // request.orderDeliveryWithGC();
    }

//    @Given("user updates delivery address with with invalid details {string} and {string}")
//    public void user_updates_delivery_address_with_with_invalid_details_and(String section, String value) {
//        request.setDeliveryAddressWithInvalidDetails(section, value);
//    }
//
//    @Given("User gets settings with invalid {string}")
//    public void user_gets_settings_with_invalid(String authToken) {
//        request.getSettingsWithInvalidAuth(authToken);
//    }
//
//    @Then("Order should be placed")
//    public void user_validates_the_order() throws InterruptedException {
//        request.orderValidation();
//    }

    @Then("Order should be placed with GC")
    public void user_validates_the_order_GC() throws InterruptedException {
        request.getUser();
    }

//    @Then("Pick up order should be placed with GC")
//    public void pick_up_order_validation_with_GC() throws InterruptedException {
//        request.orderValidationGC();
//    }
//
//    @Then("User submits the {string} {string} of {string} order with {string} and {string} and {string}")
//    public void user_submits_the_of_order_with(String mode, String type, String scheduleType, String pickupInfo,
//                                               String paymentType, String addtionalPayment) {
//        request.submitOrderRequest(mode, type, scheduleType, pickupInfo, paymentType, addtionalPayment);
//    }
//
//    @Then("get the Order")
//    public void get_the_order() throws InterruptedException {
//        request.getOrder();
//    }
//
//    @Then("user gets the orders")
//    public void get_the_orders() throws InterruptedException {
//        request.getOrders();
//    }
//
//    @Then("try to get the Order with {string} and {string}")
//    public void try_to_get_the_order_with_and(String inputField, String inputData) throws InterruptedException {
//        request.getOrdersInvalid(inputField, inputData);
//    }

//    @Given("get the Order with {string} and {string}")
//    public void get_the_order_with_and(String inputField, String inputData) throws InterruptedException {
//        request.getOrderModified(inputField, inputData);
//    }
//
//    @Given("update the Order {string}")
//    public void update_the_order(String message) throws InterruptedException {
//        request.updateOrder(message);
//    }
//
//    @Given("update the Order with {string} and {string}")
//    public void update_the_order_with_and(String inputField, String inputData) throws InterruptedException {
//        request.updateOrderModified(inputField, inputData);
//    }
//
//    @Then("User submits the pickup order with valid details {string}")
//    public void user_submits_the_pickup_order_with_valid_details(String moreDetails) {
//        request.modifiedValidOrderPickupWithCC(moreDetails);
//    }
//
//    @Then("User submits the pickup order with invalid details {string} of {string}")
//    public void user_submits_the_pickup_order_with_invalid_details(String moreDetails, String section) {
//        request.modifiedInvalidOrderPickupWithCC(moreDetails, section);
//    }
//
//    @Given("User calculates the delivery estimates with invalid details {string} and {string}")
//    public void user_calculates_the_delivery_estimates_with_invalid_details_and(String section, String value) {
//        request.calculateDeliveryEstimatesWithInvalidDetails(section, value);
//    }
//
//    @Then("User submits the delivery order with valid details {string}")
//    public void user_submits_the_delivery_order_with_valid_details(String moreDetails) {
////		request.calculateDeliveryDetails();
//        request.modifiedValidOrderDeliveryWithGC(moreDetails);
//    }
//
//    @Then("User submits the delivery order with invalid details {string} of {string}")
//    public void user_submits_the_delivery_order_with_invalid_details_of(String inputData, String section) {
////		request.calculateDeliveryDetails();
//        request.modifiedInvalidOrderDeliveryWithGC(section, inputData);
//    }
//
//    @Then("{string} {string} order should be placed with {string} {string}")
//    public void order_should_be_placed_with(String mode, String type, String paymentType, String addtionalPayment) {
////		request.validateOrderPlaced(mode, type, paymentType, addtionalPayment);
//    }
//
//    @Then("user can cancel the order {string}")
//    public void user_can_cancel_the_order(String cancelOrder) {
//        request.cancelOrderRequest(cancelOrder);
//    }
//
//    @Then("Delivery order should be placed with GC")
//    public void user_validates_the_order_with_GC() throws InterruptedException {
//        request.deliveryOrderValidationGC();
//    }
//
//    @Then("the failures with {string} and {string} is disaplyed")
//    public void the_failures_with_and_is_disaplyed(String failureType, String failureMessage) {
//        request.orderFailures(failureType, failureMessage);
//    }
//
//    @Then("user can get list of user orders")
//    public void user_list_of_orders() throws InterruptedException {
//        request.userListOfOrders();
//    }
//
//    @And("error is displayed when User validates the basket")
//    public void user_validates_the_basket() {
//        request.validateBasketForError();
//    }
//
//    @And("user checks the refund order result")
//    public void check_refund_result() {
//        request.checkRefundResult();
//    }
//
//    @And("user can successfully refund the order")
//    public void check_refund_status() {
//        request.checkRefundStatus();
//    }
//
//    @When("User creates an order for {int} products")
//    public void user_creates_an_order(int productCount) {
//        request.createOrdersMultipleProductCount(productCount);
//    }
//
//    @And("User validates basket and submits the pickup order receives code {string}")
//    public void user_validates_basket_submits_the_pickup_order(String code) {
//        request.basketValidate();
//        request.orderPickUpGC();
//    }
//
//    @And("Admin user can successfully refund the order")
//    public void check_admin_refund_status() {
//        request.checkAdminRefundStatus();
//    }
//
//    @And("Admin user can successfully refund the partial order")
//    public void check_partial_refund_status() {
//        request.checkPartialRefundStatus();
//    }
//
//    @And("A new user profile is created")
//    public void new_user_profile_creation() {
////        request.createNewUserProfile();
//        request.pickExisitngUserFromList();
//    }
//
//    @Then("create user profile invalid body {string} and {string}")
//    public void create_user_profile_invalid_body_and(String inputFeild, String InputData) {
//        request.createNewUserProfileWithInvalidBody(inputFeild, InputData);
//    }
//
//    @And("user details are retreived")
//    public void ssma_creation() {
//        request.ssmaCreate();
//    }

//    @Given("boomi authorization is generated")
//    public void boomi_auth_token() {
//        request.getAuthorization(loader.getProperty("boomiUserName"), loader.getProperty("boomiPassword"));
//    }
//
//    @Given("ncr authorization is generated")
//    public void ncr_auth_token() {
//        request.getAuthorization(loader.getProperty("ncr_cognito_username"),
//                loader.getProperty("ncr_cognito_password"));
//    }
//
//    @When("order history by email address can be generated")
//    public void history_by_email() {
//        request.orderHistoryByEmail();
//    }
//
//    @Then("order history by email with {string} and {string}")
//    public void order_history_by_email_with_and(String inputField, String inputValue) {
//        request.orderHistoryByEmailModified(inputField, inputValue);
//    }
//
//    @Then("order history by email with invalid {string} and {string}")
//    public void order_history_by_email_with_invalid_and(String inputField, String inputValue) {
//        request.orderHistoryByEmailModifiedInvalid(inputField, inputValue);
//    }
//
//    @And("user schedules the order")
//    public void user_schedules_order() {
//        request.userSchedulesOrder();
//    }
//
//    @Given("user schedules order with invalid details {string} and {string}")
//    public void and_user_schedules_order_with_invalid_details_and(String section, String value) {
//        request.userSchedulesOrderWithInvalidDetails(section, value);
//    }
//
//    @When("User submits the pickup order with {string} trayId")
//    public void user_submits_the_pickup_order_tray_id_with(String id) {
//        request.invalidOrderPickupWithCC(id);
//    }
//
//    @When("User submits the pickup order with blank trayId")
//    public void user_submits_the_pickup_order_with_blank_Id() {
//        request.BlankTrayPickUpCC();
//    }
//
//    @And("user can cancel the order successfully")
//    public void user_cancels_order() {
//        request.userCancelsOrder();
//    }
//
//    @Given("user cancels order with invalid {string}")
//    public void user_cancels_order_with_invalid(String orderID) {
//        request.userCancelsOrderWithInvalidOrderID(orderID);
//    }
//
////	@Then("user can find the order in list of orders")
////	public void order_In_List_Of_Orders() throws InterruptedException {
////		request.orderInListOfOrders();
////	}
//
//    @Then("user can find the order in list of orders with {string}")
//    public void order_In_List_Of_Orders(String inputvalue) throws InterruptedException {
//        request.orderInListOfOrders(inputvalue);
//    }
//
//    @Then("user try find the order in list of orders with invalid request {string} and {string}")
//    public void user_try_find_the_order_in_list_of_orders_with_invalid_request_and(String section, String inputValue) {
//        request.orderInListOfOrdersInvalid(section, inputValue);
//    }
//
//    @Given("get the Order result with {string} and {string}")
//    public void get_the_order_result_with_and(String inputField, String inputvalue) throws InterruptedException {
//        request.orderResultInvalid(inputField, inputvalue);
//    }
//
//    @Then("user can get the details of the order")
//    public void order_details() throws InterruptedException {
//        request.orderDetails();
//    }
//
//    @Then("user can get the details of the order by using uber and order id")
//    public void get_order_details_uber_and_orderid() throws InterruptedException {
//        request.getOrderDetailsUberAndOrderid();
//    }
//
//    @Given("User gets the delivery order details using invalid {string} and {string}")
//    public void user_gets_the_delivery_delivery_order_details_using_invalid_and(String uberOrderID, String orderID) {
//        request.getOrderDetailsWithInvalidUberAndOrderid(uberOrderID, orderID);
//    }
//
//    @Then("user can get the details of the Delivery endpoint in Uber.")
//    public void verifyDeliveryEndPoint() {
//        request.postDeliveryEndPointInUberOrder();
//    }
//
//    @Then("user can get the details of the order by using order id")
//    public void get_order_details_orderid() throws InterruptedException {
//        request.getOrderDetailsOrderid();
//    }
//
//    @Given("User gets the delivery order details using invalid {string}")
//    public void user_gets_the_delivery_order_details_using_invalid(String orderID) {
//        request.getOrderDetailsWithInvalidOrderid(orderID);
//    }
//
//    @Given("user can get the delivery address using both delivery street and zip code")
//    public void user_can_get_the_delivery_address_using__both_delivery_street_and_zip_code() {
//        request.getDeliveryDetails();
//    }
//
//    @Given("user can get the delivery address using delivery {string}")
//    public void user_can_get_the_delivery_address_using_delivery(String input) {
//        request.getDeliveryDetailsWithInput(input);
//    }
//
//    @Then("User gets delivery address with invalid {string}")
//    public void user_gets_delivery_address_with_invalid(String address) {
//        request.getDeliveryDetailsWithInvalidDetails(address);
//    }
//
//
//    @Given("the status of the order {string} can be updated")
//    public void the_status_of_the_order_can_be_updated(String status) {
//        request.updateOrderStatus(status);
//    }
//
//    @Given("the status of order is {string}")
//    public void the_status_of_order_is(String status) {
//        request.validateOrderStatus(status);
//    }
//
//    @And("refund report can be generated")
//    public void check_refund_report() {
//        request.checkRefundReport();
//    }
//
//    @And("admin can save manual refund")
//    public void save_manual_report() {
//        request.saveManualReport();
//    }
//
//    @Given("access token is generated for {string} and {string}")
//    public void access_token_is_generated_for_and(String username, String password) {
//        request.getAccessToken(loader.getProperty(username), loader.getProperty(password));
//    }
//
//    @Given("user can get the olo user profile")
//    public void get_the_olo_user_profile() {
//        request.getOloUserProfile();
//    }
//
//    @Given("get user account by userId")
//    public void get_user_account_by_id() {
//        request.getUserAccountById();
//    }
//
//    @Then("get user account by invalid {string}")
//    public void get_user_account_by_invalid(String userId) {
//        request.getUserAccountByInvalidId(userId);
//    }
//
//    @Given("user can get the subscription details")
//    public void get_subscription_by_mpid() {
//        request.getSubscriptionByMpid();
//    }
//
//    @Then("user get the subscription with invalid  {string}")
//    public void user_get_the_subscription_with_invalid(String mPid) {
//        request.getSubscriptionByInvalidMpid(mPid);
//    }
//
//    @Given("user can update the subscription details")
//    public void update_subscription_by_mpid() {
//        request.updateSubscriptionByMpid();
//    }
//
//    @Then("user update the subscription invalid details {string} and {string}")
//    public void user_update_the_subscription_invalid_details_and(String inputFeild, String inputData) {
//        request.updateSubscriptionByInvalidbody(inputFeild, inputData);
//    }
//
//    @Given("User can update the customer profile")
//    public void update_customer_profile() {
//        request.updateCustomerProfile();
//    }
//
//    @Given("Update customer profile with invalid {string} and request body {string} and {string}")
//    public void update_customer_profile_with_invalid_and_request_body_and(String section, String inputFeild, String inputData) {
//        request.UpdateCustomerProfileWithInvalidData(section, inputFeild, inputData);
//    }

//    @Given("User can get the customer profile")
//    public void get_customer_profile() {
//        request.getCustomerProfile();
//    }
//
//    @Given("Get customer profile with invalid {string}")
//    public void get_customer_profile_with_invalid(String authToken) {
//        request.getCustomerProfileWithInvalidData(authToken);
//    }
//
//    @Given("admin can get the customer profile")
//    public void admin_get_customer_profile() {
//        request.adminGetCustomerProfile();
//    }
//
//    @Given("admin can get list of customer profiles with invalid details {string} and {string}")
//    public void admin_can_get_list_of_customer_profiles_with_invalid_details_and(String inputFeild, String inputData) {
//        request.adminGetCustomerProfileWithInvalidDetails(inputFeild, inputData);
//    }
//
//    @Given("admin can get the customer profile with invalid {string}")
//    public void admin_can_get_the_customer_profile_with_invalid(String profileId) {
//        request.adminGetCustomerProfileWithInvalidID(profileId);
//    }
//
//    @Given("admin can update the customer profile")
//    public void admin_update_customer_profile() {
//        request.adminUpdateCustomerProfile();
//    }
//
//    @Given("admin can update the customer profile with invalid {string}  {string} and {string}")
//    public void admin_can_update_the_customer_profile_with_invalid_and(String section, String inputFeild, String inputData) {
//        request.adminUpdateCustomerProfileWithInvalidDetails(section, inputFeild, inputData);
//    }
//
//
//    @Given("admin can delete the customer profile")
//    public void admin_delete_customer_profile() {
//        request.adminDeletCustomerProfile();
//    }
//
//    @Given("admin can delete the customer profile with invalid {string}")
//    public void admin_can_delete_the_customer_profile_with_invalid(String profileId) {
//        request.adminDeleteCustomerProfileWithInvalidID(profileId);
//    }
//
//    @Given("admin can get list of customer profiles")
//    public void admin_list_customer_profile() {
//        request.adminListCustomerProfile();
//    }
//
//    @And("user is eligible for refund")
//    public void check_refund_eligibility() {
//        request.checkRefundEligibility();
//    }
    
    @Given("user access token is generated")
    public void generate_auth_token() {
        request.getBearerTokenAdmin();
    }

    
    @Given("a Kount session is created")
    public void cerate_session() {
        request.getUser();
    }

    
//
//    @And("Self initiated user can successfully refund the order")
//    public void check_self_initiated_refund_status() {
//        request.checkSelfInitiatedUserRefundStatus();
//    }
//
//    @Given("user access token is generated")
//    public void generate_auth_token() {
//        request.getBearerTokenAdmin();
//    }
//
//    @Given("Generate access token with invalid {string} {string} {string}")
//    public void generate_access_token_with_invalid_and(String clientId, String clientSecret, String userName) {
//        request.getAccessTokenWithInvalidDetails(clientId, clientSecret, userName);
//    }
//
//    @Given("Get SSMA contextual user with invalid {string}")
//    public void get_ssma_contextual_user_with_invalid(String authToken) {
//        request.SSMAGetMeWithInvalidAuthToken(authToken);
//    }
//
//    @Given("user can get list of allergens with total count {string}")
//    public void list_of_allergens(String expectedCount) {
//        request.listOfAllergens(expectedCount);
//    }
//
//    @Then("user cannot get list of allergens with {string} and {string}")
//    public void list_of_allergens_modified(String inputField, String inputData) {
//        request.listOfAllergensModifiedRequest(inputField, inputData);
//    }
//
//    @Given("user authorization is generated")
//    public void user_auth_token() {
//        request.getAuthorization();
//    }
//
//    @Given("user can get category capcity by category ID")
//    public void categories_by_categoryId() {
//        request.getCategoriesByCategoryId();
//    }
//
//    @Then("user cannot get category capcity by category ID with {string} and {string}")
//    public void categories_by_categoryId_modified(String inputField, String inputData) {
//        request.getCategoriesByCategoryIdModifedRequest(inputField, inputData);
//    }
//
//    @Given("user can fetch the menus for single location")
//    public void get_menus_location() {
//        request.getMenusLocation();
//    }
//
//    @Then("user can fetch the menus for single location with valid {string} and {string}")
//    public void get_menus_location_modifiedValid(String inputField, String inputData) {
//        request.getMenusLocationModifiedValidRequest(inputField, inputData);
//    }
//
//    @Then("user try to fetch the menus for single location with invalid {string} and {string} for {string} request")
//    public void get_menus_location_modifiedInValid(String section, String inputField, String inputData) {
//        request.getMenusLocationModifiedInvalidRequest(section, inputField, inputData);
//    }
//
//    @Given("user can get product by product Id")
//    public void products_by_ProductCHainId() {
//        request.getProductsByChainId();
//    }
//
//    @Then("user can get product by product Id with {string} and {string}")
//    public void user_can_get_product_by_product_id_with_and(String inputField, String inputData) {
//        request.getProductsByChainIdModified(inputField, inputData);
//    }
//
//    @Given("user can get list of all locations")
//    public void locations_by_channel() {
//        request.getLocationsByChannel();
//    }
//
//    @Then("user can get list of all locations with valid {string} and {string}")
//    public void locations_by_channel_modified_Valid(String inputField, String inputData) {
//        request.getLocationsByChannelModifiedValidRequest(inputField, inputData);
//    }
//
//    @Then("user cannot get list of all locations with invalid {string} and {string}")
//    public void locations_by_channel_modified_InValid(String inputField, String inputData) {
//        request.getLocationsByChannelModifiedInvalidRequest(inputField, inputData);
//    }
//
//    @Given("user can get location by locationId")
//    public void get_location_by_id() {
//        request.getLocationById();
//    }
//
//    @Then("user can get location by locationId  with invalid {string} and {string}")
//    public void get_location_by_id_modified_Valid(String inputField, String inputData) {
//        request.getLocationByIdModifiedInvalidRequest(inputField, inputData);
//    }
//
//    @Then("user can get location by locationId with valid {string} and {string}")
//    public void get_location_by_id_modified_InValid(String inputField, String inputData) {
//        request.getLocationByIdModifiedValidRequest(inputField, inputData);
//    }
//
//    @Given("user can get Places by placeId")
//    public void get_Places_by_place_id() {
//        request.getPlacesById();
//    }
//
//    @Then("user try get Places by placeId  with invalid {string} and {string}")
//    public void get_Places_by_place_id_modified(String inputField, String inputData) {
//        request.getPlacesByIdModified(inputField, inputData);
//    }
//
//    @Given("user chooses a timeslot")
//    public void get_timeslot() {
//        request.getTimeSlot();
//    }
//
//    @Then("user try to choose a timeslot with invalid {string} and {string} for {string} request")
//    public void get_timeslot_modified(String section, String inputField, String inputData) {
//        request.getTimeSlotModifiedRequest(section, inputField, inputData);
//    }

//    @Then("user try to patch a timeslot with invalid {string} and {string} for {string} request")
//    public void get_timeslot_modified_patch(String section, String inputField, String inputData) {
//        request.getTimeSlotModifiedRequestPatch(section, inputField, inputData);
//    }
//
//    @Given("user can get fulfillment time for single location")
//    public void get_Fulfillment_time() {
//        request.getFullfilmentTime();
//    }
//
//    @Then("user try can get fulfillment time for single location with invalid {string} and {string}")
//    public void get_Fulfillment_time_modifiedInvalid(String inputField, String inputData) {
//        request.getFullfilmentTimeModifiedInvalid(inputField, inputData);
//    }
//
//
//    @Given("user can check the product suggestion")
//    public void get_Product_Suggestion() {
//        request.getProductSuggestion();
//    }
//
//    @Then("user can check the product suggestion with valid {string} and {string}")
//    public void get_Product_Suggestion_ModifiedValid_Request(String inputField, String inputData) {
//        request.getProductSuggestionModifiedValidRequest(inputField, inputData);
//    }
//
//    @Then("user try check the product suggestion with invalid {string} and {string} for {string} request")
//    public void get_Product_Suggestion_ModifiedInvalid_Request(String section, String inputField, String inputData) {
//        request.getProductSuggestionModifiedInvalidRequest(section, inputField, inputData);
//    }
//
//
//    @Then("Order Ahead Data for {string} with {string} {string} {string} in location can be updated")
//    public void order_ahead_data_for_with_in_location_can_be_updated(String platform, String flag, String description,
//                                                                     String internalKey) {
//        request.oderAheadData(platform, flag, description, internalKey);
//    }
//
//    @Then("Invalid Order Ahead Data for {string} with {string} {string} {string} in location can be updated")
//    public void invalid_order_ahead_data_for_with_in_location_can_be_updated_ModifiedRequest(String platform, String flag, String description,
//                                                                                             String internalKey) {
//        request.oderAheadDataModifiedRequest(platform, flag, description, internalKey);
//    }
//
//    @Given("Order Ahead Data in location can be updated")
//    public void order_Ahead_Data() {
//        request.oderAheadData();
//    }
//
//    @Given("user can get possible locations in google")
//    public void get_locations_in_google() {
//        request.getPlacesInGoogle();
//    }
//
//    @Then("user can get possible locations in google with valid {string} and {string}")
//    public void get_locations_in_google_modified_valid(String inputField, String inputData) {
//        request.getPlacesInGoogleModifiedRequestValid(inputField, inputData);
//    }
//
//    @Then("user try get possible locations in google with invalid {string} and {string}")
//    public void get_locations_in_google_modified_invalid(String inputField, String inputData) {
//        request.getPlacesInGoogleModifiedRequestInvalid(inputField, inputData);
//    }
//
//    @Given("user can get all the regions")
//    public void get_all_regions() {
//        request.getAllRegions();
//    }
//
//    @Then("user can get all the regions with valid {string} and {string}")
//    public void get_all_regions_modified_valid(String inputField, String inputData) {
//        request.getAllRegionsModifiedValid(inputField, inputData);
//    }
//
//    @Then("user try to get all the regions with invalid {string} and {string} for {string} request")
//    public void get_all_regions_modified_invalid(String section, String inputField, String inputData) {
//        request.getAllRegionsModifiedInvalid(section, inputField, inputData);
//    }
//
//
//    @Given("user can get geocode using google")
//    public void get_geocode() {
//        request.getGeocode();
//    }
//
//    @Then("user can get geocode with valid {string} and {string}")
//    public void get_geocodeModifiedValid(String inputField, String inputData) {
//        request.getGeocodeModifiedValidRequest(inputField, inputData);
//    }
//
//    @Then("user try to get geocode with invalid {string} and {string} for {string} request")
//    public void get_geocodeModifiedInvalid(String section, String inputField, String inputData) {
//        request.getGeocodeModifiedInvalidRequest(section, inputField, inputData);
//    }
//
//
//    @Given("CMS throttle can be created for a location")
//    public void cms_throttle_location() {
//        request.createCMSThrottle();
//    }
//
//    @Then("CMS throttle created errorneous request for a location with {string} and {string}")
//    public void cms_throttle_location_modified(String inputField, String inputData) {
//        request.createCMSThrottleModifiedRequest(inputField, inputData);
//    }
//
//    @Given("CMS throttle can be updated for a location")
//    public void cms_throttle_update_location() {
//        request.updateCMSThrottle();
//    }
//
//    @Given("CMS throttle updated errorneous request for a location with {string} and {string}")
//    public void updateCMSThrottleModified(String inputField, String inputData) {
//        request.updateCMSThrottleModifedRequest(inputField, inputData);
//    }
//
//    @Given("timeslot options can be obtained")
//    public void timeslot_options() {
//        request.timeslotOptions();
//    }
//
//    @Then("timeslot options errorneous request with {string} and {string}")
//    public void ttimeslot_optionsModified(String inputField, String inputData) {
//        request.timeslotOptionsModifiedRequest(inputField, inputData);
//    }
//
//    @Given("webhook fulfillment time is updated")
//    public void webhook_fulfillment_time() throws JsonSyntaxException, IOException, ParseException {
//        request.webHookFulfillmentTime();
//    }
//
//    @Then("webhook fulfillment time is updated invalid request with {string} and {string}")
//    public void webhook_fulfillment_time_Modified(String inputField, String inputData)
//            throws JsonSyntaxException, IOException {
//        request.webHookFulfillmentTimeModifiedRequest(inputField, inputData);
//    }
//
//    @Then("Provide kitchen status updates about an order")
//    public void provide_kitchen_status_updates_about_an_order() throws JsonSyntaxException, IOException {
//        request.provideKitchenStatus();
//    }
//
//    @Then("Provide kitchen status updates about an order invalid request with {string} and {string}")
//    public void provide_kitchen_status_updates_about_an_order_invalid_request_with_and(String inputFeild, String inputData) throws JsonSyntaxException, IOException {
//        request.provideKitchenStatusModifiedRequest(inputFeild, inputData);
//    }
//
//    @Given("User send message {string} to {string} {string} with {string} {string} {string} {string} {string} for {string}")
//    public void user_SMS_send_message(String commType, String shackId, String phoneNumber, String messageType,
//                                      String handoffMode, String channel, String cmsgtype, String cmsgsource, String vendorDisplayOrderNum) {
//        int shackIdIntValue;
//        try {
//            shackIdIntValue = Integer.parseInt(shackId);
//        } catch (Exception e) {
//            shackIdIntValue = 0;
//        }
//        request.sendSMSMessage(commType, shackIdIntValue, phoneNumber, messageType, handoffMode, channel, cmsgtype,
//                cmsgsource, vendorDisplayOrderNum);
//    }
//
//    @Then("Successfully message delivered with Response code {string}")
//    public void validationOfSuccessfull(String expectedStatusCode) throws InterruptedException {
//        request.validationResponeStatusCode(expectedStatusCode);
//    }
//
//    @Given("a Kount session is created")
//    public void cerate_session() {
//        request.createSession();
//    }
//
//    @Given("a Kount session is created for guest user")
//    public void cerate_session_guestUser() {
//        request.createSessionGestUser();
//    }
//
//    @Given("FP session is created")
//    public void fp_session() throws InterruptedException {
//        request.fpSession();
//    }
//
//    @Then("FP session is created modified {string} and {string}")
//    public void fp_sessionModified(String inputField, String inputData) {
//        request.fpSessionModifiedRequest(inputField, inputData);
//    }
//
//    @Given("get the hosted payment control iframe")
//    public void hosted_payment_control() throws InterruptedException {
//        request.hostedPaymentControl();
//    }
//
//    @Then("hosted payment control iframe created modified {string} and {string}")
//    public void hostedPaymentControlCreatedModified(String inputField, String inputData) {
//        request.hostedPaymentControlModified(inputField, inputData);
//    }
//
//    @Given("generate payment token")
//    public void generate_payment_token() throws InterruptedException {
//        request.generatePaymentToken();
//    }
//
//    @Given("generate payment token with invalid {string}")
//    public void generate_payment_token_with_invalid(String cardData) {
//        request.generatePaymentTokenWithInvalidDetails(cardData);
//    }
//
//
//    @Given("generate card token")
//    public void add_card_token() {
//        request.addCardToken();
//    }
//
//    @Then("generate card token with modified {string} and {string}")
//    public void generateCardTokenModified(String inputField, String inputData) {
//        request.addCardTokenModifiedRequest(inputField, inputData);
//    }
//
//    @Given("new card is found in list of wallet")
//    public void card_in_wallet() {
//        request.getCardInWallet();
//    }
//
//    @Given("Get card in wallet with invalid {string} and its {string}")
//    public void get_card_in_wallet_with_invalid_and_its(String token, String value) {
//        request.getCardInWalletWithInvalidToken(token, value);
//    }
//
//    @Given("then delete the new added card")
//    public void delete_card_in_wallet() {
//        request.deleteCardWallet();
//    }
//
//    @Then("then delete the new added card with {string} getting {string} and {string}")
//    public void invalid_delete_card_in_wallet(String invalidCardDetails, String code, String message) {
//        request.deleteCardWalletWIthInvalidData(invalidCardDetails, code, message);
//    }
//
//    @Given("payment control session key and RSA certificate can be retrieved")
//    public void retreiveSessionKeyCertificate() {
//        request.retrieve_session_key_and_rsacertificate();
//    }
//
//    @Given("payment control session key and RSA certificate can not be retrieved with {string}")
//    public void retreiveSessionKeyCertificateModified(String inputData) {
//        request.retrieveSessionKeyRSACertificateModifiedRequest(inputData);
//    }
//
//    @Then("Verify the {string} and {string}")
//    public void verifyErrorResponse(String expectedStatusCode, String expectedMessage) {
//        request.validationResponeStatusCode(expectedStatusCode);
//        request.validationResponeMessage(expectedMessage);
//
//    }
//
//    @Given("gift card balance can be retrieved")
//    public void gift_card_balance() {
//        request.giftCardBalance();
//    }
//
//    @Then("user cannot get giftcard balance with invalid {string} {string} and {string}")
//    public void user_cannot_get_giftcard_balance_with_invalid_and(String section, String inputFeild, String inputData) {
//        request.getGiftCardBalanceWithInvalidRequest(section, inputFeild, inputData);
//    }
//
//    @Then("user can get gift card balance with {string} and {string}")
//    public void user_can_get_gift_card_balance_with_and(String inputFeild, String inputData) {
//        request.getGiftCardBalanceWithAllHeaders(inputFeild, inputData);
//    }
//
//    @Given("User send email of {string}")
//    public void user_send_email(String emailType) {
//        request.sendEmailMessage(emailType);
//    }
//
//    @Given("User send email of {string} with invalid input {string} and {string}")
//    public void user_send_email_invalid_data(String emailType, String inputFieldName, String inputFieldValue) {
//        request.sendEmailMessageInvalidData(emailType, inputFieldName, inputFieldValue);
//    }
//
//    @Given("User send contact-us email of {string}")
//    public void sendContactUsEmailMessage(String emailType) {
//
//        request.sendContactUsEmailMessage(emailType);
//    }
//
//    @Given("User send contact-us email of {string} with invalid input {string} and {string}")
//    public void user_send_contactUS_email_invalid_data(String emailType, String inputFieldName,
//                                                       String inputFieldValue) {
//        request.sendContactUsEmailMessageInvalidData(emailType, inputFieldName, inputFieldValue);
//    }
//
//    @Given("User send file attached to S3")
//    public void user_send_file_attached_to_s3() {
//        request.sendMessageAttachment();
//    }
//
//    @Given("User provide valid details for Kiosk Heartbeat Login")
//    public void validCenteralizedKioskheartbeatLoginRequest() {
//        request.sendValidCenterlizedLoginRequest();
//    }
//
//    @Then("Login response with Response code {string}")
//    public void validationCenteralizeLoginResponse(String expectedStatusCode) {
//        request.validationResponeStatusCode(expectedStatusCode);
//    }
//
//    @Given("User provide invalid details for Kiosk Heartbeat Login with {string} and {string}")
//    public void invalidCenteralizedKioskheartbeatLoginRequest(String inputFieldName, String inputFieldValue) {
//        request.sendInvalidCenterlizedLoginRequest(inputFieldName, inputFieldValue);
//    }
//
//    @Given("User provide invalid authtoken for Kiosk Heartbeat Login with {string}")
//    public void invalidAuthTokenCenteralizedKioskheartbeatLoginRequest(String inputFieldValue) {
//        request.sendInvalidAuthTokenCenterlizedLoginRequest(inputFieldValue);
//    }
//
//    @Given("User provide valid details for Loging log Post Request")
//    public void validCenteralizedLoginLogRequest() {
//        request.sendValidCenterlizedLoginLogRequest();
//    }
//
//    @Then("Verify Log rquest is accepted and id is generated.")
//    public void verifyCenteralizedLoginLogRequest() {
//        request.validationCenterlizedLoginLogResponse();
//    }
//
//    @Given("User provide invalid details for Centralized Loging log with {string} and {string}")
//    public void invalidCenteralizedLoginLogRequest(String inputFieldName, String inputFieldValue) {
//        request.sendInvalidCenterlizedLoginLogRequest(inputFieldName, inputFieldValue);
//    }
//
//    @Given("User provide invalid authtoken for Centralized Loging log with {string}")
//    public void invalidAuthTokenCenteralizedLogLoginRequest(String inputFieldValue) {
//        request.sendInvalidAuthTokenCenterlizedLogLoginRequest(inputFieldValue);
//    }
//
//    @Given("User send get config details")
//    public void sendGetConfigDetails() {
//        request.sendConfigRequest();
//    }
//
//    @Then("verify the config details")
//    public void verifyConfigDetails() throws IOException {
//        request.verifyConfigResponseDetails();
//    }
//
//    @Then("distribute the offer to {string},{string} and {string}")
//    public void distributeOffer(String type, String value, String mail) {
//        request.distributeOfferRequest(type, value, mail);
//    }
//
//    @Then("distribute the offer with invalid request of  {string} and {string}")
//    public void ddistributeOfferModified(String inputField, String inputData) {
//        request.distributeOfferRequestModified(inputField, inputData);
//    }
//
//    @Given("user able to send card recharge request with {string} and {string}")
//    public void user_able_to_send_card_recharge_request_with_and(String cardNumber, String amount) {
//        soapRequest.giftCardRechargeRequest(cardNumber, amount);
//    }
//
//    @Then("validating the card balance for {string}")
//    public void validating_the_card_balance_for_and(String cardNumber) {
//        soapRequest.giftCardBalanceRequest(cardNumber);
//    }

//    @Then("Admin User perform {string} refund")
//    public void admin_user_perform_refund(String refundType) {
//        request.adminPerformRefund(refundType);
//    }
//
//    @Then("Admin user try to perform refund the order again")
//    public void admin_user_try_to_perform_refund_the_order_again() {
//        request.performRefundAgainAdmin();
//    }
//
//    @Then("Admin user try to refund order with invalid details {string} of {string}")
//    public void admin_user_try_to_refund_order_with_invalid_details_of(String inputData, String section) {
//        request.performRefundAdminInvalid(inputData, section);
//    }
//
//    @Then("User perform {string} refund")
//    public void user_perform_refund(String refundType) {
//        request.userPerformRefund(refundType);
//    }
//
//    @Given("User try to perform Self initiated the order again")
//    public void user_try_to_perform_self_initiated_the_order_again() {
//        request.checkSelfInitiatedUserRefundStatusAgain();
//    }
//
//    @Then("User try to refund order with invalid details {string} of {string}")
//    public void user_try_to_refund_order_with_invalid_details_of(String inputData, String section) {
//        request.performRefundUserInvalid(inputData, section);
//    }
//
//    @Then("User try to refund order with valid details {string} of {string}")
//    public void user_try_to_refund_order_with_valid_details_of(String inputData, String section) {
//        request.performRefundUserValid(inputData, section);
//    }
//
//    @Then("User checking eligible for refund with valid details {string} of {string}")
//    public void user_checking_eligible_for_refund_with_valid_details_of(String inputData, String section) {
//        request.checkRefundEligibilityModifiedValid(inputData, section);
//    }
//
//    @Then("User checking eligible for refund with invalid details {string} of {string}")
//    public void user_checking_eligible_for_refund_with_invalid_details_of(String inputData, String section) {
//        request.checkRefundEligibilityModifiedInvalid(inputData, section);
//    }
//
//    @And("admin try to save manual refund with {string} and {string}")
//    public void save_manual_report_invalid(String section, String inputData) {
//        request.saveManualReportInvalid(section, inputData);
//    }
//
//    @And("refund report try to generated with {string} and {string}")
//    public void refund_report_try_to_generated_with_and(String section, String inputData) {
//        request.checkRefundReportInvalid(section, inputData);
//    }
//    /* Olo webhook  */
//
//    @Given("Olo webhook {string} with valid {string}")
//    public void verify_with_valid(String type, String inputData) {
//        request.oloWebhookWithValidDetails(type, inputData);
//    }
//
//    @Given("Olo webhook {string} reset with valid {string}")
//    public void verify_with_valid_reset(String type, String inputData) {
//        request.oloWebhookWithValidDetails(type, inputData);
//    }
//
//    @Given("Olo webhook {string} with invalid {string} and {string}")
//    public void verify_with_invalid_and(String type, String inputFeild, String inputData) {
//        request.oloWebhookWithInvalidDetails(type, inputFeild, inputData);
//    }
//
//    /* SSMA - Kiosk out of service */
//
//
//    @Given("kiosk user access token is generated")
//    public void generate_kiosk_auth_token() {
//        request.getKioskBearerToken();
//    }
//
//    @Then("Kiosk Out of Service API collection for valid reason with valid {string}")
//    public void kiosk_out_of_service_api_collection_for_valid_reason_with_valid(String inputData) {
//        request.verifySSMAKioskOutOfServiceWithvalidReason(inputData);
//    }
//
//    @When("user can get ssma global settings")
//    public void user_can_get_ssma_global_settings() {
//        request.getSSMAGlobalSettings();
//    }
//
//    @Given("verify list of Kiosk_out_of_service_reason_codes with {string}")
//    public void verify_list_of_kiosk_out_of_service_reason_codes(String reason) {
//        request.verifyKioskOutOfServiceReasonCodes(reason);
//    }
//
//    @When("user getting setting details for {string} and {string}")
//    public void user_getting_setting_details_for_and(String userName, String password) {
//        request.getSetting(loader.getProperty(userName), loader.getProperty(password));
//    }
//
//    @Then("Kiosk Out of Service API collection for valid reason with Invalid {string}")
//    public void kiosk_out_of_service_api_collection_for_valid_reason_with_invalid(String inputData) {
//        request.verifySSMAKioskOutOfServiceWithInvalidReason(inputData);
//    }
//
//    @When("Kiosk Out of Service API collection for Invalid {string} and {string}")
//    public void kiosk_out_of_service_api_collection_for_invalid_and(String inputFeild, String inputData) {
//        request.verifySSMAKioskOutOfServiceWithInvalidInputData(inputFeild, inputData);
//    }
//
//    @Then("Setup location of open store")
//    public void setup_location_of_open_store() {
//        request.setUpOpenStoreVendorId();
//    }
//
//    @Then("Setup menu of open store")
//    public void setup_menu_of_open_store() {
//        request.setMenusLocation();
//    }
//
//    @Given("user can get Places by place")
//    public void get_Places_by_place() {
//        request.getPlacesByLocation(loader.getProperty("deliveryAddress"));
//    }
//
//    @Given("user can get estimates with Address")
//    public void user_get_estimates() {
//        request.calculateDeliveryDetailsUsingAddress();
//    }
//
//    @Given("User get autherized")
//    public void user_get_autherized() {
//        request.clearAuth0Cookies();
//        request.authorizationGetRequest();
//    }
//
//    @Then("User entering username {string}")
//    public void user_entering_username(String username) {
//        request.loginIdentifierPostRequest(username);
//    }
//
//    @Then("User add password {string}")
//    public void user_add_password(String password) {
//        request.loginPasswordPostRequest(password);
//    }
//
//    @Then("Autherization code generated")
//    public void autherization_code_generated() {
//        request.auth0Token();
//    }
//
//    @Given("Exisitng User is logged into application")
//    public void Existing_user_logged_into_application() {
//        request.pickExisitngUserFromList();
//        request.OLOProxyRequestExisitngUser();
//    }
//
//    @Given("user able to get all build details")
//    public void userAbleToGetAllBuildDetails() {
//        request.getFileIdFromSauceLabs("ios", "Staging Backend", "174");
//    }
//
//    @Then("user validates the details")
//    public void userValidatesTheDetails() {
//        System.out.println(loader.getProperty("buildid"));
//    }
//
//    @Given("user uber access token is generated")
//    public void userUberAccessTokenIsGenerated() {
//        request.uberAccessToken();
//    }
//
//    @Then("user create uber delivery quote")
//    public void userCreateUberDeliveryQuote() throws IOException {
//        request.uberCreateDeliveryQuote();
//    }
//
//    @Then("user create uber delivery")
//    public void userCreateUberDelivery() throws IOException {
//        request.uberCreateDeliveryOrder();
//    }
//
//    @Then("user cancel uber delivery")
//    public void userCancelUberDelivery() {
//        request.uberCancelDeliveryOrder();
//    }
//
//    @And("user get uber delivery details for {string}")
//    public void userGetUberDeliveryDetailsFor(String status) {
//        request.uberGetDeliveryStatus(status);
//    }
//
//    @And("Daas refund report can be generated")
//    public void daasRefundReportCanBeGenerated() {
//        request.checkDassRefundReport();
//    }
//
//    @And("Daas refund report try to generated with {string} and {string}")
//    public void daasRefundReportTryToGeneratedWithAnd(String section, String inputData) {
//        request.checkDaasRefundReportInvalid(section, inputData);
//        System.out.println(loader.getProperty("androidbuildid"));
//    }
//
//    @Then("user can get autocomplete session in google with valid {string} and {string}")
//    public void gettingautoCompleteGoogleLocationSession(String inputField, String inputData) {
//        request.getautoCompleteGoogleLocationSession(inputField, inputData);
//    }

//    @Then("user can get autocomplete session in google with invalid {string} and {string}")
//    public void gettingautoCompleteGoogleLocationSessionInvalid(String inputField, String inputData) {
//        request.getautoCompleteGoogleLocationSessionInvalid(inputField, inputData);
//    }
//
//    @Given("User creates delivery estimates with goggleSession")
//    public void user_get_estimates_goggleSessionID() {
//        request.calculateDeliveryDetailsUsingAddressgoggleSessionID();
//    }
//
//    @Then("get valid request for toggle status with the {string}")
//    public void gettingToggleStatusValidRequest(String inputData) throws IOException {
//        request.getToggleStatusValidRequest(inputData);
//    }
//
//    @Then("get invalid request for toggle status with {string} and {string}")
//    public void gettingToggleStatusInvalidRequest(String section, String inputData) throws IOException {
//        request.getToggleStatusInvalidRequest(section, inputData);
//    }
//
//    @Then("get valid request for store toggle detail status with the {string}")
//    public void gettingToggleDetailStatusValidRequest(String inputData) throws IOException {
//        request.getToggleDetailStatusValidRequest(inputData);
//    }
//
//    @Then("get invalid request for detail toggle status with {string} and {string}")
//    public void gettingDetailToggleStatusInvalidRequest(String section, String inputData) throws IOException {
//        request.getDetailToggleStatusInvalidRequest(section, inputData);
//    }
//
//    @Then("update toggle status with the {string} and validate {string}")
//    public void updatingToggleStatusRequest(String inputData, String expectedData) throws IOException {
//        request.updateToggleStatusRequest(inputData, expectedData);
//    }
//
//    @Then("update toggle invalid request for {string} and {string}")
//    public void updatingToggleStatusInvalidRequest(String section, String inputData) throws IOException {
//        request.updateToggleStatusInvalidRequest(section, inputData);
//    }
//
//    @Then("get toggle log request and validate {string}")
//    public void gettingToggleLogStatusRequest(String inputData) throws IOException {
//        request.getToggleLogStatusRequest(inputData);
//    }
//
//    @Then("get toggle invalid request for {string} and {string}")
//    public void gettingToggleLogInvalidRequest(String section, String inputData) throws IOException {
//        request.getToggleLogInvalidRequest(section, inputData);
//    }
//
//    @Then("update the store closure status with {string}")
//    public void updatingClousreStatusRequest(String inputData) throws IOException {
//        request.updateClosureStatusRequest(inputData);
//    }
//
//    @Then("get the store closure status with {string}")
//    public void gettingClousreStatusRequest(String inputData) throws IOException {
//        request.getClosureStatusRequest(inputData);
//    }
//
//    @Then("Verify Store status and close the store")
//    public void checkingStoreStatusClose() throws IOException {
//        String closeStatus = "false";
//        String closeStore = "is_available: false,reason: Event,sub_reason: Other,details: automationTestOpen";
//        String result = request.getStoreStatusRequest();
//        if (!result.equalsIgnoreCase(closeStatus)) {
//            if (!result.equalsIgnoreCase(closeStatus)) {
//                request.updateClosureStatusRequest(closeStore);
//            }
//        }
//    }
//
//    @Then("Verify Store status and open the store")
//    public void checkingStoreStatusOpen() throws IOException {
//        String openStatus = "true";
//        String openStore = "is_available:true,details:automationTestOpen,is_twelve_plus_hours_closed:false,is_store_damged_over_10K:false  ";
//        String result = request.getStoreStatusRequest();
//        if (!result.equalsIgnoreCase(openStatus)) {
//            request.updateClosureStatusRequest(openStore);
//        }
//    }
//
//    @Then("Try to update status to {string} when it is already in same status")
//    public void consecutiveClosureRequest(String inputData) throws IOException {
//        String closeStoreString = "is_available: false,reason: Safety,sub_reason: Team Illness,details: automationTestOpen";
//        String openStoreString = "is_available:true,details:automationTestOpen,is_twelve_plus_hours_closed:false,is_store_damged_over_10K:false";
//        if (inputData.equalsIgnoreCase("open")) {
//            checkingStoreStatusOpen();
//            request.updateClosureStatusConsecutiveRequest(inputData, openStoreString);
//        } else {
//            checkingStoreStatusClose();
//            request.updateClosureStatusConsecutiveRequest(inputData, closeStoreString);
//        }
//    }
//
//    @Then("get invalid request for closure status with {string} and {string}")
//    public void gettingClosureStatusInvalidRequest(String section, String inputData) throws IOException {
//        String closeStoreString = "is_available: false,reason: Safety,sub_reason: Team Illness,details: automationTestOpen";
//        String openStoreString = "is_available:true,details:automationTestOpen,is_twelve_plus_hours_closed:false,is_store_damged_over_10K:false";
//        if (section.contains("open")) {
//            checkingStoreStatusClose();
//        } else if (section.contains("close")) {
//            checkingStoreStatusOpen();
//        }
//        request.getClosureStatusInvalidRequest(section, inputData);
//    }
//
//    @Given("get closure report and verify details")
//    public void gettingClosureReportValidRequest() {
//        request.getClosureReportValidRequest();
//    }
//
//    @Then("get invalid request for closure report with {string} and {string}")
//    public void gettingClosureReportInvalidRequest(String section, String inputData) throws IOException {
//        request.getClosureReportInvalidRequest(section, inputData);
//    }
//
//
//    @Then("performing store pre-condition setup as {string} for toggle validation")
//    public void togglePreconditionSetup(String inputData) throws Exception {
//        Object result = request.getStoreToggleStatusRequest();
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(result);
//        org.json.JSONObject resultJsonObject = new org.json.JSONObject(jsonString);
//        String[] data = inputData.split(",");
//        List<String> dataList = Arrays.asList(data);
//        HashMap<String, Boolean> compareResult = new HashMap<>();
//        HashMap<String, Boolean> expectedData = new HashMap<>();
//        dataList.stream().forEach((d) -> {
//            String field = d.split(":")[0];
//            String value = d.split(":")[1];
//            expectedData.put(field, Boolean.valueOf(value));
//            String responseData = String.valueOf(resultJsonObject.get(field));
//            compareResult.put(field, value.equalsIgnoreCase(responseData));
//        });
////        web_order_ahead_killswitch:false,mobile_order_ahead_killswitch:false,is_order_ahead_available:true,is_mobile_order_ahead_available:true
//        String storeName = loader.getProperty("toggleLocationName");
//        if (!compareResult.get("web_order_ahead_killswitch") || !compareResult.get("mobile_order_ahead_killswitch")) {
//            ssmaUtils.setKillSwitchToggleChanges(expectedData.get("web_order_ahead_killswitch"), expectedData.get("mobile_order_ahead_killswitch"), storeName);
//        }
//        String orderAheadWebClose = "order_ahead_off_web_reason:2,is_web_order_ahead_available:false,web_order_ahead_reset_time:75";
//        String orderAheadWebOpen = "is_web_order_ahead_available:true";
//        String orderAheadAppClose = "order_ahead_off_mobile_reason:2,is_mobile_order_ahead_available:false,mobile_order_ahead_reset_time:75";
//        String orderAheadAppOpen = "is_mobile_order_ahead_available:true";
//        if (!compareResult.get("is_order_ahead_available")) {
//            if (expectedData.get("is_order_ahead_available")) {
//                request.setStoreToggleStatusRequest(orderAheadWebOpen);
//            } else {
//                request.setStoreToggleStatusRequest(orderAheadWebClose);
//            }
//        }
//        if (!compareResult.get("is_mobile_order_ahead_available")) {
//            if (expectedData.get("is_mobile_order_ahead_available")) {
//                request.setStoreToggleStatusRequest(orderAheadAppOpen);
//            } else {
//                request.setStoreToggleStatusRequest(orderAheadAppClose);
//            }
//        }
//    }
//    @Then("user get WorkDay authorization")
//    public void gettingWorkDayAutherization() throws IOException {
//        request.getWorkDayAutherization();
//    }
//    @Then("user get WorkDay profile details")
//    public void gettingWorkDayProfile() throws IOException {
//        request.getWorkDayProfile();
//    }


}
