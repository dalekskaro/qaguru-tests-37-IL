## Урок 8: 
[8. JUnit 5. Дмитрий Тучс](https://school.qa.guru/pl/teach/control/lesson/view?id=343208848&editMode=0)
1. Аннотация @Test
2. Что из себя представляют аннотации в Java
3. Пишем свою простейшую реализацию JUnit5
4. @Disabled , @DisplayName
5. Тест кейс – что такое и из чего состоит
6. Параметризованные тесты:  
– концепция  
– sources (дата-провайдеры)  
– нэйминг

### Аннотации перед тестом:
**@DisplayName**
```java
@DisplayName("Наименование теста")
```
**@Disabled**
```java
@Disabled("Почему тест падает")
```
**@Tag - над тестом и над классом можно**
```java
@Tag("UI") @Tag("API") @Tag("SMOKE")
```
**@TestMethodOrder - BadPractice - если нужна очередь тестов**
```java
@TestMethodOrder(MethodOrder.OrderAnnotaion.class) // над классом
@Order(1) // над тестом
```
### Параметризация
**@ValueSource**
```java
@ValueSource(strings = {"первый параметр", "второй параметр", "третий параметр"})
@ParameterizedTest(name = "Название теста, где {0} это параметр")
void testName(Strimg value) //сюда подтянится значение параметра
```
**@CsvSource - задаем значения над тестом**
```java
@CsvSource(value = {
"Selenide , https://selenide.org",
"JUnit 5 , https://junit.org"
})
@ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink)
```
**@CsvFileSourc - задчем значения в файле**
```java
@CsvFileSource(resources = "путь до ресурса")
@ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink)
```

### Enum
```java
public enum Language {
RU("ЧТО ТАКОЕ SELENIDE?"),
EN("WHAT IS SELENIDE?");

public final String description;

Language(String description) {
this.description = description;
}
```
### Дополнительно:
Информация взята из статьи [Gradle для тестировщика](https://software-testing.ru/library/testing/testing-tools/4079-gradle-)
Повысить уровень логирования можно добавить строки ниже и `build.gradle`
```groovy
test {
    testLogging {
        events "passed", "skipped", "failed", "standardOut", "standardError"
        exceptionFormat "full"
    }
}
```

### Домашнее задание для урока 8:
1. Написать свои варианты параметризованных wеб-тестов (не на поиск в янексе или гугле)   
1.1. Попробовать разные варианты датапровайдеров (аннотаций), минимум 3
1.2. Научится запускать ат через гредл с определенным тегом
2. Если вы новичок в тесткейсах, то к каждому тесту написать отдельные тексткейсы в текстовых файлах

#### Как запускать тесты через Gradle с определенным тегом
1. Ставишь над тестом тег `@Tag("regress")`
2. Добавляешь в конец build.gradle следующее:
```groovy
// Задача для запуска тестов с тегом "regress"
tasks.register('regress', Test) {
    testLogging {
        events "passed", "skipped", "failed", "standardOut", "standardError"
        exceptionFormat "full"
    }
    useJUnitPlatform {
        includeTags("regress")
    }
}
```
3. Запускаешь командой
```bash
./gradlew regress
```