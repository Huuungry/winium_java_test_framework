package com.lgc.testFramework;

import java.awt.Rectangle;

import org.openqa.selenium.WebElement;

public interface RegionFromWinElementInterface {
	
	public default  Rectangle getRectangle(WebElement element) {
		int x = Integer.parseInt(element.getAttribute("BoundingRectangle")
				.split(",")[0]);
		int y = Integer.parseInt(element.getAttribute("BoundingRectangle")
				.split(",")[1]);
		int width = Integer.parseInt(element.getAttribute("BoundingRectangle")
				.split(",")[2]);
		int height = Integer.parseInt(element.getAttribute("BoundingRectangle")
				.split(",")[3]);

		Rectangle newRect = new Rectangle(x, y, width, height);
		return newRect;  
	}
}
