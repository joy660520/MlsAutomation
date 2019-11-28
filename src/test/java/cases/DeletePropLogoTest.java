package cases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import model.UserModel;
import tasks.AddPropLogo;
import tasks.DeletePropLogo;
import util.MultiBrowser;
import util.Screenshots;

public class DeletePropLogoTest {
	private static Logger logger = LogManager.getLogger(DeletePropLogoTest.class.getName()); // 定義Logger
	
	WebDriver dr;
	UserModel userModel;
	ITestResult finalTestResult;
	

	@Parameters({ "browser", "url", "username", "password" })
	@BeforeMethod
	public void setup(String browser, String url, String username, String password)
			throws Exception {
		UserModel userModel=new UserModel();
		userModel.setBrowser(browser);
		userModel.setUrl(url);
		userModel.setName(username);
		userModel.setPassword(password);
		
		

		// logger.info("browser is" + browser + "url is" + url);

	}

	@Test
	public void deletePropLogo() throws Exception {
		dr = MultiBrowser.openChrome(dr, userModel);
		DeletePropLogo.deletePropLogo(dr, userModel);
		logger.info("Property Logo deleted successfully");

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
