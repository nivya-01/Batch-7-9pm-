package genericUtilityOrangeHRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility 
{
	public String toGetDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\orangeHRM.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}

}
