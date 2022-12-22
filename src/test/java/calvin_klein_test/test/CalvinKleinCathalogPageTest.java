package calvin_klein_test.test;

import calvin_klein_test.model.User;
import calvin_klein_test.page.CalvinKleinCathalogPage;
import calvin_klein_test.service.TestDataReader;
import calvin_klein_test.service.UserCreator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalvinKleinCathalogPageTest extends CommonConditions{
    @Test(description = "Test empty search result")
    @DisplayName("Test empty search result")
    @Description("Tests if empty view text displayed on incorrect enter")
    @Severity(SeverityLevel.NORMAL)
    void testEmptySearchResult() {
        String catalogName = TestDataReader.getTestData("testdata.catalog.page");
        String emptyView = "Sorry, we can’t find a match for";

        String emptyViewText = new CalvinKleinCathalogPage(driver, catalogName)
                .openPage()
                .acceptCookies()
                .getEmptySearchView();

        Assert.assertEquals(emptyViewText, emptyView);
    }

    @Test(description = "Test sign in validation")
    @DisplayName("Test sign in validation")
    @Description("Tests if sign in form validated correctly")
    @Severity(SeverityLevel.BLOCKER)
    void testSignInValidation() {
        String wrongEmail = TestDataReader.getTestData("testdata.user.wrongemail");
        String wrongEmailError = TestDataReader.getTestData("testdata.validation.wrongemail");

        User testUser = UserCreator.withCustomEmail(wrongEmail);

        String wrongEmailResult = new CalvinKleinCathalogPage(driver, "men")
                .openPage()
                .acceptCookies()
                .signIn(testUser)
                .getValidationError();

        Assert.assertEquals(wrongEmailResult, wrongEmailError);
    }
}
