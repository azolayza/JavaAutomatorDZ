package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome()
    {
           if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMv())){
               return;
           }
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

            WelcomePageObject.waitForLearMoreLink();
            WelcomePageObject.clickNextButton();

            WelcomePageObject.waitForNewWayToExplore();
            WelcomePageObject.clickNextButton();

            WelcomePageObject.waitForAddOrEditPreferredLangText();
            WelcomePageObject.clickNextButton();

            WelcomePageObject.waitForLearnMoreAboutDataCollected();
            WelcomePageObject.clickGetStarted();
        }
}
