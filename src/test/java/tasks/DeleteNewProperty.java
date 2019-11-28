package tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import model.UserModel;
import util.ReadProperties;

public class DeleteNewProperty {
	public static void deleteNewProperty(WebDriver dr, UserModel userModel)
			throws FileNotFoundException, IOException, InterruptedException {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(10000);

		try {
			String propertyName = dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "propname")))
					.getText();
			if (propertyName == "東湖畔") {
				System.out.println("建案名稱為" + propertyName);
			}
			dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "deletepropbtn"))).click();
			dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "confirmdelbtn"))).click();
			Thread.sleep(10000);
		} catch (org.openqa.selenium.NoSuchElementException ee) {
			System.out.println("element doesn't exist");
		}

		boolean result = dr.findElements(By.xpath("//div[@id='root']//h2[contains(text(),'東湖畔')]")).size() == 0;
		Assert.assertTrue(result);
	}

}
