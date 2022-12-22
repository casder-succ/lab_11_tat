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

public class CalvinKleinCathalogPage extends AbstractPage {
    protected final Logger logger = LogManager.getRootLogger();
    private final String productLink;

    public CalvinKleinCathalogPage(WebDriver driver, String cathalog) {
        super(driver);

        this.productLink = "https://www.calvinklein.co.uk/" + cathalog;
    }

    @Step("Open product page")
    public CalvinKleinCathalogPage openPage() {
        driver.get(this.productLink);

        logger.info("Product page[" + this.productLink + "] was opened");

        return this;
    }

    @Step("Accept cookies")
    public CalvinKleinCathalogPage acceptCookies() {
        By acceptCookiesButtonLocator = By.xpath("//button[@class='ck-Button ck-Button__primary cookie-notice__agree-button']");
        By loaderLocator = By.xpath("//div[@class='ck-loader ']");

        try {
            WebElement acceptCookiesButton = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));
            acceptCookiesButton.click();

            (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));

            logger.info("Accepted cookies on product page");
        } catch (Exception e) {
            logger.info("Cookies modal didn't appear");
            return this;
        }

        return this;
    }

    @Step("Signed in with {testUser.email} and {testUser.password}")
    public CalvinKleinCathalogPage signIn(User testUser) {
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

    @Step("Get empty search view")
    public String getEmptySearchView() {
        By emptySearchLocator = By.xpath("//h1[@class='NoSearchResults__title']");

        WebElement emptySearch = (new WebDriverWait(driver, Duration.ofSeconds(30))).until(ExpectedConditions.visibilityOfElementLocated(emptySearchLocator));

        return emptySearch.getText().substring(0, 32);
    }
}
