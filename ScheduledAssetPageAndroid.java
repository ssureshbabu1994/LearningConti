package androidPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class ScheduledAssetPageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "Picklist";
	Assertion assertion = new Assertion();
	public static String platform;
	public static String assetID;
	public static String type;
	public static String place;
	static List<String> coilList=new ArrayList<>();
	static List<String> coils=new ArrayList<>();
	static String rt="";
	static String finalMachine="";
	static List<WebElement> finalMachineWebEle=new ArrayList<>();
	static WebElement machServiceInvMandatory;
	static WebElement machServiceInvMandatoryIcon;

	String mandatoryInvIconXpath = "(//android.view.ViewGroup[@content-desc=\"Inventory Icon\"])";
	String backPicklistXpath = "//android.widget.TextView[@content-desc=\"Back\"]";
	String discardChangesXpath = "//android.widget.TextView[@content-desc=\"Discard Changes\"]";
	String picklistXpath = "//android.widget.TextView[@content-desc=\"Pick List\"]";
	String serviceInvXpath = "//android.widget.TextView[@content-desc=\"Service/Inventory\"]";
	String cashXpath = "//android.widget.TextView[@content-desc=\"Cash\"]";
	
	String machinesXPath="(//android.widget.TextView[@content-desc=\"Machine\"])";
	
	String machine3XPath="(//android.widget.TextView[@content-desc=\"Machine\"])[3]";
	String machine2XPath="(//android.widget.TextView[@content-desc=\"Machine\"])[2]";
	
	String machine1XPath="(//android.widget.TextView[@content-desc=\"Machine\"])[1]";
	String machine4XPath="(//android.widget.TextView[@content-desc=\"Machine\"])[4]";
	
	String coil1XPath="(//android.widget.TextView[@content-desc=\"Coils\"])[1]";
	String coil2XPath="(//android.widget.TextView[@content-desc=\"Coils\"])[2]";
	String coil3XPath="(//android.widget.TextView[@content-desc=\"Coils\"])[3]";
	String coil4XPath="(//android.widget.TextView[@content-desc=\"Coils\"])[4]";
	
	String locationsXPath="(//android.widget.TextView[@content-desc=\"Location\"])";
	
	String listOfMachinesXPath="(//android.widget.TextView[@content-desc=\"Machine\"])";
	
	String location1XPath="(//android.widget.TextView[@content-desc=\"Location\"])[1]";
	String location2XPath="(//android.widget.TextView[@content-desc=\"Location\"])[2]";
	String location3XPath="(//android.widget.TextView[@content-desc=\"Location\"])[3]";
	String location4XPath="(//android.widget.TextView[@content-desc=\"Location\"])[4]";
	
	String scheduledAssetPaneXPath="//android.view.ViewGroup[@content-desc=\"schedule_button\"]/android.view.ViewGroup/android.widget.TextView[1]";
	String servicedAssetPaneXPath="//android.view.ViewGroup[@content-desc=\"serviced_button\"]/android.view.ViewGroup/android.widget.TextView[1]";


	private static Logger LOGGER;

	public ScheduledAssetPageAndroid clickOnAsset() {
		finalMachineWebEle.get(0).click();
//		click("xpath", machine3XPath);
		return this;
	}
	
	public WebElement getFirstInventoryIcon() {
		for (WebElement inventoryIcon : getElements("xpath", mandatoryInvIconXpath)) {
			machServiceInvMandatoryIcon=inventoryIcon;
			break;
		}
//		click("xpath", machine3XPath);
		return machServiceInvMandatoryIcon;
	}
	
	public ScheduledAssetPageAndroid clickOnAssetServiceInvMandatory() {
		machServiceInvMandatory.click();
//		click("xpath", machine3XPath);
		return this;
	}
	
	public ScheduledAssetPageAndroid clickOnAnotherAsset() {
		finalMachineWebEle.get(1).click();
//		click("xpath", machine2XPath);
		return this;
	}
	
	public ScheduledAssetPageAndroid clickOnBack() {
		click("xpath", backPicklistXpath);
		return this;
	}
	
