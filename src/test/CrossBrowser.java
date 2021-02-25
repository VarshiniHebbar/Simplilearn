package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class CrossBrowser {
	
	WebDriver chromeDriver;
	WebDriver firefoxDriver;
	
	@Test
	public void LaunchChrome() {
		
		System.setProperty("webdriver.chrome.driver", "/home/varshinihebbart/Downloads/chromedriver");
	   
		chromeDriver = new ChromeDriver();

		chromeDriver.get("https://www.simplilearn.com/");
		chromeDriver.manage().window().maximize();
		chromeDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
	}
	
	@Test(dependsOnMethods="LaunchChrome")
	public void testcase1() {
		
		WebElement lnkLogin = chromeDriver.findElement(By.linkText("Log in"));
		lnkLogin.click();
		
	}
	
	@Test
    public void LaunchFirefox() {
    	
	    System.setProperty("webdriver.gecko.driver", "/home/varshinihebbart/Downloads/geckodriver");
		
		firefoxDriver = new FirefoxDriver();
		
		firefoxDriver.get("https://www.simplilearn.com/");
		firefoxDriver.manage().window().maximize();
		firefoxDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
	}
    
    @Test(dependsOnMethods="LaunchFirefox")
    public void testcase2() {
    	WebElement lnkLogin = firefoxDriver.findElement(By.linkText("Log in"));
		lnkLogin.click();
    	
    }

}
