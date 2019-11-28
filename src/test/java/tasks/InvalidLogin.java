package tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import model.UserModel;
import util.ReadProperties;

public class InvalidLogin {
	public static void invalidLogin(WebDriver dr, UserModel userModel)
			throws InterruptedException, FileNotFoundException, IOException { // 變數要等於列數(String name, String password)

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).clear(); // 清空輸入框
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username")))
				.sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).clear();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password")))
				.sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(3000);

		boolean result = dr.findElements(By.className("login-error-text")).size() != 0; // 加斷言判斷，查找錯誤訊息是否存在(findElements返回的是集合，如果有找到這個元素表示集合大小不為0)
		Assert.assertTrue(result);

	}

	public static void invalidLoginErrorCheck(WebDriver dr)
			throws InterruptedException, FileNotFoundException, IOException { // 變數要等於列數(String name, String password)

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).clear(); // 清空輸入框
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys("");
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).clear();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys("");
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(3000);

		WebElement errorWrapper = dr
				.findElement(By.xpath(ReadProperties.getprop("element", "elements", "errormessage")));
		String errorMessage = errorWrapper.getText();
		System.out.println(errorMessage);
		boolean result = dr.findElements(By.className("login-error-text")).size() != 0; // 加斷言判斷，查找錯誤訊息是否存在(findElements返回的是集合，如果有找到這個元素表示集合大小不為0)
		Assert.assertTrue(result);

	}

	public static void invalidLoginEmptyAccount(WebDriver dr, UserModel userModel)
			throws InterruptedException, FileNotFoundException, IOException { // 變數要等於列數(String name, String password)

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).clear();
		; // 清空輸入框
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys("");

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).clear();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password")))
				.sendKeys(userModel.getPassword());

		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(3000);

		WebElement errorWrapper = dr
				.findElement(By.xpath(ReadProperties.getprop("element", "elements", "errormessage")));
		String errorMessage = errorWrapper.getText();
		System.out.println(errorMessage);
		boolean result = dr.findElements(By.className("login-error-text")).size() != 0; // 加斷言判斷，查找錯誤訊息是否存在(findElements返回的是集合，如果有找到這個元素表示集合大小不為0)
		Assert.assertTrue(result);

	}

	public static void invalidLoginEmptyPassword(WebDriver dr, UserModel userModel)
			throws InterruptedException, FileNotFoundException, IOException { // 變數要等於列數(String name, String password)

		// dr.findElement(By.xpath(ReadProperties.getprop("element", "elements",
		// "username"))).sendKeys(Keys.HOME,
		// Keys.chord(Keys.SHIFT, Keys.END), ""); // 清空輸入框
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).clear();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username")))
				.sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).clear();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys("");
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(3000);

		WebElement errorWrapper = dr
				.findElement(By.xpath(ReadProperties.getprop("element", "elements", "errormessage")));
		String errorMessage = errorWrapper.getText();
		System.out.println(errorMessage);
		boolean result = dr.findElements(By.className("login-error-text")).size() != 0; // 加斷言判斷，查找錯誤訊息是否存在(findElements返回的是集合，如果有找到這個元素表示集合大小不為0)
		Assert.assertTrue(result);

	}
}
