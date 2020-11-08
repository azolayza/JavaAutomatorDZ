package tests.iOS;

import lib.iOsCoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOsCoreTestCase {
    @Test
    public void testPassThroughWelcome()
    {
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
