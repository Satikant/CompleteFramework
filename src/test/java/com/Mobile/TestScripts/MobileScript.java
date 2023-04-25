package com.Mobile.TestScripts;

import static utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.Mobile.PageObjects.HomeScreenPageObj;

import genericLib.BaseTest;
import genericLib.Config;

public class MobileScript extends BaseTest {
	@Test
	public void AppLogin(Method method) throws Exception {
		startTest(method.getName(), Config.property.getProperty("TC10"));
		HomeScreenPageObj.readPageObjectProperties();
		HomeScreenPageObj.homescreeninitialization();
	}

	@Test
	public void Applaunch(Method method) throws Exception {
		startTest(method.getName(), Config.property.getProperty("TC20"));
		HomeScreenPageObj.readPageObjectProperties();
		HomeScreenPageObj.homescreeninitialization();
		HomeScreenPageObj.onlinepaymentscreen();
	}
}
