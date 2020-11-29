package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_Of_Folder = "Learning programming";
    private static final String login = "Azolayza";
    private static final String password = "azolayza8601";
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
            ArticlePageObject.closeArticle();

//------- Save 2d article
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("JAVA");
            SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.callOptionAddToMyList();
            MyListsPageObject.clickToMyList();
            ArticlePageObject.closeArticle();
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
                    "Java (disambiguation)",
                    article_title2
            );
            //for iOS
        } else if (Platform.getInstance().isiOS())
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
            //check saved article
            SearchPageObject.waitForSearchResult("Java (disambiguation)");
        } else
            //for MW
        //----------Save 1 article for MW
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }
        //----------Save 2 article for MW
        SearchPageObject.initSearchInput();
        SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMySaved();
        //----------Delete and check
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.savedListResult();
    }
}
