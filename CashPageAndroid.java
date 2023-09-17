package androidPages;

import java.util.List;

import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class CashPageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Cash";
	Assertion assertion = new Assertion();
	public static String platform;

	String coil1XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[1]";
	String coil2XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[2]";
	String coil3XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[3]";
	String coil4XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[4]";
	String cashXpath = "//android.widget.TextView[@content-desc=\"Cash\"]";
	String backBtn="//android.widget.TextView[@content-desc=\"Back\"]";
	String saveDoneBtn="//android.widget.TextView[@content-desc=\"Save and Done\"]";
//	String duplicateBagValueAlertMsg="//android.widget.TextView[@content-desc=\"No Duplicate bag number for the same route\"]";
	String finishLaterBtn="//android.widget.TextView[@content-desc=\"Finish Later (Not Done)\"]";
	
	String duplicateBagValueAlertMsg="//android.widget.TextView[@content-desc=\"text1\"]";
	String okBtn="//android.widget.TextView[@content-desc=\"button1\"]";

	/******* Test Data from Excel sheet ********/

	String cashHeaders = data.getCellData(sheetName, "CashHeaders", 2);

	String bagActualData = data.getCellData(sheetName, "Bag Actual", 2);
	String coilRefillActualData = data.getCellData(sheetName, "Coin Refill Actual", 2);
	String recyclerRefillActualData = data.getCellData(sheetName, "Recycler Refill Actual", 2);
	String refundActualData = data.getCellData(sheetName, "Refund Actual", 2);
	String testVendActualData = data.getCellData(sheetName, "Test Vend Actual", 2);
	String salesMeterActualData = data.getCellData(sheetName, "Sales Meter Actual", 2);

	private static Logger LOGGER;

	public CashPageAndroid validateCashHeadersDisplayed() {
		for (String cashHeader : cashHeaders.split(";")) {
			eleIsDisplayedAccessibilityID(cashHeader);
		}
		return this;
	}

	public CashPageAndroid clickOnElement(String icon) {
		clickAccessibilityID(icon);
		return this;
	}
	
	public CashPageAndroid clickOnOkayBtn() {
		click("xpath", okBtn);
		return this;
	}

	public CashPageAndroid validatecharLimitsOfAllFields() {
		String val = "";
		for (String cashHeader : cashHeaders.split(";")) {
			switch (cashHeader) {
			case "Bag":
				clickAccessibilityID(cashHeader);
				for (int i = 1; i < 9; i++) {
					clickAccessibilityID(String.valueOf(i));

				}
				val = getTextAccessibilityID(cashHeader);
				if (val.length() == 8) {
					System.out.println(cashHeader + " can accept only 8 characters and it is validated successfully");
				}
				break;
			case "Coin Refill":
				clickAccessibilityID(cashHeader);
				for (int i = 1; i < 9; i++) {
					clickAccessibilityID(String.valueOf(i));

				}
				val = getTextAccessibilityID(cashHeader);
				if (val.length() == 7) {
					System.out.println(cashHeader + " can accept only 7 characters and it is validated successfully");
				}
				break;
			case "Recycler Refill":
				clickAccessibilityID(cashHeader);
				for (int i = 1; i < 9; i++) {
					clickAccessibilityID(String.valueOf(i));

				}
				val = getTextAccessibilityID(cashHeader);
				if (val.length() == 7) {
					System.out.println(cashHeader + " can accept only 7 characters and it is validated successfully");
				}
				break;

			case "Refund":
				clickAccessibilityID(cashHeader);
				for (int i = 1; i < 3; i++) {
					clickAccessibilityID(String.valueOf(i));

				}
				val = getTextAccessibilityID(cashHeader);
				if (val.length() == 2) {
					System.out.println(cashHeader + " can accept only 2 characters and it is validated successfully");
				}
				break;
			case "Test Vend":
				clickAccessibilityID(cashHeader);
				for (int i = 1; i < 9; i++) {
					clickAccessibilityID(String.valueOf(i));

				}
				val = getTextAccessibilityID(cashHeader);
				if (val.length() == 7) {
					System.out.println(cashHeader + " can accept only 7 characters and it is validated successfully");
				}
				break;
			case "Sales Meter":
				clickAccessibilityID(cashHeader);
				for (int i = 1; i <= 9; i++) {
					clickAccessibilityID(String.valueOf(i));

				}
				clickAccessibilityID("1");
				val = getTextAccessibilityID(cashHeader);
				if (val.length() == 9) {
					System.out.println(cashHeader + " can accept only 9 characters and it is validated successfully");
				}
				break;

			default:
				break;
			}
		}
		return this;
	}

	public CashPageAndroid validateDataIsSavedForAllFields() {
		String val = "";
		for (String cashHeader : cashHeaders.split(";")) {
			switch (cashHeader) {
			case "Bag":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase(bagActualData)) {
					System.out.println("Data entered in " + cashHeader + " is saved and validated successfully");
				} else {
					throw new RuntimeException("Data entered in " + cashHeader + " is not saved");
				}
				break;
			case "Coin Refill":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase(coilRefillActualData)) {
					System.out.println("Data entered in " + cashHeader + " is saved and validated successfully");
				} else {
					throw new RuntimeException("Data entered in " + cashHeader + " is not saved");
				}
				break;
			case "Recycler Refill":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase(recyclerRefillActualData)) {
					System.out.println("Data entered in " + cashHeader + " is saved and validated successfully");
				} else {
					throw new RuntimeException("Data entered in " + cashHeader + " is not saved");
				}
				break;

			case "Refund":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase(refundActualData)) {
					System.out.println("Data entered in " + cashHeader + " is saved and validated successfully");
				} else {
					throw new RuntimeException("Data entered in " + cashHeader + " is not saved");
				}
				break;
			case "Test Vend":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase(testVendActualData)) {
					System.out.println("Data entered in " + cashHeader + " is saved and validated successfully");
				} else {
					throw new RuntimeException("Data entered in " + cashHeader + " is not saved");
				}
				break;
			case "Sales Meter":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase(salesMeterActualData)) {
					System.out.println("Data entered in " + cashHeader + " is saved and validated successfully");
				} else {
					throw new RuntimeException("Data entered in " + cashHeader + " is not saved");
				}
				break;

			default:
				break;
			}
		}
		return this;
	}

	public CashPageAndroid validateRoutePlaceTypeDisplayed() {
		List<String> assetIDRouteTypePlace = new ScheduledAssetPageAndroid().getAssetIDRouteTypePlaceDate();
		for (String val : assetIDRouteTypePlace) {
			eleIsDisplayedAccessibilityID(val);
		}
		return this;
	}

	public CashPageAndroid checkMarkNotDisplayed() {
		if (getResourceID("xpath", cashXpath).contains("tick: false")) {
			System.out.println("Pick List icon does not have completed checkmark");
		}
		return this;
	}

	public CashPageAndroid checkMarkDisplayed() {
		if (getResourceID("xpath", cashXpath).contains("tick: true")) {
			System.out.println("Cash icon has completed checkmark");
		}
		return this;
	}
	
	public CashPageAndroid clickOnEle(String ele) {
		clickAccessibilityID(ele);
		return this;
	}
	
	public CashPageAndroid navigateBackToScheduledAssetScreen() {
		click("xpath", backBtn);
		click("xpath", finishLaterBtn);
		return this;
	}
	
	public CashPageAndroid validateDecimalValueInField(String field) {
		clickAccessibilityID(field);
		clickAccessibilityID("clear");
		clickAccessibilityID("clear");
		clickAccessibilityID("1");
		clickAccessibilityID("dot");
		clickAccessibilityID("2");
		clickAccessibilityID("Bag");
		if(Float.valueOf(getTextAccessibilityID(field)).equals(Float.valueOf("1.2"))) {
			System.out.println(field+" field accepts decimal value as expected");
		}
		else {
			throw new RuntimeException(field+" field does not accept decimal value");
		}
		return this;
	}
	
	public CashPageAndroid validateOnClickingTapFocusIsOnField(String field) {
		if(getAttributeUsingAccessibilityID(field, "focused").equalsIgnoreCase("true")) {
			System.out.println("Focus is on field "+field+" when we make a tap on "+field);
		}
		else {
			throw new RuntimeException("Focus is not on field "+field+" when we make a tap on "+field);
		}
		return this;
	}
	
	public CashPageAndroid enterPreviousAssetFieldValue(String field) {
		char[] value=bagActualData.toCharArray();
		if(field.equalsIgnoreCase("Bag")) {
			clickAccessibilityID(field);
			for (char c : value) {
				clickAccessibilityID(String.valueOf(c));
				
			}
			
		}
		
		return this;
	}
	
	public CashPageAndroid validateDuplicateBagValueAlert() {
		String error=getText("xpath", duplicateBagValueAlertMsg);
		eleIsDisplayed("xpath", duplicateBagValueAlertMsg);
		System.out.println(error+" message is displayed as expected");
		return this;
	}
	
	public CashPageAndroid validateWatermarkDisplayedForAllFields() {
		String val = "";
		for (String cashHeader : cashHeaders.split(";")) {
			switch (cashHeader) {
			case "Bag":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase("0")) {
					System.out.println("Watermark is displayed for " + cashHeader + " and validated successfully");
				} else {
					throw new RuntimeException("Watermark is not displayed for " + cashHeader);
				}
				break;
			case "Coin Refill":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase("0.00")) {
					System.out.println("Watermark is displayed for " + cashHeader + " and validated successfully");
				} else {
					throw new RuntimeException("Watermark is not displayed for " + cashHeader);
				}
				break;
			case "Recycler Refill":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase("0.00")) {
					System.out.println("Watermark is displayed for " + cashHeader + " and validated successfully");
				} else {
					throw new RuntimeException("Watermark is not displayed for " + cashHeader);
				}
				break;

			case "Refund":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase("0.00")) {
					System.out.println("Watermark is displayed for " + cashHeader + " and validated successfully");
				} else {
					throw new RuntimeException("Watermark is not displayed for " + cashHeader);
				}
				break;
			case "Test Vend":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase("0.00")) {
					System.out.println("Watermark is displayed for " + cashHeader + " and validated successfully");
				} else {
					throw new RuntimeException("Watermark is not displayed for " + cashHeader);
				}
				break;
			case "Sales Meter":
				val = getTextAccessibilityID(cashHeader);
				if (val.equalsIgnoreCase("0")) {
					System.out.println("Watermark is displayed for " + cashHeader + " and validated successfully");
				} else {
					throw new RuntimeException("Watermark is not displayed for " + cashHeader);
				}
				break;

			default:
				break;
			}
		}
		return this;
	}

}
