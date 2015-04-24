package app.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationUtility {

	public  static String getPropertyValue(String propertyName) throws IOException {

		String result = "";
		Properties prop = new Properties();
		String propFileName = "src/main/resources/config.properties";
		prop.load(new FileInputStream(propFileName));
		// get the property value and print it out
		result = prop.getProperty(propertyName);
		
		return result;
	}

}
