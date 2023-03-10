package calvin_klein_test.page;

import calvin_klein_test.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalvinKleinWishlistPage extends AbstractPage {
    public CalvinKleinWishlistPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open wishlist page")
    public CalvinKleinWishlistPage openPage() {
        String WISHLIST_PAGE_URL = "https://www.calvinklein.co.uk/wishlist";

        driver.get(WISHLIST_PAGE_URL);

        logger.info("Wishlist page[" + WISHLIST_PAGE_URL + "] was opened");

        return this;
    }

    public String getEmptyViewText() {
        By emptyViewLocator = By.xpath("//span[@class='IconWithText_text__MFBOf Typography_Typography__jHH_m ck-alias_Typography__u21R5 Typography_bodyMedium__g_5G8']");

        WebElement emptyViewWrapper = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT)))
                .until(ExpectedConditions.presenceOfElementLocated(emptyViewLocator));
        String emptyStateText = emptyViewWrapper.getText();

        logger.info("Got empty state text for bag page: " + emptyStateText);

        return emptyStateText;
    }

    public CalvinKleinWishlistPage acceptCookies() {
        By acceptCookiesButtonLocator = By.xpath("//button[@class='CookieNotice_CookieNoticeButtons__tTjYU Button_Button__xGhUc Button_large__W1whq']");

        WebElement acceptCookiesButton = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));
        acceptCookiesButton.click();

        logger.info("Accepted cookies");

        return this;
    }

    public CalvinKleinWishlistPage signIn(User testUser) {
        By registerButtonLocator = By.xpath("//button[@class='Link_Link__H_TIr Link_plain__4f0jV Link_medium__lHKjM HeaderAccount_SignInRegisterText__GVQzk Typography_Typography__jHH_m ck-alias_Typography__u21R5 Typography_bodyMedium__g_5G8']");
        By loginFieldLocator = By.id("email-login-form");
        By passwordFieldLocator = By.id("password-login-form");
        By signInButtonLocator = By.xpath("//button[@class='LoginForm_loginButton__jCpWf Button_Button__xGhUc Button_success__zV2Ri Button_fullwidth__SN3JB Button_large__W1whq Button_withicon__Cm3xG']");

        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(registerButtonLocator)).click();
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(loginFieldLocator)).sendKeys(testUser.getEmail());
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator)).sendKeys(testUser.getPassword());
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(signInButtonLocator)).click();

        logger.info("Signed in with email: [" + testUser.getEmail() + "] and password: [" + testUser.getPassword() + "]");

        return this;
    }

    public CalvinKleinWishlistPage reload() {
        driver.navigate().refresh();

        logger.info("Page was reloaded");

        return this;
    }

}
