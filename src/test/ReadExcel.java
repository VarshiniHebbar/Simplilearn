package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReadExcel {
	WebDriver driver;
	SoftAssert assertobj = new SoftAssert();
	
	ExtentReports extent;
	ExtentTest test;
	
	XSSFWorkbook Wb;
	XSSFSheet sheet;
	
	@BeforeMethod
	public void setup() throws IOException {

		System.setProperty("webdriver.chrome.driver", "/home/varshinihebbart/Downloads/chromedriver");
	    System.setProperty("webdriver.gecko.driver", "/home/varshinihebbart/Downloads/geckodriver");
		
		driver = new ChromeDriver();
		extent= new ExtentReports ("ExtentReports.html", true);
		driver.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		FileInputStream file = new FileInputStream("DataSheet.xlsx");
		Wb= new XSSFWorkbook(file);
		sheet = Wb.getSheet("datasheet");
}
	
	@Test
    public void testcase1() {
		
		test = extent.startTest("test");
		
		WebElement lnklogin = driver.findElement(By.linkText("Log in"));
		lnklogin.click();
		test.log(LogStatus.PASS, "clicked on login button");
		
		WebElement editUsername = driver.findElement(By.name("user_login"));
		String UserName = sheet.getRow(1).getCell(0).getStringCellValue();
		editUsername.sendKeys(UserName);
		test.log(LogStatus.PASS, "entered username");
		WebElement editPwd = driver.findElement(By.name("user_pwd"));
		String Password = sheet.getRow(1).getCell(1).getStringCellValue(); 
		editPwd.sendKeys(Password);
		test.log(LogStatus.PASS, "entered password");
		WebElement chkBox = driver.findElement(By.className("rememberMe"));
		chkBox.click();
		test.log(LogStatus.PASS, "clicked on remember me");
		WebElement btnPwd = driver.findElement(By.name("btn_login"));
		btnPwd.click();
		test.log(LogStatus.PASS, "clicked on login button");
		
        WebElement error = driver.findElement(By.id("msg_box"));
        
        String ActError = error.getText();
        
        String ExpError = "The email or password you have entered is invalid.";
        
        Assert.assertEquals(ActError, ExpError);
        //assertobj.assertEquals(ActError, ExpError);
        
        System.out.println("After Failure");
		
        
 }

	@AfterMethod
      public void teardown() {
	 

		driver.quit();
		//assertobj.assertAll();
		extent.endTest(test);
		extent.flush();
		extent.close();
 }

}
