package com.lgc.testFramework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;



public class AddDataWin extends CreateMapOfPatterns {

	static DesktopOptions option = new DesktopOptions();
	static WebDriver driver = null;
	public static final String PATH = "C:\\Testing\\Inventory";
//	String parent = "Wells";
//	String child = "Well Lists";
//	String secondChild = "No";

	private Map<String, Pattern> map;

	public AddDataWin() {
		map = getPatterns(PATH);
	}

	@BeforeClass
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

//	 @Test
	public void selectDataType(WebDriver driver, String parent, String child, String secondChild) {

		// String parent = "Wells";
		// String child = "Interpretation";
		// String secondChild = "Surface Picks";

		// Open and Maximize Add Data window
		for (int i = 0; i < 5; i++) {
			try {
				WebElement addData = (new WebDriverWait(driver, 20))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.xpath(("*[starts-with(@Name, 'Add Data -')]"))));
				System.out.println("Add Data window is opened");
				try {
					addData.findElement(By.id("Maximize")).click();
					System.out.println("Add Data window is maximized");
				} catch (Exception e) {
					addData.findElement(By.id("Restore"));
					System.out.println("Add Data window was already maximized");
				}
				break;
			} catch (Exception e) {
				driver.findElement(By.name("Add Data")).click();
			}
		}

		WebElement dataPanel = driver.findElement(By.name("Openworks"));
		WebElement projectData = dataPanel.findElement(By.xpath("*"));
		List<WebElement> ssdData = projectData.findElements(By.xpath("*"));

		// Data to be present in AddData Window
		String[] designedData = { "2D Lines", "3D Surveys", "Faults",
				"Horizons", "Velocity Models", "Wells", "Line Of Section",
				"Mapping", "Frameworks", "Inter-well Points",
				"HWC Interpretations", "GeoShapers", "GeoShells", "3D Grids",
				"Gridsets", "Fracture Networks", "Lithotype Proportions",
				"Images", "Notes", "Microseismic", "Project Outlines", "VSP",
				"Well Planning Scenarios", "KM Queries"
 };
		// Check that all data is present
		try {
		for (int i = 0; i < ssdData.size(); i++) {
			if (designedData[i].equals(ssdData.get(i).getAttribute("Name")) == false) {
				System.out
						.println("WARNING: " + designedData[i] + " is missed");
			}
		}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Warning: Add data elements list was changed");
		}



		projectData.findElement(By.name(parent)).click();
		if (child == "No") {
		} else {
			WebElement childData = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.elementToBeClickable(By
							.name((child))));
			childData.click();
			if (secondChild == "No") {
			} else {
				WebElement secondChildData = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.elementToBeClickable(By
								.name((secondChild))));
				secondChildData.click();
			}
		}

	}

	// @Test
	public void selectDataByName(WebDriver driver, String name)
			throws FindFailed, InterruptedException {

		// Find appropriate part of Add Data Window
		List<WebElement> addDataWindow = driver
				.findElement(By.xpath("*[starts-with(@Name, 'Add Data -')]"))
				.findElement(By.xpath("*")).findElement(By.xpath("*"))
				.findElement(By.xpath("*")).findElements(By.xpath("*"));

		// Second element in list is the table with data (3 of them)
		WebElement addDataElement = addDataWindow.get(1);
		addDataElement.click();
		// System.out.print(addDataElement.get(0).getAttribute("AutomationId"));

		Region addDataPart = new Region(Integer.parseInt(addDataElement
				.getAttribute("BoundingRectangle").split(",")[0]),
				Integer.parseInt(addDataElement.getAttribute(
						"BoundingRectangle").split(",")[1]) + 60,
				Integer.parseInt(addDataElement.getAttribute(
						"BoundingRectangle").split(",")[2]),
				Integer.parseInt(addDataElement.getAttribute(
						"BoundingRectangle").split(",")[3]));

		Region nameInFilter = new Region(Integer.parseInt(addDataElement
				.getAttribute("BoundingRectangle").split(",")[0]) + 100,
				Integer.parseInt(addDataElement.getAttribute(
						"BoundingRectangle").split(",")[1]) + 55, (int) 100,
				(int) 50);

		nameInFilter.click();
		nameInFilter.type(name + Key.ENTER);
		nameInFilter.type("a", Key.CTRL);

	}

	// @Test
	public void addSelectedDataToTheSession(WebDriver driver) {
		driver.findElement(By.className("Button"));
		// Find appropriate part of Add Data Window (3 of them)
		List<WebElement> addDataWindow = driver
				.findElement(By.xpath("*[starts-with(@Name, 'Add Data -')]"))
				.findElement(By.xpath("*")).findElement(By.xpath("*"))
				.findElement(By.xpath("*")).findElements(By.xpath("*"));
		try {
			WebElement inventoryPart = addDataWindow.get(2);
			inventoryPart.findElement(By.className("Button")).click();
		} catch (WebDriverException e) {
			System.out.println("WARNING: Add button was not found or is not active");
		}
	}

//	@Test
	public void closeAddDataWindow(WebDriver driver)
			throws InterruptedException {

		WebElement addDataWindow = driver.findElement(By
				.xpath("*[starts-with(@Name, 'Add Data -')]"));

		for (int i = 0; i < 10; i++) {

			WebElement closeButton = (new WebDriverWait(driver, 20))
					.until(ExpectedConditions
							.elementToBeClickable(addDataWindow.findElement(By
									.name("Close"))));
			closeButton.click();
			System.out.println("Close button is clicked");

			// Check that Add data window is closed
			try {
				driver.findElement(By
						.xpath("*[starts-with(@Name, 'Add Data -')]"));
			} catch (NoSuchElementException e) {
				System.out.println("Add Data window is closed");
				break;
			}

		}
	}
}
