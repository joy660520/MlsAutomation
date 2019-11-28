package tasks;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.UserModel;
import util.Constants;
import util.ImportPhotos;
import util.MouseHover;
import util.MultiBrowser;
import util.ReadProperties;
import util.Screenshots;
import util.ScrollingElementToView;

public class InvalidUpdateHouseInfo {

	public static void main(String[] args) throws Exception {
		WebDriver dr = null;
		String url = "https://mls-test.istaging.com/login";
		String username = "joymls";
		String password = "000000";
		System.setProperty("webdriver.chrome.driver", "/Users/joyshen/eclipse-workspace/MlsAutomation/chromedriver");
		dr = new ChromeDriver();
		dr.get(url);
		UserModel userModel = new UserModel();
		userModel.setName(username);
		userModel.setPassword(password);
		AddInvalidDefaultLivetour.addInvalidDefaultLivetour(dr, userModel);

	}

	public static void invalidUpdateHouseInfo(WebDriver dr, UserModel userModel) throws Exception {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(10000);

		JavascriptExecutor js;
		js = (JavascriptExecutor) dr;
		js.executeScript("window.location='https://mls-test.istaging.com/list'");

		Thread.sleep(6000);

		List<WebElement> elementList = dr
				.findElements(By.xpath(ReadProperties.getprop("element", "elements", "proplist"))); // 獲取所有物件列表

		try {
			String propname = "幸福城市";
			for (WebElement element : elementList) { // 遍歷物件列表
				if (element.getText().equals(propname)) {
					Actions action = new Actions(dr);
					action.moveToElement(element).perform();
					action.moveToElement(element).click().perform();
				}
				Thread.sleep(2000);
			}
		} catch (org.openqa.selenium.StaleElementReferenceException ee) {
			System.out.println("find 幸福城市");
		}

		Thread.sleep(5000);

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "iconhouseinfo"))).click();
		ScrollingElementToView.scrollingToHouseInfoInputbox(dr);
		System.out.println("滾動到銷售狀況輸入框");

		WebDriverWait wait = new WebDriverWait(dr, 40);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(ReadProperties.getprop("element", "elements", "sellingstatusinputbox"))));
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "sellingstatusinputbox")))
				.sendKeys(Constants.SellingStatus);
		Thread.sleep(3000);
		
		boolean invalidCheck = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "errormessage2")))
				.isDisplayed();
		if (invalidCheck == true) {
			String errorMessage = dr
					.findElement(By.xpath(ReadProperties.getprop("element", "elements", "errormessage2"))).getText();
			System.out.println(errorMessage);
//			
			ScrollingElementToView.scrollingToSavebutton(dr);
			Screenshots.getScreenshots(dr);

		} else {
			System.out.println("can't find the element");
		}

		boolean result = dr.findElements(By.xpath(ReadProperties.getprop("element", "elements", "errormessage2")))
				.size() != 0;
		Assert.assertTrue(result);
		Thread.sleep(3000);

	}

}
