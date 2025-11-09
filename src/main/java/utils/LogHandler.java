package utils;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogHandler {
	
	private static Logger LOG;

	public LogHandler(final Class<?> clazz) {
		LOG = LogManager.getLogger(clazz);
	}

	public void testLog(String message) {
		LOG.info(message);
		ExtentCucumberAdapter.addTestStepLog(message);

	}
	public static void logHeaders(List<Header> headersList) {
		ExtentCucumberAdapter.addTestStepLog("Headers are :: ");
		headersList.stream().forEach((hl)->{
			LOG.info(hl.getName()+": "+hl.getValue());
			ExtentCucumberAdapter.addTestStepLog(hl.getName()+": "+hl.getValue());
		});
	}
	public static void logBody(String message) {
		ExtentCucumberAdapter.addTestStepLog("Body is :: ");
		String [] msgBody=message.split("\n");
		Arrays.stream(msgBody).forEach((body)->{
			LOG.info(body);
			ExtentCucumberAdapter.addTestStepLog(body);
		});

	}

}
