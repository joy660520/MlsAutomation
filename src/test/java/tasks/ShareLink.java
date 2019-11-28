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

public class ShareLink {

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
		ShareLink.shareLink(dr, userModel);
		

	}

	public static void shareLink(WebDriver dr, UserModel userModel) throws Exception {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(10000);

		JavascriptExecutor js;
		js = (JavascriptExecutor) dr;
		js.executeScript("window.location='https://mls-test.istaging.com/list'");

		Thread.sleep(6000);

		WebElement shareBtn = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "sharebtn")));
		Actions action = new Actions(dr);
		action.moveToElement(shareBtn).perform();
		action.moveToElement(shareBtn).click().perform();

		Thread.sleep(5000);

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "copybtn"))).click();
		WebElement sharemsg = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "sharemessage")));
		String text = sharemsg.getText();
		System.out.println(text);
        String message="已將網址複製到剪貼簿";
		boolean result = text.equalsIgnoreCase(message);
		Assert.assertTrue(result);
		System.out.println("Share link successfully!");

	}

}
