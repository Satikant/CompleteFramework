package utils.listeners;

import com.aventstack.extentreports.Status;

import genericLib.BaseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.extentreports.ExtentManager;
import utils.logs.Log;

import java.util.Objects;

import static utils.extentreports.ExtentTestManager.getTest;

public class TestListener extends BaseTest implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		Log.info("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		Log.info("I am in onFinish method " + iTestContext.getName());
		// Do tier down operations for ExtentReports reporting!
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is succeed.");
		// ExtentReports log operation for passed tests.
		try {
			Object testClass = iTestResult.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriver();
			String base64ScreenshotPass = "data:image/png;base64,"
					+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

			getTest().log(Status.PASS, "Test passed",
					getTest().addScreenCaptureFromBase64String(base64ScreenshotPass).getModel().getMedia().get(0));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is failed.");
		try {
			// Get driver from BaseTest and assign to local webdriver variable.
			Object testClass = iTestResult.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriver();

			// Take base64Screenshot screenshot for extent reports
			String base64ScreenshotFail = "data:image/png;base64,"
					+ ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

			// ExtentReports log and screenshot operations for failed tests.
			getTest().log(Status.FAIL, "Test Failed",
					getTest().addScreenCaptureFromBase64String(base64ScreenshotFail).getModel().getMedia().get(0));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is skipped.");
		// ExtentReports log operation for skipped tests.
		getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}
