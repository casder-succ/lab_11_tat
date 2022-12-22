package calvin_klein_test.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;

public class DriverSingleton {
    public static WebDriver driver;
    private static List<Long> threadIds = new ArrayList<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null && !threadIds.contains(Thread.currentThread().getId())) {
            String browser = System.getProperty("browser") == null ? "" : System.getProperty("browser");

            threadIds.add(Thread.currentThread().getId());

            switch (browser) {
                case "chrome": {
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--no-sandbox");

                    driver = new ChromeDriver();
                }
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    firefoxBinary.addCommandLineOptions("--headless");
                    firefoxBinary.addCommandLineOptions("--no-sandbox");

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(firefoxBinary);

                    driver = new FirefoxDriver(firefoxOptions);
                }
                default: {
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    firefoxBinary.addCommandLineOptions("--headless");
                    firefoxBinary.addCommandLineOptions("--no-sandbox");

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(firefoxBinary);

                    driver = new FirefoxDriver(firefoxOptions);
                }
            }
        }

        return driver;
    }

    public static void closeDriver() {
        if (threadIds.contains(Thread.currentThread().getId())){
            driver.quit();
            driver = null;
            threadIds.remove(Thread.currentThread().getId());
        }
    }
}
