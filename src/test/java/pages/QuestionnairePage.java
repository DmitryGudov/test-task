package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import managers.ScreenshotManager;

import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class QuestionnairePage {

    private SelenideElement dataEmailInput = $x("//input[@id='dataEmail']");
    private SelenideElement dataNameInput = $x("//input[@id='dataName']");
    private SelenideElement dataGender = $x("//select[@id='dataGender']");
    private SelenideElement dataEmailBlock = $x("//*[@id='inputsPage']/form/div[2]");
    private SelenideElement dataNameBlock = $x("//*[@id='inputsPage']/form/div[3]");
    private SelenideElement dataGenderBlock = $x("//*[@id='inputsPage']/form/div[4]");
    private SelenideElement dataCheck11 = $x("//input[@id='dataCheck11']");
    private SelenideElement dataCheck12 = $x("//input[@id='dataCheck12']");
    private SelenideElement dataCheck11name = $x("//*[@id='inputsPage']/form/div[5]");
    private SelenideElement dataCheck12name = $x("//*[@id='inputsPage']/form/div[6]");
    private SelenideElement dataSelect21 = $x("//input[@id='dataSelect21']");
    private SelenideElement dataSelect22 = $x("//input[@id='dataSelect22']");
    private SelenideElement dataSelect23 = $x("//input[@id='dataSelect23']");
    private SelenideElement dataSelect21name = $x("//*[@id='inputsPage']/form/div[7]");
    private SelenideElement dataSelect22name = $x("//*[@id='inputsPage']/form/div[8]");
    private SelenideElement dataSelect23name = $x("//*[@id='inputsPage']/form/div[9]");
    private SelenideElement dataSend = $x("//button[@id='dataSend']");
    private SelenideElement emailFormatError = $x("//div[@id='emailFormatError']");
    private SelenideElement nameInputIsEmpty = $x("//div[@id='blankNameError']");
    private SelenideElement errorMessageEmailFormatCloseButton = $x("//div[@id='emailFormatError']//a[@class='uk-alert-close uk-close']");
    private SelenideElement errorMessageBlankNameCloseButton = $x("//div[@id='blankNameError']//a[@class='uk-alert-close uk-close']");
    private SelenideElement modalWindow = $x("/html/body/div[3]/div");
    private SelenideElement okButton = $x("/html/body/div[3]/div/div/div[2]/button");
    private SelenideElement firstRowEmailCell = $x("//table[@id='dataTable']/tbody/tr[1]/td[1]");
    private SelenideElement firstRowNameCell = $x("//table[@id='dataTable']/tbody/tr[1]/td[2]");
    private SelenideElement firstRowGenderCell = $x("//table[@id='dataTable']/tbody/tr[1]/td[3]");
    private SelenideElement firstRowCheck1Cell = $x("//table[@id='dataTable']/tbody/tr[1]/td[4]");
    private SelenideElement firstRowCheck2Cell = $x("//table[@id='dataTable']/tbody/tr[1]/td[5]");

    @Step("Проверить видимость элементов страницы анкеты")
    public boolean questionnairePageElementsAreVisible() {
        boolean result = dataEmailInput.shouldBe(visible).isDisplayed()
                && dataNameInput.shouldBe(visible).isDisplayed()
                && dataGender.shouldBe(visible).isDisplayed()
                && dataSend.shouldBe(visible).isDisplayed();
        ScreenshotManager.attachScreenshot("Элементы страницы анкеты отображаются", dataEmailBlock, dataNameBlock, dataGenderBlock, dataSend);
        return result;
    }

    @Step("Ввести адрес электронной почты: {email}")
    public void enterEmail(String email) {
        dataEmailInput.setValue(email);
        ScreenshotManager.attachScreenshot("Ввести адрес электронной почты", dataEmailBlock);
    }

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        dataNameInput.setValue(name);
        ScreenshotManager.attachScreenshot("Ввести имя", dataNameBlock);
    }

    @Step("Выбрать пол: {gender}")
    public void selectGender(String gender) {
        dataGender.selectOptionByValue(gender);
        ScreenshotManager.attachScreenshot("Выбрать пол", dataGenderBlock);
    }

    @Step("Установить чек-бокс 'dataCheck11'")
    public void checkDataCheck11() {
        dataCheck11.click();
        ScreenshotManager.attachScreenshot("Установить чек-бокс 'dataCheck11'", dataCheck11name);
    }

    @Step("Установить чек-бокс 'dataCheck12'")
    public void checkDataCheck12() {
        dataCheck12.click();
        ScreenshotManager.attachScreenshot("Установить чек-бокс 'dataCheck12'", dataCheck12name);
    }

    @Step("Снять выбор с чек-бокса 'dataCheck11'")
    public void uncheckDataCheck11() {
        if (dataCheck11.isSelected()) {
            dataCheck11.click();
        }
    }

    @Step("Снять выбор с чек-бокса 'dataCheck12'")
    public void uncheckDataCheck12() {
        if (dataCheck12.isSelected()) {
            dataCheck12.click();
        }
    }

    @Step("Рандомно заполнить чек-боксы")
    public void randomlyFillCheckboxes() {
        Random random = new Random();

        boolean selectCheck11 = random.nextBoolean();
        boolean selectCheck12 = random.nextBoolean();

        if (selectCheck11) {
            checkDataCheck11();
        } else {
            uncheckDataCheck11();
        }

        if (selectCheck12) {
            checkDataCheck12();
        } else {
            uncheckDataCheck12();
        }

    }

    @Step("Выбрать радио-кнопку 'dataSelect21'")
    public void checkDataSelect21() {
        dataSelect21.click();
        ScreenshotManager.attachScreenshot("Выбрать радио-кнопку 'dataSelect21'", dataSelect21name);
    }

    @Step("Выбрать радио-кнопку 'dataSelect22'")
    public void checkDataSelect22() {
        dataSelect22.click();
        ScreenshotManager.attachScreenshot("Выбрать радио-кнопку 'dataSelect22'", dataSelect22name);
    }

    @Step("Выбрать радио-кнопку 'dataSelect23'")
    public void checkDataSelect23() {
        dataSelect23.click();
        ScreenshotManager.attachScreenshot("Выбрать радио-кнопку 'dataSelect23'", dataSelect23name);
    }

    @Step("Рандомно выбрать радио-кнопку")
    public void randomlySelectRadioButton() {
        Random random = new Random();

        int radioButtonIndex = random.nextInt(3) + 1;

        switch (radioButtonIndex) {
            case 1:
                checkDataSelect21();
                break;
            case 2:
                checkDataSelect22();
                break;
            case 3:
                checkDataSelect23();
                break;
        }

    }

    @Step("Нажать кнопку 'Добавить'")
    public void clickDataSend() {
        dataSend.click();
        ScreenshotManager.attachScreenshot("Нажать кнопку 'Добавить'", dataSend);
    }

    @Step("Проверить видимость сообщения об ошибке: 'Неверный формат E-mail'")
    public boolean emailFormatErrorIsDisplayed() {
        boolean result = emailFormatError.isDisplayed();
        ScreenshotManager.attachScreenshot("Отображается сообщение об ошибке: 'Неверный формат E-mail'", emailFormatError);
        return result;
    }

    @Step("Проверить видимость сообщения об ошибке: 'Поле имя не может быть пустым'")
    public boolean nameInputIsEmptyIsDisplayed() {
        boolean result = nameInputIsEmpty.isDisplayed();
        ScreenshotManager.attachScreenshot("Отображается сообщение об ошибке: 'Поле имя не может быть пустым'", nameInputIsEmpty);
        return result;
    }

    @Step("Закрыть сообщение об ошибке 'Неверный формат E-mail'")
    public void closeEmailFormatError() {
        ScreenshotManager.attachScreenshot("Закрыть сообщение об ошибке 'Неверный формат E-mail'", errorMessageEmailFormatCloseButton);
        errorMessageEmailFormatCloseButton.click();
    }

    @Step("Закрыть сообщение об ошибке 'Поле имя не может быть пустым'")
    public void closeNameInputIsEmpty() {
        ScreenshotManager.attachScreenshot("Закрыть сообщение об ошибке 'Поле имя не может быть пустым'", errorMessageBlankNameCloseButton);
        errorMessageBlankNameCloseButton.click();
    }

    @Step("Нажать кнопку 'ОК'")
    public void clickOkButton() {
        okButton.click();
        ScreenshotManager.attachScreenshot("Нажать кнопку 'ОК'", okButton);
    }

    @Step("Заполнить анкету")
    public void fillInTheForm(String email, String name, String gender) {
        enterEmail(email);
        enterName(name);
        selectGender(gender);
        randomlyFillCheckboxes();
        randomlySelectRadioButton();
        clickDataSend();
    }

    @Step("Проверить, что модальное окно с текстом: 'Данные добавлены.' отображается")
    public boolean modalWindowIsDisplayed() {
        boolean isDisplayed = modalWindow.isDisplayed();
        boolean containsText = modalWindow.getText().contains("Данные добавлены.");
        boolean result = isDisplayed && containsText;
        ScreenshotManager.attachScreenshot("Отображается модальное окно с текстом: 'Данные обновлены.'", modalWindow);
        return result;
    }

    @Step("Получить значение для чек-боксов 'dataCheck1'")
    public String getCheck1Value() {
        StringBuilder result = new StringBuilder();

        if (dataCheck11.isSelected()) {
            if (result.length() > 0) {
                result.append(", ");
            }
            String value1 = dataCheck11name.getText().replaceAll(".*\\s(\\d+\\.\\d+).*", "$1");
            result.append(value1);
        }

        if (dataCheck12.isSelected()) {
            if (result.length() > 0) {
                result.append(", ");
            }
            String value2 = dataCheck12name.getText().replaceAll(".*\\s(\\d+\\.\\d+).*", "$1");
            result.append(value2);
        }

        return result.toString();
    }

    @Step("Получить значение для радио-кнопок 'dataSelect2'")
    public String getCheck2Value() {
        if (dataSelect21.isSelected()) {
            return dataSelect21name.getText().replaceAll(".*\\s(\\d+\\.\\d+).*", "$1");
        } else if (dataSelect22.isSelected()) {
            return dataSelect22name.getText().replaceAll(".*\\s(\\d+\\.\\d+).*", "$1");
        } else if (dataSelect23.isSelected()) {
            return dataSelect23name.getText().replaceAll(".*\\s(\\d+\\.\\d+).*", "$1");
        } else {
            return "";
        }
    }

    @Step("Проверить, что данные в таблице: {email} {name} {gender} {check1} {check2} отображаются")
    public boolean checkContainsInTable(String email, String name, String gender, String check1, String check2) {
        boolean containsTextEmail = firstRowEmailCell.getText().contains(email);
        boolean containsTextName = firstRowNameCell.getText().contains(name);
        boolean containsTextGender = firstRowGenderCell.getText().contains(gender);
        boolean containsTextCheck1 = firstRowCheck1Cell.getText().contains(check1);
        boolean containsTextCheck2 = firstRowCheck2Cell.getText().contains(check2);
        boolean result = containsTextEmail && containsTextName && containsTextGender && containsTextCheck1 && containsTextCheck2;
        ScreenshotManager.attachScreenshot("Таблица содержит данные: " + email + " " + name + " " + gender + " " + check1 + " " + check2, firstRowEmailCell, firstRowNameCell, firstRowGenderCell, firstRowCheck1Cell, firstRowCheck2Cell);
        return result;
    }

}
