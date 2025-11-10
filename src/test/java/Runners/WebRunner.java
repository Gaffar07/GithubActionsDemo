package Runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		
		features= {"./src/test/resources/web_features"},
		glue= {"Hooks","webStepDef"},
		tags="@Hero",
		plugin = {
				  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				  "json:target/cucumber.json",
				  "timeline:test-output-timelines/",
				  "rerun:target/rerun.txt"
				}

	)

public class WebRunner  extends AbstractTestNGCucumberTests{
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		
		return super.scenarios();
	}


}
