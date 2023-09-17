package androidPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class PicklistPageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Picklist";
	Assertion assertion = new Assertion();
	public static String platform;
	public static String assetID;
	public static String type;
	public static String place;
	static List<String> coilList = new ArrayList<>();
	static List<String> coils = new ArrayList<>();

	String checkMarkXpath = "";
	String backPicklistXpath = "//android.widget.TextView[@content-desc=\"Back\"]";
	String picklistXpath = "//android.widget.TextView[@content-desc=\"Pick List\"]";
	String serviceInvXpath = "//android.widget.TextView[@content-desc=\"Service/Inventory\"]";
	String noteXpath = "//android.widget.TextView[@content-desc=\"Note\"]";
	String machine1XPath = "(//android.widget.TextView[@content-desc=\"machine\"])[1]";
	String dexPriceMismatch = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView[1]";
	String coilsXpath = "(//android.widget.TextView[@content-desc=\"Coils\"])";
	
	String coil1XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[1]";
	String coil2XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[2]";
	String coil3XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[3]";
	String coil4XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[4]";
	String coil5XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[5]";
	String coil6XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[6]";
	String coil7XPath = "(//android.widget.TextView[@content-desc=\"Coils\"])[7]";

	/******* Test Data from Excel sheet ********/
	String dexPriceMismatchErrorMsgData = data.getCellData(sheetName, "Dex Price Mismatch Error Msg", 2);

	private static Logger LOGGER;

	public PicklistPageAndroid clickOnElement(String icon) {
		clickAccessibilityID(icon);
		return this;
	}

	public PicklistPageAndroid validateRoutePlaceTypeDisplayed() {
		List<String> assetIDRouteTypePlaceDate = new ScheduledAssetPageAndroid().getAssetIDRouteTypePlaceDate();
		sleep(3000);
		for (String val : assetIDRouteTypePlaceDate) {
			eleIsDisplayedAccessibilityID(val);
		}
		return this;
	}

	public PicklistPageAndroid checkMarkNotDisplayed(String icon) {
		switch (icon) {
		case "Pick List":
			if (getResourceID("xpath", picklistXpath).contains("tick: false")) {
				System.out.println("Pick List icon does not have completed checkmark");
			} else {
				throw new RuntimeException("Pick List icon has completed checkmark");
			}
			break;
		case "Service/Inventory":
			if (getResourceID("xpath", serviceInvXpath).contains("tick: false")) {
				System.out.println("Service/Inventory icon does not have completed checkmark");
			} else {
				throw new RuntimeException("Service/Inventory icon has completed checkmark");
			}
			break;
		case "Note":
			if (getResourceID("xpath", noteXpath).contains("tick: false")) {
				System.out.println("Note icon does not have completed checkmark");
			} else {
				throw new RuntimeException("Note icon has completed checkmark");
			}
			break;

		default:
			break;
		}

		return this;
	}

	public PicklistPageAndroid checkMarkDisplayed(String icon) {
		switch (icon) {
		case "Pick List":
			if (getResourceID("xpath", picklistXpath).contains("tick: true")) {
				System.out.println("Pick List icon has completed checkmark");
			} else {
				throw new RuntimeException("Pick List icon does not have completed checkmark");
			}
			break;
		case "Service/Inventory":
			if (getResourceID("xpath", serviceInvXpath).contains("tick: true")) {
				System.out.println("Service/Inventory icon has completed checkmark");
			} else {
				throw new RuntimeException("Service/Inventory icon does not have completed checkmark");
			}
			break;
		case "Note":
			if (getResourceID("xpath", noteXpath).contains("tick: true")) {
				System.out.println("Note icon has completed checkmark");
			} else {
				throw new RuntimeException("Note icon does not have completed checkmark");
			}
			break;

		default:
			break;
		}
		return this;
	}

	public PicklistPageAndroid clickOnBackBtnInPicklist() {
		click("xpath", backPicklistXpath);
		return this;
	}

	public List<String> extractCoilsDetails() {
		
		String clDetails = "";
		for (WebElement cl : getElements("xpath", coilsXpath)) {
				clDetails = cl.getAttribute("resource-id");
//				clName = clDetails.split(";")[0];
				coilList.add(clDetails);
			
		}
		return coilList;
	}

	public List<String> extractCoilsNames() {
		for (String cl : coilList) {
			String coi = cl.split(";")[0];
			coils.add(coi);
		}
		return coils;
	}

	public List<String> getCoilNames() {

		return coils;
	}

	public List<String> getCoilDetails() {

		return coilList;
	}

	public PicklistPageAndroid validateDexPriceMismatchIfDisplayed() {
		if(getElements("xpath", dexPriceMismatch).size()!=0) {
		if (getText("xpath", dexPriceMismatch).equalsIgnoreCase(dexPriceMismatchErrorMsgData)) {
			System.out.println("Dex Price msimatch error message is displayed as expected");
		} else {
			throw new RuntimeException("Dex Price msimatch error message is not displayed ");
		}
		}
		else {
			System.out.println("Dex Price mismatch error message is not there for the asset selected");
		}
		return this;
	}

//	public PicklistPageAndroid validateCoilDetailsDisplay() {
//		String coil1DetailsFromApp=getResourceID("xpath", coil1XPath);
//		if(coil1DetailsFromApp.equalsIgnoreCase(coil1Data)) {
//			System.out.println(coil1Data+" coil details is displayed as expected");
//		}
//		String coil2DetailsFromApp=getResourceID("xpath", coil2XPath);
//		if(coil2DetailsFromApp.equalsIgnoreCase(coil2Data)) {
//			System.out.println(coil2Data+" coil details is displayed as expected");
//		}
//		String coil3DetailsFromApp=getResourceID("xpath", coil3XPath);
//		if(coil3DetailsFromApp.equalsIgnoreCase(coil3Data)) {
//			System.out.println(coil3Data+" coil details is displayed as expected");
//		}
//		String coil4DetailsFromApp=getResourceID("xpath", coil4XPath);
//		if(coil4DetailsFromApp.equalsIgnoreCase(coil4Data)) {
//			System.out.println(coil4Data+" coil details is displayed as expected");
//		}
//		return this;
//	}

}
