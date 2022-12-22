package calvin_klein_test.test;

import calvin_klein_test.page.CalvinKleinWishlistPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinWishlistPageTest extends CommonConditions {
    @Test(description = "Test empty wishlist")
    void testEmptyWishlist() {
        boolean isEmptyViewDisplayed = new CalvinKleinWishlistPage(driver)
                .openPage()
                .acceptCookies()
                .signIn()
                .reload()
                .checkIsEmptyViewDisplayed();

        Assert.assertTrue(isEmptyViewDisplayed);
    }
}
