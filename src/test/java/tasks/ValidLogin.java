package tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import util.ReadProperties;

public class ValidLogin {
	public static void login(WebDriver dr) throws Exception {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys("joymls");
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys("000000");
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(8000);
		boolean result =
				 dr.findElements(By.className("header-title")).size() != 0;
				 Assert.assertTrue(result);
	}
}
