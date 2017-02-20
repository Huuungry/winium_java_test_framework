package com.lgc.testFramework.testDSG;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.sikuli.script.Key;

public class ManualRuner {

	// DesktopOptions option = new DesktopOptions();
	//
	// WebDriver driver = null;
	//
	// {
	// option.setApplicationPath("");
	// option.setDebugConnectToRunningApp(true);
	//
	// try {
	// driver = new WiniumDriver(new URL("http://localhost:9999"),option);
	// } catch (MalformedURLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// @Test
	public void fillTestCase(WebDriver driver) throws MalformedURLException,
			InterruptedException {

		Actions builder = new Actions(driver);

		for (int i = 1; i < 11; i++) {

			WebElement manualRuner = (new WebDriverWait(driver, 28800000))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("Change Detection Mode:")));

			WebElement manualRunerWindow = driver.findElement(By
					.id("ManualRunnerForm"));
			System.out.println("Manual runner detected " + i);
			// OS specifying
			WebElement os = (new WebDriverWait(driver, 300))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("* Operating System:")));

			builder.moveToElement(os, 200, 10).click().build().perform();
			manualRuner.sendKeys("win7");

			// Cycle specifying
			WebElement cycle = (new WebDriverWait(driver, 300))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("* Test Cycle:")));

			builder.moveToElement(cycle, 200, 10).click().build().perform();
			manualRuner.sendKeys("n/a");

			// Release specifying
			WebElement release = (new WebDriverWait(driver, 300))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.name("* Release:")));

			builder.moveToElement(release, 200, 10).click().build().perform();
			manualRuner.sendKeys("5000.10");

			WebElement titleBar = manualRunerWindow.findElement(By
					.id("TitleBar"));

			builder.moveToElement(titleBar, 20, 40).click().build().perform();
			System.out.println("Clicked Run");
			// manualRuner.sendKeys(Key.CTRL+"r");
			Thread.sleep(10000);

		}
	}
}
