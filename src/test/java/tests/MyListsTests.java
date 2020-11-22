package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_Of_Folder = "Learning programming";
    @Test
    public void testSaveTwoArticlesToMyList(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JAVA");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
//----------Save 1 article
            ArticlePageObject.callOptionAddToMyList();
            ArticlePageObject.tapGotItOverlay();
            ArticlePageObject.createNewMyList(name_Of_Folder);
            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            MyListsPageObject.clickOkAfterCreateMyList();
            ArticlePageObject.closeArticleButton();

            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();

//------- Save 2d article
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("JAVA");
            SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.callOptionAddToMyList();
            MyListsPageObject.clickToMyList();
            ArticlePageObject.closeArticleButton();
//------- Delete 1 article and check title of 2d
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();
            MyListsPageObject.clickToMyList();
            MyListsPageObject.swipeByArticleToDelete(article_title);
 //check saved article
            SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
            ArticlePageObject.waitForTitleElement();
            String article_title2 = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title2
            );
        } else //for iOS
        {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.tapSavedListOverlay();
            ArticlePageObject.closeArticle();

            SearchPageObject.initSearchInput();
            SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            MyListsPageObject.swipeByArticleToDelete(article_title);

        }
//check saved article 
        SearchPageObject.waitForSearchResult("Java (disambiguation)");
    }
}
