package com.lgc.testFramework.testDSG;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumOptions;
import org.sikuli.script.FindFailed;


public class StartSession {



	// @Test
	public void startNewSession(WebDriver driver) throws MalformedURLException {

		// The button doesn't have a name, so click 100 pixels below the label
		Actions builder = new Actions(driver);

		String xpath = "*[starts-with(@Name, '%trimmedwindow.label.eclipseSDK')]//*[contains(@Name,'Welcome')]";

		WebElement startNow = (new WebDriverWait(driver, 300))
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath(xpath)));

		// Detect and close abnormal shutdown window if occurs
		try {

			builder.moveToElement(startNow, 50, 100).click().build().perform();

			WebElement newSessionWindow = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("New Session")));
		} catch (TimeoutException e) {
			try {
				WebElement abnormal = (new WebDriverWait(driver, 10))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.name("Detected abrupt shutdown")));
				abnormal.findElement(By.name("OK")).click();
				builder.moveToElement(startNow, 50, 100).click().build()
						.perform();
			} catch (Exception e1) {
				builder.moveToElement(startNow, 50, 100).click().build()
						.perform();
			}
		}
	}

	// @Test
	public void configureSession(WebDriver driver, String district,
			String project, String interpreter, String system, String domain,
			String perspective) throws MalformedURLException,
			InterruptedException {

		WebElement newSessionWindow = (new WebDriverWait(driver, 300))
				.until(ExpectedConditions.presenceOfElementLocated(By
						.name("New Session")));

		// Find all comboboxes
		List<WebElement> dropdowns = driver.findElements(By
				.className("ComboBox"));
		for (int i = 0; i < 10; i++) {
			// District
			try {
				WebElement districtItem = dropdowns.get(0);

				districtItem.findElement(By.name("*District:")).click();
				districtItem.findElement(By.name(district)).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(2000);
				System.out.println("District Not initialized yet");
			}
		}
		// Project
		for (int i = 0; i < 10; i++) {

			try {
				WebElement projectItem = dropdowns.get(1);
				projectItem.findElement(By.name("*Project:")).click();
				projectItem.findElement(By.name(project)).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(1000);
				System.out.println("Project Not initialized yet");
			}
		}
		// Interpretation project
		for (int i = 0; i < 10; i++) {

			try {
				WebElement intProjectItem = dropdowns.get(2);
				intProjectItem.findElement(By.name("*Interpretation project:"))
						.click();
				intProjectItem.findElement(By.name("ALL_DATA")).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(1000);
				System.out
						.println("Interpretation project Not initialized yet");
			}
		}
		// Interpreter
		for (int i = 0; i < 10; i++) {

			try {
				WebElement interpreterItem = dropdowns.get(3);
				interpreterItem.findElement(By.name("*Interpreter:")).click();
				interpreterItem.findElement(By.name(interpreter)).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(1000);
				System.out.println("Interpreter Not initialized yet");
			}
		}
		// Measurement system
		for (int i = 0; i < 10; i++) {

			try {
				WebElement systemItem = dropdowns.get(4);
				systemItem.findElement(By.name("*Measurement System:")).click();
				systemItem.findElement(By.name(system)).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(1000);
				System.out.println("Measurement system Not initialized yet");
			}
		}
		// Domain
		for (int i = 0; i < 10; i++) {

			try {
				WebElement domainItem = dropdowns.get(5);
				domainItem.findElement(By.name("*Domain:")).click();
				domainItem.findElement(By.name(domain)).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(1000);
				System.out.println("Domain Not initialized yet");
			}
		}

		// Perspective
		for (int i = 0; i < 10; i++) {

			try {
				WebElement perspectiveItem = dropdowns.get(6);
				perspectiveItem.findElement(
						By.name("*Select Your Perspective:")).click();
				perspectiveItem.findElement(By.name(perspective)).click();
				break;
			} catch (WebDriverException e) {
				Thread.sleep(1000);
				System.out.println("Perspective Not initialized yet");
			}
		}
	}

//	 @Test
	public void selectTemplate(WebDriver driver, String view1, String view2,
			String view3, String view4) throws MalformedURLException {

		WebElement cube = driver.findElement(By.name("Cube"));
		WebElement map = driver.findElement(By.name("Map"));
		WebElement section = driver.findElement(By.name("Section"));
		WebElement correlation = driver.findElement(By.name("Correlation"));
		if (view1 == "Cube" || view2 == "Cube" || view3 == "Cube"
				|| view4 == "Cube") {
			cube.sendKeys("+");
		} else {
			cube.sendKeys("-");
		}

		if (view1 == "Map" || view2 == "Map" || view3 == "Map"
				|| view4 == "Map") {
			map.sendKeys("+");
		} else {
			map.sendKeys("-");
		}

		if (view1 == "Section" || view2 == "Section" || view3 == "Section"
				|| view4 == "Section") {
			section.sendKeys("+");
		} else {
			section.sendKeys("-");
		}

		if (view1 == "Correlation" || view2 == "Correlation"
				|| view3 == "Correlation" || view4 == "Correlation") {
			correlation.sendKeys("+");
		} else {
			correlation.sendKeys("-");
		}

	}

}