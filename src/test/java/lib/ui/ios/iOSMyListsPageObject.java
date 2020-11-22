package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        OK_BUTTON_MY_LIST = "xpath://*[@text='OK']";
        MY_LIST_XPATH = "xpath://*[@text='Learning programming']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@name='{TITLE}']";
        ARTICLE_BUTTON_DELETE = "id:swipe action delete";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
