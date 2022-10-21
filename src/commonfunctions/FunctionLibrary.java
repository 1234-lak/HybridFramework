package commonfunctions;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
	//method for admin login
		public static boolean verifyLogin(String username,String password)throws Throwable
		{
			driver.findElement(By.xpath(conprop.getProperty("ObjUser"))).sendKeys(username);
			driver.findElement(By.xpath(conprop.getProperty("Objpass"))).sendKeys(password);
			driver.findElement(By.xpath(conprop.getProperty("ObjLogin"))).click();
			Thread.sleep(3000);
			String expected ="adminflow";
			String actual =driver.getCurrentUrl();
			if(actual.toLowerCase().contains(expected.toLowerCase()))
			{
				Reporter.log("Login success::"+expected+"    "+actual,true);
				return true;
			}
			else
			{
				Reporter.log("Login Fail::"+expected+"    "+actual,true);
				return false;
			}
		}
		//method for branch button
		public static void clickBranches()throws Throwable
		{
			driver.findElement(By.xpath(conprop.getProperty("ObjBranches"))).click();
			Thread.sleep(2000);
		}
	//method for new branch creation
		public static boolean verifyNewBranch(String branchname,String Address1,String Address2,String Address3,
				String Area,String zipcode,String Country,String State,String City)throws Throwable
		{
			driver.findElement(By.xpath(conprop.getProperty("ObjNewBranch"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(conprop.getProperty("ObjBranch"))).sendKeys(branchname);
			driver.findElement(By.xpath(conprop.getProperty("ObjAddress1"))).sendKeys(Address1);
			driver.findElement(By.xpath(conprop.getProperty("ObjAddress2"))).sendKeys(Address2);
			driver.findElement(By.xpath(conprop.getProperty("ObjAddress3"))).sendKeys(Address3);
			driver.findElement(By.xpath(conprop.getProperty("ObjArea"))).sendKeys(Area);
			driver.findElement(By.xpath(conprop.getProperty("ObjZipCode"))).sendKeys(zipcode);
			new Select(driver.findElement(By.xpath(conprop.getProperty("ObjCountry")))).selectByVisibleText(Country);
			new Select(driver.findElement(By.xpath(conprop.getProperty("ObjState")))).selectByVisibleText(State);
			new Select(driver.findElement(By.xpath(conprop.getProperty("ObjCity")))).selectByVisibleText(City);
			driver.findElement(By.xpath(conprop.getProperty("Objsubmit"))).click();
			Thread.sleep(2000);
			String expectedAlert =driver.switchTo().alert().getText();
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			String actualalert="New Branch with";
			if(expectedAlert.toLowerCase().contains(actualalert.toLowerCase()))
			{
				Reporter.log(expectedAlert,true);
				return true;
			}
			else
			{
				Reporter.log("Branch Creation is fail",true);
				return false;
			}
			
		}
		public static boolean verifyBranchUpdate(String branch,String Address,String Area,String zip)throws Throwable
		{
			driver.findElement(By.xpath(conprop.getProperty("ObjEdit"))).click();
			Thread.sleep(3000);
			WebElement element1 = driver.findElement(By.xpath(conprop.getProperty("ObjBranchName")));
			element1.clear();
			element1.sendKeys(branch);
			WebElement element2 = driver.findElement(By.xpath(conprop.getProperty("ObjAddress")));
			element2.clear();
			element2.sendKeys(Address);
			WebElement element3 = driver.findElement(By.xpath(conprop.getProperty("ObjAreaName")));
			element3.clear();
			element3.sendKeys(Area);
			WebElement element4 = driver.findElement(By.xpath(conprop.getProperty("ObjZip")));
			element4.clear();
			element4.sendKeys(zip);
			driver.findElement(By.xpath(conprop.getProperty("ObjUpdate"))).click();
			Thread.sleep(4000);
			String expectedalert =driver.switchTo().alert().getText();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			String actualalert ="Branch updated";
			if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
			{
				Reporter.log(expectedalert,true);
				return true;
			}
			else
			{
				Reporter.log("Branch Update Fail",true);
				return false;
			}
		}
			//method for logout
			public static boolean verifyLogout()throws Throwable
			{
				driver.findElement(By.xpath(conprop.getProperty("ObjLogout"))).click();
				Thread.sleep(3000);
				if(driver.findElement(By.xpath(conprop.getProperty("ObjLogin"))).isDisplayed())
				{
					Reporter.log("Logout Success",true);
					return true;
				}
				else
				{
					Reporter.log("Logout Fail",true);
					return false;
				}
			}
		}

