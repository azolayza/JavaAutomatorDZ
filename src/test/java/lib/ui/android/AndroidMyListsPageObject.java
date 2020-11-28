package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        OK_BUTTON_MY_LIST = "xpath://*[@text='OK']";
        MY_LIST_XPATH = "xpath://*[@text='Learning programming']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }
    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
