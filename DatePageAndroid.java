package androidPages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

import mobileWrap.MobileWrapper;

public class DatePageAndroid extends MobileWrapper {

	static String routeSelected = "";
	static String dateToVerify = "";
	String date = "(//android.widget.TextView[@content-desc=\"Text\"])[2]";
	String route = "(//android.widget.TextView[@content-desc=\"Text\"])[6]";
	String dateText = "//android.widget.TextView[@content-desc=\"Date\"]";
	String startBtn = "(//android.view.ViewGroup[@content-desc=\"Start\"])[1]/android.widget.TextView";
	String viewBtn = "//android.view.ViewGroup[@content-desc=\"View\"]/android.widget.TextView";

	String dates = "(//android.widget.TextView[@content-desc=\"Date\"])";
	String startBtns = "(//android.view.ViewGroup[@content-desc=\"Start\"])";
	String viewBtns = "//android.view.ViewGroup[@content-desc=\"View\"]";

	// Verify the Date Text
	public DatePageAndroid VerifyDateText() {
		if (getElements("xpath", dateText).size() != 0) {
			eleIsDisplayed("xpath", dateText);
		}
//		retrieveDate();
		return this;
	}

	public DatePageAndroid validateSelectedRouteDisplayed() {

		routeSelected = new RoutePageAndroid().getRouteSelected();
		eleIsDisplayedAccessibilityID(routeSelected);

		return this;
	}

	public DatePageAndroid validateSelectedRouteWithMultipleSchedulesDisplayed() {

		routeSelected = new RoutePageAndroid().getRouteSelectedWithMultipleSchedules();
		eleIsDisplayedAccessibilityID(routeSelected);

		return this;
	}

	public DatePageAndroid verifyStartViewBtnsDsiplayed() {
		if (getElements("xpath", startBtn).size() != 0) {
			eleIsDisplayed("xpath", startBtn);
		}
		if (getElements("xpath", viewBtn).size() != 0) {
			eleIsDisplayed("xpath", viewBtn);

		}
		return this;
	}

	public DatePageAndroid validateDatesInAscendingOrderDisplayed() {
		List<String> datesList = new ArrayList<>();
		List<String> datesBeforeSort = new ArrayList<>();
		List<String> dateFinalSort = new ArrayList<>();
		List<WebElement> datesInScreen = getElements("xpath", dates);
		for (WebElement date : datesInScreen) {
			datesList.add(date.getText());
		}
		datesList.remove(0);
		for (String dt : datesList) {
			datesBeforeSort.add(dt.split(" ")[1]);
		}
		dateFinalSort.addAll(datesBeforeSort);
		Collections.sort(dateFinalSort);
		if (dateFinalSort.equals(datesBeforeSort)) {
			System.out.println("Dates displayed are in ascending order");
		} else {
			throw new RuntimeException("Dates displayed are in ascending order");
		}
		return this;
	}

	public DatePageAndroid clickOnEnabledstartOrViewBtn(String startOrView) {
		List<WebElement> startButtons = new ArrayList<WebElement>();
		List<WebElement> viewButtons = new ArrayList<WebElement>();
		if (getElements("xpath", dateText).size() != 0) {
			if (getElements("xpath", startBtns).get(0).getAttribute("enabled").equalsIgnoreCase("true")) {
				startButtons.add(getElements("xpath", startBtns).get(0));
			}
				
			if (getElements("xpath", startBtns).get(1).getAttribute("enabled").equalsIgnoreCase("true")) {
				startButtons.add(getElements("xpath", startBtns).get(1));
			}
			if (getElements("xpath", viewBtns).get(0).getAttribute("enabled").equalsIgnoreCase("true")) {
				viewButtons.add(getElements("xpath", viewBtns).get(0));
			}
			
			else {
				System.out.println("View button is not enabled");
			}
			if (startOrView.equalsIgnoreCase("Start")) {
				if(startButtons.size()!=0) {
					for (WebElement startBtn : startButtons) {
						if (startBtn.getAttribute("enabled").equalsIgnoreCase("true")) {
							clickByWebEle(startBtn);
							break;
						}
					}
				}
				else {
					System.out.println("Start button is not enabled and cannot be clicked");
				}
				
			} else if (startOrView.equalsIgnoreCase("View")) {
				if(viewButtons.size()!=0) {
					for (WebElement viewBtn : viewButtons) {
						if (viewBtn.getAttribute("enabled").equalsIgnoreCase("true")) {
							clickByWebEle(viewBtn);
							break;
						}
						else {
							System.out.println("View button is not enabled");
						}
				}
				}
				else {
					System.out.println("View button is not enabled and cannot be clicked");
				}
			}
		}
		return this;
	}

	public String retrieveDate() {
		List<String> datesList = new ArrayList<>();
		String finalDate = "";
		String monthFromDateInApp = "";
		String dateFromDateInApp = "";
		List<WebElement> datesInScreen = getElements("xpath", dates);
		for (WebElement date : datesInScreen) {
			datesList.add(date.getText());
		}
		datesList.remove(0);
		for (String dt : datesList) {
			monthFromDateInApp = dt.split(" ")[0];
			dateFromDateInApp = dt.split(" ")[1];
			break;
		}
		finalDate = dateFromDateInApp + "-" + monthFromDateInApp + "-" + "2022";
//		Date date = new Date();  
//	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
//	    String strDate = formatter.format(date);  

		try {

			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date varDate = (Date) formatter.parse(finalDate);
//			formatter = new SimpleDateFormat("MM/dd/yyyy");
			dateToVerify = formatter.format(varDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateToVerify;
	}

	public String getDate() {
		return dateToVerify;
	}

}
