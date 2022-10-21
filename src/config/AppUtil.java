package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver driver;
	public static Properties conprop;
	@BeforeSuite
	public void setUp()throws Throwable
	{
		conprop = new Properties();
		conprop.load(new FileInputStream("E:\\9ClockTestNG\\Hybrid_NewFrameWork\\PropertyFiles\\Environment.properties"));
		if(conprop.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(conprop.getProperty("Url"));
			Thread.sleep(3000);
		}
		else if(conprop.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.get(conprop.getProperty("Url"));
			Thread.sleep(3000);
		}
		else
		{
			System.out.println("Browser Value is Not matching");
		}
	}
	@AfterSuite
	public void tearDown()
	{
		driver.quit();
	}
	}