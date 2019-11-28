package tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import model.UserModel;
import util.ReadProperties;

public class CreateNewProperty {
	public static void createNewProperty(WebDriver dr,UserModel userModel) throws FileNotFoundException, IOException, InterruptedException {
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "username"))).sendKeys(userModel.getName());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "password"))).sendKeys(userModel.getPassword());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "loginbtn"))).click();
		Thread.sleep(10000);
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "createnewproperty"))).click();
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "591urlbox"))).sendKeys(userModel.getPropertyurl());
		dr.findElement(By.xpath(ReadProperties.getprop("element", "elements", "confirmbtn"))).click();
		Thread.sleep(30000);
		
		boolean result =
				 dr.findElements(By.xpath("//div[@id='root']//h2[contains(text(),'東湖畔')]")).size() != 0;
				 Assert.assertTrue(result);
	}
	
}
