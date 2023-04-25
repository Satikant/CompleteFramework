package genericLib;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.logs.Log;

public class BaseTest {
	public static WebDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	public static Dictionary<String, String> dataList = new Hashtable<String, String>();
//	public static Dictionary<String, String> dictPageObjects = new Hashtable<String, String>();
	public static String DataFilePath = System.getProperty("user.dir") + "//testData//ProjectData.xlsx//";

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void setupProxy() {

	}

	@BeforeMethod
	// @Parameters("MYBROWSER") /*To execute scripts in different browser */
	public void Configurations() throws IOException {
		/* Manadatory to initialize the property file */
		Config.propertyfileInit();

		if (Constants.Platform.equalsIgnoreCase("BrowserstackAndroid")) {
			/* Android CAPABILITIES */
			try {
				DesiredCapabilities caps = new DesiredCapabilities();

				// Specify device and os_version for testing
				caps.setCapability("device", "OnePlus 9");
				caps.setCapability("os_version", "11.0");

				// Set your access credentials
				caps.setCapability("browserstack.user", "satikantap_hrWRfx");
				caps.setCapability("browserstack.key", "HM2BjxPSHhex1SzksnNZ");

				// Set URL of the application under test
				caps.setCapability("app", "bs://eb706d3b62063a7d98dc28ca94145f47b037c7f0");

				// Set other BrowserStack capabilities
				caps.setCapability("project", "Mobiquity");
				caps.setCapability("build", "Mobiquity-build-002");
				caps.setCapability("name", "BrowserStackExecution");

				// Debuggingg capabilities
				caps.setCapability("browserstack.debug", "true"); // to enable visual logs
				caps.setCapability("browserstack.networkLogs", "true"); // to enable network logs

				/* LOCAL Testing */
				caps.setCapability("browserstack.local", true);
//					localInstance = new Local();
//				    Map<String, String> options = new HashMap<String, String>();
//				    options.put("key", "HM2BjxPSHhex1SzksnNZ");
//				    localInstance.start(options);

				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above
				driver = new AndroidDriver(new URL("http://hub.browserstack.com/wd/hub"), caps);
			} catch (Exception e) {
				System.out.println("Exceptions: There is an issue launching app");
			}
		} else if (Constants.Platform.equalsIgnoreCase("BrowserstackiOS")) {
			/* iOS CAPABILITIES */
			try {
				DesiredCapabilities caps = new DesiredCapabilities();

				// Set your access credentials
				caps.setCapability("browserstack.user", "satikantap_hrWRfx");
				caps.setCapability("browserstack.key", "HM2BjxPSHhex1SzksnNZ");

				// Set URL of the application under test
				caps.setCapability("app", "bs://3a3686fca8842854792886955b4b087523d7bfba");

				// Specify device and os_version for testing
				caps.setCapability("device", "iPhone XS");
				caps.setCapability("os_version", "15");

				// Set other BrowserStack capabilities
				caps.setCapability("project", "Mobiquity");
				caps.setCapability("build", "Mobiquity-iOS-build-003");
				caps.setCapability("name", "BrowserStackExecution");

				// Debuggingg capabilities
				caps.setCapability("browserstack.debug", "true"); // to enable visual logs
				caps.setCapability("browserstack.networkLogs", "true"); // to enable network logs
				caps.setCapability("autoDismissAlerts", true);

				/* LOCAL Testing */
				caps.setCapability("browserstack.local", true);

				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above
				driver = new IOSDriver(new URL("http://hub.browserstack.com/wd/hub"), caps);
			} catch (Exception e) {
				System.out.println("Exceptions: There is an issue launching app");
			}
		} else if (Constants.Platform.equalsIgnoreCase("Android")) {
			try {
				System.out.println("Setting Capabilities of Android Driver");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.devicename);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.deviceplatform);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.deviceversion);
				capabilities.setCapability("appPackage", "com.mahidracomviva.payplus.sdkdemo");
				capabilities.setCapability("appActivity", "com.mahidracomviva.payplus.sdkdemo.MainActivity");
				driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			} catch (Exception e) {
				System.out.println("Exceptions: There is an issue launching Android app");
			}
		} else if (Constants.Platform.equalsIgnoreCase("iOS")) {
			try {
				System.out.println("Setting Capabilities of IOS Driver");
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITEST");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.devicename);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.deviceplatform);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.deviceversion);
				capabilities.setCapability(MobileCapabilityType.UDID, Constants.deviceudid);
			    capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/resources/payplusconsumersdkdemo.app");
//				capabilities.setCapability(MobileCapabilityType.APP, Constants.apppath);
				capabilities.setCapability("connectHardwareKeyboard", false);
				try {
					driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} catch (SessionNotCreatedException e) {
				System.out.println("Exceptions: There is an issue launching iOS app");
			}
		} else if (Constants.Platform.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./resources/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Constants.Platform.equalsIgnoreCase("Chrome")) {
			// System.setProperty("webdriver.chrome.driver", "./resources/chromedriver");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			/*
			 * Wait for every webelement in one TestScripts
			 */
			CommonLibrary.implicitwait();
		}
	}

	@AfterMethod
	public void teardown() {
		Log.info("Tests are ending!");
		driver.quit();
	}
}