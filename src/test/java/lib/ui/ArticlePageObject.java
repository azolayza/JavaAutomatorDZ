package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc=\"More options\"]",
    OPTIONS_ADD_TO_MY_LIST = "//*[@text='Add to reading list']",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    OVERLAY_GOTIT = "org.wikipedia:id/onboarding_button",
    MY_LIST_OK_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page",15);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void callOptionAddToMyList()
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find option 'Add to reading list'",
                5
        );
    }
    public void tapGotItOverlay()
    {
        this.waitForElementAndClick(
                By.id(OVERLAY_GOTIT),
                "Cannot find button 'GOT IT' tip overlay",
                5
        );
    }
    public void createNewMyList(String name_Of_Folder)
    {
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_Of_Folder,
                "Cannot put text into article folder input",
                5
        );
    }
    public void closeArticleButton()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find button X to Close article",
                5
        );
    }
}
