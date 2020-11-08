package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"More options\"]",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            OPTIONS_ADD_TO_MY_LIST = "xpath://*[@text='Add to reading list']",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page",15);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void callOptionAddToMyList()
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option 'Add to reading list'",
                5
        );
    }
    public void tapGotItOverlay()
    {
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button 'GOT IT' tip overlay",
                5
        );
    }
    public void createNewMyList(String name_Of_Folder)
    {
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_Of_Folder,
                "Cannot put text into article folder input",
                5
        );
    }
    public void closeArticleButton()
    {
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find button X to Close article",
                5
        );
    }
}
