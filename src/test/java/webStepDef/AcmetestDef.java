package webStepDef;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DriverFactory;
import web.LoginPage;

public class AcmetestDef {
	
	private WebDriver driver;
	
	private LoginPage loginPage;
	
	
	public AcmetestDef() {
		driver=DriverFactory.getDriver();
		
		loginPage=new LoginPage(driver);
		
	}
	
	
	
	@Given("i am in Acmetest Home Page")
	public void i_am_in_acmetest_home_page() {
	    loginPage.launchApp();
	}

	@Given("i Sign in as normal user")
	public void i_sign_in_as_normal_user() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		loginPage.enterUsername("abdul.shaikh@aqmtechnologies.com");
		loginPage.enterPassword("Gaffar@12345");
	}

	@When("i scroll down form home")
	public void i_scroll_down_form_home() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	    
	}

	@Then("I should see food menu over Hero module")
	public void i_should_see_food_menu_over_hero_module() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		System.out.println("page landed");
	}

	@When("i scroll up form Home")
	public void i_scroll_up_form_home() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		System.out.println("page landed");
		loginPage.clickOnSignIn();
	}

	@Then("Hero module is visible")
	public void hero_module_is_visible() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		loginPage.verifyLoginSuccess();
	}
	
	
	@Then("I Also Verify Page Title is {string}")
	public void verifyTitle(String s) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		loginPage.verifyPageTitle(s);
	}

}
