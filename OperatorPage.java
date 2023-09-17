package androidPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class OperatorPage extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Operator";
	Assertion assertion = new Assertion();
	public static String platform;
	static List<String> opr=new ArrayList<String>();
	static List<String> lisTofOP=new ArrayList<String>();

	String operatorText = "//android.widget.TextView[@content-desc=\"Operator\"]";
	String logout = "//android.widget.TextView[@content-desc=\"Logout\"]";
	String usernameText = "//android.view.ViewGroup[@content-desc=\"Username, skaushik, Username\"]/android.widget.TextView";
	String operatorField = "//android.widget.EditText[@content-desc=\"Item Search\"]";
	String operatorFieldClearBtn = "//android.widget.TextView[@content-desc=\"clear\"]";
	String invalidOperatorErrorMsg = "//android.view.ViewGroup[@content-desc=\"Search not found, Search not found, Search not found\"]/android.widget.TextView";
	String accentMainOpXpath="//android.widget.TextView[@content-desc=\"Accent - Main HQ (TX)\"]";
	String allStarServicesOpXpath="//android.widget.TextView[@content-desc=\"All Star Services\"]";
	String continentalCafeOpXpath="//android.widget.TextView[@content-desc=\"CONTINENTAL CAFE, INC. dba Continental Service\"]";
	String gvcsOpXpath="//android.widget.TextView[@content-desc=\"Gulftex Vending and Coffee Service, Inc\"]";
	String tomdraOpXpath="//android.widget.TextView[@content-desc=\"Tomdra Inc.\"]";
	String inValidOp="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView";
	
	String opXPath="(//android.widget.TextView[@content-desc=\"List\"])";
	String op1XPath="(//android.widget.TextView[@content-desc=\"List\"])[1]";
	String op2XPath="(//android.widget.TextView[@content-desc=\"List\"])[2]";
	String op3XPath="(//android.widget.TextView[@content-desc=\"List\"])[3]";
	String op4XPath="(//android.widget.TextView[@content-desc=\"List\"])[4]";
	
	
	
	
	/******* Test Data from Excel sheet ********/
	String usernameOperatorPageData = data.getCellData(sheetName, "UsernameOperatorPage", 2);
	String invalidOperatorData = data.getCellData(sheetName, "InvalidOperator", 2);
	String invalidOpErrorMsgData = data.getCellData(sheetName, "InvalidOpErrorMsgData", 2);

	private static Logger LOGGER;

	public OperatorPage verifyOperatorText() {
		eleIsDisplayed("xpath", operatorText);
		return this;
	}
	
	public OperatorPage extractOperartors() {
		lisTofOP=getTexts("xpath", opXPath);
		return this;
	}

	public OperatorPage clickOnOperator() {
		click("xpath", op1XPath);
		return this;
	}

	public OperatorPage validatelogoutWithUsernameDisplayed() {
		eleIsDisplayedAccessibilityID(usernameOperatorPageData);
		return this;
	}

	public OperatorPage enterInvalidOperator() {
		enterValue(invalidOperatorData, "xpath", operatorField);
		return this;
	}

	public OperatorPage validateInvalidateOperatorErrorMsg() {
		if(getText("xpath", inValidOp).equalsIgnoreCase(invalidOpErrorMsgData)) {
			System.out.println("Invalid operator error message is displayed as expected");
		}
		else {
			throw new RuntimeException("Invalid operator error message is not displayed");
		}
		return this;
	}

	public OperatorPage clearOperatorField() {
		click("xpath", operatorFieldClearBtn);
		return this;
	}

	public OperatorPage validateIfOperatorsAreDisplayedAlphabeticalOrder() {
//		ArrayList<String> beforeSortOpAppList = new ArrayList<String>();
//		for (String op : operatorsListData.split(";")) {
//			String s=getTextAccessibilityID(op);
//			beforeSortOpAppList.add(s);
//		}
//		List<String> newOp = new ArrayList<String>(beforeSortOpAppList);
//		Collections.sort(newOp);
////		beforeSortOpAppList.add(getText("xpath",accentMainOpXpath));
////		beforeSortOpAppList.add(getText("xpath",allStarServicesOpXpath));
////		beforeSortOpAppList.add(getText("xpath",continentalCafeOpXpath));
////		beforeSortOpAppList.add(getText("xpath",gvcsOpXpath));
////		beforeSortOpAppList.add(getText("xpath",tomdraOpXpath));
////		ArrayList<String> afterSortOpAppList = new ArrayList<String>();
////		afterSortOpAppList.add(getText("xpath",accentMainOpXpath));
////		afterSortOpAppList.add(getText("xpath",allStarServicesOpXpath));
////		afterSortOpAppList.add(getText("xpath",continentalCafeOpXpath));
////		afterSortOpAppList.add(getText("xpath",gvcsOpXpath));
////		afterSortOpAppList.add(getText("xpath",tomdraOpXpath));
//////		List<String> beforeSortOpList = Arrays.asList(operatorsListData.split(","));
//////		List<String> opList = Arrays.asList(operatorsListData.split(","));
////		Collections.sort(afterSortOpAppList);
//		if (beforeSortOpAppList.equals(newOp)) {
//			System.out.println("Operators list is displayed in alphabetical order");
//		} else {
//			throw new RuntimeException("Operators list is not displayed in alphabetical order");
//		}
		List<String> beforeSortOpList = Arrays.asList(getText("xpath",op1XPath),getText("xpath",op2XPath),getText("xpath",op3XPath));
		List<String> opList = Arrays.asList(getText("xpath",op1XPath),getText("xpath",op2XPath),getText("xpath",op3XPath));
		Collections.sort(opList);
		if (opList.equals(beforeSortOpList)) {
			System.out.println("Operator list is displayed in alphabetical order");
		} else {
			throw new RuntimeException("Operator list is not displayed in alphabetical order");
		}
		return this;
	}
	
	public OperatorPage enterValidOperator() {
		enterValue(getText("xpath", op1XPath), "xpath", operatorField);
		return this;
	}
	
	public OperatorPage validateIfListNarrowedDownOnOpEntry() {
		eleIsDisplayed("xpath",op1XPath);
			
			eleIsNotDisplayed(op2XPath);
			eleIsNotDisplayed(op3XPath);
			eleIsNotDisplayed(op4XPath);
		return this;
	}
	
	
	
	public OperatorPage validateIfOpValueIsCleared() {
		click("xpath", operatorFieldClearBtn);
		verifyText("", "xpath", operatorField);
		return this;
	}
	
//	public OperatorPage extractListOfOperators() {
//		opr.add(getText("xpath", op1XPath));
//		opr.add(getText("xpath", op2XPath));
//		opr.add(getText("xpath", op3XPath));
//		opr.add(getText("xpath", op4XPath));
//		
////		int opSize=sizeOfList("Operator");
////		for (int i=1;i<opSize;i++) {
////			opr.add(getTextAccessibilityID("Operator"));
////			
////		}
//		return this;
//	}
	
	
}
