package com.Web.TestScripts;

import static utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.Web.PageObjects.Login;

import genericLib.BaseTest;
import genericLib.Config;

public class WebScript extends BaseTest {
	@Test(priority = 1, enabled = true)
	public void Login001(Method method) throws Throwable {
		startTest(method.getName(), Config.property.getProperty("TC01"));
		Login lpage = PageFactory.initElements(driver, Login.class);
		lpage.RedirectToSite();
		lpage.LoginTosite_ValidCredentials();
	}

	@Test(priority = 2, enabled = true)
	public void Login002(Method method) throws Throwable {
		startTest(method.getName(), Config.property.getProperty("TC02"));
		Login lpage = PageFactory.initElements(driver, Login.class);
		lpage.RedirectToSite();
		lpage.LoginTosite_InValidCredentials();
	}
}
