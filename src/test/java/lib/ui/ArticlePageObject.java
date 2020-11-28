package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
            TITLE,
            OPTIONS_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            SAVE_LIST_OVERLAY_CLOSE_BUTTON,
            OPTIONS_ADD_TO_MY_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page",15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isiOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
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

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find button X to Close article",
                5
        );
    }

    public void tapSavedListOverlay()
    {
        this.waitForElementAndClick(
                SAVE_LIST_OVERLAY_CLOSE_BUTTON,
                "Cannot find button 'X' for close overlay",
                5
        );
    }
    public void addArticleToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to reading list", 5);
    }
}
