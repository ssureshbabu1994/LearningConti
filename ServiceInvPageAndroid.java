package androidPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import Pages.DataRead;
import mobileWrap.MobileWrapper;

public class ServiceInvPageAndroid extends MobileWrapper {

	DataRead data = new DataRead();
	String sheetName = "ServiceInv";
	Assertion assertion = new Assertion();
	public static String platform;
	static List<String> coilNames = new ArrayList<>();
	static List<String> coilNamesFromPicklistScreen = new ArrayList<>();
	static List<String> coilDetails = new ArrayList<>();
	static List<String> fin = new ArrayList<>();
	static List<String> coilDtPicklist = new ArrayList<>();
	static List<String> coilNmPicklist = new ArrayList<>();

	String checkMarkXpath = "";
	String continueServiceInvXpath = "//android.widget.TextView[@content-desc=\"button1\"]";
	String serviceMandatoryMsgLeaveXpath = "//android.widget.TextView[@content-desc=\"button2\"]";

	String backServiceInvXpath = "//android.widget.TextView[@content-desc=\"Back\"]";
	String swipeLeftRightBetweenCoilsInvXpath = "//android.widget.TextView[@content-desc=\"Swipe left/right to move between coils\"]";
	String invMandatoryErrorMsg = "//android.widget.TextView[@content-desc=\"text1\"]";
	String coilsXpath = "(//android.view.ViewGroup[@content-desc=\"Coils\"])";
	String coilNameServiceInv = "//android.widget.TextView[@content-desc=\"Coil\"]";

	/******* Test Data from Excel sheet ********/
	String invMandatoryErrorMsgData = data.getCellData(sheetName, "Inv Mandatory Error Msg", 2);

	private static Logger LOGGER;

	public ServiceInvPageAndroid clickOnElement(String ele) {
		clickAccessibilityID(ele);
		return this;
	}

	public ServiceInvPageAndroid clickOnContinueInErrorMsg() {
		click("xpath", continueServiceInvXpath);
		return this;
	}

	public ServiceInvPageAndroid clickOnLeaveInErrorMsg() {
		click("xpath", continueServiceInvXpath);
		return this;
	}

	public ServiceInvPageAndroid addValueInField(String value, String fieldName) {

		clickAccessibilityID(fieldName);
		clickAccessibilityID("clear");
		clickAccessibilityID(value);
		return this;
	}

	public ServiceInvPageAndroid clickOnBackBtnInServiceInv() {
		click("xpath", backServiceInvXpath);
		return this;
	}

	public ServiceInvPageAndroid clickOnLeaveInServiceInvIfDisplayed() {
		if (getElements("xpath", serviceMandatoryMsgLeaveXpath).size() != 0) {
			click("xpath", serviceMandatoryMsgLeaveXpath);
		}
		return this;
	}

	public ServiceInvPageAndroid fillInInvForAllCoils(String field) {
		String coilDetailsApp = "";
		String coilCap = "";
		int intCoilCapValue;
		int invValueRandom;
		int min = 1;
//		coilNames = new PicklistPageAndroid().getCoilNames();
		String invValue = "";
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			coilDetailsApp = getResourceID("xpath", coilNameServiceInv);
			coilCap = coilDetailsApp.split(";")[3];
			intCoilCapValue = Integer.valueOf(coilCap);
			if (intCoilCapValue >= 19) {
				invValueRandom = 19;
			} else {
				invValueRandom = (int) (Math.random() * (intCoilCapValue - min + 1) + min);
			}

			clickAccessibilityID("Fill");
			clickAccessibilityID("clear");
			clickAccessibilityID(String.valueOf(invValueRandom));

			clickAccessibilityID(field);
			clickAccessibilityID("clear");
			clickAccessibilityID(String.valueOf(invValueRandom));
			System.out.println(
					String.valueOf(invValueRandom) + " is entered in " + field + " successfully for Coil " + cl);
		}

