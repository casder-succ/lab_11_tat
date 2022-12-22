package calvin_klein_test.test;

import calvin_klein_test.model.User;
import calvin_klein_test.page.CalvinKleinWishlistPage;
import calvin_klein_test.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinWishlistPageTest extends CommonConditions {
    @Test(description = "Test empty wishlist")
    void testEmptyWishlist() {
        User testUser = UserCreator.withCredentialsFromProperty();

        String emptyView = "Looks like your wishlist is empty";

        String emptyViewText = new CalvinKleinWishlistPage(driver)
                .openPage()
                .acceptCookies()
                .signIn(testUser)
                .reload()
                .getEmptyViewText();

        Assert.assertEquals(emptyViewText, emptyView);
    }
}
