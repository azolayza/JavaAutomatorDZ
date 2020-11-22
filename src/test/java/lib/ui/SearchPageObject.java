package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_CANCEL_BUTTON;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //Templates Methods
    private static String getSearchResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    //Templates Methods

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search Input after clicking search init element");

    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 15);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getSearchResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSearchResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find cancel button X", 5);
    }

    public void waitForCancelButtonToDisAppear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 15);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click cancel button", 10);
    }

    public void assertElementHasText(String SEARCH_INIT_ELEMENT, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(SEARCH_INIT_ELEMENT, error_message, 20);
        String element_text = element.getAttribute("text");
        Assert.assertEquals(error_message, expected_text, element_text);
    }
}


