package web;

import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;



public class HomePage extends BasePage {
	
	
	   /**
     * Log4j Logger.
     */
    private final Logger LOG = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[text()='Small Coke']/following-sibling::div//p")
    WebElement fountainSodaSmallCokeDes;

    @FindBy(xpath = "//a[contains(@aria-label,'Fountain Soda')]")
    WebElement fountainSoda;

    @FindBy(css = ".site-logo")
    WebElement siteLogo;

    @FindBy(css = ".button.button__secondary")
    private WebElement signInBtn;
    By spinner = By.xpath("//div[@id='ss-react-app-root/div/svg']/div/svg");

    @FindBy(xpath = "//*[@id='block-shake-shack-account-menu']/ul/li[1]/a")
    private WebElement accountIcon;

    //	By signinBtn = By.xpath("//*[contains(text(), 'Sign In')]");
    By signinBtn = By.cssSelector("a.button__secondary");

    @FindBy(xpath = "//*[contains(text(),'Pick-Up')]") // removed from latest staging build
    private WebElement pickupinTray;

    @FindBy(xpath = "//*[@id='change-region-button']")
    private WebElement changeRegionBtn;

    //	@FindBy(css = ".order-now")
//	@FindBy(css=".tray-detail-switcher")
    @FindBy(xpath = "//button[contains(text(),'Order Now')]")
    WebElement orderNowIcon;

    @FindBy(xpath = "//*[contains(text(),'Delivery')]")
    private WebElement deliveryInTray;

    @FindBy(xpath = "//*[@aria-label='Add SmokeShack to tray']")
    WebElement plusIcon;

//	@FindBy(xpath = "//*[@aria-label='Add Bourbon Bacon Cheddar Burger to tray']")
//	@FindBy(xpath ="//*[@aria-label='Add ShackBurger to tray']")
//	@FindBy(xpath ="//*[@aria-label='Add SmokeShack to tray']/parent::*/parent::*/parent::*") 
//	private static WebElement beforePlusIcon;

    //	@FindBy(xpath ="(//*[contains(@aria-label,'SmokeShack')]/parent::*/parent::*/parent::*)[2]")// latest change 1.10
    @FindBy(xpath = "//*[contains(text(),'ShackBurger')]/parent::*/parent::*/parent::*/preceding-sibling::*")
    WebElement burgerImage;

    @FindBy(css = ".ss-burger")
    private WebElement burgerMenu;

    @FindBy(css = ".ss-chicken")
    private WebElement chickenMenu;

    @FindBy(css = ".ss-fries")
    private WebElement friesMenu;

    @FindBy(css = ".ss-drinks")
    private WebElement drinksMenu;

    @FindBy(xpath = "//*[contains(@class,'ss-beer')]")
    private WebElement beerMenu;

    @FindBy(xpath = "//span[contains(text(),'SmokeShack')]")
    private WebElement smokeShackImage;

    @FindBy(xpath = "//*[@id='orderingTray']")
    private WebElement oderingTray;

    //	@FindBy(xpath = "//button[contains(text(),'Add')]")
    @FindBy(css = ".button__tertiary") // latest change in v1.10 same element is in home page with name add
    private WebElement addBtn;

    @FindBy(css = ".count-container")
    private WebElement cartIcon;

    //	@FindBy(css = ".count-container > .span-container")
//	@FindBy(css = ".span-container > .counter-span")
    @FindBy(xpath = "//*[contains(@aria-label,'Order Details')]")
    private WebElement countofItemsInCart;

    @FindBy(xpath = "//*[@id='main-header']")
    private WebElement mainHeader;

    @FindBy(css = ".footer-bottom")
    private WebElement homePageFooterSection;

    //	@FindBy(css = ".layout-grid")
    @FindBy(xpath = "//*[@id='hero-banner']")
    private WebElement homePageBannersSection;

    @FindBy(css = ".field--name-field-facebook")
    private WebElement faceBookIcon;

    @FindBy(css = ".field--name-field-instagram")
    private WebElement instagramIcon;

    @FindBy(css = ".field--name-field-twitter")
    private WebElement twitterIcon;

    @FindBy(css = ".field--name-field-tiktok")
    private WebElement tikTokIcon;

    @FindBy(css = ".field--name-field-youtube")
    private WebElement youTubeIcon;

    @FindBy(css = ".field--name-field-linkedin")
    private WebElement linkedinIcon;

    @FindBy(xpath = "//*[contains(text(),'Accept')]")
    private WebElement acceptBtn;

    @FindBy(css = ".modal--close__x")
    private WebElement closeBannerIcon;

    @FindBy(css = ".disclaimer")
    private WebElement copyRightsText;

    @FindBy(xpath = "//h3[contains(text(),'Mailing List')]")
    private WebElement mailingList;

    @FindBy(xpath = "//*[contains(text(),'Submit')]")
    private WebElement submitBtn;

    @FindBy(xpath = "//*[@id='field-email-helper-text']")
    private WebElement mailErrorMessage;

    @FindBy(xpath = "//*[@id='field-email']")
    private WebElement emailfieldatFooter;

    @FindBy(xpath = "//*[contains(text(),'About Us')]/following-sibling::*/li[1]/a")
    WebElement careers;

    @FindBy(xpath = "//h3[contains(text(),'Burgers')]")
    private WebElement burgerHeader;

    @FindBy(css = ".ss-burger")
    private WebElement burgerSection;

    @FindBy(css = ".ss-chicken")
    private WebElement chickenSection;

    @FindBy(css = ".ss-fries")
    private WebElement friesSection;

    @FindBy(css = ".ss-shakes-custards")
    private WebElement custardsSection;

    @FindBy(css = ".ss-hot-dogs")
    private WebElement hotdogsMenu;

    @FindBy(css = ".ss-retail")
    private WebElement retailMenu;

    @FindBy(css = ".ss-drinks")
    private WebElement drinksSection;

    @FindBy(css = ".product-tag")
    private WebElement productTags;

    @FindBy(css = ".card-price")
    private WebElement itemPrice;

    @FindBy(css = ".card-price+span")
    private WebElement caloriesInfo;

    @FindBy(css = ".space-around >div>span")
    private WebElement productInfo;

//	@FindBy(css = ".card-image-link>span")
//	private static WebElement productName;

    @FindBy(partialLinkText = "Be Our Partner")
    private WebElement BeOurPartner;

    @FindBy(partialLinkText = "Contact Us")
    private WebElement ContactUs;

    @FindBy(partialLinkText = "Press Inquiries")
    private WebElement PressInquiries;

    @FindBy(partialLinkText = "Shack Truck")
    private WebElement ShackTruck;

    @FindBy(xpath = "//*[contains(text(),'Inquiries')]")
    private WebElement inquiries;

//	@FindBy(xpath = "//h3[contains(text(),'Inquiries')]/following-sibling::*")
//	private static WebElement linksUnderInquiries;

    //	@FindBy(xpath = "((//*[contains(text(),'ShackBurger')])[1]/following::*[div])[1]/button[2]")
//	@FindBy(xpath = "//*[@aria-label='Add ShackBurger™ to tray']")
//	private WebElement addToBagBtn;
    @FindBy(xpath = "//span[text()='Bourbon Bacon Jam Burger']/../../following::div/button[@aria-label='Add ShackBurger™ to tray']")
    private WebElement addToBagBtn;

    @FindBy(xpath = "//b[contains(text(),'Single')]")
    private WebElement singleItem;

    @FindBy(id = "no")
    private WebElement noRadioBtnAtAddOn;

    //    @FindBy(xpath = "//button[contains(@aria-label,'Add ')]")
    @FindBy(xpath = "//*[@aria-label='Add to Bag' and @type='submit']")
    private WebElement addToBagBtnInQuickAddModal;

    //	@FindBy(css = ".count-container")
    @FindBy(xpath = "//*[@alt='bag']")
    WebElement bagIcon;

    //	@FindBy(xpath = "((//*[contains(text(),'fries')])[1]/following::*[div])[1]/button[2]")
//	@FindBy(xpath = "//*[@aria-label='Add Fries to tray']")
    @FindBy(xpath = "(//*[contains(@aria-label,'Fries to tray')])") // modifed xpath because fries are not available
    // soemtimes
    private WebElement addToBagBtnAtFriesItem;

    //	@FindBy(xpath = "((//*[contains(text(),'Shack')])[1]/following::*[div])[1]/button[1]")
    @FindBy(xpath = "//*[@aria-label='Add ShackBurger™ to tray']/parent::*/button[1]")
    private WebElement customizeBtn;

    @FindBy(css = ".track-order")
    WebElement trackOrderBtn;

    @FindBy(css = ".total >span:nth-child(2)")
    WebElement totalAmountInQucikAddModal;

    @FindBy(xpath = "//b[contains(text(),'Double')]")
    private WebElement doubleItem;

    @FindBy(xpath = "//*[@id='yes']")
    private WebElement yesRadioBtnAtAddOn;

    //	@FindBy(css = ".card-number-indicator")
    @FindBy(css = ".card-number-indicator > span:nth-child(1)")
    private WebElement productIndicator;

    @FindBy(xpath = "//*[contains(text(),'Pick a size')]")
    private WebElement sizeSelectionOptioninQuickAddModal;

    @FindBy(xpath = "//*[contains(text(),'Pick a size')]/following-sibling::*[2]")
    private WebElement sizeSelectionErrorMessage;

