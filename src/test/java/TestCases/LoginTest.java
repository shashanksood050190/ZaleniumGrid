


package TestCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

	RemoteWebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	void setUp(String br) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		if (br.equals("chrome")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			dc.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
		}
		if (br.equals("firefox")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
		}
		URL url = new URL("http://192.168.99.100:4444/wd/hub");
		driver = new RemoteWebDriver(url, dc);
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test

	void loginTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("shashanksood.jecrc@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("nopcommerce0501");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

		Thread.sleep(5000);

		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");

	}

	@AfterMethod
	void tearDown() {
		driver.quit();
	}
}
