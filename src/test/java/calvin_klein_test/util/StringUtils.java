package calvin_klein_test.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
    public static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");

        return ZonedDateTime.now().format(formatter);
    }
}
