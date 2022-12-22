package calvin_klein_test.page;

import calvin_klein_test.model.User;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT = 30;

    protected abstract AbstractPage openPage();
    protected abstract AbstractPage acceptCookies();
    protected abstract AbstractPage signIn(User testUser);

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

}
