package calvin_klein_test.test;

import calvin_klein_test.model.User;
import calvin_klein_test.page.CalvinKleinBagPage;
import calvin_klein_test.service.TestDataReader;
import calvin_klein_test.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinBagPageTest extends CommonConditions {
    @Test(description = "Test empty bag page")
    void testEmptyBagPage() {
        User testUser = UserCreator.withCredentialsFromProperty();

        String emptyState = TestDataReader.getTestData("testdata.bag.emptystate");
        String emptyStateText = new CalvinKleinBagPage(driver)
                .openPage()
                .acceptCookies()
                .closeAdsModal()
                .signIn(testUser)
                .reload()
                .getEmptyStateText();

        Assert.assertEquals(emptyStateText, emptyState);
    }

    @Test(description = "Test recommendations on empty bag page")
    void testRecommendationsOnEmptyBagPage() {
        User testUser = UserCreator.withCredentialsFromProperty();

        boolean isRecommendationListShown = new CalvinKleinBagPage(driver)
                .openPage()
                .acceptCookies()
                .closeAdsModal()
                .signIn(testUser)
                .reload()
                .isRecommendationsListShown();

        Assert.assertTrue(isRecommendationListShown);
    }
}
