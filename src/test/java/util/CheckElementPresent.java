package util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CheckElementPresent {// 查找單一元素
	WebDriver driver;

	public CheckElementPresent(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(String locator, String type) { // locator:定位路徑 type:定位方式
		type = type.toLowerCase();
		if (type.equals("id")) {
			System.out.println("find element by id" + locator);
			return this.driver.findElement(By.id(locator));
		} else if (type.equals("xpath")) {
			System.out.println("find element by xpath" + locator);
			return this.driver.findElement(By.xpath(locator));
		} else if (type.equals("name")) {
			System.out.println("find element by name" + locator);
			return this.driver.findElement(By.name(locator));
		} else if (type.equals("css")) {
			System.out.println("find element by css" + locator);
			return this.driver.findElement(By.cssSelector(locator));
		} else if (type.equals("classname")) {
			System.out.println("find element by classname" + locator);
			return this.driver.findElement(By.className(locator));
		} else if (type.equals("tagname")) {
			System.out.println("find element by tagname" + locator);
			return this.driver.findElement(By.tagName(locator));
		} else if (type.equals("linktext")) {
			System.out.println("find element by linktext" + locator);
			return this.driver.findElement(By.linkText(locator));
		} else if (type.equals("partiallinktext")) {
			System.out.println("find element by partiallinktext" + locator);
			return this.driver.findElement(By.partialLinkText(locator));
		} else {
			System.out.println("invalid locator");
			return null;
		}

	}

	public List<WebElement> getElementList(String locator, String type) { // locator:定位路徑 type:定位方式
		type = type.toLowerCase();
		List<WebElement> elementList = new ArrayList<WebElement>();
		if (type.equals("id")) {
			elementList = this.driver.findElements(By.id(locator));
			// System.out.println("find element by id" + locator);
			// return this.driver.findElements(By.id(locator));
		} else if (type.equals("xpath")) {
			elementList = this.driver.findElements(By.xpath(locator));
			// System.out.println("find element by xpath" + locator);
			// return this.driver.findElements(By.xpath(locator));
		} else if (type.equals("name")) {
			elementList = this.driver.findElements(By.name(locator));
			// System.out.println("find element by name" + locator);
			// return this.driver.findElements(By.name(locator));
		} else if (type.equals("css")) {
			elementList = this.driver.findElements(By.cssSelector(locator));
			// System.out.println("find element by css" + locator);
			// return this.driver.findElements(By.cssSelector(locator));
		} else if (type.equals("classname")) {
			elementList = this.driver.findElements(By.className(locator));
			// System.out.println("find element by classname" + locator);
			// return this.driver.findElements(By.className(locator));
		} else if (type.equals("tagname")) {
			elementList = this.driver.findElements(By.tagName(locator));
			// System.out.println("find element by tagname" + locator);
			// return this.driver.findElements(By.tagName(locator));
		} else if (type.equals("linktext")) {
			elementList = this.driver.findElements(By.linkText(locator));
			// System.out.println("find element by linktext" + locator);
			// return this.driver.findElements(By.linkText(locator));
		} else if (type.equals("partiallinktext")) {
			elementList = this.driver.findElements(By.partialLinkText(locator));
			// System.out.println("find element by partiallinktext" + locator);
			// return this.driver.findElements(By.partialLinkText(locator));
		} else {
			System.out.println("invalid locator");
		}
		if (elementList.isEmpty()) {
			System.out.println("with" + type + ": " + locator + "can't find elements");
		}else {
			System.out.println("with" + type + ": " + locator + "can find elements");
		}
		return elementList;

	}

	public boolean isMarkerElementPresent(String locator, String type) {
		List<WebElement> elementList = getElementList(locator, type);
		int size = elementList.size();
		if (size == 16) {
			return true;
		} else {
			return false;
		}
	}

	public static void deleteCustomIcon(WebDriver driver) throws Exception {
		Actions action = new Actions(driver);
		List<WebElement> elementList = driver
				.findElements(By.xpath("//img[contains(@class,'image form-photo-large')]"));
		System.out.println(elementList);
		int size = elementList.size();
		if (size <= 10) {
			for (int index = 1; index <= 10; index++) {
				WebElement mouseHover = driver
						.findElement(By.xpath("//img[contains(@class,'image form-photo-large')]"));
				action.moveToElement(mouseHover).perform();
				WebElement deleteIcon = driver
						.findElement(By.xpath("//a[contains(@class,'file-remove file-remove-marker-icon')]"));
				Thread.sleep(1000);
				action.moveToElement(deleteIcon).click().perform();
			}
		}

	}

	public boolean isCustomIconElementPresent(String locator, String type) {
		List<WebElement> elementList = getElementList(locator, type);
		int size = elementList.size();
		if (size == 6) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementPresent(String locator, String type) { // 集合>0，表示元素存在頁面上
		List<WebElement> elementList = getElementList(locator, type);
		int size = elementList.size();
		if (size > 0) {
			return true;
		} else {
			return false;
		}

	}
}
