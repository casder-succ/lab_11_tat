package calvin_klein_test.test;

import calvin_klein_test.page.CalvinKleinBagPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalvinKleinBagPageTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    void getDriver() {
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        firefoxBinary.addCommandLineOptions("--no-sandbox");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);

        driver = new FirefoxDriver(firefoxOptions);
    }

    @Test(description = "Test empty bag page")
    void testEmptyBagPage() {
        boolean isEmptyStateDisplayed = new CalvinKleinBagPage(driver)
                .openPage()
                .acceptCookies()
                .closeAdsModal()
                .signIn()
                .reload()
                .isEmptyStateShown();

        Assert.assertTrue(isEmptyStateDisplayed);
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

    @AfterMethod(alwaysRun = true)
    void resetDriver() {
        driver.quit();
        driver = null;
    }
}
