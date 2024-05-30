package managers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;

public class ScreenshotManager {

    public static void attachScreenshot(String name) {
        byte[] screenshotBytes = Selenide.screenshot(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshotBytes));
    }

    public static void attachScreenshot(String name, WebElement... elements) {
        for (WebElement element : elements) {
            highlightElement(element, true);
        }

        byte[] screenshotBytes = Selenide.screenshot(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshotBytes));

        for (WebElement element : elements) {
            highlightElement(element, false);
        }
    }

    private static void highlightElement(WebElement element, boolean highlight) {
        String color = highlight ? "5px solid red" : "";
        Selenide.executeJavaScript("arguments[0].style.border='" + color + "'", element);
    }

}
