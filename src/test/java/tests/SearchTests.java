package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearch()
    {
        /*testCancelSearch,search word, ждем и проверяем что есть в результатах статья с этим словом? отмена поиска,
        проверка что результат пропал = что контейнера со списком статей */

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisAppear();
    }

    @Test
    //проверяет наличие текста ожидаемого текста у элемента
    public void checkTextInputField(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertElementHasText(
                "xpath://*[contains(@text,'Search Wikipedia')]",
                "Search…",
                "The expected text is not found."
        );
    }
}
