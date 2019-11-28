package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseHover {
	public static void moveTo(WebDriver dr) throws Exception {
		WebElement mouseHover = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "movetoelement")));
		Actions action = new Actions(dr);
		action.moveToElement(mouseHover).perform();
		action.moveToElement(mouseHover).click().perform();
	}
	
	public static void moveToDefaultLivetourLabel(WebDriver dr) throws Exception {
		WebElement mouseHover = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourlabel")));
		Actions action = new Actions(dr);
		action.moveToElement(mouseHover).perform();
		action.moveToElement(mouseHover).click().perform();
	}
}
