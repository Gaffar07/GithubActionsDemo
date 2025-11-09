package Runners;
import com.aventstack.extentreports.service.ExtentService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.ConfigLoader;
import utils.DriverFactory;

@CucumberOptions(
		features = { "./src/test/resources/android_features" }, 
		glue = { "hooks", "androidStepDef" },
		tags = "@Smoke",
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-timelines/",
				"rerun:target/rerun.txt" }, 
		monochrome = true)

public class AndroidRuner extends AbstractTestNGCucumberTests {
	
	@AfterClass
	public void quitDriver() {
		if (DriverFactory.getMobileDriver() != null) {
			DriverFactory.quitMobileDriver();
		}
		String appName=System.getProperty("app");
		String Enviroment=System.getProperty("testenv");
		if (appName==null){
			appName= ConfigLoader.getInstance().getTestTEST_APP_NAME();
			Enviroment=ConfigLoader.getInstance().getTestConfigFileName();
		}
		ExtentService.getInstance().setSystemInfo("Application Name", appName);
		ExtentService.getInstance().setSystemInfo("Enviroment", Enviroment);
	}
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();
	}


}
