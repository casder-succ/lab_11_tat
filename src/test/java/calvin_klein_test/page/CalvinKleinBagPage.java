package calvin_klein_test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalvinKleinBagPage extends AbstractPage {
    public CalvinKleinBagPage(WebDriver driver) {
        super(driver);
    }

    public CalvinKleinBagPage openPage() {
        String BAG_PAGE_URL = "https://www.calvinklein.co.uk/shopping-bag";

        driver.get(BAG_PAGE_URL);

        return this;
    }

    public CalvinKleinBagPage signIn() {
        String ACCOUNT_EMAIL = "casderiopus1@gmail.com";
        String ACCOUNT_PASSWORD = "casdercasder";

        By registerButtonLocator = By.xpath("//button[@class='ck-Button__no-style header-account__sign-in']");
        By loginFieldLocator = By.id("logonId");
        By passwordFieldLocator = By.id("logonPassword");
        By signInButtonLocator = By.xpath("//button[@class='ck-Button ck-Button__primary ck-Button--with-icon ck-Button--full-width login-popup__secondary-action-send']");

        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(registerButtonLocator)).click();
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(loginFieldLocator)).sendKeys(ACCOUNT_EMAIL);
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator)).sendKeys(ACCOUNT_PASSWORD);
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(signInButtonLocator)).click();

        return this;
    }

    public CalvinKleinBagPage acceptCookies() {
        By acceptCookiesButtonLocator = By.xpath("//button[@class='ck-Button ck-Button__primary cookie-notice__agree-button']");

        WebElement acceptCookiesButton = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));
        acceptCookiesButton.click();

        return this;
    }

    public CalvinKleinBagPage closeAdsModal() {
        By adsModalLocator = By.xpath("//div[@class='ck-modal__backdrop ck-modal__backdrop--open']");
        By modalCloseButtonLocator = By.xpath("//button[@class='ck-Button__no-style ck-modal__close-btn']");

        try {
            WebElement adsModal =(new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(adsModalLocator));
            adsModal.findElement(modalCloseButtonLocator).click();
        } catch (Exception e) {
            return this;
        }

        return this;
    }

    public CalvinKleinBagPage reload() {
        driver.navigate().refresh();

        return this;
    }

    public boolean isEmptyStateShown() {
        By emptyStateLocator = By.xpath("//p[text()='Your shopping bag is empty']");

        WebElement emptyState = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(emptyStateLocator));

        return emptyState.isDisplayed();
    }

    public boolean isRecommendationsListShown() {
        By recommendationsLocator = By.xpath("//div[@class='recommendations basket']");

        WebElement recommendations = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(recommendationsLocator));

        return recommendations.isDisplayed();
    }
}
