package demoWebShop;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestNGDemoWebshop {
	AndroidDriver driver;

	@Test
	public void CameraList() {
		driver.findElement(By.xpath("//div[@id='mob-menu-button']/a/span[1]/span[2]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[2]/li[3]/span")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[2]/li[3]/ul/li[1]/a")).click();
		driver.findElement(By.xpath(
				"//select[@name='products-viewmode']/option[@value='http://demowebshop.tricentis.com/camera-photo?viewmode=list']"))
				.click();
		String actual = driver.findElement(By.xpath("//select[@name='products-viewmode']/option[@selected='selected']")).getText();
		assertEquals(actual, "List");
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Sonali");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("http://demowebshop.tricentis.com/login");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("sonali.k@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Password");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//input[@value=\"Log in\"]")).click();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
