package DDT;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToReadDataFromJsonFile {

	public static void main(String[] args) throws IOException, ParseException
	{
		FileReader fir=new FileReader(".\\src\\test\\resources\\Commondata1.json");
		JSONParser parser=new JSONParser();
		//convert physical file to java obj
		Object javaobj = parser.parse(fir);
		JSONObject jsobj=(JSONObject) javaobj;
		String BROWSER = jsobj.get("Browser").toString();
		String USERNAME = jsobj.get("Username").toString();
		String PASSWORD = jsobj.get("Password").toString();
		System.out.println(BROWSER+"\n"+USERNAME+"\n"+PASSWORD);
		

	}

}
