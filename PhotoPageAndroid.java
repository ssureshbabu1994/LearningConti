package androidPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class PhotoPageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Photo";
	Assertion assertion = new Assertion();
	public static String platform;

	String capture = "//android.view.ViewGroup[@content-desc=\"Camera\"]/android.view.ViewGroup";
	String cancel = "//android.view.ViewGroup[@content-desc=\"Cancel\"]/android.widget.TextView";
	String save = "//android.view.ViewGroup[@content-desc=\"Save\"]/android.widget.TextView";
	String flash = "//android.view.ViewGroup[@content-desc=\"Flash\"]/android.widget.TextView";
	String previewOption = "//android.view.ViewGroup[@content-desc=\"Save\"]/android.widget.TextView";
	/******* Test Data from Excel sheet ********/

	String photoFeatures = data.getCellData(sheetName, "Photo features", 2);

	private static Logger LOGGER;

	public PhotoPageAndroid clickOnElement(String icon) {
		clickAccessibilityID(icon);
		return this;
	}

	public PhotoPageAndroid validatePhotoIconDisplayed() {
		eleIsDisplayedAccessibilityID("Photo");
		return this;
	}
	
	public PhotoPageAndroid validatePreviewOptionOfCapturedPhotoDisplayed() {
		eleIsDisplayed("xpath", previewOption);
		return this;
	}

	public PhotoPageAndroid validatePhotoFeaturesDisplayed() {
		List<String> photoFeat = Arrays.asList(photoFeatures.split(";"));
		for (String feature : photoFeat) {
			switch (feature) {
			case "Capture":
				eleIsDisplayed("xpath", capture);
				break;
			case "Save":
				eleIsDisplayed("xpath", save);
				break;
			case "Cancel":
				eleIsDisplayed("xpath", cancel);
				break;
			case "Flash":
				eleIsDisplayed("xpath", flash);
				break;

			default:
				break;
			}
		}
		return this;
	}

	public PhotoPageAndroid clickOnIconInPhotoScreen(String icon) {

		switch (icon) {
		case "Capture":
			click("xpath", capture);
			break;
		case "Save":
			click("xpath", save);
			break;
		case "Cancel":
			click("xpath", cancel);
			break;
		case "Flash":
			click("xpath", flash);
			break;

		default:
			break;
		}

		return this;

	}

}
