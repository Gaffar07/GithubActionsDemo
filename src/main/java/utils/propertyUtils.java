package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class propertyUtils {

	
	public static Properties propertyLoader(String filepath) {
		Properties properties=new Properties();
		
		BufferedReader reader;
		
		try {
			
		reader =new BufferedReader(new FileReader(filepath));
		
		try {
			properties.load(reader);
			reader.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("filed to load propetyu file"+filepath);
		}
		}
		catch(FileNotFoundException e) {
			
		}
		
		return properties;
	}
}
