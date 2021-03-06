package cases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import model.UserModel;
import tasks.InvalidLogin;
import tasks.ValidLogin;
import util.Constants;
import util.ExcelUtility;
import util.MultiBrowser;
import util.ReadProperties;
import util.Screenshots;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class EmptyAccountLoginTest {
	WebDriver dr;
	UserModel userModel;
	ITestResult finalTestResult;

	@Parameters({ "browser", "url" ,"password"})
	@BeforeMethod
	public void setup(String browser, String url, String password) throws Exception {
		userModel = new UserModel();
		userModel.setBrowser(browser);
		userModel.setUrl(url);
		userModel.setPassword(password);

	}

	@Test
	public void invalidLoginTest() throws Exception {
		dr = MultiBrowser.openChrome(dr, userModel);
		InvalidLogin.invalidLoginEmptyAccount(dr,userModel);

	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws Exception {
		this.finalTestResult = testResult;
		if (finalTestResult.getStatus() == ITestResult.FAILURE) {
			Screenshots.getScreenshots(dr);
		}
		MultiBrowser.closeBrowser(dr);

	}

}
