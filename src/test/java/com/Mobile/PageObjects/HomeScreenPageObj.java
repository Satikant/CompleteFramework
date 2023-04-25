package com.Mobile.PageObjects;

import java.util.Dictionary;
import genericLib.BaseTest;
import genericLib.CommonLibrary;
import genericLib.Excelutils;

public class HomeScreenPageObj extends BaseTest {
	private static Dictionary<String, String> dataList;
	private static String SheetName = "Prerequisites";

	public static void readTestData() throws Exception {
		dataList = Excelutils.getExcelFile(DataFilePath, SheetName);
	}

	public static void readPageObjectProperties() throws Exception {
		String pageObjectsSheetName = "";
		readTestData();
		System.out.println(dataList.get("DeviceType"));
		if (dataList.get("DeviceType").equals("Android")) {
			pageObjectsSheetName = "HomeScreenData";
			System.out.println("Fetching data for Android");
		} else {
			pageObjectsSheetName = "HomeScreenData";
			System.out.println("Fetching data for iOS");
		}
		System.out.println("Page Object Sheet Name = " + pageObjectsSheetName);
		Excelutils.getExcelFile(DataFilePath, pageObjectsSheetName);
	}

	public static void homescreeninitialization() throws Exception {
		try {
			dataList = Excelutils.getExcelFile(DataFilePath, "HomeScreenData");
			if (dataList.get("DeviceType").equals("Android")) {
				System.out.println("Execution started for Android");
				CommonLibrary.explicitwait(dataList.get("buyNowButton"));
				CommonLibrary.clickElement(dataList.get("buyNowButton"),"Clicking on BuyNow button");
				Thread.sleep(10000);
			} else {
				System.out.println("Execution started for iOS");
				Thread.sleep(2000);
				CommonLibrary.explicitwait(dataList.get("iOSbuyNowButton"));
				CommonLibrary.clickElement(dataList.get("iOSbuyNowButton"),"Clicking on BuyNow button");
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Authorization token could not be retrieved");
		}
	}
	public static void onlinepaymentscreen() throws Exception {
		try {
			if (dataList.get("DeviceType").equals("Android")) {
				System.out.println("Execution started for Android");
				CommonLibrary.explicitwait(dataList.get("cardButton"));
				CommonLibrary.verifyEqualMobile(dataList.get("cardButton"), dataList.get("payplus_iOSsdk_card"));
				Thread.sleep(10000);
			} else {
				System.out.println("Execution started for iOS");
				Thread.sleep(5000);
				CommonLibrary.verifyEqualMobile(dataList.get("iOScardButton"), dataList.get("payplus_iOSsdk_card"));
				Thread.sleep(10000);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Authorization token could not be retrieved");
		}
		
	}

}