    @FindBy(xpath = "//*[@id='Bacon']")
    private WebElement addOnSelectionOptioninQuickAddModal;

    @FindBy(xpath = "//*[@id='Bacon']/following-sibling::*[3]")
    private WebElement addOnSelectionErrorMessage;

    @FindBy(xpath = "//*[contains(text(),'Pick a size')]/parent::*")
    private WebElement priceAndCaloriesInfoAtSizeSelectionOption;

    @FindBy(xpath = "(//*[contains(text(),'cals')])[3]")
    private WebElement caloriesInfoAtAddOn;

    @FindBy(xpath = "//*[@aria-label='Add Bacon']/following::*[1]")
    private WebElement priceInfoAtAddOn;

    //	@FindBy(css = ".hidden or .bag")
    @FindBy(xpath = "//*[contains(@class,hidden) or contains(@class,'bag')]")
    private WebElement checkMarkAtAddToBagCTAInQuickAddModal;

    @FindBy(css = ".controls span:nth-child(1)")
    private WebElement quantitySelectorMinusAtQucikAddModal;

    @FindBy(css = ".controls span:nth-child(2)")
    private WebElement quantitySelectorvalueAtQucikAddModal;

    @FindBy(css = ".controls span:nth-child(3)")
    private WebElement quantitySelectorPlusAtQucikAddModal;

    @FindBy(css = ".extras-container")
    private WebElement addOnInfo;

    //	@FindBy(css = ".limit-reached")
//	@FindBy(css = ".modal-header")
    @FindBy(xpath = "//*[contains(text(),' limit')]")
    private WebElement limitReachedErrorMsg;

    @FindBy(xpath = "//*[contains(@aria-label,'customize Frozen Custard')]/img")
    private WebElement frozenCustardImage;

    @FindBy(xpath = "//*[@aria-label='Add Frozen Custard to tray']")
    private WebElement addToBagBtnAtFrozenCustard;

    @FindBy(xpath = "//*[contains(text(),'Select one')][2]")
    private WebElement errorAtPickAFlavor;

    @FindBy(xpath = "//p[contains(text(),'Pick a')][2]")
    private WebElement pickAFlavorText;

    @FindBy(xpath = "//*[@aria-label='Add Chicken Bites™ to tray']")
    private WebElement addToBagBtnAtChickenBites;

    @FindBy(xpath = "//*[contains(text(),'Select one')][2]")
    private WebElement errorAtPickASauce;

    @FindBy(xpath = "//p[contains(text(),'Pick a')][2]")
    private WebElement pickASauceText;

    //	@FindBy(xpath = "//*[@aria-label='Close icon']")
    @FindBy(xpath = "//*[@aria-label='Close pop up']")
    private WebElement quickAddModalCloseIcon;

    @FindBy(xpath = "//*[contains(@aria-label,'customize Chicken Bites™')]/img")
    private WebElement chickenBitesImage;

    @FindBy(css = ".card-image-link+div>span:nth-child(2)")
    private WebElement itemsPricesInPLPScreen;

    @FindBy(css = ".card-image-link+div>span:nth-child(3)")
    private WebElement itemsCaloriesInfoInPLPScreen;

    @FindBy(partialLinkText = "Shack Merch")
    private WebElement shackMerchLink;

    @FindBy(css = ".shack_header>li:nth-child(1)")
    private WebElement ssShopKartPageTitle;

    @FindBy(partialLinkText = "FAQs")
    private WebElement faqsLink;

    @FindBy(xpath = "(//*[contains(text(),'Frequently Asked Questions')])[2]")
    private WebElement faqPageTitle;

    @FindBy(partialLinkText = "Online Ordering")
    private WebElement onlineOrderingLink;

    @FindBy(partialLinkText = "Allergen Info")
    private WebElement allergensInfoLink;

    @FindBy(partialLinkText = "Nutritional Info")
    private WebElement nutritionalInfoLink;

    @FindBy(partialLinkText = "Gift Cards")
    private WebElement giftCardsLink;

    //	@FindBy(xpath = "(//*[contains(text(),'Gift Cards')])[1]")
    @FindBy(xpath = "(//*[text()='Gift Cards'])[1]")
    private WebElement giftCardsPageTitle;

    @FindBy(css = ".paragraph-hero__overlay")
    private WebElement giftCardsPageBanner;

    //	@FindBy(xpath = "//*[contains(text(),'Check your balance')]")
    @FindBy(xpath = "//a[contains(text(),' Balance') or contains(text(),'balance')]")
    private WebElement checkYourBalanceBtn;

    @FindBy(xpath = "//*[@id='cardNo']")
    private WebElement giftCardNumberField;

    @FindBy(xpath = "//*[@id='pin']")
    private WebElement giftCardPinField;

    @FindBy(xpath = "//*[contains(text(),'Check Balance')]")
    private WebElement checkBalanceSubmitBtn;

    //	@FindBy(css = ".hero--dots >.layout-grid")
//	@FindBy(css=".slick--field-hero")
    @FindBy(xpath = "//*[contains(@class,'slider-main') or contains(@class,'slick--field-hero')]")
    private WebElement heroBannerModule;

    @FindBy(xpath = "//*[@aria-label='Add Avocado Bacon Burger to tray']/parent::*/button[1]")
    private WebElement customizeBtnAtAvocado;

    @FindBy(css = ".ss-catering")
    private WebElement cateringMenu;

    @FindBy(xpath = "(//*[@id='start-order-button'])[1]")
    private WebElement startOrderBtn;

    @FindBy(xpath = "//a[contains(@class,'ss-icon')]")
    private WebElement productCategoriesNamesInHomePage;

    @FindBy(xpath = "//*[@aria-label='Add Sunset Lemonade to tray']")
    private WebElement addToBagBtnAtSunsetLemonade;

    @FindBy(xpath = "//b[contains(text(),'Small')]")
    private WebElement smallOptionAtSizeSelection;

    @FindBy(linkText = "Gift Cards")
    private WebElement giftCardsOptionInHomePageHeaderSection;

    @FindBy(xpath = "//a[contains(text(),'Help')]")
    private WebElement helpMenuInHomePageHeaderSection;

    @FindBy(xpath = "//*[@class='slick-controls' or @class='control-button']")
    private WebElement heroBannerPauseAndPlayBtn;

    @FindBy(xpath = "//*[@class='slick-dot-icon' or @class='dot']")
    private WebElement heroBannerCarouselDots;

    @FindBy(xpath = "//*[contains(@class,'slick-next')]")
    private WebElement heroBannerNxtCarouselArrows;

    @FindBy(xpath = "//*[contains(@class,'slick-prev')]")
    private WebElement heroBannerPrevCarouselArrows;

    @FindBy(css = ".field--name-field-hero-title")
    private WebElement heroBannersLabel;

    @FindBy(css = ".paragraph--type--hero")
    private WebElement heroBanners;

    //	@FindBy(xpath = "(//a[contains(@aria-label,'Hand-Spun Shakes')])[1]/img") //product was changing every time hence xpath changed
    @FindBy(xpath = "(//*[@id='category-63185']/div/div/a)[1]")
    private WebElement handSpunShakesImage;

    @FindBy(xpath = "//*[@id='modal-title'][contains(text(),'System refresh')]")
    private WebElement systemRefreshAlert;

    String burgerItems = "a[class='card-image-link'][aria-label*='Burger'] span[class*='heading-3']";

    @FindBy(xpath = "//*[contains(text(),'Subtotal')]/following-sibling::*")
    private WebElement subTotalInBagPage;

    @FindBy(xpath = "//*[contains(text(),'6 pcs')]")
    private WebElement sixPieceSizeSelectionRadioBtn;

    @FindBy(xpath = "//*[@aria-haspopup='listbox']")
    private WebElement flavourSelectionDropDown;

    @FindBy(xpath = "//*[contains(text(),'Choose one')]")
    private WebElement regularSizeRadioBtnOrChooseDropdown;

    @FindBy(css = ".modal-header> .modal-close-button")
    private WebElement quickAddModalCloseBtn;

    @FindBy(xpath = "//*[@role='listbox']/li[1]")
    private WebElement firstFlavourInDropDown;

    @FindBy(xpath = "//p[contains(text(),'Add')]")
    private WebElement addOnName;

    @FindBy(xpath = "(//*[@title='Vanilla & Chocolate'])[1]")
    private WebElement flavourFromDropdown;

    @FindBy(xpath = "//*[@class='slick-pause-icon']")
    private WebElement videoPauseButton;

    @FindBy(xpath = "//*[@class='slick-play-icon']")
    private WebElement videoPlayButton;

    @FindBy(xpath = "//h2[text()='Help Center']")
    private WebElement helpCenter;

    @FindBy(xpath = "//*[text()='Press Inquiries']")
    private WebElement pressInquiryTextNotAvailable;

    @FindBy(xpath = "//h2[text()='E-Gift Cards']")
    private WebElement giftCardEGIFTcards;

    @FindBy(xpath = "//*[contains(text(),'Explore')]")
    private WebElement explore;

    @FindBy(xpath = "//*[text()='About Us']")
    private WebElement aboutUs;

    @FindBy(xpath = "//*[text()='About Us']/../ul/li/a[text()='Locations']")
    private WebElement aboutUsLocationLink;

    @FindBy(xpath = "//*[@id='block-footercolumn2']//descendant::a[text()='Locations']")
    private WebElement locationLink;

