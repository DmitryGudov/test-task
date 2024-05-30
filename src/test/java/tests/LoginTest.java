package tests;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Feature;
import managers.DataHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;
import pages.QuestionnairePage;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private QuestionnairePage questionnairePage = new QuestionnairePage();

    private String validEmail = configManager.getProperty("email");
    private String validPassword = configManager.getProperty("password");

    @Test
    @DisplayName("Отображение элементов страницы авторизации")
    @Feature("Login")
    public void shouldBeVisibleElementsOnTheLoginPage() {
        assertTrue(loginPage.loginPageElementsAreVisible());
    }

    @Test
    @DisplayName("Авторизация с валидными данными через кнопку 'Вход'")
    @Feature("Login")
    public void shouldBeLogInUsingTheLoginButtonWithValidData() {
        loginPage.loginViaTheButton(validEmail, validPassword);
        assertTrue(questionnairePage.questionnairePageElementsAreVisible());
    }

    @Test
    @DisplayName("Авторизация с валидными данными через клавишу 'Enter'")
    @Feature("Login")
    public void shouldBeLogInUsingTheEnterKeyWithValidData() {
        loginPage.loginViaTheEnterKey(validEmail, validPassword);
        assertTrue(questionnairePage.questionnairePageElementsAreVisible());
    }

    @ParameterizedTest(name = "Авторизация с невалидными данными: email={0}, password={1}")
    @CsvFileSource(resources = "/loginCredentials.csv", numLinesToSkip = 1)
    @Feature("Login")
    public void shouldBeLogInWithInvalidData(String email, String password, boolean expected) {
        loginPage.loginViaTheButton(email, password);
        assertEquals(expected, loginPage.invalidEmailOrPasswordErrorIsVisible());
    }

    @ParameterizedTest(name = "Авторизация с пустыми полями: email={0}, password={1}")
    @CsvSource({
            ",test,true",
            ",,true"
    })
    @Feature("Login")
    public void shouldBeLogInWithInvalidEmailFormat(String email, String password, boolean expected) {
        loginPage.loginViaTheButton(email, password);
        assertEquals(expected, loginPage.emailFormatErrorIsVisible());
    }

    @Test
    @DisplayName("Авторизация с валидным E-Mail и случайным паролем")
    @Feature("Login")
    public void shouldBeLogInWithRandomPasswordAndValidEmail() {
        loginPage.loginViaTheButton(validEmail, DataHelper.getRandomPassword());
        assertTrue(loginPage.invalidEmailOrPasswordErrorIsVisible());
    }

    @Test
    @DisplayName("Авторизация со случайным E-Mail и валидным паролем")
    @Feature("Login")
    public void shouldBeLogInWithRandomEmailAndValidPassword() {
        loginPage.loginViaTheButton(DataHelper.getRandomEmail(), validPassword);
        assertTrue(loginPage.invalidEmailOrPasswordErrorIsVisible());
    }

    @Test
    @DisplayName("Авторизация со случайными E-Mail и паролем")
    @Feature("Login")
    public void shouldBeLogInWithRandomEmailAndPassword() {
        loginPage.loginViaTheButton(DataHelper.getRandomEmail(), DataHelper.getRandomPassword());
        assertTrue(loginPage.invalidEmailOrPasswordErrorIsVisible());
    }

    @Test
    @DisplayName("Закрытие окна с сообщением об ошибке 'Неверный формат E-mail'")
    @Feature("Login")
    public void shouldBeCloseErrorMessage() {
        loginPage.clickAuthButton();
        loginPage.closeErrorMessage();
        sleep(200);
        assertThrows(ElementNotFound.class, () -> loginPage.emailFormatErrorIsVisible());
    }

    @Test
    @DisplayName("Закрытие окна с сообщением об ошибке 'Неверный E-mail или пароль'")
    @Feature("Login")
    public void shouldBeCloseInvalidEmailOrPasswordErrorMessage() {
        loginPage.loginViaTheButton(DataHelper.getRandomEmail(), DataHelper.getRandomPassword());
        loginPage.closeErrorMessage();
        sleep(200);
        assertThrows(ElementNotFound.class, () -> loginPage.invalidEmailOrPasswordErrorIsVisible());
    }

}
