package tests;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Feature;
import managers.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.QuestionnairePage;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.sleep;

public class QuestionnaireTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();
    private QuestionnairePage questionnairePage = new QuestionnairePage();

    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage.loginViaTheButton(configManager.getProperty("email"), configManager.getProperty("password"));
    }

    @Test
    @DisplayName("Отображение элементов страницы анкеты")
    @Feature("Questionnaire")
    public void shouldBeVisibleElementsOnTheQuestionnairePage() {
        assertTrue(questionnairePage.questionnairePageElementsAreVisible());
    }

    @Test
    @DisplayName("Заполнение анкеты валидными значениями")
    @Feature("Questionnaire")
    public void shouldBeFillTheQuestionnaireWithValidData() {
        String email = DataHelper.getRandomEmailWithLocale();
        String name = DataHelper.getRandomNameWithLocale();
        String gender = DataHelper.getRandomRusGender();
        questionnairePage.fillInTheForm(email, name, gender);
        String check1 = questionnairePage.getCheck1Value();
        String check2 = questionnairePage.getCheck2Value();
        assertTrue(questionnairePage.modalWindowIsDisplayed());
        questionnairePage.clickOkButton();
        assertTrue(questionnairePage.checkContainsInTable(email, name, gender, check1, check2));
    }

    @Test
    @DisplayName("Отправка пустой анкеты")
    @Feature("Questionnaire")
    public void shouldBeSendEmptyQuestionnaire() {
        questionnairePage.clickDataSend();
        assertTrue(questionnairePage.emailFormatErrorIsDisplayed());
    }

    @Test
    @DisplayName("Отправка анкеты только с полем E-Mail")
    @Feature("Questionnaire")
    public void shouldBeSendQuestionnaireWithoutName() {
        String email = DataHelper.getRandomEmailWithLocale();
        questionnairePage.enterEmail(email);
        questionnairePage.clickDataSend();
        assertTrue(questionnairePage.nameInputIsEmptyIsDisplayed());
    }

    @Test
    @DisplayName("Отправка анкеты только с полем Имя")
    @Feature("Questionnaire")
    public void shouldBeSendQuestionnaireWithoutEmail() {
        String name = DataHelper.getRandomNameWithLocale();
        questionnairePage.enterName(name);
        questionnairePage.clickDataSend();
        assertTrue(questionnairePage.emailFormatErrorIsDisplayed());
    }

    @Test
    @DisplayName("Закрытие окна с сообщением об ошибке 'Неверный формат E-mail'")
    @Feature("Questionnaire")
    public void shouldBeCloseErrorMessage() {
        questionnairePage.clickDataSend();
        questionnairePage.closeEmailFormatError();
        sleep(200);
        assertThrows(ElementNotFound.class, () -> questionnairePage.emailFormatErrorIsDisplayed());
    }

    @Test
    @DisplayName("Закрытие окна с сообщением об ошибке 'Поле имя не может быть пустым'")
    @Feature("Questionnaire")
    public void shouldBeCloseNameInputIsEmptyErrorMessage() {
        questionnairePage.enterEmail(DataHelper.getRandomEmailWithLocale());
        questionnairePage.clickDataSend();
        questionnairePage.closeNameInputIsEmpty();
        sleep(200);
        assertThrows(ElementNotFound.class, () -> questionnairePage.nameInputIsEmptyIsDisplayed());
    }

}
