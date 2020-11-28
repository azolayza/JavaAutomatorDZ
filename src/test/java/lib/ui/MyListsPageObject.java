package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            OK_BUTTON_MY_LIST,
            MY_LIST_XPATH,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_BUTTON_DELETE;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_MANE}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find created folder by name " + name_of_folder,
                5
        );
    }
    public void clickToMyList()
    {
        this.waitForElementAndClick(
                MY_LIST_XPATH,
                "Cannot find folder for saving 2d article",
                5
        );
    }
    public void clickOkAfterCreateMyList()
    {
        this.waitForElementAndClick(
                OK_BUTTON_MY_LIST,
                "Cannot press OK button'",
                5
        );
    }
    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isiOS()){
            //this.clickElementToTheRightUpperCorner(article_xpath, "Cannot fined saved article");
            this.waitForElementAndClick(
                    ARTICLE_BUTTON_DELETE,
                    "Cannot find button for delete article in saved",
                    5
            );
        }
    }
}
