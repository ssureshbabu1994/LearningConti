package androidPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class NotePageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Note";
	Assertion assertion = new Assertion();
	public static String platform;

	String backBtn = "//android.widget.TextView[@content-desc=\"Back\"]";
	String textBox = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText";
	String novedSavedMsg = "//android.widget.TextView[@content-desc=\"text1\"]";
	String noteOkay = "//android.widget.TextView[@content-desc=\"button1\"]";
	/******* Test Data from Excel sheet ********/

	String noteInputTextData = data.getCellData(sheetName, "InputText", 2);
	String confirmationPopupMsgData = data.getCellData(sheetName, "Confirmation Pop up Message", 2);

	private static Logger LOGGER;

	public NotePageAndroid clickOnElement(String icon) {
		clickAccessibilityID(icon);
		return this;
	}

	public NotePageAndroid clickOnBack() {
		click("xpath", backBtn);
		return this;
	}

	public NotePageAndroid validateRoutePlaceTypeDisplayed() {
		List<String> assetIDRouteTypePlace = new ScheduledAssetPageAndroid().getAssetIDRouteTypePlaceDate();
		for (String val : assetIDRouteTypePlace) {
			eleIsDisplayedAccessibilityID(val);
		}
		return this;
	}

	public NotePageAndroid validateNoteTextDisplayed(String text) {
		eleIsDisplayedAccessibilityID(text);
		return this;
	}

	public NotePageAndroid validateTextBoxToComposeMessageDisplayed() {
		String text = getText("xpath", textBox);
		if (text.equalsIgnoreCase("Compose your message here")) {
			System.out.println(text + " text is displayed as expected");
		} else {
			throw new RuntimeException(text + " text is not displayed");
		}
		return this;
	}

	public NotePageAndroid validateIfUserCanEnterTextInNoteTextBox() {

		enterValue(noteInputTextData, "xpath", textBox);
		if (getText("xpath", textBox).equalsIgnoreCase(noteInputTextData)) {
			System.out.println("User can enter text in Note text box successufully as expected");
		} else {
			throw new RuntimeException("User cannot enter text in Note text box");
		}
		return this;
	}

	public NotePageAndroid navigateBackAndValidateConfirmationPopUp() {

		click("xpath", backBtn);
		eleIsDisplayed("xpath", novedSavedMsg);
		eleIsDisplayed("xpath", noteOkay);
//		eleIsDisplayedAccessibilityID(confirmationPopupMsgData);
//		eleIsDisplayedAccessibilityID("Okay");
		System.out.println(getText("xpath", novedSavedMsg) + " and " + "Okay button is displayed as expected");
		click("xpath", noteOkay);

		return this;
	}

	public NotePageAndroid validateLastSavedNoteDisplayedInNoteTextBox() {

		if (getText("xpath", textBox).equalsIgnoreCase(noteInputTextData)) {
			System.out.println("Last saved note is displayed in Note text box successufully as expected");
		} else {
			throw new RuntimeException("Last saved note is not displayed in Note text box");
		}
		return this;
	}

	public NotePageAndroid validateIfUserCanEditExistingNotes() {
		try {
			click("xpath", textBox);

			Robot rb = new Robot();
			type(rb, KeyEvent.VK_CONTROL, KeyEvent.VK_HOME);
			for (int i = 0; i < noteInputTextData.length(); i++) {
				type(rb, KeyEvent.VK_RIGHT);
			}
			rb.keyPress(KeyEvent.VK_A);
			rb.keyRelease(KeyEvent.VK_A);

//			for (int i=0;i<noteInputTextData.length();i++) {
//				rb.keyPress(KeyEvent.VK_ALT);
//				rb.keyPress(KeyEvent.VK_RIGHT);
//				rb.keyRelease(KeyEvent.VK_RIGHT); 
//				rb.keyRelease(KeyEvent.VK_ALT);
//				
//			}

			if (getText("xpath", textBox).equalsIgnoreCase(noteInputTextData + "A")) {
				System.out.println("User can edit existing notes in Note text box successufully as expected");
			} else {
				throw new RuntimeException("User cannot edit existing notes in Note text box");
			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		enterValue(noteInputTextData, "xpath", textBox);

		return this;
	}

	public void type(Robot robot, int... keycodes) {
		for (int keycode : keycodes) {
			robot.keyPress(keycode);
		}
		for (int keycode : keycodes) {
			robot.keyRelease(keycode);
		}
	}

}
