package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import model.UserModel;

public class MultiBrowser {
//	UserModel userModel = new UserModel();

	public static WebDriver openBrowser(WebDriver dr, String browser, UserModel userModel) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/joyshen/eclipse-workspace/MlsAutomation/chromedriver");
			dr = new ChromeDriver();
		} else if (browser.equals("safari")) {
			dr = new SafariDriver();
		} else {
			System.out.println("not support");
		}
		dr.manage().window().maximize();
		dr.get(userModel.getUrl());
		return dr;
	}

	public static void closeBrowser(WebDriver dr) {
		dr.quit();
	}

	public static WebDriver openChrome(WebDriver driver, UserModel userModel) {
		System.setProperty("webdriver.chrome.driver", "/Users/joyshen/eclipse-workspace/MlsAutomation/chromedriver");
		driver = new ChromeDriver();
		// driver.manage().window().maximize();
		driver.get(userModel.getUrl());
		return driver;
	}
}
