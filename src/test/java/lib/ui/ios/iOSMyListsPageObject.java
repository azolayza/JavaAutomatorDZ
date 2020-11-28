package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        OK_BUTTON_MY_LIST = "xpath://*[@text='OK']";
        MY_LIST_XPATH = "xpath://*[@text='Learning programming']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@name='{TITLE}']";
        ARTICLE_BUTTON_DELETE = "id:swipe action delete";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
