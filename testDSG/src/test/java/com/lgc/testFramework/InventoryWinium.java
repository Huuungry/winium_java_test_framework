package com.lgc.testFramework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumOptions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class InventoryWinium implements RegionFromWinElementInterface, MapPatterns {

	public static final String PATH = "C:\\Testing\\Inventory";
	private Map<String, Pattern> map;

	public InventoryWinium() {
		map = getPatterns(PATH);
	}

//	static DesktopOptions option = new DesktopOptions();
//	static WebDriver driver = null;
//
//	@Before
//	public void initializeWiniumDriver() {
//		{
//			option.setApplicationPath("");
//			option.setDebugConnectToRunningApp(true);
//
//			try {
//				driver = new WiniumDriver(new URL("http://localhost:9999"),
//						option);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	// @Test
	public void contextMenu(WebDriver driver, String dataName,
			String action, String actionchild) throws InterruptedException {
		WebDriverWait waitShort = new WebDriverWait(driver, 20);
		WebDriverWait waitLong = new WebDriverWait(driver, 120);
		Actions builder = new Actions(driver);
		WebElement inventory = driver.findElement(By.name("Inventory"));
		inventory.click();

		String dataNameXPATH = "*[contains(@Name, '%trimmedwindow.label.eclipseSDK')]//*[starts-with(@Name, '"
				+ dataName + "')]";
		String actionXPATH = "*[contains(@Name, 'Menu')]//*[starts-with(@Name, '"
				+ action + "')]";
		String actionchildXPATH = "*[contains(@Name, 'Menu')]//*[starts-with(@Name, '"
				+ actionchild + "')]";

		// Find by name and if not try by Xpath (too long)
		try {
			WebElement parent = inventory.findElement(By.name(dataName));
			builder.contextClick(parent).build().perform();
		} catch (NoSuchElementException e) {

			try {
				WebElement parent = driver.findElement(By.xpath(dataNameXPATH));
				builder.contextClick(parent).build().perform();
			} catch (NoSuchElementException e1) {
				System.out.println("WARNING: " + dataName
						+ " is not present in inventory");
				inventory.click();
			}
		}

		Thread.sleep(2000);
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
				System.out.println("WARNING: " + "Option " + action
						+ " was not found in MB3 Menu");
				inventory.click();
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
					System.out.println("WARNING: " + "Option " + actionchild
							+ " was not found in MB3 Menu");
					inventory.click();
				}
			}
		}
	}

	@Test
	public void dndToActiveView(WebDriver driver, String item) throws FindFailed {
//		WebDriverWait waitShort = new WebDriverWait(driver, 20);
//		WebDriverWait waitLong = new WebDriverWait(driver, 120);
//		Actions builder = new Actions(driver);
//		WebElement inventory = driver.findElement(By.name("Inventory"));

		// Xpath for the item in inventory
		String xpathItem = "*[starts-with(@Name, '%trimmedwindow.label.eclipseSDK')]//*[contains(@Name,'"
				+ item + "')]";
		try {
			WebElement itemDND = driver.findElement(By.xpath(xpathItem));
			// Just to navigate to right element
			String xpath = "*[contains(@Name, '%trimmedwindow.label.eclipseSDK')]//*[starts-with(@Name, 'Inventory')]/..";
			List<WebElement> find = driver.findElement(By.xpath(xpath))
					.findElements(By.xpath("*[starts-with(@Name,'')]"));

			for (int i = 0; i < find.size(); i++) {
				// The trick is that empty element is active
				if (find.get(i).getAttribute("Name").equals("")) {
					System.out.println("Found Empty");

					Screen scr = new Screen();
					
					Region activeViewRegion = new Region(getRectangle(find
							.get(i)));
					Region itemDnDRegion = new Region(getRectangle(itemDND));
					scr.dragDrop(itemDnDRegion, activeViewRegion);
				} else {
					System.out.println("No active view were found for " + i
							+ " element in the list");
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Couldn't find " + item);
			System.out.println(xpathItem);
//			System.out.println(e);

		}

	}

	// Doesn't work yet
	public void expandInTree(WebDriver driver, String parentItem) {
		Actions builder = new Actions(driver);
		WebElement inventory = driver.findElement(By.name("Inventory"));
		inventory.click();

		try {
			WebElement parent = driver.findElement(By.name(parentItem));
			parent.click();

			inventory.sendKeys(Key.ADD);

		} catch (Exception e) {
			System.out.println(parentItem + " is not present in inventory");
		}
	}

//	@After
//	public void close(){
//		driver.close();
//	}
}