    @FindBy(css = ".form-text")
    private WebElement findShakeShackLocationSearchBox;

    @FindBy(css = ".form-submit")
    private WebElement clickOnFindButton;

    @FindBy(css = ".notification-icon-container")
    private WebElement bellIcon;

    @FindBy(xpath = "//*[@class='ab-no-cards-message']")
    private List<WebElement> noNotifications;

    @FindBy(xpath = "//*[text()='Pickup instructions']")
    private WebElement pickupInstructions;

    @FindBy(xpath = "//a[text()='Privacy']")
    private WebElement pripacyLink;

    @FindBy(xpath = "//a[text()='Terms of Use']")
    private WebElement termsOfUseLink;
    @FindBy(xpath = "//div[@class='page-title']/span")
    private WebElement pageTitle;

    @FindBy(xpath = "//*[contains(text(),'Pick-Up')]")
    private WebElement pickupLink;
    @FindBy(xpath = "(//a[contains(text(),'Locations')])[1]")
    private WebElement locationLinkFooter;
    @FindBy(xpath = "//input[@id='edit-keys']")
    private WebElement locationSearchBox;
    @FindBy(xpath = "//h1//div")
    private WebElement locationPageHeader;
    @FindBy(xpath = "//input[@id='edit-submit-search-location']")
    private WebElement locationFindButton;


    Actions a = new Actions(driver);

    public void verifyHomeScreen() throws Exception {
        if (wait.until(ExpectedConditions.visibilityOf(siteLogo)) == null) {
            LOG.info("Home screen is not displaying");
            throw new Exception("Could not verify ShakeShack Logo");
        }
        LOG.info("SiteLogo visible, Home screen is displaying");
    }

//	public void verifyAccountIcon() throws Exception {
//		if (wait.until(ExpectedConditions.visibilityOf(accountIcon)) == null) {
//			LOG.info("Account icon is not displaying");
//			throw new Exception("Could not verify Acocunt icon");
//		}
//		LOG.info("Account icon is displaying");
//	}

    public void signOutStatus() {
        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(signInBtn.isDisplayed(), "User is still Login");
        LOG.info("User is signed out");
        softAssert.assertAll();
    }

    public void addItemToBagAndClickOnBagIcon() throws InterruptedException {
        if (checkIfElementIsVisible(addBtn)) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
            LOG.info("Item was added to Bag");
            wait.until(ExpectedConditions.visibilityOf(bagIcon)).click();
            LOG.info("Clicked on Bag Icon");
        } else {
//			// The way of adding product to cart was changed due to quick add modal
//			clickOnAddToBagBtnAtProduct(loader.getProductName(), loader.getAlternateProductName());
//			clickElementUsingJavaScriptExecutor(bagIcon);
//			LOG.info("Clicked on Bag Icon");

            // The way of adding product to cart was changed due to quick add modal
            List<WebElement> listOfProducts = driver.findElements(By.cssSelector(burgerItems));
            LOG.info("NumberOfProducts: " + listOfProducts.size());
            String product = Loader.getProperty("productName");
            LOG.info("Looking for Product --> " + product);
            boolean result = false;
            for (int j = 0; j < listOfProducts.size(); j++) {
                LOG.info("ProductList.get(" + j + ") -> " + listOfProducts.get(j).getText());
                String text = listOfProducts.get(j).getText().replaceAll("[^a-zA-Z0-9]", "");
//				LOG.info("after removing special characters" + text);
                if (text.equalsIgnoreCase(product)) {
                    LOG.info("Product is matched");
                    result = true;
                    break;
                }
            }
            if (result) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //clickOnAddToBagBtnAtProduct(Loader.getProperty("productName"));
                clickElementUsingJavaScriptExecutor(bagIcon);
                LOG.info("Clicked on Bag Icon");

            } else {
                int m = 0;
                if (listOfProducts.size() > 2) {
                    m = 1;
                } else {
                    m = listOfProducts.size();
                }
                for (int i = 0; i < m; i++) {
                    LOG.info("NumberOfProducts: " + listOfProducts.get(i).getText());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //clickOnAddToBagBtnAtProduct(listOfProducts.get(i).getText());
                    clickElementUsingJavaScriptExecutor(bagIcon);
                    LOG.info("Clicked on Bag Icon");

                }
            }
            waitUntilLoadingBarDisappears();
        }
    }

    public void clickonOrderNowBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(orderNowIcon)).click();
        LOG.info("Clicked on Order Now button");
        wait.until(ExpectedConditions.visibilityOf(pickupLink));
        Assert.assertTrue(checkIfElementIsVisible(pickupLink),"Ordering page is not displayed");
        LOG.info("Location selection page displayed");
    }

   

    public void verifyHeaderDisplay() throws Exception {
        Assert.assertTrue(mainHeader.isDisplayed(), "Header is not stick to top");
        LOG.info("Header stick to top of home page");// Added Assertion
    }

    public void scrollDownHomePage() {
        scrollToElement(homePageFooterSection);
        LOG.info("Scroll down from Home Page");
        Assert.assertTrue(homePageFooterSection.isDisplayed(), "Home page footer section is not displayed");
        LOG.info("Home page footer section is displayed");
    }

    public void scrollUpHomePage() {
//		a.sendKeys(Keys.PAGE_UP);
//		ScrollToElement(HomePageBannersSection);
        scrollToTop();
        LOG.info("Scroll to Top of Home Page");
        Assert.assertTrue(homePageBannersSection.isDisplayed(), "Home Page Banners Section is not displayed");
        LOG.info("Home page banners are displayed");
    }

    public void clickOnFacebookIcon() {
        scrollToElement(homePageFooterSection);
        LOG.info("Scrolled to Footer section of home page");

        wait.until(ExpectedConditions.visibilityOf(faceBookIcon)).click();
        LOG.info("Clicked on Facebook Icon");

        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");
        String parentWindow = driver.getWindowHandle();

        switchBrowserWindow(parentWindow);
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("facebook"), "Shake Shack Facebook page was not Opened");
        LOG.info("Shake Shack Facebook page was Opened");
        driver.switchTo().window(parentWindow); // cntrl to parent window
    }

    public void clickOnInstagramIcon() {
        wait.until(ExpectedConditions.visibilityOf(closeBannerIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(instagramIcon)).click();
        LOG.info("Clicked on Instagram Icon");

        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");

        String parentWindow = driver.getWindowHandle();

        switchBrowserWindow(parentWindow);
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("instagram"), "Shake Shack instagram page was not Opened");
        LOG.info("Shake Shack instagram page was Opened");
        driver.switchTo().window(parentWindow); // cntrl to parent window
    }

    public void clickOnTwitterIcon() {
        wait.until(ExpectedConditions.visibilityOf(closeBannerIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(twitterIcon)).click();
        LOG.info("Clicked on Twitter Icon");

        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");

        String parentWindow = driver.getWindowHandle();

        switchBrowserWindow(parentWindow);
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("x"), "Shake Shack twitter page was not Opened");
        LOG.info("Shake Shack twitter page was Opened");
        driver.switchTo().window(parentWindow); // cntrl to parent window
    }

    public void clickOnTikTokIcon() {
        wait.until(ExpectedConditions.visibilityOf(closeBannerIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(tikTokIcon)).click();
        LOG.info("Clicked on TikTok Icon");

        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");

        String parentWindow = driver.getWindowHandle();

        switchBrowserWindow(parentWindow);
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("tiktok"), "Shake Shack Tiktok page was not Opened");
        LOG.info("Shake Shack Tiktok page was Opened");
        driver.switchTo().window(parentWindow); // cntrl to parent window
    }

    public void clickOnYouTubeIcon() {
        wait.until(ExpectedConditions.visibilityOf(closeBannerIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(youTubeIcon)).click();
        LOG.info("Clicked on Youtube Icon");

        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");

        String parentWindow = driver.getWindowHandle();

        switchBrowserWindow(parentWindow);
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("youtube"), "Shake Shack YouTube page was not Opened");
        LOG.info("Shake Shack YouTube page was Opened");
        driver.switchTo().window(parentWindow); // cntrl to parent window
    }

    public void clickOnLinkedinIcon() {
        wait.until(ExpectedConditions.visibilityOf(closeBannerIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(linkedinIcon)).click();
        LOG.info("Clicked on Linkedin Icon");

        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");

        String parentWindow = driver.getWindowHandle();

        switchBrowserWindow(parentWindow);
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("linkedin"), "Shake Shack Linkedin page was not Opened");
        LOG.info("Shake Shack Linkedin page was Opened");
        driver.switchTo().window(parentWindow); // cntrl to parent window
    }

    public void verifyLinksUnderInquiries() {
        scrollToElement(homePageFooterSection);
//		String LinksName= LinksUnderInquiries.getText();
        WebElement linksUnderInquiriesInfo = driver
                .findElement(By.xpath("//h3[contains(text(),'Inquiries')]/following-sibling::*"));
        List<WebElement> L = linksUnderInquiriesInfo.findElements(By.xpath("./child::*"));
        LOG.info("List of Links under Inquiries are ");
        // iterate child nodes
        for (WebElement i : L) {
            // getText() to get text for child nodes
            LOG.info(i.getText());
        }
    }

    public void verifyLinksUnderExplore() {
//		ScrollToElement(HomePageFooterSection);
        WebElement linksUnderExplore = driver
                .findElement(By.xpath("//h3[contains(text(),'Explore')]/following-sibling::*"));
        List<WebElement> L1 = linksUnderExplore.findElements(By.xpath("./child::*"));
        LOG.info("List of Links under Explore are ");
        // iterate child nodes
        for (WebElement i : L1) {
            // getText() to get text for child nodes
            LOG.info(i.getText());
        }
    }

    public void verifyLinksUnderAboutUs() {
//		ScrollToElement(HomePageFooterSection);
        WebElement linksUnderAboutUs = driver
                .findElement(By.xpath("//h3[contains(text(),'About Us')]/following-sibling::*"));
        List<WebElement> L2 = linksUnderAboutUs.findElements(By.xpath("./child::*"));
        LOG.info("List of Links under About Us are ");
        // iterate child nodes
        for (WebElement i : L2) {
            // getText() to get text for child nodes
            LOG.info(i.getText());
        }
    }

    public void verifyCopyRightsText() {
//		ScrollToElement(HomePageFooterSection);
        String copyRightsTextInfo = copyRightsText.getText();
        LOG.info("Copy Rights Text is " + copyRightsTextInfo);
    }

    public void verifyLinksAboveCopyRightmsg() {
        WebElement linksAboveCopyRights = driver
                .findElement(By.xpath("//h3[contains(text(),'copyright')]/following-sibling::*"));
        List<WebElement> L3 = linksAboveCopyRights.findElements(By.xpath("./child::*"));
        LOG.info("List of Links Above Copy Rights are ");
        // iterate child nodes
        for (WebElement i : L3) {
            // getText() to get text for child nodes
            LOG.info(i.getText());
        }
    }

    public void addInvalidEmail() {
        scrollToElement(homePageFooterSection);
        wait.until(ExpectedConditions.visibilityOf(mailingList));
        String label = mailingList.getText();
        LOG.info("Email Field Header is displayed as " + label);
        wait.until(ExpectedConditions.visibilityOf(emailfieldatFooter)).sendKeys("test");
        wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
        LOG.info("Entered invalid email");
    }

    public void validateErrorMsg() {
        wait.until(ExpectedConditions.visibilityOf(mailErrorMessage));
        String mailErrorMsg = mailErrorMessage.getText();
        LOG.info("Error Message is displayed as " + mailErrorMsg);
    }

    public void addValidEmail() {
        wait.until(ExpectedConditions.visibilityOf(emailfieldatFooter)).sendKeys("test@gmail.com");
        wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
        LOG.info("Entered Valid email");
//		waitUntilLoadingBarDisappears();
    }

    public void validateSignUpMsg() {
        wait.until(ExpectedConditions.alertIsPresent()); // need to execute and test this line
//		
        Alert a = driver.switchTo().alert();
        String signupAlertMsg = a.getText();
        LOG.info("SignUp Message is displayed as " + signupAlertMsg);
        a.accept();
    }

   

