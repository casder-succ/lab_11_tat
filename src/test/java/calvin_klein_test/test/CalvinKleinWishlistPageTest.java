package calvin_klein_test.test;

import calvin_klein_test.model.User;
import calvin_klein_test.page.CalvinKleinWishlistPage;
import calvin_klein_test.service.TestDataReader;
import calvin_klein_test.service.UserCreator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinWishlistPageTest extends CommonConditions {
    @Test(description = "Test empty wishlist")
    @DisplayName("Test empty wishlist")
    @Description("Tests if empty wishlist displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    void testEmptyWishlist() {
        User testUser = UserCreator.withCredentialsFromProperty();

        String emptyView = TestDataReader.getTestData("testdata.pages.wishlist.emptystate");

        String emptyViewText = new CalvinKleinWishlistPage(driver)
                .openPage()
                .acceptCookies()
                .signIn(testUser)
                .reload()
                .getEmptyViewText();

        Assert.assertEquals(emptyViewText, emptyView);
    }
}
