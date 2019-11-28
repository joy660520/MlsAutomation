package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ScrollingElementToView {
	
	public static void scrollingToAddLogobutton(WebDriver driver) throws FileNotFoundException, IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(ReadProperties.getprop("element", "elements", "addlogoicon")));//定位目标元素
 		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void scrollingToSavebutton(WebDriver driver) throws FileNotFoundException, IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(ReadProperties.getprop("element", "elements", "savebotton")));//定位目标元素
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}
	
	public static void scrollingToPropertyLogo(WebDriver driver) throws FileNotFoundException, IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(ReadProperties.getprop("element", "elements", "propertydeveloper")));//定位目标元素
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

	}
	
	public static void scrollingToPropertyName(WebDriver driver) throws FileNotFoundException, IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(ReadProperties.getprop("element", "elements", "propertyname")));//定位目标元素
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

	}
	
	public static void scrollingToDefaultLivetourInputbox(WebDriver driver) throws FileNotFoundException, IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourinputbox")));//定位目标元素
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

	}
	
	public static void scrollingToHouseInfoInputbox(WebDriver driver) throws FileNotFoundException, IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(ReadProperties.getprop("element", "elements", "sellingstatusinputbox")));//定位目标元素
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

	}

}
