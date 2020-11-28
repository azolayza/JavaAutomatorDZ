package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase{
    @Test
    public void testAssertTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "JAVA";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("object-oriented programming language");
        String search_title = "Java (programming language)";
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_of_article = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We check title for article",
                title_of_article,
                search_title
        );
    }
}
