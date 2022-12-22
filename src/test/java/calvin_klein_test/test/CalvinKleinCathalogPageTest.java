package calvin_klein_test.test;

import calvin_klein_test.page.CalvinKleinCathalogPage;
import calvin_klein_test.service.TestDataReader;
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
}