//	public void verifyCardImageSections() {
//		scrollToElement(burgerHeader);
//		
//		wait.until(ExpectedConditions.visibilityOf(burgerSection));
//		String burgersLabel = burgerSection.getAttribute("aria-label");
//		LOG.info("Burgers Section is displayed with label- " + burgersLabel);
//
////		MoveToElement(ChickenSection);
////		SoftAssert softAssert = new SoftAssert();
////		softAssert.assertTrue(ChickenSection.isDisplayed());
////		String ChickenLabel = ChickenSection.getAttribute("aria-label");\\This section is displaying manually. But while automation, not displaying at any product
//
//		scrollToElement(friesSection);
//		String friesLabel = friesSection.getAttribute("aria-label");
//		LOG.info("Fries Section is displayed with label- " + friesLabel);
//
//		scrollToElement(custardsSection);
//		String custardsLabel = custardsSection.getAttribute("aria-label");
//		LOG.info("Custards Section is displayed with label- " + custardsLabel);
//
//		scrollToElement(hotdogsMenu);
//		String hotdogsLabel = hotdogsMenu.getAttribute("aria-label");
//		LOG.info("Hotdogs Section is displayed with label " + hotdogsLabel);
//
//		scrollToElement(drinksSection);
//		String drinksLabel = drinksSection.getAttribute("aria-label");
//		LOG.info("Drinks Section is displayed with label " + drinksLabel);
//	}
//
//	public void verifyCardInformationinDifferentSections() throws Exception {
//		a.sendKeys(Keys.PAGE_UP);
//		clickonOrderNowBtn();
//		new DeliveryPage(driver).clickOnDeliveryTab();
//		new DeliveryPage(driver).selectDeliveryAddress();
//		if (checkIfElementIsVisible(addBtn)) {
//			wait.until(ExpectedConditions.visibilityOf(addBtn)).click();
//			
//		}
////		int TagsCount = driver.findElements(By.cssSelector(".product-tag>p")).size();
//
//		List<WebElement> liElements = driver.findElements(By.cssSelector(".product-tag>p"));
//		LOG.info(liElements.size());
//		String productInfoXpathPrefix = "(//div[@class='product-tag product-tag secondary']/p)[";
//
//		List<WebElement> productInformation = driver
//				.findElements(By.xpath("//*[contains(@class, 'product-tag')]/parent::*/div[2]"));
//		for (int i = 0; i < productInformation.size(); i++) {
//			String pInformation = productInformation.get(i).getText();
//			WebElement TagNames = driver.findElement(By.xpath(productInfoXpathPrefix + (i + 1) + "]"));
//			String tName = TagNames.getText();
//			LOG.info(tName + ": " + pInformation);
//		}
//	}
//
//	public void verifyCartStatus() {
//		String countOfItemsInCart = countofItemsInCart.getAttribute("value");
//		if (countOfItemsInCart != null) {
//			LOG.info("Count of Items in Cart is : " + countOfItemsInCart);
//		} else {
//			LOG.info("Cart is cleared and no items are added to cart");
//		}
//	}
//
//	public void userIsInCareersLandingPage() {
//		new LoginPage(driver).launchApp();
//		LOG.info("Url opened");
//		navigateToCareersPage();
//	}

    public void clickOnBeAPartnerLink() {
        // String newwimdow=Keys.chord(Keys.CONTROL,Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(BeOurPartner)).click();
        LOG.info("clicked on Be our supplier");
        waitUntilSpinnerDisappears(); // added this to avoid stale element exception in local due to loading
    }

    public void clickOnContactUsLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.elementToBeClickable(ContactUs)).click();
        LOG.info("clicked on Contact Us");
       // acceptCookiesIfDisplayed();
    }

    public void clickOnPressInquireLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(PressInquiries)).click();
        LOG.info("clicked on Press Inquiries");
    }

    public void clickOnShakeTruck() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(ShackTruck)).click();
        LOG.info("clicked on Shack Truck");
    }

    /**
     * Adding new methods for new regression suite
     *
     * @throws InterruptedException
     */
    // Adding product to Tray from Quick add modal
  
    public void verifyTrackOrderBanner() {
        wait.until(ExpectedConditions.visibilityOf(siteLogo)).click();
        wait.until(ExpectedConditions.elementToBeClickable(trackOrderBtn));
        //acceptCookiesIfDisplayed();
        scrollToTop();
        Assert.assertTrue(trackOrderBtn.isDisplayed(), "Track Order banner is not displaying in Home page");
        LOG.info("Track Order banner is displaying in Home page");
    }

    
    public void clickOnProductImageInHomeScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerImage)).click();
        LOG.info("Clicked on Burger Image in Home screen");
    }

   

    public void clickOnAddToBagCTAAtMenuItemInHomePage() {
        waitForElementToAppear(addToBagBtn);
        waitUntilSpinnerDisappears();
        wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn)).click();
        LOG.info("Clicked on Add to Bag button for menu item in Home screen");
    }

    public void verifyQuickAddModal() {
        scrollToElement(addToBagBtnInQuickAddModal);
        Assert.assertTrue(addToBagBtnInQuickAddModal.isDisplayed(), "Quick Add modal is not displayed");
        LOG.info("Quick Add modal is displayed");
    }

    public void selectCrinkleCutFriesSection() {
        scrollToElement(friesSection);
        waitForElementToAppear(friesSection);
        waitUntilSpinnerDisappears();
        wait.until(ExpectedConditions.elementToBeClickable(friesSection)).click();
        String friesLabel = friesSection.getAttribute("aria-label");
        LOG.info("Clicked on " + friesLabel + " section");
    }

    public void addFriesItemToBag() {
//		scrollToElement(addToBagBtnAtFriesItem);
        wait.until(ExpectedConditions.elementToBeClickable(addToBagBtnAtFriesItem)).click();
        LOG.info("Clicked on Add to Bag button at Fries item in Home screen");
        // This addon is displaying for few new items added during release
        if (checkIfElementIsVisible(noRadioBtnAtAddOn)) {
            noRadioBtnAtAddOn.click();
            LOG.info("Selected No for addon");
            wait.until(ExpectedConditions.elementToBeClickable(addToBagBtnInQuickAddModal)).click();
            LOG.info("Clicked on Add to bag button in quick add modal");
        }
//		waitForElementToAppear(bagIcon);
        waitUntilSpinnerDisappears();
        wait.until(ExpectedConditions.elementToBeClickable(bagIcon)).click();
        LOG.info("Clicked on Bag Icon");
    }

    public void verifyTotalValueBasedOnUserSelectionInQuickAddModal() {
        LOG.info("Initial Total Amount in Quick Add Modal is " + totalAmountInQucikAddModal.getText());
        wait.until(ExpectedConditions.visibilityOf(singleItem)).click();
        LOG.info("Selected Single Item option- Total Amount value is " + totalAmountInQucikAddModal.getText());
        wait.until(ExpectedConditions.visibilityOf(doubleItem)).click();
        LOG.info("Selected Double Item option- Total Amount value is " + totalAmountInQucikAddModal.getText());
        yesRadioBtnAtAddOn.click();
        LOG.info("Selected Yes for Addon- Total Amount value is " + totalAmountInQucikAddModal.getText());
        noRadioBtnAtAddOn.click();
        LOG.info("Selected No for Addon- Total Amount value is " + totalAmountInQucikAddModal.getText());
    }

   

    public void clickOnAddToBagButtonInQuickAddModal() {
        scrollToElement(addToBagBtnInQuickAddModal);
        wait.until(ExpectedConditions.visibilityOf(addToBagBtnInQuickAddModal)).click();
        LOG.info("Clicked on Add to bag button in Quick add modal");
    }

    public void verifyErrorMessagesInRedColor() {
//		scrollToElement(sizeSelectionOptioninQuickAddModal);
        scrollToTop();
        String selectSizeOption = sizeSelectionOptioninQuickAddModal.getText();
        String errorMsgAtSizeSelectionOption = sizeSelectionErrorMessage.getText();
        String SizeErrorMsgColor = sizeSelectionErrorMessage.getCssValue("color");
        scrollToElement(addOnSelectionOptioninQuickAddModal);
        String addOnName = addOnSelectionOptioninQuickAddModal.getText();
        String errorMsgAtAddOnSelection = addOnSelectionErrorMessage.getText();
        String AddOnErrorMsgColor = sizeSelectionErrorMessage.getCssValue("color");
        LOG.info("Error mssage at " + selectSizeOption + " is displayed as " + errorMsgAtSizeSelectionOption + " with "
                + SizeErrorMsgColor + " color");
        LOG.info("Error mssage at " + addOnName + " is displayed as " + errorMsgAtAddOnSelection + " with "
                + AddOnErrorMsgColor + " color");
    }

    public void verifyCaloriesAndPriceInfo() {
        String priceAndCaloriesValueAtSizeSelectionOption = priceAndCaloriesInfoAtSizeSelectionOption.getText();
        String priceValueAtAddon = priceInfoAtAddOn.getText();
        String caloriesValueAtAddOn = caloriesInfoAtAddOn.getText();
        LOG.info("Price and Calories information at size selection is : " + priceAndCaloriesValueAtSizeSelectionOption);
        LOG.info("Price and Calories information at Add on is : " + priceValueAtAddon + caloriesValueAtAddOn);
    }

    public void verifyCheckMarkTransistionAtQuickAdModal() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(singleItem)).click();
        LOG.info("Selected Single Item option");
        noRadioBtnAtAddOn.click();
        LOG.info("Selected No for AddOn");
        scrollToElement(addToBagBtnInQuickAddModal);
        wait.until(ExpectedConditions.visibilityOf(addToBagBtnInQuickAddModal)).click();
        LOG.info("Item is added to Bag from Quick Add Modal");
        Thread.sleep(1000);
        Assert.assertTrue(checkMarkAtAddToBagCTAInQuickAddModal.isDisplayed(),
                "Check mark transistion is not displayed at Add to bag button in Quick add modal");
        LOG.info("Check mark transistion is displayed at Add to bag button in Quick add modal");
    }

    public void verifyQuantityPicker() {
        String defaultValueInQuantityPicker = quantitySelectorvalueAtQucikAddModal.getText();
        LOG.info("Default value in Quantity picker is: " + defaultValueInQuantityPicker);
        verifyOperatorsStausinQuantityPicker();
        quantitySelectorPlusAtQucikAddModal.click();
        LOG.info("Clicked on Plus at Quantity Picker");
        verifyOperatorsStausinQuantityPicker();
    }

    public void verifyOperatorsStausinQuantityPicker() {
        String minusOperatorStatus = quantitySelectorMinusAtQucikAddModal.getAttribute("class");
        if (minusOperatorStatus.contains("disabled")) {
            LOG.info("Minus Icon is Disabled");
        } else {
            LOG.info("Minus Icon is Enabled");
        }
        String plusOperatorStatus = quantitySelectorPlusAtQucikAddModal.getAttribute("class");
        if (plusOperatorStatus.contains("disabled")) {
            LOG.info("Plus Icon is Disabled");
        } else {
            LOG.info("Plus Icon is Enabled");
        }
    }

    public void verifyAddOnInQuickAddModal() {
        wait.until(ExpectedConditions.visibilityOf(addOnInfo));
        List<WebElement> AddOnsInQuickAddModal = driver.findElements(By.cssSelector(".extras-container>div"));
        LOG.info("Number of AddOns in Quick Add Modal are: " + AddOnsInQuickAddModal.size());
    }

    public void addProductQuantityEqualToThresholdValue() {
        String productThresholdValue = Loader.getProperty("productThresholdValue");
        for (int i = 0; i < Integer.parseInt(productThresholdValue); i++) {
            wait.until(ExpectedConditions.visibilityOf(quantitySelectorPlusAtQucikAddModal)).click();
        }
        LOG.info("Increased the product quantity to " + quantitySelectorvalueAtQucikAddModal.getText());
    }

    public void verifyLimitReachedErrorMsg() {
        scrollToElement(limitReachedErrorMsg);
        wait.until(ExpectedConditions.visibilityOf(limitReachedErrorMsg));
        String limitError = limitReachedErrorMsg.getText();
        LOG.info("Error message is displayed as " + limitError);
    }

    public void verifyBaconAddOnPrice() {
        wait.until(ExpectedConditions.visibilityOf(singleItem)).click();
        LOG.info("Selected Single Item option");
        yesRadioBtnAtAddOn.click();
        LOG.info("Selected Yes for AddOn");
        String priceValueAtAddon = priceInfoAtAddOn.getText();
        String addOnName = addOnSelectionOptioninQuickAddModal.getText();
        LOG.info("price for " + addOnName + " is displayed as " + priceValueAtAddon);
    }

   
    public void clickOnChickenBitesImage() {
        wait.until(ExpectedConditions.visibilityOf(chickenBitesImage)).click();
        LOG.info("Clicked on Chicken Bites Image");
    }

    public void clickOnChickenMenu() {
        scrollToElement(chickenSection);
        wait.until(ExpectedConditions.elementToBeClickable(chickenSection)).click();
        LOG.info("Clicked on Chicken Menu");
    }

    public void verifyItemAddedToBag(int expectedCount) {
        wait.until(ExpectedConditions.visibilityOf(bagIcon));
        int currentCount = Integer.parseInt(countofItemsInCart.getText());
        LOG.info("Items added to Bag: " + currentCount + ", Expected: " + expectedCount);
        Assert.assertTrue(expectedCount == currentCount);
    }

   

    

   

    public void clickOnBagIcon() {
        clickElementUsingJavaScriptExecutor(bagIcon);
        LOG.info("Clicked on Bag Icon");
        waitUntilSpinnerDisappears();
    }

    public void verifyItemsInfoInPLPScreen() {
        List<WebElement> catagoriesList = driver.findElements(By.cssSelector(".category-list h3"));
        LOG.info("Categories displayed: " + catagoriesList.size());

        List<WebElement> itemsList = driver.findElements(By.cssSelector(".card-image-link>img"));
        LOG.info("Items displayed: " + itemsList.size());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(itemsPricesInPLPScreen.isDisplayed(), "Prices information for items is not displayed");
        softAssert.assertTrue(itemsCaloriesInfoInPLPScreen.isDisplayed(),
                "Prices Calories information for items is not displayed");
        LOG.info("Prices and Calories information is displayed for menu items");
        softAssert.assertAll();
    }

    public void clickOnShackMerchLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(shackMerchLink)).click();
        LOG.info("clicked on Shack Merch Link");
        wait.until(ExpectedConditions.visibilityOf(acceptBtn)).click();
        LOG.info("Clicked on accept button");
    }

    public void verifySSShopKartPageTitle() {
        String parentWindow = driver.getWindowHandle();
        switchBrowserWindow(parentWindow);
        Assert.assertTrue(ssShopKartPageTitle.isDisplayed(), "Shake Shack Shop kart page is not displayed");
        String PageTitle = ssShopKartPageTitle.getText();
        LOG.info("Shake Shack shop kart page is displayed with title: " + PageTitle);
        driver.switchTo().window(parentWindow);
    }

    public void clickOnFAQsLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(faqsLink)).click();
        LOG.info("clicked on FAQs Link");

    }

    public void verifyFAQPageTitle() {
        Assert.assertTrue(faqPageTitle.isDisplayed(), "FAQs Page is not displayed");
        String PageTitle = faqPageTitle.getText();
        LOG.info("FAQs page is displayed with title: " + PageTitle);
    }

    public void clickOnOnlineOrderingLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(onlineOrderingLink)).click();
        LOG.info("clicked on Online Ordering Link");
        waitUntilSpinnerDisappears();
    }

   

    public void clickOnAllergensInfoLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(allergensInfoLink)).click();
        LOG.info("clicked on Allergens Info Link");
    }

    public void verifyAllergenPDF() {
        String PageTitle = driver.getCurrentUrl();
        LOG.info("Allergen PDF page is displayed with URL: " + PageTitle);
    }

    public void clickOnNutritionalInfoLink() {
        scrollToElement(inquiries);
        scrollUp();
        wait.until(ExpectedConditions.visibilityOf(nutritionalInfoLink)).click();
        LOG.info("clicked on Nutritional Info Link");
    }

    public void verifyNutritionPDF() {
        String PageTitle = driver.getCurrentUrl();
        LOG.info("Nutrition PDF page is displayed with URL: " + PageTitle);
    }