//	public ScheduledAssetPageAndroid extractAssetIDTypePlace() {
//		String machine="";
//		rt=new RoutePageAndroid().getRouteSelected();
//		for (int i=0; i<4;i++) {
//			switch (i) {
//			case 0:
//				click("xpath", machine1XPath);
//				if(eleIsDisplayedReturnBoolean("xpath", picklistXpath)) {
//					
//				}
//				machine=getResourceID("xpath",machine1XPath);
//				
//				break;
//			case 1:
//				 machine=getResourceID("xpath",machine2XPath);
//				break;
//			case 2:
//				 machine=getResourceID("xpath",machine3XPath);
//				break;
//			case 3:
//				 machine=getResourceID("xpath",machine4XPath);
//				break;
//
//			default:
//				break;
//			}
//			assetID=machine.split(";")[0].split(":")[1].trim();
//			type=machine.split(";")[1].split(":")[1].trim();
//			place=machine.split(";")[2].split(":")[1].trim();
//			
//		}
//		
//		
//		return this;
//	}
	
	public ScheduledAssetPageAndroid extractAssetIDTypePlaceOfMachines() {
		rt=new RoutePageAndroid().getRouteSelected();
		for (WebElement mach : getElements("xpath",machinesXPath)) {
			mach.click();
			if(getElements("xpath", picklistXpath).size()!=0) {
			if(getResourceID("xpath", picklistXpath).contains("menu: true")&&getResourceID("xpath", serviceInvXpath).contains("menu: true")&&getResourceID("xpath", cashXpath).contains("menu: true")) {
				System.out.println("Pick List, Service/Inventory and Cash screens are enabled and we can proceed with this asset for validation");
				click("xpath", backPicklistXpath);
				if(getElements("xpath",discardChangesXpath).size()!=0) {
				click("xpath", discardChangesXpath);
				}
				finalMachineWebEle.add(mach);
				if(finalMachineWebEle.size()==2) {
				break;
				}
			}
			else {
				click("xpath", backPicklistXpath);
				if(getElements("xpath",discardChangesXpath).size()!=0) {
					click("xpath", discardChangesXpath);
					}
			}
				
			}
			
			else {
				click("xpath", backPicklistXpath);
				if(getElements("xpath",discardChangesXpath).size()!=0) {
					click("xpath", discardChangesXpath);
					}
				continue;
			}
			
		}
		
		assetID=finalMachineWebEle.get(0).getAttribute("resource-id").split(";")[0].split(":")[1].trim();
		type=finalMachineWebEle.get(0).getAttribute("resource-id").split(";")[1].split(":")[1].trim();
		place=finalMachineWebEle.get(0).getAttribute("resource-id").split(";")[2].split(":")[1].trim();
			
			
		
		
		return this;
	}
	
	public ScheduledAssetPageAndroid extractAssetIDTypePlaceOfMachinesServiceInvMandatory() {
		String assetWithInvIcon="";
		rt=new RoutePageAndroid().getRouteSelected();
		WebElement invIcon=getFirstInventoryIcon();
		assetWithInvIcon=invIcon.getAttribute("resource-id");
		for (WebElement mach : getElements("xpath",machinesXPath)) {
			machServiceInvMandatory=mach;
			assetID=mach.getAttribute("resource-id").split(";")[0].split(":")[1].trim();
			type=mach.getAttribute("resource-id").split(";")[1].split(":")[1].trim();
			place=mach.getAttribute("resource-id").split(";")[2].split(":")[1].trim();
			if(assetID.equalsIgnoreCase(assetWithInvIcon)) {
				break;
			}
			else {
				continue;
			}
		}
		
		return this;
	}

	public ScheduledAssetPageAndroid clickOnElement(String icon) {
		clickAccessibilityID(icon);
		return this;
	}
	
	public List<String> getAssetIDRouteTypePlaceDate(){
		List<String> assetIDRouteTypePlaceDate=new ArrayList<>();
		assetIDRouteTypePlaceDate.add(assetID);
		assetIDRouteTypePlaceDate.add(rt);
		if(!type.equalsIgnoreCase("null")&&!type.equalsIgnoreCase("undefined")) {
		assetIDRouteTypePlaceDate.add("Type: "+type);
		}
		if(!place.equalsIgnoreCase("null")&&!place.equalsIgnoreCase("undefined")) {
		assetIDRouteTypePlaceDate.add(place);
		}
//		assetIDRouteTypePlaceDate.add(new DatePageAndroid().getDate());
		return assetIDRouteTypePlaceDate;
		
	}
	
	public ScheduledAssetPageAndroid validateRouteDisplayed() {
		eleIsDisplayedAccessibilityID(rt);
		return this;
	}
	
	public ScheduledAssetPageAndroid validateLocationsDisplayed() {
		List<WebElement> loc=getElements("xpath", locationsXPath);
		for (WebElement lc : loc) {
			if(lc.isDisplayed()) {
				System.out.println("Location "+lc.getText()+" is displayed as expected");
			}
			else {
				throw new RuntimeException("Location "+lc.getText()+" is not displayed");
			}
		}
		
		return this;
	}
	
	public ScheduledAssetPageAndroid validateAssetsDisplayed() {
		List<WebElement> machines=getElements("xpath", listOfMachinesXPath);
		for (WebElement mac : machines) {
			if(mac.isDisplayed()) {
				System.out.println("Asset "+mac.getText()+" is displayed as expected");
			}
			else {
				throw new RuntimeException("Asset "+mac.getText()+" is not displayed");
			}
		}
		
		return this;
	}
	
	public ScheduledAssetPageAndroid validateAssetsPaneAreDisplayed(String scheduled,String serviced) {
		if(getText("xpath", scheduledAssetPaneXPath).equalsIgnoreCase(scheduled)) {
			System.out.println(scheduled+" Asset pane is displayed as expected");
		}
		else{
			throw new RuntimeException(scheduled+" Asset pane is not displayed");
		}
		if(getText("xpath", servicedAssetPaneXPath).equalsIgnoreCase(serviced)) {
			System.out.println(serviced+" Asset pane is displayed as expected");
		}
		else{
			throw new RuntimeException(serviced+" Asset pane is not displayed");
		}
		
		return this;
	}
	
	public ScheduledAssetPageAndroid validateActionPerformed(String anyOrNo) {
		if(anyOrNo.equalsIgnoreCase("any")) {
			eleIsDisplayed("xpath", scheduledAssetPaneXPath);
			eleIsDisplayed("xpath", servicedAssetPaneXPath);
		}
		
		else{ if(anyOrNo.equalsIgnoreCase("no")) {
			eleIsDisplayed("xpath", scheduledAssetPaneXPath);
			eleIsNotDisplayed(servicedAssetPaneXPath);
		}
		
		
	}
		return this;
	}


	

}
