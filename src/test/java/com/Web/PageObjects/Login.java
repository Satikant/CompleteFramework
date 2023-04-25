package com.Web.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericLib.BaseTest;
import genericLib.CommonLibrary;
import genericLib.Constants;

public class Login {
	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameeditbox;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordeditbox;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-heade']")
	private WebElement forgotpwd;
	
//	 By userNameId = By.xpath("//input[@name='username']");

	public void RedirectToSite() throws Throwable {
		BaseTest.driver.get(Constants.URL);
		Thread.sleep(3000);
	}

	public void LoginTosite_ValidCredentials() throws Throwable {
		CommonLibrary.implicitwait();
		CommonLibrary.setText(usernameeditbox, Constants.UserName, "Username");
		CommonLibrary.implicitwait();
		CommonLibrary.setText(passwordeditbox, Constants.Password, "Password");
		CommonLibrary.implicitwait();
		loginBtn.click();
		Thread.sleep(3000);
	}
	public void LoginTosite_InValidCredentials() throws Throwable {
		CommonLibrary.implicitwait();
		CommonLibrary.setText(usernameeditbox, Constants.UserName, "Username");
		CommonLibrary.implicitwait();
		CommonLibrary.setText(passwordeditbox, Constants.InvalidPassword, "Password");
		CommonLibrary.implicitwait();
		loginBtn.click();
		Thread.sleep(3000);
		forgotpwd.click();
	}

}
