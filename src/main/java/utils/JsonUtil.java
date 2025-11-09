package utils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonUtil {
	static volatile JSONParser jsonParser = new JSONParser();
	static volatile JSONObject jsonObject =null;
	static volatile JSONArray jsonArray =null;
	public synchronized static JSONObject getJsonData(String filePath)   {
        try (FileReader reader = new FileReader(filePath)){
		Object object = jsonParser.parse(reader);
		jsonObject = (JSONObject) object;		
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonObject;
	}
	public synchronized static JSONObject getJsonBody(String jsonString)   {
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(jsonString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return jsonObject;
	}
	public synchronized static JSONArray getJsonArray(String jsonString)   {
		
		try {
			jsonArray = (JSONArray) jsonParser.parse(jsonString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return jsonArray;
	}


}
