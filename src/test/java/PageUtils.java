import java.io.IOException;

public class PageUtils {

    final static String BASE_URL = "https://jira2.codecool.codecanvas.hu/";
    final static String WEBDRIVER = "webdriver.chrome.driver";
    final static String CHROME_DRIVER_LOCATION = "/usr/bin/chromedriver";
    final static String PROFILE_PAGE = "https://jira2.codecool.codecanvas.hu/secure/ViewProfile.jspa";
    final static String PROJECT_SETTINGS = "https://jira2.codecool.codecanvas.hu/plugins/servlet/project-config/ELP/summary";
    final static String GLASS_URL = "https://jira2.codecool.codecanvas.hu/projects/ELP?selectedItem=com.codecanvas.glass:glass";
    static String USERNAME;
    static String PASSWORD;

    static {
        try {
            USERNAME = ReadLoginProperties.getUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            PASSWORD = ReadLoginProperties.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
