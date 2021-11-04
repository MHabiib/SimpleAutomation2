package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Camera {

    WebDriver driver;

    @Given("^user set an image$")
    public void userSetAnImage() {
        try {
            ImageIO.write(ImageIO.read(new ClassPathResource("qr.png").getInputStream()), "png", new File(System.getenv("ANDROID_SDK_ROOT") + "\\emulator\\resources\\custom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("^user open scanner apps$")
    public void userOpenScannerApps() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "device");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("newCommandTimeout", 20);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.gamma.scan");
        capabilities.setCapability("appActivity", "com.gamma.barcodeapp.ui.BarcodeCaptureActivity");

        try {
            System.out.println("appium is opening");
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            System.out.println("appium opened");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("com.gamma.scan:id/zoomin_icon")).click();
    }
}
