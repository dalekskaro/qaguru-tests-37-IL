## Урок 10: Allure Reports
1. Практика
– Подключение Allure Reports и настройка отчетности о прохождении автотестов  
– Построение более информативной отчетности c вложениями:  
– прикладываем скриншоты, файлы, логи и другие вложения в отчет  

2. Теория. Обзор возможностей Allure Reports

### Домашнее задание для урока 10:
Написать тест на проверку названия Issue в репозитории через Web-интерфейс.  
Этот тест представить в трех вариантах:  
1. Чистый Selenide (с Listener)
2. Лямбда шаги через step (name, () -> {})
3. Шаги с аннотацией @Step

#### Подключение:
```groovy
plugins {
    // https://github.com/allure-framework/allure-gradle
    id 'io.qameta.allure' version '3.0.0'
}
```
```groovy
allure {
    report {
        // https://github.com/allure-framework/allure2
        version.set("2.35.1") //версия Allure Report
    }
    adapter { // Отвечает за появление папки build/allure-results
        aspectjWeaver.set(true) // обработка аннотации @Step
        frameworks {
            junit5 { // Название фреймворка
                // https://github.com/allure-framework/allure-java
                adapterVersion.set("2.29.1") //версия Allure JUnit5 - версия интеграции фреймворка и allure
            }
        }
    }
}
```
После этого запускаешь тесты и в папке build появится папка allure-results  
Чтобы посмотреть отчет нужна команда `./gradlew allureServe` либо можно посмотреть через Gradle -> Task -> verification -> allureServe
#### Интеграция с Selenide
```groovy
dependencies {
    // https://mvnrepository.com/artifact/io.qameta.allure/allure-selenide
    testImplementation 'io.qameta.allure:allure-selenide:2.29.1'
}
```
В класс теста:
```java
SelenideLogger.addListener("allure", new AllureSelenide());
```
#### Лямбда-шаги:
Можем использовать лямбду, когда у всего классе есть только один метод. Ведь в таком случае будет сразу понятно, что вызывается
```java
step("описание шага", () -> {
//сами шаги
});
```
- `()` - тело метода
- `->` то что передается в метод  
Как выглядел бы метод без лямбды:
```java
step("описание шага", new Allure.ThrowableContextRunnableVoid<Allure.StepContext>() {
  @Override
      public void run(Allure.StepContext context) throw Throwable {
    //сами шаги
  }
});
```