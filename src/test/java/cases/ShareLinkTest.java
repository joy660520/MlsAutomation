package cases;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import model.UserModel;
import tasks.AccountLogin;
import tasks.ShareLink;
import tasks.ValidLogin;
import util.MultiBrowser;
import util.Screenshots;

import org.testng.annotations.Parameters;

public class ShareLinkTest {
	private static Logger logger = LogManager.getLogger(ShareLinkTest.class.getName()); // 定義Logger
	WebDriver dr;
	UserModel userModel;
	ITestResult finalTestResult;

	@Parameters({ "browser", "url", "username", "password" })
	@BeforeMethod
	public void setup(String browser, String url, String username, String password) throws Exception {
		userModel = new UserModel();
		userModel.setBrowser(browser);
		userModel.setUrl(url);
		userModel.setName(username);
		userModel.setPassword(password);

		// logger.info("browser is" + browser + "url is" + url);

	}

	@Test
	public void validLoginTest() throws Exception {
		dr = MultiBrowser.openChrome(dr, userModel);
		ShareLink.shareLink(dr, userModel);

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
