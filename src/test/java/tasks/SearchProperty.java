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
import util.Screenshots;
import util.ScrollingElementToView;

public class SearchProperty {

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

	public static void searchProperty(WebDriver dr, UserModel userModel) throws Exception {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(8000);

		WebElement searchinputbox = dr
				.findElement(By.xpath(ReadProperties.getprop("element", "elements", "searchinputbox")));
		searchinputbox.sendKeys("search");

		Thread.sleep(3000);

		List<WebElement> properties = dr
				.findElements(By.xpath(ReadProperties.getprop("element", "elements", "properties")));
		int size = properties.size();
		for (int i = 0; i < size; i++) {
			String propertyName = properties.get(i).getText();
			System.out.println(propertyName);
		}
		boolean result = properties.size() == 5;
		Assert.assertTrue(result);
        System.out.println("Search property successfully!");
        
        
	}

}
