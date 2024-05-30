# О проекте
- В данном проекте реализована автоматизация основных тестовых сценариев для страницы авторизации и страницы анкеты веб-приложения. </br>
- С перечнем тестовых сценариев можно ознакомиться по ссылке: ...


# Окружение

- **Java Development Kit (JDK):** 11
- **Maven:** 3.x
- **Selenide:** 6.19.1
- **JUnit Jupiter:** 5.9.2
- **JavaFaker:** 1.0.2
- **Allure:** 2.10.0

# Процедура запуска автотестов

## Шаг 1

Открыть терминал с помощью сочетания клавиш `Ctrl + Alt + T` и перейти в директорию, куда будет клонироваться проект.

## Шаг 2

Необходимо склонировать удаленный репозиторий на свой ПК с помощью следующей команды:

```
git clone git@github.com:DmitryGudov/test-task.git
```

## Шаг 3

Открыть склонированный проект в **IntelliJ IDEA**.

## Шаг 4

Запустить в терминале проекта команду для запуска автотестов и генерации отчета

```
mvn clean test && mvn allure:report
```

## Шаг 5

После завершения прогона перейти в директорию
`.../test-task/target/site/allure-maven-plugin`
и открыть в браузере `Chrome` файл `index.html`.

# Отчет


