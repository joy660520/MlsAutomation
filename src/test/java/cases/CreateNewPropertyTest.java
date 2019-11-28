package cases;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import model.UserModel;
import tasks.AccountLogin;
import tasks.CreateNewProperty;
import tasks.ValidLogin;
import util.MultiBrowser;
import util.Screenshots;

import org.testng.annotations.Parameters;

public class CreateNewPropertyTest {
	private static Logger logger = LogManager.getLogger(CreateNewPropertyTest.class.getName()); // 定義Logger

	WebDriver dr;
	UserModel userModel;
	ITestResult finalTestResult;

	@Parameters({ "browser", "url", "username", "password", "propertyurl" })
	@BeforeMethod
	public void setup(String browser, String url, String username, String password, String propertyurl)
			throws Exception {
		userModel = new UserModel();
		userModel.setBrowser(browser);
		userModel.setUrl(url);
		userModel.setName(username);
		userModel.setPassword(password);
		userModel.setPropertyurl(propertyurl);
		
		// logger.info("browser is" + browser + "url is" + url);

	}

	@Test
	public void createNewPropertyTest() throws Exception {
		dr = MultiBrowser.openChrome(dr, userModel);
		CreateNewProperty.createNewProperty(dr, userModel);
		logger.info("Property created successfully");

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
