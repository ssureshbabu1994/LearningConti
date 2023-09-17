package androidPages;

import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class LoginLogoutPageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "LoginDetails";
	Assertion assertion = new Assertion();
	public static String platform;

	String username = "//android.widget.EditText[@content-desc=\"Username\"]";
	String password = "//android.widget.EditText[@content-desc=\"Password\"]";
	String login = "//android.widget.TextView[@content-desc=\"LOG IN\"]";
	String show = "//android.widget.TextView[@content-desc=\"Show\"]";
	String usernameorpassword = "//android.widget.TextView[@content-desc=\"Error Message\"]";
	String validpasswordError = "//android.widget.TextView[@content-desc=\"Error Message\"]";
	String validUsername = "//android.widget.TextView[@content-desc=\"Error Message\"]";
	String Operator = "(//android.widget.TextView[@content-desc=\"Text\"])[1]";
	String logout = "//android.widget.TextView[@content-desc=\"Logout\"]";
//	String yes = "//android.widget.TextView[@content-desc=\"Yes\"]";
	
	String yes = "//android.widget.TextView[@content-desc=\"button1\"]";
	
	String itemSearch = "//android.widget.EditText[@content-desc=\"Item Search\"]";
	String item = "(//android.widget.TextView[@content-desc=\"Text\"])[4]";

	String splashScreen = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";
	String skipUsernamePwdErrorMsg = "//android.widget.TextView[@content-desc=\"Error Message\"]";
	String validUsernameInCorrectPwdErrorMsg1 = "//android.widget.TextView[@content-desc=\" Incorrect Username or Password\"]";
	String validUsernameInCorrectPwdErrorMsg2 = "//android.widget.TextView[@content-desc=\"Please contact your admin if you need to reset your password\"]";
	String blankUsernameValidPwdErrorMsg = "//android.widget.TextView[@content-desc=\"Error Message\"]";
	String validUsernameBlankPwdErrorMsg = "//android.widget.TextView[@content-desc=\" Please enter a valid Password\"]";
	String hideOrShow = "(//android.widget.TextView[@content-desc=\"Text\"])[1]";
	String copyRightText = "(//android.widget.TextView[@content-desc=\"Text\"])[3]";
	String version = "(//android.widget.TextView[@content-desc=\"Text\"])[4]";
	String blankSpacesErrorMsg = "//android.widget.TextView[@content-desc=\"Error Message\"]";

