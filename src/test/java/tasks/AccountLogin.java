package tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import model.UserModel;
import util.ReadProperties;

public class AccountLogin {
	public static void accountLogin(WebDriver dr,UserModel userModel) throws FileNotFoundException, IOException, InterruptedException {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(8000);
		boolean result =
				 dr.findElements(By.className("header-title")).size() != 0;
				 Assert.assertTrue(result);
	}
	
}