		return this;
	}

	public ServiceInvPageAndroid fillInFieldForAnyCoil(String field1, String field2) {
		String coilDetailsApp = "";
		String coilCap = "";
		int intCoilCapValue;
		int invValueRandom;
		int min = 1;
//		coilNames = new PicklistPageAndroid().getCoilNames();
		String invValue = "";
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			coilDetailsApp = getResourceID("xpath", coilNameServiceInv);
			coilCap = coilDetailsApp.split(";")[3];
			intCoilCapValue = Integer.valueOf(coilCap);
			if (intCoilCapValue >= 19) {
				invValueRandom = 19;
			} else {
				invValueRandom = (int) (Math.random() * (intCoilCapValue - min + 1) + min);
			}

			clickAccessibilityID(field1);
			for (int i = 0; i < 2; i++) {
				clickAccessibilityID("clear");
			}
			clickAccessibilityID(String.valueOf(invValueRandom));
			System.out.println(
					String.valueOf(invValueRandom) + " is entered in " + field1 + " successfully for Coil " + cl);
			clickAccessibilityID(field2);
			clickAccessibilityID("clear");
			clickAccessibilityID(String.valueOf(invValueRandom));
			System.out.println(
					String.valueOf(invValueRandom) + " is entered in " + field2 + " successfully for Coil " + cl);
			break;
		}

		return this;
	}

	public ServiceInvPageAndroid validateIfInvCannotBeGreaterThanCap(String field) {
		String coilDetailsApp = "";
		String coilCap = "";
		int intCoilCapValue;
		int intAddedCoilCapValue;
		String addedCoilCapValue = "";
//		coilNames = new PicklistPageAndroid().getCoilNames();
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			coilDetailsApp = getResourceID("xpath", coilNameServiceInv);
			coilCap = coilDetailsApp.split(";")[3];
			intCoilCapValue = Integer.valueOf(coilCap);
			intAddedCoilCapValue = intCoilCapValue + 1;
			addedCoilCapValue = String.valueOf(intAddedCoilCapValue);
			clickAccessibilityID(field);
			clickAccessibilityID("clear");
			for (int i = 0; i < addedCoilCapValue.length() - 1; i++) {
				clickAccessibilityID(String.valueOf(addedCoilCapValue.charAt(i)));

			}
			String finalText = getTextAccessibilityID("Inv");
			if (!finalText.equalsIgnoreCase(addedCoilCapValue)) {
				System.out.println("User cannot enter " + field
						+ " field value greater than Cap and it is validated successfully");
			} else {
				throw new RuntimeException("User can enter " + field + " field value greater than Cap");
			}

			break;
		}

		return this;
	}

	public ServiceInvPageAndroid validateIfInvCannotBeLesserThanFill(String field) {
		String coilDetailsApp = "";
		String coilCap = "";
		int intCoilCapValue;
		int intSubtractedCoilCapValue;
		String subtractedCoilCapValue = "";
//		coilNames = new PicklistPageAndroid().getCoilNames();
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			coilDetailsApp = getResourceID("xpath", coilNameServiceInv);
			coilCap = coilDetailsApp.split(";")[3];
			intCoilCapValue = Integer.valueOf(coilCap);
			intSubtractedCoilCapValue = intCoilCapValue - 1;
			subtractedCoilCapValue = String.valueOf(intSubtractedCoilCapValue);
			clickAccessibilityID("Fill");
			for (int i = 0; i < 2; i++) {
				clickAccessibilityID("clear");
			}

			for (int i = 0; i < coilCap.length() - 1; i++) {
				clickAccessibilityID(String.valueOf(coilCap.charAt(i)));

			}
			clickAccessibilityID(field);
			clickAccessibilityID("clear");
			for (int i = 0; i < subtractedCoilCapValue.length() - 1; i++) {
				clickAccessibilityID(String.valueOf(subtractedCoilCapValue.charAt(i)));

			}

		}

		click("xpath", backServiceInvXpath);
		if (eleIsDisplayedReturnBoolean("xpath", invMandatoryErrorMsg)) {
			System.out.println("User cannot enter Inv value lesser than Fill and is validated successfully");
		} else {
			throw new RuntimeException("User can enter Inv value lesser than Fill");
		}

		return this;
	}

	public ServiceInvPageAndroid fillInInvForAllCoilsRetrievedFromPickListScreen(String field) {
		coilNamesFromPicklistScreen = new PicklistPageAndroid().getCoilNames();
		for (String cl : coilNamesFromPicklistScreen) {
			if (getAttributeAccessibilityID(cl, "enabled").equalsIgnoreCase("true")) {
				clickAccessibilityID(cl);
				clickAccessibilityID(field);
				clickAccessibilityID("clear");
//				clickAccessibilityID(invData);
//				System.out.println(invData + " is entered in " + field + " successfully for Coil " + cl);
			} else {
				System.out.println("Coil " + cl + " is disabled and cannot be validated");
			}
		}

		return this;
	}

	public ServiceInvPageAndroid validateInvMandatoryErrorMsg() {
		eleIsDisplayed("xpath", invMandatoryErrorMsg);
		return this;
	}

	public ServiceInvPageAndroid validateCoilDetailsForEachCoil() {
//		List<String> ar = new ArrayList<>();
//		int i = 1;
//		String coilDetailsApp = "";
		coilNames = new ServiceInvPageAndroid().extrtactCoilsFromServiceInvScreen();
//		coilNames = new PicklistPageAndroid().getCoilNames();
//		coilDetails = new PicklistPageAndroid().getCoilDetails();
//		removePickDexValuesFromCoilDetails();
//		for (String cl : coilNames) {
//			clickAccessibilityID(cl);
//			coilDetailsApp = getResourceIDUsingAccessibilityID("Coil " + i);
//
//			if (fin.get(i - 1).equalsIgnoreCase(coilDetailsApp)) {
//				System.out.println(
//						"Coil Details from Picklist is matching with coil details in Service/Inventory screen for Coil "
//								+ cl);
//			} else {
//				throw new RuntimeException(
//						"Coil Details from Picklist is not matching with coil details in Service/Inventory screen for Coil "
//								+ cl);
//			}
//
//			i++;
//		}
		return this;
	}

	public List<String> extrtactCoilsFromServiceInvScreen() {
		String clDetails = "";
		String clName = "";
		List<String> coil = new ArrayList<>();
		for (WebElement cl : getElements("xpath", coilsXpath)) {
			if (cl.getAttribute("enabled").equalsIgnoreCase("true")) {
				cl.click();
				clDetails = getResourceID("xpath", coilNameServiceInv);
				clName = clDetails.split(";")[0];
				coil.add(clName);
			} else {
				System.out.println("Coil is disabled because of grouping concept in Pick List");
			}
		}
		return coil;
	}

	public List<String> removePickDexValuesFromCoilDetails() {
		int i = 1;
		for (String cl : coilNmPicklist) {
			List<String> list = new LinkedList<>(Arrays.asList(coilDtPicklist.get(i - 1).split(";")));

			list.remove(2);
			list.remove(4);
			fin.add(list.get(0) + ";" + list.get(1) + ";" + list.get(2) + ";" + list.get(3));
			i++;
		}
		return fin;
	}

	public ServiceInvPageAndroid validateFieldsDisplayedForEachCoil() {
//		coilNames = new PicklistPageAndroid().getCoilNames();
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			eleIsDisplayedAccessibilityID("Fill");
			eleIsDisplayedAccessibilityID("Remove");
			eleIsDisplayedAccessibilityID("Spoil");
			eleIsDisplayedAccessibilityID("Inv");
		}

		return this;
	}

	public ServiceInvPageAndroid validateFieldValueIsNotEmptyByDefault() {
		List<String> fields = Arrays.asList("Fill", "Spoil", "Remove");
//		coilNames = new PicklistPageAndroid().getCoilNames();
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			for (String field : fields) {
				clickAccessibilityID(field);
				switch (field) {
				case "Fill":
					if (Integer.valueOf(getTextAccessibilityID("Spoil")) >= 0) {
						System.out.println(
								"Spoil value is not empty by default and validated successully for Coil " + cl);
					} else {
						throw new RuntimeException("Spoil value is empty by default for Coil " + cl);
					}
					if (Integer.valueOf(getTextAccessibilityID("Remove")) >= 0) {
						System.out.println(
								"Remove value is not empty by default and validated successully for Coil " + cl);
					} else {
						throw new RuntimeException("Remove value is empty by default for Coil " + cl);
					}
					break;
				case "Spoil":
					if (Integer.valueOf(getTextAccessibilityID("Fill")) >= 0) {
						System.out
								.println("Fill value is not empty by default and validated successully for Coil " + cl);
					} else {
						throw new RuntimeException("Fill value is empty by default for Coil " + cl);
					}
					if (Integer.valueOf(getTextAccessibilityID("Remove")) >= 0) {
						System.out.println(
								"Remove value is not empty by default and validated successully for Coil " + cl);
					} else {
						throw new RuntimeException("Remove value is empty by default for Coil " + cl);
					}
					break;
				case "Remove":
					if (Integer.valueOf(getTextAccessibilityID("Fill")) >= 0) {
						System.out
								.println("Fill value is not empty by default and validated successully for Coil " + cl);
					} else {
						throw new RuntimeException("Fill value is empty by default for Coil " + cl);
					}
					if (Integer.valueOf(getTextAccessibilityID("Spoil")) >= 0) {
						System.out.println(
								"Spoil value is not empty by default and validated successully for Coil " + cl);
					} else {
						throw new RuntimeException("Spoil value is empty by default for Coil " + cl);
					}
					break;
				default:
					break;
				}

			}

		}

		return this;
	}

	public ServiceInvPageAndroid validateFieldValueIsEmptyByDefault(String field) {
//		coilNames = new PicklistPageAndroid().getCoilNames();
		for (String cl : coilNames) {
			clickAccessibilityID(cl);
			if (getTextAccessibilityID(field).equalsIgnoreCase("")) {
				System.out.println(field + " value is empty by default and validated successully for Coil " + cl);
			} else {
				throw new RuntimeException(field + " value is not empty by default for Coil " + cl);
			}

		}

		return this;
	}

	public ServiceInvPageAndroid validateFieldValuesOfCoilIsLessOrEqualToItsCap() {
		List<String> fields = Arrays.asList("Fill", "Spoil", "Remove", "Inv");
		String coilDetailsApp = "";
		String coilCap = "";
//		coilNames = new PicklistPageAndroid().getCoilNames();
//		coilDetails = new PicklistPageAndroid().getCoilDetails();
		for (String cl : coilNames) {
			clickAccessibilityID(cl);

//			coilDetailsApp = getResourceIDUsingAccessibilityID("Coil " + i);
			coilDetailsApp = getResourceID("xpath", coilNameServiceInv);
			coilCap = coilDetailsApp.split(";")[3];
			if (!coilCap.equalsIgnoreCase("0")) {
				for (String field : fields) {
					clickAccessibilityID(field);
					switch (field) {
					case "Fill":
						if (Integer.valueOf(getTextAccessibilityID("Spoil")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Spoil value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Spoil value is not lesser than or equal to Cap value for Coil" + cl);
						}
						if (Integer.valueOf(getTextAccessibilityID("Remove")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Remove value is lesser than or equal to Cap value and validated successully for Coil  "
											+ cl);
						} else {
							throw new RuntimeException(
									"Remove value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (!getTextAccessibilityID("Inv").equalsIgnoreCase("")) {
							if (Integer.valueOf(getTextAccessibilityID("Inv")) <= Integer.valueOf(coilCap)) {
								System.out.println(
										"Inv value is lesser than or equal to Cap value and validated successully for Coil "
												+ cl);
							} else {
								throw new RuntimeException(
										"Inv value is not lesser than or equal to Cap value for Coil " + cl);
							}
						} else {
							System.out.println("Inv is empty and cannot be validated against Cap");
						}
						break;
					case "Spoil":
						if (Integer.valueOf(getTextAccessibilityID("Fill")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Fill value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Fill value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (Integer.valueOf(getTextAccessibilityID("Remove")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Remove value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Remove value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (!getTextAccessibilityID("Inv").equalsIgnoreCase("")) {
							if (Integer.valueOf(getTextAccessibilityID("Inv")) <= Integer.valueOf(coilCap)) {
								System.out.println(
										"Inv value is lesser than or equal to Cap value and validated successully for Coil "
												+ cl);
							} else {
								throw new RuntimeException(
										"Inv value is not lesser than or equal to Cap value for Coil " + cl);
							}
						} else {
							System.out.println("Inv is empty and cannot be validated against Cap");
						}
						break;
					case "Remove":
						if (Integer.valueOf(getTextAccessibilityID("Fill")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Fill value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Fill value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (Integer.valueOf(getTextAccessibilityID("Spoil")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Spoil value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Spoil value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (!getTextAccessibilityID("Inv").equalsIgnoreCase("")) {
							if (Integer.valueOf(getTextAccessibilityID("Inv")) <= Integer.valueOf(coilCap)) {
								System.out.println(
										"Inv value is lesser than or equal to Cap value and validated successully for Coil "
												+ cl);
							} else {
								throw new RuntimeException(
										"Inv value is not lesser than or equal to Cap value for Coil " + cl);
							}
						} else {
							System.out.println("Inv is empty and cannot be validated against Cap");
						}
						break;
					case "Inv":
						if (Integer.valueOf(getTextAccessibilityID("Fill")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Fill value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Fill value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (Integer.valueOf(getTextAccessibilityID("Spoil")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Spoil value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Spoil value is not lesser than or equal to Cap value for Coil " + cl);
						}
						if (Integer.valueOf(getTextAccessibilityID("Remove")) <= Integer.valueOf(coilCap)) {
							System.out.println(
									"Remove value is lesser than or equal to Cap value and validated successully for Coil "
											+ cl);
						} else {
							throw new RuntimeException(
									"Remove value is not lesser than or equal to Cap value for Coil " + cl);
						}
						break;
					default:
						break;
					}
				}
			} else {
				System.out.println("Cap is 0 for Coil " + cl + " and cannot be validated");
			}
		}

		return this;
	}

	public ServiceInvPageAndroid validateSwipeLeftRightToMoveBetweenCoilsTextDisplayed() {
		eleIsDisplayed("xpath", swipeLeftRightBetweenCoilsInvXpath);
		System.out.println("Swipe left or right to move between coils is displayed as expected");
		return this;
	}

	public ServiceInvPageAndroid validateCoilDetailsRetrievedFromPickListToDisplayServiceInv() {
		coilDtPicklist = new PicklistPageAndroid().getCoilDetails();
		coilNmPicklist = new PicklistPageAndroid().getCoilNames();
		List<String> coilDtServiceInv = new ArrayList<>();
		for (String cl : coilNmPicklist) {
			if (getAttributeUsingAccessibilityID(cl, "enabled").equalsIgnoreCase("true")) {
				clickAccessibilityID(cl);
				coilDtServiceInv.add(getResourceID("xpath", coilNameServiceInv));

			}
		}
		Set<String> coilDtSet = new LinkedHashSet<>(coilDtServiceInv);
		removePickDexValuesFromCoilDetails();
		if (fin.containsAll(coilDtSet)) {
			System.out.println(
					"Coil details retrieved from Picklist screen are displayed in Service Inv screen and are validated successfully");
		} else {
			throw new RuntimeException(
					"Coil details retrieved from Picklist screen are not displayed in Service Inv screen");
		}
		return this;
	}

}
