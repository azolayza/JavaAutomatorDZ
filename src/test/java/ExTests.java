import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
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
import java.time.Duration;

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
        capabilities.setCapability("orientation", "PORTRAIT");

        driver = new AndroidDriver(new URL("http:127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    //testElementHasText
    //click to Search Wiki
    //Check the text for element

    @Test
    public void testElementHasText()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        assertElementHasText(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Search…",
                "Search input not contains text 'Search...'"
        );
    }

    //testCancelSearch
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

    @Test
    public void saveTwoArticlesToMyList(){
    //save 1 article
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JAVA",
                "cannot find Search Wikipedia input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option 'Add to reading list'",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'GOT IT' tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        String name_Of_Folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_Of_Folder,
                "Cannot put text into article folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find button X to Close article",
                25
        );

        //------- Save 2d article
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JAVA",
                "cannot find Search Wikipedia input",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (disambiguation)']"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option 'Add to reading list'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find folder for saving 2d article",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find button X to Close article",
                5
        );
        //------- Delete 1 article and check title of 2d

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]/android.widget.ImageView"),
                "Cannot find navigation button 'My Lists''",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find created folder",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Java (disambiguation)']"),
                "Cannot find article about JAVA from saving list",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (disambiguation)",
                article_title
        );
    }

    @Test
    public void TestAssertTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        String search_line = "Appium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "cannot find Search input",
                20
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Appium']"),
                "Cannot find Search Wikipedia input",
                5
        );
        /*waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
      String search_title_locator = "[@resource_id='org.wikipedia:id/view_page_title_text";

        Assert.assertTrue(
                "We check title for article",
                search_title_locator == "view_page_title_text"
        );*/
        String search_title = "Appium";
        String title_of_article = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "We check title for article",
                title_of_article,
                search_title
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
    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementHasText(By xpath, String article_title, String error_message)
    {
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cannot find 'Search…",
                5
        );
        article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see text: Search",
                "Search…",
                article_title
        );
    }
}




