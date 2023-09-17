package androidPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class RoutePageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Route";
	Assertion assertion = new Assertion();
	public static String platform;
	static List<String> rout = new ArrayList<String>();
	static List<String> lisTofRt = new ArrayList<String>();
	static String routeSelected = "";
	static String routeWithMultipleSchedulesSelected = "";

	String routeText = "//android.widget.TextView[@content-desc=\"Route\"]";
	String operator = "(//android.widget.TextView[@content-desc=\"Text\"])[1]";
	String routesearch = "//android.widget.EditText[@content-desc=\"Item Search\"]";
	String routefilter = "(//android.widget.TextView[@content-desc=\"Text\"])[5]";
	String routeField = "//android.widget.EditText[@content-desc=\"Item Search\"]";
	String routeFieldClearBtn = "//android.widget.TextView[@content-desc=\"clear\"]";
	String invalidRoute = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView";
	String backXpath = "//android.widget.TextView[@content-desc=\"Back\"]";
	String machineXPath="(//android.widget.TextView[@content-desc=\"Machine\"])[1]";
	String dateText = "//android.widget.TextView[@content-desc=\"Date\"]";
	
	String austinRoute = "//android.widget.TextView[@content-desc=\"10000-Austin Installs\"]";
	String oneZoneoute = "//android.widget.TextView[@content-desc=\"101\"]";
	String oneZtworoute = "//android.widget.TextView[@content-desc=\"102\"]";
	String oneZthreeroute = "//android.widget.TextView[@content-desc=\"103-Test Rt MKTS\"]";
	String oneZfourroute = "//android.widget.TextView[@content-desc=\"104\"]";
	String oneZsixroute = "//android.widget.TextView[@content-desc=\"106\"]";
	String oneZnineroute = "//android.widget.TextView[@content-desc=\"109\"]";
	String oneTwoTworoute = "//android.widget.TextView[@content-desc=\"122\"]";

	String rtXPath = "(//android.widget.TextView[@content-desc=\"List\"])";
	String rt1XPath = "(//android.widget.TextView[@content-desc=\"List\"])[1]";
	String rt2XPath = "(//android.widget.TextView[@content-desc=\"List\"])[2]";
	String rt3XPath = "(//android.widget.TextView[@content-desc=\"List\"])[3]";
	String rt4XPath = "(//android.widget.TextView[@content-desc=\"List\"])[4]";

	String invalidRouteData = data.getCellData(sheetName, "InvalidRoute", 2);
	String invalidRouteErrorMsgData = data.getCellData(sheetName, "InvalidRouteErrorMsgData", 2);
	String routesWithMultipleSchedulesData = data.getCellData(sheetName, "Routes with Multiple Schedules", 2);

	public RoutePageAndroid extractOperartors() {
		lisTofRt = getTexts("xpath", rtXPath);
		return this;
	}

	public RoutePageAndroid clickRoutefilter() {
		click("xpath", routefilter);

		return this;
	}

	public RoutePageAndroid VerifyInactiveRoutefilterDrpDwnPresent() {

		verifyText("JP nagar", "xpath", routefilter);

		return this;
	}

	public RoutePageAndroid VerifyActiveRoutefilterPresent(String route) {

		verifyText(route, "xpath", routefilter);

		return this;
	}

	public RoutePageAndroid enterSearchItem(String data) {
		enterValue(data, "xpath", routesearch);

		return this;
	}

	public RoutePageAndroid verifyRouteSearch() {
		eleIsDisplayed("xpath", routesearch);

		return this;
	}

	public RoutePageAndroid VerifyOperatorBackButton() {

		verifyText("Operator", "xpath", operator);

		return this;
	}

	public RoutePageAndroid VerifyRouteText() {

		eleIsDisplayedAccessibilityID("Route");

		return this;
	}

	public RoutePageAndroid clickOnRoute() {
		getRoute();
		click("xpath", rt3XPath);

		return this;
	}

	public RoutePageAndroid clickOnRouteWithMultipleSchedules() {
		getRouteWithMultipleSchedulesandClickOnIt();
		
		return this;
	}

	public String getRoute() {
		routeSelected = getText("xpath", rt3XPath);
		return routeSelected;
	}

	public String getRouteWithMultipleSchedulesandClickOnIt() {
		for (WebElement rt : getElements("xpath", rtXPath)) {
			String routeName = rt.getText();
			if (routeName.equalsIgnoreCase(routesWithMultipleSchedulesData)) {
				System.out.println(routeName
						+ " is having Multiple schedules and we can proceed with this route for Date screen validation");
				routeWithMultipleSchedulesSelected = routeName;
				rt.click();
				break;
			} else {
				continue;
			}

		}
		
		return routeWithMultipleSchedulesSelected;
	}
	
	public String getRouteWithMultipleSchedulesandClickScheduledAssetBack() {
		for (WebElement rt : getElements("xpath", rtXPath)) {
			String routeName = rt.getText();
			rt.click();
			if (eleIsDisplayedReturnBoolean("xpath", machineXPath)) {
				click("xpath", backXpath);
				
			} else if(eleIsDisplayedReturnBoolean("xpath", dateText)){
				routeWithMultipleSchedulesSelected = routeName;
				break;
			}

		}
		
		return routeWithMultipleSchedulesSelected;
	}

	public String getRouteSelected() {
		return routeSelected;
	}

	public String getRouteSelectedWithMultipleSchedules() {
		return routeWithMultipleSchedulesSelected;
	}

	public RoutePageAndroid enterInvalidRoute() {
		enterValue(invalidRouteData, "xpath", routeField);
		return this;
	}

	public RoutePageAndroid validateInvalidateRouteErrorMsg() {
		if (getText("xpath", invalidRoute).equalsIgnoreCase(invalidRouteErrorMsgData)) {
			System.out.println("Invalid route error message is displayed as expected");
		} else {
			throw new RuntimeException("Invalid route error message is not displayed");
		}
		return this;
	}

	public RoutePageAndroid clearRouteField() {
		click("xpath", routeFieldClearBtn);
		return this;
	}

	public RoutePageAndroid validateIfRoutesAreDisplayedAlphabeticalOrder() {
//		ArrayList<String> beforeSortRouteAppList = new ArrayList<String>();
//		for (String rt : routesListData.split(";")) {
//			String s=getTextAccessibilityID(rt);
//			beforeSortRouteAppList.add(s);
//		}
//		List<String> newRoute = new ArrayList<String>(beforeSortRouteAppList);
//		Collections.sort(newRoute);
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
//		if (beforeSortRouteAppList.equals(newRoute)) {
//			System.out.println("Routes list is displayed in alphabetical order");
//		} else {
//			throw new RuntimeException("Routes list is not displayed in alphabetical order");
//		}
		List<String> beforeSortOpList = Arrays.asList(getText("xpath", rt1XPath), getText("xpath", rt2XPath),
				getText("xpath", rt3XPath));
		List<String> opList = Arrays.asList(getText("xpath", rt1XPath), getText("xpath", rt2XPath),
				getText("xpath", rt3XPath));
		Collections.sort(opList);
		if (opList.equals(beforeSortOpList)) {
			System.out.println("Routes list is displayed in alphabetical order");
		} else {
			throw new RuntimeException("Routes list is not displayed in alphabetical order");
		}

		return this;
	}

	public RoutePageAndroid enterValidRoute() {
		enterValue(getText("xpath", rt1XPath), "xpath", routeField);
		return this;
	}

	public RoutePageAndroid validateIfListNarrowedDownOnRouteEntry() {
		String routeS=getText("xpath", rt1XPath);
		eleIsDisplayed("xpath", rt1XPath);
		if(!getText("xpath", rt2XPath).equalsIgnoreCase(routeS)) {
			System.out.println("List is narrowed down on Route entry as expected");
		}
		else {
			throw new RuntimeException("List is not narrowed down on Route entry");
		}
		if(!getText("xpath", rt3XPath).equalsIgnoreCase(routeS)) {
			System.out.println("List is narrowed down on Route entry as expected");
		}
		else {
			throw new RuntimeException("List is not narrowed down on Route entry");
		}
//		eleIsNotDisplayed(rt2XPath);
//		eleIsNotDisplayed(rt3XPath);
		return this;
	}

	public RoutePageAndroid validateIfRouteValueIsCleared() {
		click("xpath", routeFieldClearBtn);
		verifyText("", "xpath", routeField);
		return this;
	}

	public RoutePageAndroid validateScheduledAssetScreenDisplayed(String screenName) {
		String[] screen = screenName.split(" ");
		for (String sc : screen) {
			eleIsDisplayedAccessibilityID(sc);
		}

		return this;
	}

//	public RoutePageAndroid extractListOfRoutes() {
//		rout.add(getText("xpath", rt1XPath));
//		rout.add(getText("xpath", rt2XPath));
//		rout.add(getText("xpath", rt3XPath));
////		int rtSize=sizeOfList("Route");
////		for (int i=1;i<rtSize;i++) {
////			rout.add(getTextAccessibilityID("Route"));
////			
////		}
//		return this;
//	}

}
