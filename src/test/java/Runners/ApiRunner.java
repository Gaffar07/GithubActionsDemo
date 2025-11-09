package Runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.ConfigLoader;
import api.common.ApiRequest;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import utils.ConfigLoader;



@CucumberOptions(
        features = {"./src/test/resources/api_Features"},
        glue = {"Hooks", "apiStepDef"},
        tags = "@giftcard1",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-timelines/",
                "rerun:target/rerun.txt"},
        monochrome = true
)

public class ApiRunner extends AbstractTestNGCucumberTests{
	ConfigLoader loader = ConfigLoader.getInstance();
	
	 @Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }


}
