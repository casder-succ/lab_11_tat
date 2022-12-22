package calvin_klein_test.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverSingleton {
    private static final Map<Long, WebDriver> drivers = new HashMap<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        long threadId = Thread.currentThread().getId();

        if (!drivers.containsKey(threadId)) {
            String browser = System.getProperty("browser") == null ? "" : System.getProperty("browser");

            switch (browser) {
                case "chrome": {
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--no-sandbox");

                    drivers.put(threadId, new ChromeDriver());
                }
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    firefoxBinary.addCommandLineOptions("--headless");
                    firefoxBinary.addCommandLineOptions("--no-sandbox");

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(firefoxBinary);

                    drivers.put(threadId, new FirefoxDriver(firefoxOptions));
                }
                default: {
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    firefoxBinary.addCommandLineOptions("--headless");
                    firefoxBinary.addCommandLineOptions("--no-sandbox");

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(firefoxBinary);

                    drivers.put(threadId, new FirefoxDriver(firefoxOptions));
                }
            }
        }

        return drivers.get(threadId);
    }

    public static void closeDriver() {
        long threadId = Thread.currentThread().getId();
        if (drivers.containsKey(threadId)) {
            drivers.get(threadId).quit();
            drivers.remove(threadId);
        }
    }
}
