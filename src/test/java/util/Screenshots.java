/**
 * 
 */
/**
 * @author joyshen
 *
 */
package util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshots { //需要放在AfterMethod裡
	WebDriver driver;
	
	public static String getRandomString() throws InterruptedException {
		String s = UUID.randomUUID().toString();
		return s.toString();
	}

	public static void getScreenshots(WebDriver driver) throws Exception {
		String fileName = getRandomString() + ".png";
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "screenshot");
		// String directory = "/Users/joyshen/Documents/autotesting screenshot";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(sourceFile, new File(appDir, fileName));
	}
}