//	String logoutNo = "//android.widget.TextView[@content-desc=\"No\"]";
	String logoutNo = "//android.widget.TextView[@content-desc=\"button2\"]";

	String allDriverAppAccessLocation = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";
	
	String driverAppTakePhotoVideo = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";
	String allDriverAppAccessMedia = "com.android.permissioncontroller:id/permission_allow_button";
	
	/******* Test Data from Excel sheet ********/
	
	String usernameData = data.getCellData(sheetName, "Username", 2);
	String passwordData = data.getCellData(sheetName, "Password", 2);
	String skipUsernamePwdErrorMsgData = data.getCellData(sheetName, "Skip username or pwd error msg", 2);
	String validUsernameIncorrectPwdErrorMsgData = data.getCellData(sheetName, "Valid username incorrect pwd error msg",
			2);
	String blankUsernameValidPwdErrorMsgData = data.getCellData(sheetName, "Blank username valid pwd error msg", 2);
	String validUsernameBlankPwdErrorMsgData = data.getCellData(sheetName, "Valid username blank pwd error msg", 2);
	String copyRightTextData = data.getCellData(sheetName, "Copyright text", 2);
	String versionData = data.getCellData(sheetName, "Version", 2);
	String blankSpacesErrorMsgData = data.getCellData(sheetName, "Blank spaces error msg", 2);
	String usernameInHomescreenData = data.getCellData(sheetName, "Username in Homescreen", 2);
	String blankSpacesUsernameData = data.getCellData(sheetName, "Username with Blank spaces", 2);
	String blankSpacesPwdData = data.getCellData(sheetName, "Password with Blank spaces", 2);

	private static Logger LOGGER;

	public LoginLogoutPageAndroid clickOperator() {
		click("xpath", item);

		return this;
	}

	public LoginLogoutPageAndroid VerifyItemSearchFilterSuccessfully() {

		verifyText("", "xpath", item);
		return this;
	}

	public LoginLogoutPageAndroid enterSearchItem() {
		enterValue("", "xpath", itemSearch);
		return this;
	}

	public LoginLogoutPageAndroid verifyItemSearch() {
		eleIsDisplayed("xpath", itemSearch);
		return this;
	}

	public LoginLogoutPageAndroid clickLogoutYes() {
		click("xpath", yes);
		return this;
	}

	public LoginLogoutPageAndroid clickLogout() {
		click("xpath", logout);
		return this;
	}

	public LoginLogoutPageAndroid verifyOperatorText() {
		eleIsDisplayed("xpath", Operator);
		return this;
	}

	public LoginLogoutPageAndroid verifyValidUsernameError() {
		eleIsDisplayed("xpath", validUsername);
		return this;
	}

	public LoginLogoutPageAndroid enterValidPassword() {
		clear("xpath", password);
		enterValue(passwordData, "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid enterIncorrectPassword() {
		clear("xpath", password);
		enterValue("ab12", "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid enterPasswordAlone() {
		clear("xpath", username);
		enterValue(passwordData, "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid enterIncorrectPasswordAlone() {
		enterValue("abc", "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid verifyValidPasswordError() {
		eleIsDisplayed("xpath", validpasswordError);
		return this;
	}

	public LoginLogoutPageAndroid enterEmailAddress() {
		clear("xpath", username);
		enterValue(usernameData, "xpath", username);

		return this;
	}

	public LoginLogoutPageAndroid enterBlankSpacesAddress() {
		clear("xpath", username);
		enterValue(blankSpacesUsernameData, "xpath", username);

		return this;
	}

	public LoginLogoutPageAndroid enterBlankSpacesPwd() {
		clear("xpath", password);
		enterValue(blankSpacesPwdData, "xpath", password);

		return this;
	}

	public LoginLogoutPageAndroid enterBlankEmailAddress() {
		clear("xpath", username);
		enterValue("", "xpath", username);

		return this;
	}

	public LoginLogoutPageAndroid enterBlankPassword() {
		clear("xpath", password);
		enterValue("", "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid verifyUsernamePasswordError() {
		eleIsDisplayed("xpath", usernameorpassword);

		return this;
	}

	public LoginLogoutPageAndroid clickLogin() {
		click("xpath", login);
		return this;
	}

	public LoginLogoutPageAndroid clickdriverAppAccessLocation() {
		clickById(allDriverAppAccessLocation);
		return this;
	}

	public LoginLogoutPageAndroid clickdriverAppAccessMedia() {
		clickById(allDriverAppAccessMedia);
		return this;
	}
	
	public LoginLogoutPageAndroid clickdriverAppTakePhotoVideo() {
		clickById(driverAppTakePhotoVideo);
		return this;
	}

	public LoginLogoutPageAndroid verifyUsername(String version) {
		platform = version;
		eleIsDisplayed("xpath", username);
		return this;
	}

	public void driverquit() {
		if (platform.equalsIgnoreCase("iOS")) {
			iOSdriver.closeApp();
			iOSdriver.quit();

		} else if (platform.equalsIgnoreCase("android")) {
			Androiddriver.closeApp();
			Androiddriver.quit();
		}
	}

	public LoginLogoutPageAndroid verifyPassword() {
		eleIsDisplayed("xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid verifyShowlink() {
		eleIsDisplayed("xpath", show);
		return this;
	}

	public LoginLogoutPageAndroid verifyLogin() {
		eleIsDisplayed("xpath", login);
		return this;
	}

	public LoginLogoutPageAndroid validateSplashScreenDisplayed() {
		eleIsDisplayed("xpath", splashScreen);
		return this;
	}

	public LoginLogoutPageAndroid verifySkipUsernamePwdErrorMsg() {
		eleIsDisplayedAccessibilityID(skipUsernamePwdErrorMsgData);
		return this;
	}

	public LoginLogoutPageAndroid verifyValidUsernameInCorrectPwdErrorMsg() {
		eleIsDisplayedAccessibilityID(validUsernameIncorrectPwdErrorMsgData);
		return this;
	}

	public LoginLogoutPageAndroid verifyBlankUsernameValidPwdErrorMsg() {
		eleIsDisplayedAccessibilityID(blankUsernameValidPwdErrorMsgData);
		return this;
	}

	public LoginLogoutPageAndroid verifyValidUsernameBlankPwdErrorMsg() {
		eleIsDisplayed("xpath", validUsernameBlankPwdErrorMsg);
		return this;
	}

	public LoginLogoutPageAndroid verifyValidUsernameIncorrectPwdErrorMsg() {
		eleIsDisplayed("xpath", validUsernameInCorrectPwdErrorMsg1);
		eleIsDisplayed("xpath", validUsernameInCorrectPwdErrorMsg2);
		return this;
	}

	public LoginLogoutPageAndroid verifyHideOrShowDisplayed() {
		eleIsDisplayedAccessibilityID("Show");
		return this;
	}

	public LoginLogoutPageAndroid verifyCopyrightTextVersionDisplayed() {
		eleIsDisplayedAccessibilityID(copyRightTextData);
		eleIsDisplayedAccessibilityID(versionData);
		return this;
	}

	public LoginLogoutPageAndroid verifyErrorMsgLoggedInWithBlankSpacesDisplayed() {
		eleIsDisplayedAccessibilityID(blankSpacesErrorMsgData);
		return this;
	}

	public LoginLogoutPageAndroid verifyUsernameDisplayedInHomescreen(String emailname) {
		eleIsDisplayedAccessibilityID(usernameInHomescreenData);
		return this;
	}

	public LoginLogoutPageAndroid verifySavedUsernameBlankPwdDisplayed(String name) {
		verifyText(name, "xpath", username);
		verifyText("", "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid enterPwdAlone() {
		clear("xpath", username);
		enterValue(passwordData, "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid enterPwdForFilledInUsername() {
		enterValue(passwordData, "xpath", password);
		return this;
	}

	public LoginLogoutPageAndroid swipeToElement(String element) {
		if (element.equalsIgnoreCase("login")) {
			swipeUp(2, login);
		}
		return this;
	}

	public LoginLogoutPageAndroid clickLogOutAlone() {
		click("xpath", logout);
		return this;
	}

	public LoginLogoutPageAndroid validateLogoutYesNo() {
		eleIsDisplayed("xpath", yes);
		eleIsDisplayed("xpath", logoutNo);
		return this;
	}

	public LoginLogoutPageAndroid clickUsername() {
		click("xpath", username);
		return this;
	}

	public LoginLogoutPageAndroid validateLogoutPopupYes() {
		click("xpath", yes);
		eleIsDisplayed("xpath", login);
		return this;
	}

	public LoginLogoutPageAndroid validateLogoutPopupNo() {
		click("xpath", logoutNo);
		eleIsDisplayedAccessibilityID(usernameInHomescreenData);
		return this;
	}

	public LoginLogoutPageAndroid clickOnOperator(String operator) {
		click("xpath", logout);
		return this;
	}
	
	public LoginLogoutPageAndroid disableWifiData() {
		click("xpath", logout);
		return this;
	}

}
