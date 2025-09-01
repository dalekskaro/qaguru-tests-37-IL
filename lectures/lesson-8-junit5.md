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
**@CsvFileSource - задаем значения в файле**
```java
@CsvFileSource(resources = "путь до ресурса")
@ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink)
```
**@EnumSource - из enum значений**
```java
enum Direction {
    EAST, WEST, NORTH, SOUTH
}

@ParameterizedTest
@EnumSource(Direction.class)
void testWithEnumSource(Direction d) {
    assertNotNull(d);
}
```
**@MethodSource - ссылка на метод**
- Она используется для ссылки на один или несколько фабричных методов тестового класса или внешних классов. Фабричный метод должен генерировать поток аргументов, где каждый аргумент в потоке будет использоваться методом, аннотированным @ParameterizedTest.
- Фабричный метод должен быть static, если тестовый класс не аннотирован с помощью @TestInstance(Lifecycle.PER_CLASS).
- Кроме того, фабричный метод не должен принимать аргументы.
```java
@ParameterizedTest
@MethodSource("argsProviderFactory")
void testWithMethodSource(String argument) {
    assertNotNull(argument);
}

static Stream<String> argsProviderFactory() {
    return Stream.of("alex", "brian");
}
```
**@ArgumentsSource - многоразовый поставщик аргументов**
Аннотацию @ArgumentsSource можно использовать для указания настраиваемого многоразового поставщика аргументов ArgumentsProvider.
```java
@ParameterizedTest(name = "{index} - {0} is older than 40")
@ArgumentsSource(EmployeesArgumentsProvider.class)
void isEmployeeAgeGreaterThan40(Employee e) {
    assertTrue(Period.between(e.getDob(), LocalDate.now()).get(ChronoUnit.YEARS) > 40);
}

class EmployeesArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new Employee(1, "Alex", LocalDate.of(1980, 2, 3))),
                Arguments.of(new Employee(2, "Brian", LocalDate.of(1979, 2, 3))),
                Arguments.of(new Employee(3, "Charles", LocalDate.of(1978, 2, 3)))
        );
    }
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