import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestPlan {

    private WebDriver driver;

    LoginPage loginPage;
    MainPage mainPage;
    ProjectSettingsPage projectSettingsPage;
    GlassPage glassPage;

    public TestPlan() {
    }

    @Parameters({"Port"})
    @BeforeClass
    public void initiateDriver(String Port) throws MalformedURLException {
        if (Port.equalsIgnoreCase("9001")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), new ChromeOptions());
        } else if (Port.equalsIgnoreCase("9002")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), new FirefoxOptions());
        }
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        projectSettingsPage = new ProjectSettingsPage(driver);
        glassPage = new GlassPage(driver);
        loginPage.start(PageUtils.BASE_URL);
        loginPage.login();
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);
        mainPage.resetSidebar();
        loginPage.start(PageUtils.BASE_URL);
        loginPage.login();
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);
        projectName = mainPage.getProjectName();
    }

    private String projectName;

    @AfterClass
    public void cleanUp() {
        mainPage.logout();
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test(testName = "Project Link Name")
    public void projectLinkName() {
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);

        mainPage.navigate(PageUtils.GLASS_URL);

        glassPage.verifyHeaderLinkName(projectName);
    }

    @Test(testName = "Project Header Name")
    public void projectHeaderName() {
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);

        mainPage.navigate(PageUtils.GLASS_URL);

        glassPage.verifyHeaderName(projectName);
    }

    @Test(testName = "Project Name In Basic Summary")
    public void projectNameInBasicSummary() {
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);

        mainPage.navigate(PageUtils.GLASS_URL);

        glassPage.verifyProjectName(projectName);
    }

    @Test(testName = "Project Name Info Box")
    public void projectNameInfoBox() {
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);

        mainPage.navigate(PageUtils.GLASS_URL);

        glassPage.verifyInfoBoxProjectName(projectName);
    }

    @Test(testName = "Issue Type Scheme")
    public void issueTypeSchemes() {
        String issueTypeScheme;
        mainPage.navigate(PageUtils.PROJECT_SETTINGS);

        issueTypeScheme = projectSettingsPage.getIssueTypeScheme();

        mainPage.navigate(PageUtils.GLASS_URL);

        glassPage.clickOnSchemeTab();
        glassPage.verifyIssueTypeScheme(issueTypeScheme);
    }
}
