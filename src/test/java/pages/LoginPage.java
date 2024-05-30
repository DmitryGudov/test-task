package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import managers.ScreenshotManager;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginEmailInput = $x("//input[@id='loginEmail']");
    private SelenideElement loginPasswordInput = $x("//input[@id='loginPassword']");
    private SelenideElement loginEmailBlock = $x("//*[@id='authPage']/form/div[2]");
    private SelenideElement loginPasswordBlock = $x("//*[@id='authPage']/form/div[3]");
    private SelenideElement authButton = $x("//button[@id='authButton']");
    private SelenideElement emailFormatError = $x("//div[@id='emailFormatError']");
    private SelenideElement invalidEmailOrPasswordError = $x("//div[@id='invalidEmailPassword']");
    private SelenideElement errorMessageCloseButton = $x("//div[@id='authAlertsHolder']//a[@class='uk-alert-close uk-close']");

    @Step("Проверить видимость элементов страницы входа")
    public boolean loginPageElementsAreVisible() {
        boolean result = loginEmailInput.shouldBe(visible).isDisplayed()
                && loginPasswordInput.shouldBe(visible).isDisplayed()
                && authButton.shouldBe(visible).isDisplayed();
        ScreenshotManager.attachScreenshot("Элементы страницы входа отображаются", loginEmailBlock, loginPasswordBlock, authButton);
        return result;
    }

    @Step("Ввести адрес электронной почты: {email}")
    public void enterEmail(String email) {
        loginEmailInput.setValue(email);
        ScreenshotManager.attachScreenshot("Ввести адрес электронной почты", loginEmailBlock);
    }

    @Step("Ввести пароль: {password}")
    public void enterPassword(String password) {
        loginPasswordInput.setValue(password);
        ScreenshotManager.attachScreenshot("Ввести пароль", loginPasswordBlock);
    }

    @Step("Нажать кнопку 'Вход'")
    public void clickAuthButton() {
        ScreenshotManager.attachScreenshot("Нажать кнопку 'Вход'", authButton);
        authButton.click();
    }

    @Step("Войти с помощью кнопки 'Вход' с электронной почтой: {email} и паролем: {password}")
    public void loginViaTheButton(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickAuthButton();
    }

    @Step("Войти с помощью клавиши 'Enter' с электронной почтой: {email} и паролем: {password}")
    public void loginViaTheEnterKey(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        loginPasswordInput.pressEnter();
    }

    @Step("Проверить видимость сообщения об ошибке: 'Неверный E-mail или пароль'")
    public boolean invalidEmailOrPasswordErrorIsVisible() {
        boolean result = invalidEmailOrPasswordError.shouldBe(visible).isDisplayed();
        ScreenshotManager.attachScreenshot("Отображается сообщение об ошибке: 'Неверный E-mail или пароль'", invalidEmailOrPasswordError);
        return result;
    }

    @Step("Проверить видимость сообщения об ошибке: 'Неверный формат E-mail'")
    public boolean emailFormatErrorIsVisible() {
        boolean result = emailFormatError.shouldBe(visible).isDisplayed();
        ScreenshotManager.attachScreenshot("Отображается сообщение об ошибке: 'Неверный формат E-mail'", emailFormatError);
        return result;
    }

    @Step("Закрыть сообщение об ошибке")
    public void closeErrorMessage() {
        ScreenshotManager.attachScreenshot("Закрыть сообщение об ошибке", errorMessageCloseButton);
        errorMessageCloseButton.click();
    }

}
