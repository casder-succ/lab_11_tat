package calvin_klein_test.page;

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

    public CalvinKleinWishlistPage openPage() {
        String WISHLIST_PAGE_URL = "https://www.calvinklein.co.uk/wishlist";

        driver.get(WISHLIST_PAGE_URL);

        return this;
    }

    public boolean checkIsEmptyViewDisplayed() {
        By emptyViewLocator = By.xpath("//span[text()='Looks like your wishlist is empty']");

        WebElement emptyViewWrapper = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT)))
                .until(ExpectedConditions.presenceOfElementLocated(emptyViewLocator));

        return emptyViewWrapper.isDisplayed();
    }

    public CalvinKleinWishlistPage acceptCookies() {
        By acceptCookiesButtonLocator = By.xpath("//button[@class='CookieNotice_CookieNoticeButtons__tTjYU Button_Button__xGhUc Button_large__W1whq']");

        WebElement acceptCookiesButton = (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));
        acceptCookiesButton.click();

        return this;
    }

    public CalvinKleinWishlistPage signIn() {
        String ACCOUNT_EMAIL = "casderiopus1@gmail.com";
        String ACCOUNT_PASSWORD = "casdercasder";

        By registerButtonLocator = By.xpath("//button[@class='Link_Link__H_TIr Link_plain__4f0jV Link_medium__lHKjM HeaderAccount_SignInRegisterText__GVQzk Typography_Typography__jHH_m ck-alias_Typography__u21R5 Typography_bodyMedium__g_5G8']");
        By loginFieldLocator = By.id("email-login-form");
        By passwordFieldLocator = By.id("password-login-form");
        By signInButtonLocator = By.xpath("//button[@class='LoginForm_loginButton__jCpWf Button_Button__xGhUc Button_success__zV2Ri Button_fullwidth__SN3JB Button_large__W1whq Button_withicon__Cm3xG']");

        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(registerButtonLocator)).click();
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(loginFieldLocator)).sendKeys(ACCOUNT_EMAIL);
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator)).sendKeys(ACCOUNT_PASSWORD);
        (new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT))).until(ExpectedConditions.elementToBeClickable(signInButtonLocator)).click();

        return this;
    }

    public CalvinKleinWishlistPage reload() {
        driver.navigate().refresh();

        return this;
    }

}
