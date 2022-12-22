package calvin_klein_test.page;

import calvin_klein_test.model.User;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalvinKleinBagPage extends AbstractPage {
    protected final Logger logger = LogManager.getRootLogger();

    public CalvinKleinBagPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open bag page")
    public CalvinKleinBagPage openPage() {
        String BAG_PAGE_URL = "https://www.calvinklein.co.uk/shopping-bag";

        driver.get(BAG_PAGE_URL);

        logger.info("Bag page[" + BAG_PAGE_URL + "] was opened");

        return this;
    }

    public CalvinKleinBagPage signIn(User testUser) {
        By registerButtonLocator = By.xpath("//button[@class='ck-Button__no-style header-account__sign-in']");
        By loginFieldLocator = By.id("logonId");
        By passwordFieldLocator = By.id("logonPassword");
        By signInButtonLocator = By.xpath("//button[@class='ck-Button ck-Button__primary ck-Button--with-icon ck-Button--full-width login-popup__secondary-action-send']");

        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(registerButtonLocator)).click();
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(loginFieldLocator)).sendKeys(testUser.getEmail());
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator)).sendKeys(testUser.getPassword());
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(signInButtonLocator)).click();

        logger.info("Signed in with email: [" + testUser.getEmail() + "] and password: [" + testUser.getPassword() + "]");

        return this;
    }

    public CalvinKleinBagPage acceptCookies() {
        By acceptCookiesButtonLocator = By.xpath("//button[@class='ck-Button ck-Button__primary cookie-notice__agree-button']");

        WebElement acceptCookiesButton = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));
        acceptCookiesButton.click();

        logger.info("Accepted cookies");

        return this;
    }

    public CalvinKleinBagPage closeAdsModal() {
        By modalCloseButtonLocator = By.xpath("//button[@class='ck-Button__no-style ck-modal__close-btn']");

        try {
            (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(modalCloseButtonLocator)).click();

            logger.info("Ads modal was closed");
        } catch (Exception e) {
            logger.info("Ads modal didn't appear");
            return this;
        }

        return this;
    }

    public CalvinKleinBagPage reload() {
        driver.navigate().refresh();

        logger.info("Bag page was reloaded");

        return this;
    }

    public String getEmptyStateText() {
        By emptyStateLocator = By.xpath("//p[@class='shoppingBag__empty--message']");

        WebElement emptyState = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(emptyStateLocator));
        String emptyStateText = emptyState.getText();

        logger.info("Got empty state text for bag page: " + emptyStateText);

        return emptyStateText;
    }

    public boolean isRecommendationsListShown() {
        By recommendationsLocator = By.xpath("//div[@class='recommendations basket']");

        WebElement recommendations = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(recommendationsLocator));

        logger.info("Got recommendations list: " + recommendations.isDisplayed());

        return recommendations.isDisplayed();
    }

    @Step("Get lat item in a bag")
    public String getLastItemDescription() {
        By lastItemLocator = By.xpath("//a[@class='csr-link link ProductItem__description']");

        WebElement lastItemDescription = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT)))
                .until(ExpectedConditions.visibilityOfElementLocated(lastItemLocator));

        logger.info("Get last item description: " + lastItemDescription.getText());

        return lastItemDescription.getText();
    }
}
