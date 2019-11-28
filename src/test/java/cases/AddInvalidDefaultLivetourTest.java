package cases;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import model.UserModel;
import tasks.AddDefaultLivetour;
import tasks.AddInvalidDefaultLivetour;
import tasks.AddPropLogo;
import util.MultiBrowser;

public class AddInvalidDefaultLivetourTest {
	private static Logger logger = LogManager.getLogger(AddInvalidDefaultLivetourTest.class.getName()); // 定義Logger

	WebDriver dr;
	UserModel userModel;
	ITestResult finalTestResult;

	@Parameters({ "browser", "url", "username", "password" })
	@BeforeTest
	public void setup(String browser, String url, String username, String password) throws Exception {
		userModel = new UserModel();
		userModel.setBrowser(browser);
		userModel.setUrl(url);
		userModel.setName(username);
		userModel.setPassword(password);

		// logger.info("browser is" + browser + "url is" + url);

	}

	@Test
	public void addDefaultLivetour() throws Exception {
		dr = MultiBrowser.openChrome(dr, userModel);
		AddInvalidDefaultLivetour.addInvalidDefaultLivetour(dr, userModel);
		logger.info("Invalid Default LiveTour can't be added correctly");

	}

	@AfterTest
	public void tearDown() {
		MultiBrowser.closeBrowser(dr);
	}

}
