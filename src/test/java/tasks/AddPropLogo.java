package tasks;

import java.io.IOException;
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
import util.ImportPhotos;
import util.MultiBrowser;
import util.ReadProperties;
import util.ScrollingElementToView;

public class AddPropLogo {

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

		AddPropLogo.addPropLogo(dr, userModel);

	}

	public static void addPropLogo(WebDriver dr, UserModel userModel) throws Exception {
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

		ScrollingElementToView.scrollingToAddLogobutton(dr);
		System.out.println("滾動到瀏覽icon");

		ImportPhotos.importLogoPhotos(dr);

		ScrollingElementToView.scrollingToSavebutton(dr);

		Thread.sleep(6000);

		ScrollingElementToView.scrollingToPropertyLogo(dr);

		boolean result = dr.findElements(By.xpath(ReadProperties.getprop("element", "elements", "logoimage")))
				.size() != 0;
		Assert.assertTrue(result);

		Thread.sleep(6000);

		dr.quit();
	}

}
