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
import org.testng.Assert;

import model.UserModel;
import util.Constants;
import util.ImportPhotos;
import util.MouseHover;
import util.MultiBrowser;
import util.ReadProperties;
import util.ScrollingElementToView;

public class DeleteDefaultLivetour {

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

		DeleteDefaultLivetour.deleteDefaultLivetour(dr, userModel);

	}

	public static void deleteDefaultLivetour(WebDriver dr, UserModel userModel) throws Exception {
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

		Thread.sleep(8000);

		ScrollingElementToView.scrollingToDefaultLivetourInputbox(dr);

		System.out.println("滾動到Livetour輸入框");

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourinputbox"))).click();
		Thread.sleep(1000);
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourinputbox")))
				.sendKeys(Keys.SPACE);
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourinputbox"))).clear();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourinputbox")))
				.sendKeys(Constants.DefaultLivetour);

		Thread.sleep(2000);

		MouseHover.moveToDefaultLivetourLabel(dr); // 滑鼠移到旁邊點一下

		Thread.sleep(2000);

		boolean url = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "livetoururl")))
				.isDisplayed();
		if (url == true) {
			System.out.println(url);
			dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "livetourdeletebtn"))).click();
			ScrollingElementToView.scrollingToSavebutton(dr);
		} else {
			System.out.println("can't find the element");
		}

		boolean emptyurl = dr
				.findElement(By.xpath(ReadProperties.getprop("element", "elements", "defaultLivetourinputboxtext")))
				.isDisplayed();
		if (emptyurl == true) {
			System.out.println("Delete livetour successfully!");
		} else {
			System.out.println("can't find the element");
		}

		Thread.sleep(3000);

		dr.quit();
	}

}
