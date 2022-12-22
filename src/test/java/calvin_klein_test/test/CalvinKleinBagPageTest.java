package calvin_klein_test.test;

import calvin_klein_test.model.User;
import calvin_klein_test.page.CalvinKleinBagPage;
import calvin_klein_test.service.TestDataReader;
import calvin_klein_test.service.UserCreator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinBagPageTest extends CommonConditions {
    @Test(description = "Test empty bag page")
    @DisplayName("Test empty bag page")
    @Description("Tests if empty state of bag page displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    void testEmptyBagPage() {
        User testUser = UserCreator.withCredentialsFromProperty();

        String emptyState = TestDataReader.getTestData("testdata.pages.bag.emptystate");
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
    @DisplayName("Test recommendations on empty bag page")
    @Description("Tests if empty bag page contains recommendations list")
    @Severity(SeverityLevel.NORMAL)
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
