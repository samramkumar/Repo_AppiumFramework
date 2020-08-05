package appiumtests;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Android_AmazonPurchase {
	
	static AndroidDriver<AndroidElement> driver;
	//static AppiumDriver<MobileElement> driver;
	public static void main(String[] args) {
		try {
			Purchase();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	// Connect to Android device using android tools
	public static void Purchase() throws Exception {
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability("deviceName", "vivo 1820");
		cap.setCapability("udid", "YTZHF6GUUOV47DDA");
		//Mobile can be accessed wireless using below code
		//cap.setCapability("udid", "192.168.0.103:5555");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformversion", "8.1.0");
		//collect apk info for the amazon purchase app an dadd below
		cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		
		//Appium server url, to send client methods in JSON protocol
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<AndroidElement>(url, cap);
		System.out.println("Application started..");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("in.amazon.mShop.android.shopping:id/sso_continue")));
		driver.findElementById("in.amazon.mShop.android.shopping:id/sso_continue").click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text")));
		
		// Search for iphone 11 and add in the cart
		driver.findElementById("in.amazon.mShop.android.shopping:id/rs_search_src_text").click();
		driver.findElementById("in.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("iPhone 11");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElement(By.xpath("//android.webkit.WebView")).click();
		driver.findElement(By.xpath("//android.view.View[@text='Apple iPhone 11 (64GB) - Purple')]")).click();
		//Code to scroll down and click Add to cart
		TouchAction touchAction = new TouchAction(driver);
	    PointOption pointStart = PointOption.point(750, 1060);
	    PointOption pointEnd = PointOption.point(450, 1300);
	    WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofMillis(1000));
	    touchAction.press(pointStart).waitAction(waitOption).moveTo(pointEnd).release().perform();
		driver.findElement(By.xpath("//*[@text='Add to Cart']")).click();
		
		// Search for Mac laptop and add in the cart
		driver.findElementById("in.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("Mac Air pro");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElement(By.xpath("//android.webkit.WebView")).click();
		driver.findElement(By.xpath("//android.view.View[@text='Mac Air pro')]")).click();
		driver.findElement(By.xpath("//*[@text='Add to Cart']")).click();
		
		// Search for wings of fire book and add in the cart
		driver.findElementById("in.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("Wings of fire");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElement(By.xpath("//android.webkit.WebView")).click();
		driver.findElement(By.xpath("//android.view.View[@text='Wings of fire')]")).click();
		driver.findElement(By.xpath("//*[@text='Add to Cart']")).click();
        }

}
