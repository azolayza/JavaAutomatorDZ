package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveTwoArticlesToMyList(){
        //save 1 article
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JAVA");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_Of_Folder = "Learning programming";
        ArticlePageObject.callOptionAddToMyList();
        ArticlePageObject.tapGotItOverlay();
        ArticlePageObject.createNewMyList(name_Of_Folder);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.clickOkAfterCreateMyList();
        ArticlePageObject.closeArticleButton();
//------- Save 2d article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JAVA");
        SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.callOptionAddToMyList();
        MyListsPageObject.clickToMyList();
        ArticlePageObject.closeArticleButton();
//------- Delete 1 article and check title of 2d
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject.clickToMyList();
        MyListsPageObject.swipeByArticleToDelete(article_title);
        SearchPageObject.clickByArticleWithSubstring("Java (disambiguation)");
        ArticlePageObject.waitForTitleElement();
        String article_title2 = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title",
                "Java (disambiguation)",
                article_title2
        );
    }
}
