package calvin_klein_test.service;

import calvin_klein_test.model.User;

public class UserCreator {
    public static final String TESTDATA_USER_EMAIL = "testdata.user.email";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_EMAIL), TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }
}
