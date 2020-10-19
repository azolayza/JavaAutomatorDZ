import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class ExTests {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","test");
        capabilities.setCapability("platformVersion","8.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/liza/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http:127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    //search word
    //ждем и проверяем что есть в результатах статья с этим словом
    //отмена поиска
    //проверка что результат пропал = что контейнера со списком статей

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
             By.id("org.wikipedia:id/search_container"),
             "Cannot find 'Search Wikipedia' input",
             5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "cashback",
                "cannot find Search Wikipedia input",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Cashback']"),
                "cannot find 'Cashback' topic searching by 'Cashback'",
                15
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                //By.id("org.wikipedia:id/search_close_btn"),
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Cashback']"),
                "Cannot find Cashback search",
                5
        );
    }
        private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
        {
       WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
       wait.withMessage(error_message + "\n");
       return wait.until(
               ExpectedConditions.presenceOfElementLocated(by)
       );
        }

        private WebElement waitForElementPresent(By by, String error_message)
        {
        return waitForElementPresent(by, error_message, 5);
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.click();
            return element;
        }

         private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.sendKeys(value);
            return element;
        }

        private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

         private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
        {
             WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
             element.clear();
                return element;
        }
}



