package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase{
    @Test
    public void testAssertTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Appium");
        String search_title = "Appium";
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_of_article = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We check title for article",
                title_of_article,
                search_title
        );
    }
}
