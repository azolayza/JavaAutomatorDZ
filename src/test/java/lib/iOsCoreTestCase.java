package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOsCoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http:127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iphone8");
        capabilities.setCapability("platformVersion","13.4");
        capabilities.setCapability("app","/Users/liza/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }
}