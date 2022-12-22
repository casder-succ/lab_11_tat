package calvin_klein_test.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final String bundleName = System.getProperty("environment") == null ? "qa" : System.getProperty("environment");
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName);

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
