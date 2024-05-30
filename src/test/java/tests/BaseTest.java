package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import managers.ConfigManager;
import managers.ScreenshotManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public abstract class BaseTest {

    protected ConfigManager configManager = new ConfigManager();

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        String htmlFilePath = Paths.get("src/test/resources/qa-test.html").toUri().toString();
        Selenide.open(htmlFilePath);
        ScreenshotManager.attachScreenshot("Открыть страницу авторизации");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
