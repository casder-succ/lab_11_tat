package calvin_klein_test.test;

import calvin_klein_test.page.CalvinKleinBagPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinBagPageTest extends CommonConditions {
    @Test(description = "Test empty bag page")
    void testEmptyBagPage() {
        String emptyState = "Your shopping bag is empty";
        String emptyStateText = new CalvinKleinBagPage(driver)
                .openPage()
                .acceptCookies()
                .closeAdsModal()
                .signIn()
                .reload()
                .getEmptyStateText();

        Assert.assertEquals(emptyStateText, emptyState);
    }

    @Test(description = "Test recommendations on empty bag page")
    void testRecommendationsOnEmptyBagPage() {
        boolean isRecommendationListShown = new CalvinKleinBagPage(driver)
                .openPage()
                .acceptCookies()
                .closeAdsModal()
                .signIn()
                .reload()
                .isRecommendationsListShown();

        Assert.assertTrue(isRecommendationListShown);
    }
}