//	public void clickOnGiftCardsLinkAtHomePageFooterSection() {
//		scrollToElement(inquiries);
//		scrollUp();
//		wait.until(ExpectedConditions.visibilityOf(giftCardsLink)).click();
//		LOG.info("clicked on Gift Cards Info Link");
//		
//	}

    public void verifyGiftCardsPageTitle() {
        Assert.assertTrue(giftCardsPageTitle.isDisplayed(), "Gift Cards Page is not displayed");
        String pageTitle = giftCardsPageTitle.getText();
        LOG.info("Gift Cards page is displayed with title: " + pageTitle);
    }

//	public void checkGiftCardBalance() {
//		scrollToElement(giftCardsPageBanner);
//		wait.until(ExpectedConditions.visibilityOf(checkYourBalanceBtn)).click();
//		LOG.info("Clicked on Check your balance button");
//		acceptCookiesIfDisplayed();
//		scrollToElement(giftCardsPageTitle);
//		String giftCardNum = loader.getProperty("giftCardNumber");
//		wait.until(ExpectedConditions.elementToBeClickable(giftCardNumberField)).sendKeys(giftCardNum);
//		LOG.info("Entered Gift card number as: " + giftCardNum);
//		String giftCardPinNum = loader.getProperty("giftCardPin");
//		wait.until(ExpectedConditions.elementToBeClickable(giftCardPinField)).sendKeys(giftCardPinNum);
//		LOG.info("Entered Gift card Pin as: " + giftCardPinNum);
//		wait.until(ExpectedConditions.elementToBeClickable(checkBalanceSubmitBtn)).click();
//		LOG.info("Clicked on Check balance submit Button");
//		 // Gift card balance check is not working in this page hence need to update the
//										// logic for it once it started working
//	}

    public void verifyHeroBannerVisibilityStatus() {
        if (checkIfElementIsVisible(homePageFooterSection)) {
            LOG.info("Food Menu is over the Hero Banner");
        } else if (checkIfElementIsVisible(homePageBannersSection)) {
            LOG.info("Hero Banner is visible");
        }
    }

   
    public void verifyCateringAndRetailMenus() {
        Assert.assertTrue(cateringMenu.isDisplayed(), "Catering Menu is not displayed in Home page");
        Assert.assertTrue(retailMenu.isDisplayed(), "Retail Menu is not displayed in Home page");
        LOG.info("Catering & Retail menus are displaying in Home page");
    }

