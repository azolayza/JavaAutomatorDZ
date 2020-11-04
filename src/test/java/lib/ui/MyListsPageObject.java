package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            OK_BUTTON_MY_LIST = "//*[@text='OK']",
            MY_LIST_XPATH = "//*[@text='Learning programming']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_MANE}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find created folder by name " + name_of_folder,
                5
        );
    }
    public void clickToMyList()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_XPATH),
                "Cannot find folder for saving 2d article",
                5
        );
    }
    public void clickOkAfterCreateMyList()
    {
        this.waitForElementAndClick(
                By.xpath(OK_BUTTON_MY_LIST),
                "Cannot press OK button'",
                5
        );
    }
    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
    }
}
