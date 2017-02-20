package com.lgc.testFramework.testDSG;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;


public class ViewsActions extends CreateMapOfPatterns {
	static DesktopOptions option = new DesktopOptions();
	static WebDriver driver = null;

//	@BeforeClass
	public static void initializeWiniumDriver() {
		{
			option.setApplicationPath("");
			option.setDebugConnectToRunningApp(true);

			try {
				driver = new WiniumDriver(new URL("http://localhost:9999"),
						option);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public static final String PATH = "C:\\Testing\\Cube";

	private Map<String, Pattern> map;

	public ViewsActions() {
		map = getPatterns(PATH);
	}

//	@Test
	public void selectView(WebDriver driver, String viewname) {

		// Just to navigate to right element
		String xpath = "*[contains(@Name, '%trimmedwindow.label.eclipseSDK')]//*[starts-with(@Name, 'Inventory')]/..";
		List<WebElement> find = driver.findElement(By.xpath(xpath))
				.findElements(By.xpath("*[contains(@Name, '')]"));


		// Current view
		WebElement currentView = find.get(1)
				.findElement(By.xpath("*[contains(@Name, '')]"))
				.findElement(By.xpath("*[contains(@Name, '')]"));

		// Target view
		String xpathView = "*[starts-with(@Name, '" + viewname + "')]";
		try{
		currentView.findElement(By.xpath(xpathView)).click();
		} catch(Exception e){
			System.out.println(viewname +" was not found");
			System.out.println(e);
		}
	}

	public void contextMenu(WebDriver driver, String action,
			String actionchild) throws InterruptedException {
		WebDriverWait waitShort = new WebDriverWait(driver, 20);
		WebDriverWait waitLong = new WebDriverWait(driver, 120);
		Actions builder = new Actions(driver);

		// Just to navigate to right element
		String xpath = "*[contains(@Name, '%trimmedwindow.label.eclipseSDK')]//*[starts-with(@Name, 'Inventory')]/..";
		List<WebElement> find = driver.findElement(By.xpath(xpath))
				.findElements(By.xpath("*[starts-with(@Name,'')]"));

		for (int i = 0; i < find.size(); i++){

			if (find.get(i).getAttribute("Name").equals("")){
			System.out.println("Found Empty");
			WebElement activeView = find.get(i);
			builder.contextClick(activeView).build().perform();
			}
			else{
				System.out.println("No active view were found " +i+"element in the list");
			}
		}

//		// Current view context click
//		WebElement activeView = find.get(1)
//				.findElement(By.xpath("*[contains(@Name, '')]"))
//				.findElement(By.xpath("*[contains(@Name, '')]"));


		String actionXPATH = "*[contains(@Name, 'Menu')]//*[starts-with(@Name, '"
				+ action + "')]";
		String actionchildXPATH = "*[contains(@Name, 'Menu')]//*[starts-with(@Name, '"
				+ actionchild + "')]";

		// Find action
		try {
			WebElement menuElement = waitShort.until(ExpectedConditions
					.presenceOfElementLocated(By
							.xpath("*[contains(@Name, 'Menu')]")));
			WebElement actionElement = menuElement.findElement(By.name(action));
			actionElement.click();
		} catch (NoSuchElementException | TimeoutException e) {

			try {
				WebElement parent = driver.findElement(By.xpath(actionXPATH));
				builder.contextClick(parent).build().perform();
			} catch (NoSuchElementException e1) {
				System.out.println("WARNING: Option " + action
						+ " was not found in MB3 Menu");
			}
		}
		// Find action child
		if (actionchild != "No") {
			try {
				WebElement menuElement = waitShort.until(ExpectedConditions
						.presenceOfElementLocated(By
								.xpath("*[contains(@Name, 'Menu')]")));
				WebElement actionChildElement = menuElement.findElement(By
						.name(actionchild));
				actionChildElement.click();
			} catch (NoSuchElementException | TimeoutException e) {

				try {
					WebElement parent = driver.findElement(By
							.xpath(actionchildXPATH));
					builder.contextClick(parent).build().perform();
				} catch (NoSuchElementException e1) {
					System.out.println("WARNING: Option " + actionchild
							+ " was not found in MB3 Menu");
				}
			}
		}
	}

	// @Test
	public void checkScreenShot(String name) throws InterruptedException,
			FindFailed {
		WebElement test = driver.findElement(By.name("Geoscience"));

		Region cube = new Region(Integer.parseInt(test.getAttribute(
				"BoundingRectangle").split(",")[0]), Integer.parseInt(test
				.getAttribute("BoundingRectangle").split(",")[1]),
				Integer.parseInt(test.getAttribute("BoundingRectangle").split(
						",")[2]), Integer.parseInt(test.getAttribute(
						"BoundingRectangle").split(",")[3]));

		if (cube.exists((getPatterns(PATH).get("depthbriInlineNorth"))
				.similar((float) 0.5)) != null) {
			System.out.println("Object is displayed");
		} else {
			System.out.println("Object is NOT displayed");
		}
	}

}
