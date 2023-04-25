package genericLib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.extentreports.ExtentTestManager;

public class CommonLibrary extends BaseTest {
	private static ExtentTest testNode;
	private static Markup m;
	
	
	private static void createAssertionLabel() {
		m = MarkupHelper.createLabel("Assertion Block", ExtentColor.GREEN);
		ExtentTestManager.getTest().info(m);
	}
	
	private static void createCodeBlock(String actual, String expected) {
		String code = "Actual   :" + actual + "\nExpected :" + expected;
		// String code[][] = {{"Verify", "Equality of Assertion"}, {"Actual", actual},
		// {"Expected", expected}};
		Markup m = MarkupHelper.createCodeBlock(code);
		testNode.info(m);
	}
	private static void createCodeBlockMobile(String actual, String expected) {
		String code = "Actual:" + driver.findElement(By.xpath(actual)).getText() + "\nExpected:" +expected;
		// String code[][] = {{"Verify", "Equality of Assertion"}, {"Actual", actual},
		// {"Expected", expected}};
		Markup m = MarkupHelper.createCodeBlock(code);
		testNode.info(m);
	}
	public static void assertAPI(String actual, String expected) {
		testNode = ExtentTestManager.getTest();
		try {
			createAssertionLabel();
			createCodeBlock(actual, expected);
			if (actual.equalsIgnoreCase(expected)) {
				testNode.pass(m);
			} else {
				testNode.fail(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
			testNode.fail("Test Case Failed");
			testNode.log(Status.INFO, "StackTrace Result: " + ExceptionUtils.getStackTrace(e));
		}
	}
	public static void verifyEqual(String actual, String expected) {
		testNode = ExtentTestManager.getTest();
		try {
			createAssertionLabel();
			createCodeBlock(actual, expected);
			if (actual.equalsIgnoreCase(expected)) {
				testNode.pass(m);
			} else {
				testNode.fail(m);
			}
//            captureScreenMobile(testNode, "");
		} catch (Exception e) {
			e.printStackTrace();
			testNode.fail("Test Case Failed");
			testNode.log(Status.INFO, "StackTrace Result: " + ExceptionUtils.getStackTrace(e));
		}
	}
	public static void verifyEqualMobile(String actual, String expected) {
		testNode = ExtentTestManager.getTest();
		try {
			createAssertionLabel();
			Thread.sleep(5000);
			createCodeBlockMobile(actual, expected);
//			if (actual.equals(expected)) {
//				testNode.pass(m);
//			} else {
//				testNode.fail(m);
//			}
//            captureScreenMobile(testNode, "");
		} catch (Exception e) {
			e.printStackTrace();
			testNode.fail("Test Case Failed IN ASSERTIONS");
			testNode.log(Status.INFO, "StackTrace Result: " + ExceptionUtils.getStackTrace(e));
		}
	}
	
	public static void implicitwait() {
		BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public static void explicitwait(String element) {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
	}

	public static void clickElement(String element,String elementName) {
		testNode = ExtentTestManager.getTest();
		try {
			BaseTest.driver.findElement(By.xpath(element)).click();
			testNode.info(elementName);
		} catch (Exception e) {
			e.printStackTrace();
			testNode.fail("Test Case Failed");
			testNode.log(Status.INFO, "StackTrace Result: " + ExceptionUtils.getStackTrace(e));
			System.out.println("Button is not clicked");
		}
	}
	public static void verifyContains(String actual, String expected) {
		testNode = ExtentTestManager.getTest();
		try {
			createAssertionLabel();
			createCodeBlock(actual, expected);
			if (actual.contains(expected)) {
				testNode.pass("Assertion PASS");
			} else {
				testNode.fail("Assertion FAIL");
			}
//        captureScreenMobile(testNode,"");
		} catch (Exception e) {
			e.printStackTrace();
			testNode.fail("Test Case Failed");
			testNode.log(Status.INFO, "StackTrace Result: " + ExceptionUtils.getStackTrace(e));
		}
	}

	public static void sendkeys(String element, String values) {
		BaseTest.driver.findElement(By.xpath(element)).sendKeys(values);
	}
	public static void setText(WebElement element, String text, String elementName) {
		testNode = ExtentTestManager.getTest();
		element.sendKeys(text);
//        pageInfo.info("Entered text: '" + text + "' into field " + elementName);
//        testNode.log(Status.INFO, elementName);
		testNode.info("Entered text: '" + text + "' into field " + elementName);
	}

	public static void clearfield(String element) {
		BaseTest.driver.findElement(By.xpath(element)).clear();
	}

	public static void iOSswipedown() {
		HashMap<String, Object> scrollObj = new HashMap<>();
		scrollObj.put("direction", "down");
//		BaseTest.driver.executeScript("mobile: scroll", scrollObj);

	}

//	public static void explicitWait(String locator) {
//		WebDriverWait wait = new WebDriverWait(GlobalVariables.driver, 30);
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(dictPageObjects.get(locator))));
////		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(dictPageObjects.get(locator)))));
//	}
	public String captureScreen(String testname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) BaseTest.driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + testname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}

}
