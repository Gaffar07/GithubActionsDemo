package api.common;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import utils.ConfigLoader;

public class ResponseAppSpec {
	
	ResponseSpecification responseSpec;

	static ConfigLoader loader;
	static String channel;
	/**
	 * Declared in static block to read loader file
	 */
	static {
		loader = ConfigLoader.getInstance();
		channel = ConfigLoader.getChannelName();
	}

	public ResponseSpecification commonResponseSpec() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
	}

	public ResponseSpecification commonResponseSpec201() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(201).build();
	}

	public ResponseSpecification commonResponseSpec202() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(202).build();
	}

	public ResponseSpecification commonResponseSpec204() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(204).build();
	}

	public ResponseSpecification errorResponseSpec400() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(400).build();
	}

	public ResponseSpecification errorResponseSpec403() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(403).build();
	}
	
	public ResponseSpecification errorResponseSpec404() {
		return responseSpec = new ResponseSpecBuilder().expectStatusCode(404).build();

	}

}
