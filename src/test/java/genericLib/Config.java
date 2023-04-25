package genericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static Properties property;
	private static String propertyFilePath = "./resources/testcase.properties";

	public static void propertyfileInit() throws IOException {
		property = new Properties();
		try {
			InputStream inst = new FileInputStream(propertyFilePath);
			property.load(inst);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//	private Properties properties;
//	private final String propertyFilePath= "/Users/satikantpradhan/Documents/BACKUp Project RND/TillReportNewFramework/ExtentReportsExample-master/resources/testcase.properties";
//	
//	
//    public Config() {
//		BufferedReader reader;
//		try {
//			reader = new BufferedReader(new FileReader(propertyFilePath));
//			properties = new Properties();
//			try {
//				properties.load(reader);
//				reader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
//		}
//	}
//    public String getproperty(String test) {
//		String error = properties.getProperty(test);
//		if (error != null)
//			return error;
//		else
//			throw new RuntimeException("String not found");
//	}
}
