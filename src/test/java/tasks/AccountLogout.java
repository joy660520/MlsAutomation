package tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import model.UserModel;
import util.CheckElementPresent;
import util.ReadProperties;

public class AccountLogout {
	public static void accountLogout(WebDriver dr, UserModel userModel)
			throws FileNotFoundException, IOException, InterruptedException {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(8000);
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "logoutbtn"))).click();
		Thread.sleep(3000);

		WebElement loginpagecheck = dr
				.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginpagecheck")));
		String text = loginpagecheck.getText();
		System.out.println(text);//回到登入頁面並打印"後台管理系統"
		
		CheckElementPresent present = new CheckElementPresent(dr);
		boolean result = present.isElementPresent("//div[@id='root']/div/form/h1", "xpath");
		Assert.assertTrue(result);
		System.out.println(result);
	}

}
