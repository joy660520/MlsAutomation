package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ImportPhotos {
	private static String getHostname() {
		InetAddress ip;
		String hostname = "";
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			// System.out.println("Your current IP address : " + ip);
			// System.out.println("Your current Hostname : " + hostname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostname;
	}


	public static void importLogoPhotos(WebDriver driver) throws FileNotFoundException, IOException {

		if (getHostname().equals("iMac.local")) {
			for (int index = 1; index <= 1; index++) {
				driver.findElement(By.id("photoFile"))
						.sendKeys("/Users/joyshen/Documents/automation/propLogo/propLogo" + index + ".jpg");
			}
		} else if (getHostname().equals("joy-MacBook-Pro.local")) {
			for (int index = 1; index <= 1; index++) {
				driver.findElement(By.id("photoFile"))
						.sendKeys("/Users/joyshen/Documents/automation/propLogo/propLogo" + index + ".jpg");
			}
		}
	}

}