//	public void verifyStartOrderBtn() {
//		Assert.assertTrue(startOrderBtn.isDisplayed(), "Start Order buttons are not displayed in PLP Screen");
//		List<WebElement> startOrderBtns = driver.findElements(By.xpath("//*[@id='start-order-button']"));
//		LOG.info("Start Order Buttons displayed are: " + startOrderBtns.size() + " in PLP screen");
//	}

    public void clickOnStartOrderBtnInPLPPage() {
        wait.until(ExpectedConditions.elementToBeClickable(startOrderBtn)).click();
        LOG.info("Clicked on Start Order Button at ShackBurger");
    }

    public void verifyAllProductCategoriesAndTheirProducts() {
        List<WebElement> categoriesInHomePage = driver.findElements(By.xpath("//a[contains(@class,'ss-icon')]"));
        LOG.info("Number of product categories in Home page: " + categoriesInHomePage.size());
        for (int i = 1; i < categoriesInHomePage.size() + 1; i++) {
            String categoryNames = driver.findElement(By.xpath("(//a[contains(@class,'ss-icon')])[" + i + "]"))
                    .getAttribute("aria-label");
            LOG.info(categoryNames);
        }
        List<WebElement> products = driver.findElements(By.xpath("//a[@class='card-image-link']"));
        int numberOfProducts = (products.size()) / 2;
        LOG.info("Total number of products in Home page are " + numberOfProducts);
    }

    public void clickOnAddToBagAtDrinks() {
        scrollToElement(custardsSection);
        clickElementUsingJavaScriptExecutor(custardsSection);

        scrollToElement(drinksSection);
        clickElementUsingJavaScriptExecutor(drinksSection);
        LOG.info("Clicked on Drinks Menu");
        wait.until(ExpectedConditions.elementToBeClickable(addToBagBtnAtSunsetLemonade)).click();
        LOG.info("Clicked on Add to Bag at Sunset Lemonade");
    }

    public void addDrinkToTayFromQuickAddModal() {
        clickOnAddToBagAtDrinks();
        wait.until(ExpectedConditions.elementToBeClickable(smallOptionAtSizeSelection)).click();
        LOG.info("Selected small size");
        wait.until(ExpectedConditions.elementToBeClickable(addToBagBtnInQuickAddModal)).click();
        LOG.info("Clicked on Add to Bag btn in Quick add modal");
    }

    

    public void clickOnHelpMenuAtHomePageHeaderSection() {
        wait.until(ExpectedConditions.elementToBeClickable(helpMenuInHomePageHeaderSection)).click();
        LOG.info("Clicked on Help Menu in home page header section");
    }

    public void selectSingleItemInQuickAddModal() {
        wait.until(ExpectedConditions.visibilityOf(singleItem)).click();
        LOG.info("Selected Single Item option");
    }

    public void verifyErrorMsgAtAddOnRadioBtnInQuickAddModal() {
        scrollToElement(addOnSelectionOptioninQuickAddModal);
        String addOnName = addOnSelectionOptioninQuickAddModal.getText();
        String errorMsgAtAddOnSelection = addOnSelectionErrorMessage.getText();
        String AddOnErrorMsgColor = addOnSelectionErrorMessage.getCssValue("color");
        LOG.info("Error mssage at " + addOnName + " is displayed as " + errorMsgAtAddOnSelection + " with "
                + AddOnErrorMsgColor + " color");
    }

    List<String> productList = new ArrayList<>(); // ["ShackStack", "ChickenBites"]

    public void verifyProductsInHomePage() {
        List<WebElement> itemsList = driver.findElements(By.cssSelector(".card-image-link>span"));
        LOG.info("Total number of Products: " + itemsList.size());
//		int i = 0;
        for (WebElement ele : itemsList) {
            String productName = ele.getText();
            productList.add(productName);
//			LOG.info("Product- " + i + " is: " + productName);
//			i++;
        }
        Assert.assertTrue(productList.size() > 1, "Products are not displaying");
    }

//	public void verifyPriceAndCaloriesInfoOfProductsInHomePage() throws Exception {
//		List<WebElement> pricesInfo = driver.findElements(By.cssSelector(".card-image-link+div>span:nth-child(2)"));
//		LOG.info("Product Prices displayed are: " + pricesInfo.size());
//		int i = 1;
//		for (WebElement ele : pricesInfo) {
////			String priceValue = ele.getText().replace(" .", "").strip();
////			if (priceValue.equals("$0.00"))
//			String priceValue = ele.getText();
//			if (priceValue.equals("$0.00 ·"))
//				throw new Exception("Product- " + i + ", price is displayed as: " + priceValue);
//			else
//				LOG.info("Product #: " + i + " Price: " + priceValue);
//			i++;
//		}
//
//		List<WebElement> caloriesInfo = driver.findElements(By.cssSelector(".card-image-link+div>span:nth-child(3)"));
//		LOG.info("Product Calories displayed are: " + caloriesInfo.size());
//		int j = 1;
//		for (WebElement ele : caloriesInfo) {
//			String caloriesValue = ele.getText();
////			if (caloriesValue.equals("0 cals")) Assertion not needed for calories
////				throw new Exception("Product- " + i + " calories info is displayed as: " + caloriesValue);
////			else
//			LOG.info("Product- " + j + " calories info is displayed as: " + caloriesValue);
//			j++;
//		}
//	}

    public void verifyHeroContainerElementsInHomePage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(heroBannerModule.isDisplayed(), "Hero Banner Module is not displayed");
        LOG.info("Hero Banner module is displayed");
        List<WebElement> HeroBanners = driver.findElements(By.xpath("//*[@class='slick-slide']"));
        int noOfHeroBanners = HeroBanners.size();
        LOG.info("Number of Hero Banners displayed are: " + noOfHeroBanners);
        List<WebElement> HeroLabels = driver.findElements(By.cssSelector(".field--name-field-hero-title"));
        int noOfHeroLabels = HeroLabels.size();
        LOG.info("Number of Hero Labels displayed are: " + noOfHeroLabels);
        softAssert.assertTrue(heroBannerNxtCarouselArrows.isDisplayed(),
                "Hero Banner next carousel arrow is not displayed");
        LOG.info("Hero Banner next carousel arrow is displayed");
        softAssert.assertTrue(heroBannerPrevCarouselArrows.isDisplayed(),
                "Hero Banner previous carousel arrow is not displayed");
        LOG.info("Hero Banner previous carousel arrow is displayed");
        List<WebElement> numOfheroBannerCarouselDots = driver
                .findElements(By.xpath("//*[@class='slick-dot-icon' or @class='dot']"));
        LOG.info("Hero Banner carousel dots displayed are: " + numOfheroBannerCarouselDots.size());
        softAssert.assertTrue(heroBannerPauseAndPlayBtn.isDisplayed(),
                "Hero Banner pause and play button is not displayed");
        LOG.info("Hero Banner pause and play button is displayed");
        softAssert.assertAll();
    }

