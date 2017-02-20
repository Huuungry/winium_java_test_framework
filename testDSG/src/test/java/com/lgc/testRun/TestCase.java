package com.lgc.testRun;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.sikuli.script.*;
import org.sikuli.script.App;

import com.lgc.testFramework.testDSG.CreateMapOfPatterns;
import com.lgc.testFramework.testDSG.AddDataWin;
import com.lgc.testFramework.testDSG.InventoryWinium;
import com.lgc.testFramework.testDSG.StartSession;
import com.lgc.testFramework.testDSG.ViewsActions;
import com.lgc.testFramework.testDSG.*;

public class TestCase extends CreateMapOfPatterns {

	static DesktopOptions option = new DesktopOptions();
	static WebDriver driver = null;
	String PATH = "";

	StartSession startSession = new StartSession();
	AddDataWin addData = new AddDataWin();
	InventoryWinium inventory = new InventoryWinium();
	ViewsActions viewActions = new ViewsActions();

	@Before
	public void initializeWiniumDriver() throws InterruptedException {
		for (int i = 0; i < 5; i++) {

			option.setApplicationPath("");
			option.setDebugConnectToRunningApp(true);
			try {
				driver = new WiniumDriver(new URL("http://localhost:9999"),
						option);
			} catch (Exception e) {
				e.printStackTrace();
				App.open("C:\\Utilities\\SoftforAutomatization\\Winium\\Winium1.5\\Winium.Desktop.Driver.exe -admin");
				Thread.sleep(5000);
			}
		}
	}

	@Test
	public void testCase1() throws FindFailed, InterruptedException,
			MalformedURLException {

//		App.open("C:\\Landmark\\DSG10ep.3\\LaunchDS.bat");

//		 startSession.startNewSession(driver);
//		 startSession.configureSession(driver, "INTSRV", "SALT3D", "LGC",
//		 "US Oil Field", "Time", "Geoscience");
//		 startSession.selectTemplate(driver, "Cube", "Map", "Section",
//		 "Correlation1");
//
//		 driver.findElement(By.name("Create Session")).click();
//
		 addData.selectDataType(driver, "Wells", "Well Lists", "No");
//		 addData.selectDataByName(driver, "92_poslog");
//		 addData.addSelectedDataToTheSession(driver);
//
//		 addData.selectDataType(driver, "3D Surveys", "salt3d_1",
//		 "Seismic");
//		 addData.selectDataByName(driver, "lcon8Yuriy.bri");
//		 addData.addSelectedDataToTheSession(driver);
//
//		 addData.closeAddDataWindow(driver);
//
//		 inventory.contextMenu(driver, "Wells", "Expand Selected",
//		 "No");
//		 inventory.contextMenu(driver, "Well Lists",
//		 "Expand Selected", "No");
//		 inventory.contextMenu(driver, "Well Lists", "Show All", "No");
//
//		 viewActions.selectView(driver, "Cube");
//		 viewActions.contextMenu(driver, "Viewing Angle", "View From Top");
//		 inventory.contextMenu(driver, "Well Lists", "Show All", "No");
//
//		 inventory.contextMenu(driver, "3D Surveys", "Expand", "No");
//		 inventory.dndToActiveView(driver, "lcon8Yuriy.bri");

		// Run this to crash DS :)
		// List<WebElement> monkey = driver
		// .findElement(
		// By.xpath("*[starts-with(@Name,'%trimmedwindow.label.eclipseSDK')]"))
		// .findElements(By.name(""));
		//
		// for (int i = 0; i < monkey.size(); i++) {
		// try {
		// System.out.println(monkey.get(i).getAttribute("Name"));
		// monkey.get(i).click();
		// monkey.get(i).sendKeys(Key.ENTER);
		// } catch (Exception e) {
		//
		// }
		// }

	}

	 @Test
	public void testCase2() throws InterruptedException {
		inventory.contextMenu(driver, "92_POSLOG", "Well Details...", "No");
	}

	@After
	public void closeWinium() {
		driver.close();
		System.out.println("Driver is closed");
//		App.close("C:\\Utilities\\SoftforAutomatization\\Winium\\Winium1.5\\Winium.Desktop.Driver.exe");
	}

}
