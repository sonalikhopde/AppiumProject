package demoWebShop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CameraDemoWebShop {

	public static void main(String[] args) throws InterruptedException, IOException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Sonali");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("http://demowebshop.tricentis.com/login");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("sonali.k@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Password");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//input[@value=\"Log in\"]")).click();

		driver.findElement(By.xpath("//div[@id='mob-menu-button']/a/span[1]/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[2]/li[3]/span")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[2]/li[3]/ul/li[1]/a")).click();

		// Reading excel file

		File file = new File("C:\\Sonali\\Ignite SDET\\Appium\\Project\\ExcelData\\Data.xls");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new HSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		WebElement element = driver.findElement(By.xpath("//select[@name='products-viewmode']"));
		Select viewAsList = new Select(element);
		for (int i = 1; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			String str = sheet.getRow(i).getCell(0).getStringCellValue();
			System.out.println(str);
			if (str.equalsIgnoreCase("List")) {
				driver.findElement(By.xpath(
						"//select[@name='products-viewmode']/option[@value='http://demowebshop.tricentis.com/camera-photo?viewmode=list']"))
						.click();
				System.out.println("selected value is : " + str);
			} else if (str.equalsIgnoreCase("Grid")) {
				driver.findElement(By.xpath(
						"//select[@name='products-viewmode']/option[@value='http://demowebshop.tricentis.com/camera-photo?viewmode=list']"))
						.click();
				System.out.println("selected value is : " + str);
			}

		}
		String found = driver.findElement(By.xpath("//select[@name='products-viewmode']/option[@selected='selected']"))
				.getText();
		System.out.println(found);

	}

}