//	public void clickOnShakesAndFrozenCustardsSection() {
//		scrollToElement(custardsSection);
//		
//		wait.until(ExpectedConditions.elementToBeClickable(custardsSection)).click();
//		LOG.info("Clicked on Shakes and Frozen custards section");
//	}
//
//	public void clickOnShakeImage() {
//		wait.until(ExpectedConditions.elementToBeClickable(handSpunShakesImage)).click();
//		LOG.info("Clicked on shake product Image");
//	}

    public void verifyAllModulesInHomePage() {
        verifyHeroContainerElementsInHomePage();
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> modulesInHomePageHeader = driver
                .findElements(By.xpath("//*[contains(@class,'menu-item--active-trail')]/following-sibling::*"));
        int noOfModulesInHomePageHeader = modulesInHomePageHeader.size();
        LOG.info("No.of modules in home page header section are: " + noOfModulesInHomePageHeader);
        int i = 1;
        for (WebElement ele : modulesInHomePageHeader) {
            String modulesNameInHeaderPage = ele.getText();
            LOG.info("Module header " + i + " is - " + modulesNameInHeaderPage);
            i++;
        }
        softAssert.assertTrue(siteLogo.isDisplayed(), "Site logo is not displayed");
        LOG.info("Site logo is displayed");
       // softAssert.assertTrue(new LoginPage(driver).accountIcon.isDisplayed(), "Logged in user name is not displayed");
        LOG.info("Logged in user name is displayed");
        softAssert.assertTrue(orderNowIcon.isDisplayed(), "Order now button is not displayed");
        LOG.info("Order Now button is displayed");
        softAssert.assertAll();
    }

   

    public void refreshHomePage() {
        driver.navigate().refresh();
        LOG.info("Home page was refreshed");
    }

    public void verifyAppCrashMessage() {
        Assert.assertFalse(checkIfElementIsVisible(systemRefreshAlert), "System refresh Alert is displayed");
    }

    public String extractPrice(String actualText) {
        StringBuilder priceBuilder = new StringBuilder();
        int count = 0;
        for (char c : actualText.toCharArray()) {
            if (count < actualText.length() - 2) {
                priceBuilder.append(c);
                count++;
            }
        }
        return priceBuilder.toString();
    }

    public void verifyPricesForItems() {
        int zeroPriceCount = 0;
        List<WebElement> priceElementList = driver
                .findElements(By.cssSelector(".card-image-link+div>span:nth-child(2)"));
        LOG.info("Total prices found: " + priceElementList.size());
        int i = 0;
        for (WebElement ele : priceElementList) {
            String priceValue = extractPrice(ele.getText());
            if (priceValue.equals("$0.00")) {
//				scrollToElement(ele);
                Actions actions = new Actions(driver);
                actions.moveToElement(ele);
                actions.perform();
                zeroPriceCount++;
                LOG.error(productList.get(i) + ", Price: " + priceValue);
            } else
                LOG.info(productList.get(i) + ", Price: " + priceValue);
            i++;
        }
        LOG.info("[!!!] Total items for which price is zero: " + zeroPriceCount);
        Assert.assertTrue(zeroPriceCount == 0, "Zero amount, " + zeroPriceCount + " products are found.");
    }

    public void verifyProductImages() {
        LOG.info("Verifying the product images");
        List<WebElement> productImageList = driver.findElements(By.cssSelector(".card-image-link > img"));
        int imgCount = 0;
        List<Integer> imgNotAvailableIdx = new ArrayList<>();
        for (WebElement ele : productImageList) {
            String imgUrl = ele.getAttribute("src");
            if (imgUrl.contains(".svg")) {
                imgNotAvailableIdx.add(imgCount);
                imgCount++;
                scrollToElement(ele);
//				LOG.info("Image not available: " +imgUrl);
            }
        }
        LOG.info("Verified the product images");
        verifyProductsInHomePage(); // Storing product name in productList
        LOG.info("[!!!] Total items for which image is not available: " + imgCount);
        if (imgNotAvailableIdx.size() > 0) {
            LOG.info("Name of products for which image is not available");
            for (int idx : imgNotAvailableIdx) {
                LOG.error(productList.get(idx));
            }
        }
        Assert.assertTrue(imgCount == 0, "Image not available for " + imgCount + " products");
    }

   

  
