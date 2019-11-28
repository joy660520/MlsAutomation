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
import tasks.ValidLogin;
import util.MultiBrowser;
import util.Screenshots;

import org.testng.annotations.Parameters;

public class MultiBrowserTest {
	private static Logger logger = LogManager.getLogger(MultiBrowserTest.class.getName()); //定義Logger
	WebDriver dr;
	String url;
	UserModel userModel;
	ITestResult finalTestResult;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void setup(String browser, String url) throws Exception {
		BasicConfigurator.configure(); //讀取配置文件 
		logger.info("browser is"+browser+"url is"+url);
		dr = MultiBrowser.openBrowser(dr, browser, userModel);

	}

	@Test
	public void validLoginTest() throws Exception {
		ValidLogin.login(dr);

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
