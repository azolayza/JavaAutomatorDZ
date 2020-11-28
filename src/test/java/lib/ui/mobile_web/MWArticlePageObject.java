package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        OPTIONS_ADD_TO_MY_LIST = "css:#page-action li#ca-watch button";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