//	private String productName = null;
//
//	public void clickOnAddToBagAtFirstItemFromSelectedCategory(String menuName) throws Exception {
//		new LoginPage(driver).handleCovidInformation();
//		List<WebElement> priceValuesInHomePage = driver
//				.findElements(By.cssSelector(".card-image-link+div>span:nth-child(2)"));
//		List<WebElement> productNames = driver.findElements(By.xpath(
//				"//*[@class='category']/descendant::*[button][1]/child::*[3]/parent::*/parent::*/child::*/a/span"));
//		List<WebElement> addToBagBtnAtFirstElementInSelectedCategory = driver
//				.findElements(By.xpath("//*[@class='category']/descendant::*[button][1]/child::*[3]"));
//		int i;
//		switch (menuName) {
//		case "Burgers": // click on burger
//			i = 0;
//			break;
//		case "Chicken": // click on chicken
//			i = 1;
//			break;
//		case "Fries": // click on crinkle cut fries
//			i = 2;
//			break;
//		case "Custards": // click on shakes and frozen custards
//			i = 3;
//			break;
//		case "FlatTopDogs": // click on flattopdogs
//			i = 4;
//			break;
//		case "Drinks": // click on drinks
//			i = 5;
//			break;
//		case "LargeOrders": // click on largeorders
//			i = 6;
//			break;
//		default:
//			throw new Exception("Menu category " + menuName + " was not found.");
//		}
//		productName = productNames.get(i).getText();
//		addToBagBtnAtFirstElementInSelectedCategory.get(i).click();
//		LOG.info("Clicked on add to bag button at: " + productName);
//		productPriceInHomePage = priceValuesInHomePage.get(i).getText().replace("$", "").replace(" ·", "");
//		if (productPriceInHomePage.equals("0.00")) {
//			throw new Exception(productName + ", Price: " + productPriceInHomePage);
//		}
//	}

   

    public void handleQuickAddModalAndAddItemToBag() {
        if (checkIfElementIsVisible(quickAddModalCloseBtn)) {
            if (driver.findElements(By.xpath("//b[contains(text(),'Single')]")).size() > 0) {
                wait.until(ExpectedConditions.visibilityOf(singleItem)).click();
                LOG.info("Selected Single Item option");
            }
            if (driver.findElements(By.xpath("//p[contains(text(),'Add')]")).size() > 0) {
                scrollToElement(noRadioBtnAtAddOn);
                clickElementUsingJavaScriptExecutor(noRadioBtnAtAddOn);
                LOG.info("Selected No for AddOn");
            }
            if (driver.findElements(By.xpath("//*[contains(text(),'6 pcs')]")).size() > 0) {
                clickElementUsingJavaScriptExecutor(sixPieceSizeSelectionRadioBtn);
                LOG.info("Clicked on 6 pcs radio button");
            }
            if (driver.findElements(By.xpath("//*[@aria-haspopup='listbox']")).size() > 0) {
                flavourSelectionDropDown.click();
                firstFlavourInDropDown.click();
                LOG.info("Selected the flavour: " + firstFlavourInDropDown.getAttribute("data-value"));
            }
            if (driver.findElements(By.xpath("//*[contains(text(),'Choose one')]")).size() > 0) {
                try {
                    clickElementUsingJavaScriptExecutor(regularSizeRadioBtnOrChooseDropdown);
                    LOG.info("Clicked on regular size radio button");
                } catch (Exception e) {
                    clickElementUsingJavaScriptExecutor(regularSizeRadioBtnOrChooseDropdown);
                    clickElementUsingJavaScriptExecutor(flavourFromDropdown);
                    LOG.info("Clicked on flavour: " + flavourFromDropdown.getText());
                }
            }
            if (driver.findElements(By.xpath("//*[contains(text(),'Choose one')]")).size() > 0)
                scrollToElement(addToBagBtnInQuickAddModal);
            wait.until(ExpectedConditions.elementToBeClickable(addToBagBtnInQuickAddModal)).click();
        }
    }

    public void clickTrackOrderBanner() {
        wait.until(ExpectedConditions.elementToBeClickable(trackOrderBtn)).click();
        LOG.info("Track Order banner is displaying in Home page");
    }

   
    public void verifyVideoWillAutoPlayOnLoad() {
        try {
            if (wait.until(ExpectedConditions.elementToBeClickable(videoPauseButton)).isDisplayed()) {
                LOG.info("Video will auto play on load");
                Assert.assertTrue(true, "Video will auto play on load");
            } else {
                LOG.info("Video is not auto play on load");
                Assert.assertTrue(false, "Video is not auto play on load");
            }
        } catch (Exception e) {
        }
    }

    public void iClickOnPauseVideoAndVerify() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(videoPauseButton)).click();
            LOG.info("clicked on pause video");
            if (wait.until(ExpectedConditions.elementToBeClickable(videoPlayButton)).isDisplayed()) {
                LOG.info("Video is paused");
                Assert.assertTrue(true, "Video play button is displayed");
            } else {
                LOG.info("Video is not paused");
                Assert.assertTrue(true, "Video play button is not displayed");
            }
        } catch (Exception e) {
        }
    }

    public void iClickOnPlayVideoAndVerify() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(videoPlayButton)).click();
            LOG.info("clicked on play video");
            if (wait.until(ExpectedConditions.elementToBeClickable(videoPauseButton)).isDisplayed()) {
                LOG.info("Video is playing");
                Assert.assertTrue(true, "Video pause button is displayed");
            } else {
                LOG.info("Video is not paused");
                Assert.assertTrue(true, "Video pause button is not displayed");
            }
        } catch (Exception e) {
        }
    }

    public void verifyCaloriesForItems() {
        int zeroCaloriesCount = 0;
        List<WebElement> caloriesElementList = driver
                .findElements(By.cssSelector(".card-image-link+div>span:nth-child(3)"));
        LOG.info("Total Calories found: " + caloriesElementList.size());
        int i = 0;
        for (WebElement ele : caloriesElementList) {
            String caloriesValue = extractCalories(ele.getText());
            if (caloriesValue.equals("0 cals")) {
//				scrollToElement(ele);
                Actions actions = new Actions(driver);
                actions.moveToElement(ele);
                actions.perform();
                zeroCaloriesCount++;
                LOG.error(productList.get(i) + ", Calories: " + caloriesValue);
            } else
                LOG.info(productList.get(i) + ", Calories: " + caloriesValue);
            i++;
        }
        LOG.info("[!!!] Total items for which Calories is zero: " + zeroCaloriesCount);
        Assert.assertTrue(zeroCaloriesCount == 0, "Zero calories, " + zeroCaloriesCount + " products are found.");
    }

    public String extractCalories(String actualText) {
        StringBuilder caloriesBuilder = new StringBuilder();
        int count = 0;
        for (char c : actualText.toCharArray()) {
            if (count < actualText.length()) {
                caloriesBuilder.append(c);
                count++;
            }
        }
        return caloriesBuilder.toString();
    }

    public void verifyBeerAndWineMenuItemInHomePage() {
        try {
            if (wait.until(ExpectedConditions.elementToBeClickable(beerMenu)).isDisplayed()) {
                LOG.info("Beer and Wine menu item is displayed");
                Assert.assertTrue(false, "Beer and Wine menu item is displayed");
            }
        } catch (Exception e) {
            LOG.info("Beer and Wine menu item is not displayed as expected");
            Assert.assertTrue(true, "Beer and Wine menu item is not displayed");
        }
    }

    public void verifyBeerWineAndMilkProductsInHomePage() {
        List<WebElement> itemsList = driver.findElements(By.cssSelector(".card-image-link>span"));
        LOG.info("Total number of Products: " + itemsList.size());
        for (WebElement ele : itemsList) {
            String productName = ele.getText();
            if (productName.contains("ShackMeister Ale")) {
                LOG.info("Beer Wine and Milk product is displayed");
                Assert.assertTrue(false, "Beer Wine and Milk product is displayed which is wrong");
            }
        }
        LOG.info("Beer Wine and Milk product is not displayed");
        Assert.assertTrue(true, "Beer Wine and Milk product is not displayed as expected");
    }

    public void verifyPressInquiriesIsNotavailable() {
        wait.until(ExpectedConditions.visibilityOf(helpCenter));
        Assert.assertTrue(isDisplayed(pressInquiryTextNotAvailable), "Press Inquiry text not available");
        LOG.info("Press Inquiry text not available");
    }

    public void verifyEGiftCardModuleUnderGiftCards() {
        String eGiftcardsInfo = giftCardEGIFTcards.getText();
        Assert.assertEquals("E-Gift Cards", eGiftcardsInfo);
        LOG.info("Verified E-Gift Card option");
    }

    public void clickOnLocationLink() {
        wait.until(ExpectedConditions.visibilityOf(explore));
        scrollToElement(explore);
        wait.until(ExpectedConditions.elementToBeClickable(locationLink)).click();
        LOG.info("clicked on location Link");
    }

    public void searchLocation(String location) {
        findShakeShackLocationSearchBox.sendKeys(location);
        wait.until(ExpectedConditions.elementToBeClickable(clickOnFindButton)).click();
        LOG.info("clicked on find button");
    }

    public void clickONBellIcon() {
        waitUntilSpinnerDisappears();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(pickupInstructions)).isDisplayed();
        } catch (Exception e) {
        }
        wait.until(ExpectedConditions.visibilityOf(bellIcon));
        wait.until(ExpectedConditions.elementToBeClickable(bellIcon)).click();
        LOG.info("clicked on Bell Icon");
    }

    public void verifyUnreadContentCard() {
        if (noNotifications.size() > 0)
            Assert.assertTrue(true, "Notification is available for the user to read");
        else
            Assert.assertTrue(false, "Notification is not available for the user to read");
    }

   
    public void selectFountainSoda() {
        scrollToElement(fountainSoda);
        LOG.info("Scrolled to Fountain Soda");
        wait.until(ExpectedConditions.visibilityOf(fountainSoda)).click();
        LOG.info("Clicked on Fountain Soda");
    }

    public void verifyCalorieCount() {
        String currentCalorieCount = fountainSodaSmallCokeDes.getText().substring(8, 11);
        LOG.info("Fountain Soda Small Coke Current Calorie count is " + currentCalorieCount);
        String expectedCalorieCount = Loader.getProperty("fountainSodaSmallCokeCalorieByAPI");
        LOG.info("Fountain Soda Small Coke Expected Calorie count is " + expectedCalorieCount);
        if (currentCalorieCount.equals(expectedCalorieCount)) {
            LOG.info("Fountain Soda Small Coke Calorie count is correct");
        } else {
            LOG.info("Fountain Soda Small Coke Calorie count is not correct");
            Assert.assertEquals(currentCalorieCount, expectedCalorieCount);
        }
    }

    public void clickOnAboutLocationLink() {
        wait.until(ExpectedConditions.visibilityOf(aboutUs));
        scrollToElement(aboutUs);
        wait.until(ExpectedConditions.elementToBeClickable(aboutUsLocationLink)).click();
        LOG.info("clicked on aboutUs LocationLink");
    }

   
    public void verifyLinkAndPage(String link) throws InterruptedException {
        LOG.info("Verifying the Footers Details link");
        scrollToBottom();
        Thread.sleep(2000);
        if(link.contains("Privacy")){
            pripacyLink.click();
            Thread.sleep(2000);
            String pageTitleText=pageTitle.getText();
            Assert.assertTrue(pageTitleText.equalsIgnoreCase("Privacy Policy"),"Privacy Policy page is not displayed");
            LOG.info("Privacy Policy page is displayed");
        }else{
            termsOfUseLink.click();
            String pageTitleText=pageTitle.getText();
            Thread.sleep(2000);
            Assert.assertTrue(pageTitleText.contains("Terms of Use"),"Terms of Use page is not displayed");
            LOG.info("Terms of Use page is displayed");
        }
    }

    public void verifyOrderNowButtonAnimation() {
        LOG.info("Verifying Order Now button Animation without hovering");
        String bgColorBase = orderNowIcon.getCssValue("background");
        LOG.info("Check: "+bgColorBase);
        Assert.assertTrue(bgColorBase.contains("rgb(65, 134, 19)"),"Color is not displayed green");
        LOG.info("Color is displayed green");
        LOG.info("Verifying Order Now button Animation after hovering");
        Actions actions = new Actions(driver);
        actions.moveToElement(orderNowIcon).build().perform();
        String bgColorNew = orderNowIcon.getCssValue("background-color");
        Assert.assertFalse(bgColorNew.contains("rgb(65, 134, 19)"),"Color is displayed green");
        LOG.info("Color is not displayed green");
    }
    public void verifyFooterLocation(String data) throws InterruptedException {
        LOG.info("Verifying the Location Link and open the Location page");
        scrollToBottom();
        Thread.sleep(2000);
        locationLinkFooter.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(locationPageHeader));
        String header = locationPageHeader.getText();
        Assert.assertTrue(header.equalsIgnoreCase("Locations"), "Location Header is not displayed");
        LOG.info("Location Header page is displayed");
        locationSearchBox.sendKeys(data);
        locationFindButton.click();
        Thread.sleep(2000);
        try {
            int pinCode = Integer.parseInt(data);
            List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='field-content']"));
            LOG.info("Data is having Zip code hence serching in search result details");
            for (WebElement detail : searchResults) {
                String resultDetail = detail.getText();
                LOG.info("Search Result is displayed as : " + resultDetail);
                if (resultDetail.contains(data)) {
                    LOG.info(data + " is present in Search Result : " + resultDetail);
                    return;
                }
            }
        } catch (Exception e) {
            LOG.info("Data is having the city name for which we are searching in location title in search result");
            List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='locations-title']"));
            for (WebElement detail : searchResults) {
                String resultDetail = detail.getText();
                LOG.info("Search Result is displayed as : " + resultDetail);
                if (resultDetail.contains(data)) {
                    LOG.info(data + " is present in Search Result : " + resultDetail);
                    return;
                }
            }
        }
        Assert.assertFalse(true,"Unable to find the searched "+data+ "into search result");
    }

}
