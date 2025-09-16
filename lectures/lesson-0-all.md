# qaguru-tests-37-IL
* [Урок 2 - Git](#урок-2-git)
* [Урок 3 - Погружение в инструментарий](#урок-3-)
* [Урок 4 - Selenide #1](#урок-4-selenide-1)
* [Урок 5 - Selenide #2](#урок-5-selenide-2)
* [Урок 6 - Основы Java. Часть I](#урок-6-дополнительное-занятие-основы-java-часть-i-дмитрий-тучс)
* [Урок 6 - PageObjects](#урок-6-pageobjects)
* [Урок 7 - Генерация тестовых данных](#урок-7-генерация-тестовых-данных)
* [Урок 8 - Аннотации JUnit5 ](#урок-8-junit-5-аннотации)
* [Урок 9 - Работа с файлами](#урок-9-работа-с-файлами)

---
## Урок 2: Git

Увидеть локальные настройки:
```html
git config --list --local
git config --local user.name
git config --local user.email
```
Задать настройки локально (для одного проекта)
```html
git config --local user.name "__"
git config --local user.email "__"
```
Увидеть глобальные настройки (для всех проектов):
```html
git config --list –global
```
Инициализация проекта
```html
git init
```
Посмотреть, какие проекты добавлены в гит
```html
git status
```
Добавить файл в гит
```html
git add *файл*
```
Добавить все файлы в гит, с учетом .gitignore
```html
git add .
```
Убрать файл из гита
```html
git rm --cached *файл*
```
Сделать коммит, -m - сообщение
```html
git commit -m "сообщение"
```
Изменить последний коммит
```html
git commit --amend -m "Новое название"
```
**Как сделать так, чтобы привязать уже сущствующий репозиторий в GitHub и локальный проект?**
1. Идешь в GitHub и создаешь проект, где Repository name = имени локального проекта
2. GitHub сам даст следующую комадну, она привяжет локальный и remote репозитории
```html
git remote add origin git@github.com:dalekskaro/try_git_qa_guru_37.git
```
Пуш коммита в мастер: (-u = upstream = нужен для самого первого пуша)
```html
git push -u origin master
```
Посмотреть все ветки
```html
git branch
```
Создать новую ветку, где new-branch = название ветки
```html
git branch new-branch
```
Переключиться на ветку, где new-branch = название ветки
```html
git checkout new-branch
```
**Пошагово как через консоль закомитить и запушить все свои изменения:**
```html
Делаешь изменения в коде
git add .
git commit -m "add 3 search tests"
git push -u origin new-branch
```
Обновить текущую ветку
```html
git pull
```
**Решение конфликта:**
1. В мастере что-то изменилось
1. Сделала коммит в своей ветке (test-ya)
1. Перешла в master и сделала git pull
1. Перешла в свою ветку
1. master - `“Merge ‘master’ into ‘test-ya’”`

---
## Урок 3: 

### Домашнее задание для урока 3:
Разработайте один автотест на проверку формы https://demoqa.com/automation-practice-form
- Необходимо **заполнить все поля формы** (input, textarea, загрузка картинки и тд), а не только обязательные.
- Рекомендуется **не переусложнять код** - не добавляйте генерацию рандомных значений, не добавляйте pageobjects. Это будет изучено в последующих занятиях.
### Хитрые моменты в форме и как их обойти:
#### Radio-button выбора Gender: Male, Female, Other
<img width="350" height="35" alt="Снимок экрана 2025-08-11 в 17 46 17" src="https://github.com/user-attachments/assets/1404c663-bd8e-45de-87dd-883ca63cc197" />

Код элемента:
```html
<div id="genterWrapper" class="mt-2 row">
  <div class="c">Gender</div>
    <div class="custom-control custom-radio custom-control-inline">
      <input type="radio" id="gender-radio-1" class="custom-control-input" name="gender" value="Male" required>
      <label for="gender-radio-1" class="custom-control-label" title="Male"> Male  </label>
```
Код теста:
```java
$("#genterWrapper").$(byText("Male")).click();
```
#### Календарь выбора Date of Birth
<img width="300" height="303" alt="Снимок экрана 2025-08-11 в 17 46 49" src="https://github.com/user-attachments/assets/cfac8a2c-31cd-4fd8-be23-f03bbba6d470" />

Код теста:
```java
$("#dateOfBirthInput").click();
$(".react-datepicker__year-select").$(byText("2000")).click();
$(".react-datepicker__month-select").$(byText("January")).click();
$(".react-datepicker__day--001").click();
```
#### Выбор из выпадающего списка после ввода в поле поиска Subjects
<img width="500" height="95" alt="Снимок экрана 2025-08-11 в 17 47 48" src="https://github.com/user-attachments/assets/d991f483-21b5-400e-8fb8-5b192d845616" />

Код теста:
```java
$("#subjectsInput").setValue("Math");
$(byText("Maths")).click();
```
#### Чек-боксы выбора Hobbies: Sports, Reading, Music
<img width="400" height="30" alt="Снимок экрана 2025-08-11 в 17 48 33" src="https://github.com/user-attachments/assets/50c0caf2-10df-4cd3-bc8c-c349de0b43ac" />

Код теста:
```java
$("#hobbiesWrapper").$(byText("Reading")).click();
```
#### Загрузка файла Picture
<img width="400" height="65" alt="Снимок экрана 2025-08-11 в 17 48 55" src="https://github.com/user-attachments/assets/ed8e3aea-4675-4772-8821-13fdf8a96ca4" />

Код теста:
Файл загружен в resources
```java
$("#uploadPicture").uploadFromClasspath("lesson3/knight.jpg");
```
#### Выбор значения из выпадающих списков State and City
<img width="400" height="160" alt="Снимок экрана 2025-08-11 в 17 49 32" src="https://github.com/user-attachments/assets/e869ad5c-17b6-4a2f-b8fb-2149820cf497" />

Код теста:
```java
$("#state").click();
$(byText("NCR")).click();
$("#city").click();
$(byText("Delhi")).click();
```

---
## Урок 4: Selenide #1

1. DOM для начинающих (запись)
2. Лайвкодинг – простые тесты для GitHub
3. Лайвкодинг – GitHub с решением некоторых типичных проблем

Про элементы DOM:
- `.classname` - пишем точку, когда надо искать по имени класса
- `#id` - пишем решетку, когда ищем по id
- `[attribute='attribue name']` - пишем [], когда ищем атрибут
- `a` - тег a

можно комбинировать: `a[href='abc'].red`- тег `а`, атрибут `href` со значением `abc` и должен быть класс `red`


### Домашнее задание для урока 4:

#### 1. Есть ли разница между `$("h1 div");` и `$("h1").$("div");`?
Может ли привести к тому что, поиск найдёт разные элементы? Если может - приведите пример, когда.

**Ответ:** На первый взгляд разницы нет. Я написала тест с проверками:
````java
$("body div").should(appear);
$("body").$("div").should(appear);
````
И тест успешно прошел. И казалось бы, можно написать ответ: "Нет, разницы нет." и отправить ответ.

Но все же хочется покпаться дальше и я решила обратиться к предыдущему занятию.

Видео по занятию "_3. Погружаемся в инструментарий и библиотеки_", тайминг 1:39:20, лектор изменяет `$("#output").$("#name")` на `$("#output #name")`, говоря, что разницы между ними нет, и опять можно было бы ответить "Нет, разницы нет." и отправить ответ.
Но в лекции были айди элементов, а не теги. Поэтому я решила копать еще дальше и найти страницу, где есть и div и h1. Такие теги нашлись в https://demoqa.com/frames Поэтому я написала следующий тест:
````java
@Test
void letsSeeDivTest() {
open("https://demoqa.com/frames");
$("div h1").should(appear);
$("div").$("h1").should(appear);
}
````
И уже этот тест упал на второй проверке. Текст ошибки гласил: `Element not found {div/h1}`. Ага, уже значит разница какая-то есть, значит надо посмотреть, какая будет ошибка, если поменять один из тегов в первой проверке на несуществующий. И следующий текст ошибки был: `Element not found {div h12}`. Бинго!
То есть когда мы пишем `$("div h1»)`, то selenide так и смотрит данный элемент и может спокойно найти именно то, как мы элемент обозначали. А запись вида `$("div").$("h1")` selenide преобразует и записывает через `div/h1` (какой-то xpath получается)

Остался один вопрос: почему так происходит?

Тут уже скажу честно, мой мозг понимал, что дело вероятнее всего кроется в родительских элементах, но сама суть до меня не доходила, поэтому я обратилась за помощью к ИИ.

И тут уже я поняла, что на "слона в комнате" я почти не обратила внимание.
* В первом варианте `$("div h1»)` у нас идет **один селектор**. И эта запись готовит селениду: найди мне внутри тега `div` тег `h1`.
* Во втором варианте `$("div").$("h1")` у нас **цепочка** поиска. Что означает: найти первый элемент `div`, а потом внутри него найди `h1`.

То есть если внутри первого элемента по dom-дереву не будет найден второй элемент цепочки, то тест упадет. В то время как первый вариант записи ищет сразу такой существующий элемент (если он конечно существует)

Для кода, указанного ниже, запись типа `$("div h1»)` найдет элемент, а запись типа `$("div").$("h1")` элемент не найдет, потому что в первом `div` нет внутри `h1`.
````html
<div></div>
<div>
    <h1>hello</h1>
</div>
````

#### 2. Разработайте следующий автотест:

- Откройте страницу Selenide в Github
- Перейдите в раздел Wiki проекта
- Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
- Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

---
## Урок 5: Selenide #2

### Домашнее задание для урока 5:
#### 1. GitHub -> Solutions -> Enterprize
- На главной странице GitHub выберите: Меню -> Solutions -> Enterprises (с помощью команды hover для Solutions).
- Убедитесь, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").

#### 2. (опциональное) Drag&Drop
- Запрограммируйте Drag&Drop с помощью Selenide.actions()
- Откройте https://the-internet.herokuapp.com/drag_and_drop
- Перенесите прямоугольник А на место В
- Проверьте, что прямоугольники действительно поменялись
- В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()

---
## Урок 6: Дополнительное занятие. Основы Java. Часть I. Дмитрий Тучс

В занятии разберем:

1. Структуру папок и проекта, src/main, src/test
2. Класс с точки зрения файлов
3. Класс с точки зрения Java
4. Class Fields – переменные (в чем разница)
5. Примитивные типы данных
6. Литералы
7. Операторы
8. Управляющие конструкции
9. Метод как описание поведения
10. Создание объектов и вызов методов

#### Типы данных

| Тип     | Описание                                    | Пример                | Что хранит                                                             | В степени              | Память (байт) | Память (бит) |
|---------|---------------------------------------------|-----------------------|------------------------------------------------------------------------|------------------------|---------------|--------------|
| byte    | Целочисленный тип                           | `byte a = 0;`         | Целое число от -128 до 127                                             | (-2^8) .. (+2^8 - 1)   | 1             | 8            |
| short   | Целочисленный тип                           | `short a = 0;`        | Целое число от -32768 до 32767                                         | (-2^16) .. (+2^16 - 1) | 2             | 16           |
| int     | Целочисленный тип                           | `int a = 0;`          | Целое число от -2147483648 до 2147483647                               | (-2^32) .. (+2^32 - 1) | 4             | 32           |
| long    | Целочисленный тип                           | `long a= 0L; `        | Целое число от –9 223 372 036 854 775 808 до 9 223 372 036 854 775 807 | (-2^64) .. (+2^64 - 1) | 8             | 64           |
| float   | Тип с плавающей точкой                      | `float a = 0.0F;`     | Число от -3.4E+38 до 3.4E+38                                           |                        | 4             | 32           |
| double  | Тип с плавающей точкой                      | `double a = 0.0;`     | Число от -1.7E+308 до 1.7E+308.                                        |                        | 8             | 64           |
| char    | Символьный                                  | `char a = 'a';`       | Одиночный символ                                                       |                        | 2             | 16           |
| boolean | Логический                                  | `boolean a = true;`   | Истина или Ложь                                                        |                        |               |              |
| String  | Строка (не примитив, а объектный/ссылочный) | `String a = "hello";` | Целая строка                                                           |                        |               |              |

Целочисленные типы отличаются диапазоном хранимых данных

8 примитивных: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`
1 объектный/ссылочный: `String`

Литералы:
Числовой литерал: `int aInt = 0;`
Строковый литерал: `"hello world"`

#### Арифметические операторы

- `+` - Складывает значение по обе стороны
- `-` - Вычитает правый операнд из левого
- `*` - Умножает значения по обе стороны от оператора
- `/` - Делит левый операнд на правый
- `%` - Возвращает остаток от деления левого операнда на правый
- `++` - Инкремент - увеличение операнда на 1
- `--` - Декремент - уменьшение операнда на 1

```java
public static void main(String args[]) {
   int a = 10;
   int b = 20;
   int c = 25;
   int d = 25;
   System.out.println("a + b = " + (a + b)); // a + b = 30
   System.out.println("a - b = " + (a - b)); // a - b = -10
   System.out.println("a * b = " + (a * b)); // a * b = 200
   System.out.println("b / a = " + (b / a)); // b / a = 2
   System.out.println("b % a = " + (b % a)); // b % a = 0
   System.out.println("c % a = " + (c % a)); // c % a = 5
   System.out.println("a++   = " + (a++)); //   a++   = 10
   System.out.println("b--   = " + (a--)); //   b--   = 11
   // Проверьте разницу в d++ и ++d
   System.out.println("d++   = " + (d++)); //   d++   = 25
   System.out.println("++d   = " + (++d)); //   ++d   = 27
}
```

#### Операторы сравнения

| Оператор	 | Описание                                         |
|-----------|--------------------------------------------------|
| `==`	     | Равно, для объектных типов нужен метод `.equals` |
| `!=`	     | Неравно                                          |
| `>`	      | Больше                                           |
| `<`	      | Меньше                                           |
| `>=`	     | Больше или равно                                 |
| `<=`	     | Меньше или равно                                 |

#### Логические операторы

- `&&` - логическое «И»
- `||` - логическое «ИЛИ»
- `!` - логическое «НЕ»

#### Оператор instanceof
`System.out.println(nameFirst instanceof  String);` - является ли эта переменная типом String?

#### Тернарный оператор
Используем, когда нам **надо** вернуть какой-то результат
```java
char sex = 'm';

String childName = sex == 'm'
   ? "Valentin"
   : "Valentina";
```

#### Управляющая конструкция if
Используем, когда нам **не надо** возвращать какой-то результат
```java
if (sex == 'm') {
    childName = "Valentin";
} else if (sex == 'w') {
    childName = "Valentina";
} else {
    System.out.println("((");
}
```

### Ключевое слово new
Создает объект

#### Диапазон типов данных для вещественных чисел (есть в табличке выше)
**Вещественные числа** — это числа, у которых есть дробная часть (она может быть нулевой). Они могут быть положительными или отрицательными.

Вот несколько примеров:

- 15
- 56.22
- 0.0
- 1242342343445246
- -232336.11

#### Диапазон типов данных для чисел с плавающей точкой (есть в табличке выше)

#### Перепополнение
Тут будет зацикливание. Потому что значение счётчика дойдёт до максимума (127), произойдёт переполнение и значение станет -128. И мы никогда не выйдем из цикла.
```java
for (byte i = 1; i <= 200; i++) {
   System.out.println(i);
}
```

#### Получение максимального значения
- Byte.MAX_VALUE
- Short.MAX_VALUE
- Integer.MAX_VALUE
- Long.MAX_VALUE
- Float.MAX_VALUE
- Double.MAX_VALUE
---

### Домашнее задание для урока 6:

0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
1) применить несколько арифметических операций над int и double в одном выражении
2) применить несколько логических операций ( < , >, >=, <= )
3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные
   значения есть, как их получить) и переполнение
4) получить переполнение при арифметической операции

---
## Урок 6: PageObjects

1. Добавляем в код PageObjects
2. Изучаем подходы – степовой, сценарный, dsl
3. Добавляем PageComponents

### Домашнее задание для урока 6:
1. Возьмите ваш код написанный в рамках ДЗ для формы https://demoqa.com/automation-practice-form (java/lesson3/PracticeFormTest.java)

Добавить еще как минимум 2 автотеста - проверку минимального количества данных и негативную проверку

2. Добавьте в ваш код PageObjects / Components, в тестовом классе не должно остаться локаторов

(Таблицу с результатами введенных данных тоже вынести в components)

3. Добавьте PageObjects к автотесту на TextBoxTests

---
## Урок 7: Генерация тестовых данных

1. Генерим рандомные значения, используя встроенные библиотеки
2. Используем JavaFaker

Можно хранить значения в ресурсках в файле .properties. Пример такого файла: `testdata.properties`
```
firstName=Strahd
lastName=von Zarovich
userEmail=strahd@barovia.dom
```
[JavaFaker](https://github.com/DiUS/java-faker)

### Домашнее задание для урока 7:
1. Возьмите ваш код написанный в рамках ДЗ для формы https://demoqa.com/automation-practice-form (c PageObjects)

2. Вынесите все вводимые и проверяемые значения в переменные, добавьте генерацию данных с Faker

---
## Урок 8: JUnit 5. Аннотации
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

**Запуск ат** из домашней работы: `./gradlew homework-8`

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

---
## Урок 9: Работа с файлами
1. Как скачать и прочитать файл
2. Проверяем контент в .txt, .pdf, .xls/.xlsx, .doc/.docx, .zip

Класс `File` — абстракция над путем к файлу в папке в памяти машины. Для чтения/записи существует понятие `InputStream` и `OutputStream` соответственно. Для чтения необходимо создать новый `InputStream` и передать ему файл. Далее из файла надо получить массив байт и декодировать его в необходимый стандарт. По завершении поток надо обязательно закрыть.

Для работы с файлами, будь то чтение или запись, мы работаем со стримами и ридерами. Стримы и ридеры надо за собой закрывать, так как они работают с файлами-дескрипторами операционной системы.
Это закрытие происходит через вызов метода `close()`, который определен в интерфейсе `Closeable`, который является наследником `AutoCloseable` и вызов `close()` может проходить автоматически если используется `try с ресурсами`:
`try (InputStream is = new FileInputStream(downloaded)) {тут код, по которому читаем содержимое}`

Для того, чтобы читать что-то из `classPath` нам нужен механизм `ClassLoader`  
`ClassLoader` - специальный объект в Java, что отвечает за поиск и загрузку классов в `classPath` и файлов в ресурсы. Его можно получить находясь с любом классе в джаве. Получение ClassLoader:
```java
// Этот метод вернет classLoader в котором загружен этот класс
private ClassLoader classLoader = Наименование_класса.class.getClassLoader();
```

[Как скачать файл с помощью Selenide](https://ru.selenide.org/2019/12/10/advent-calendar-download-files/)

#### Отладка, действие 'Evaluate'
Для отладки файлов в режиме дебага можно нажать на ПКМ - `Evaluate` и внутри ввести, например, `pdf.text`

#### PDF
[GitHub pdf-test](https://github.com/codeborne/pdf-test)  
Библиотека для чтения pdf-файлов:
```groovy
// https://mvnrepository.com/artifact/com.codeborne/pdf-test
implementation 'com.codeborne:pdf-test:1.8.1'
```
Статья "[Автоматизируем проверку содержимого PDF-файлов с помощью pdf-test](https://habr.com/ru/companies/simbirsoft/articles/794728/)"  
Для открытия PDF-файла необходимо использовать ClassLoader. Это поможет сохранить независимость от файловой системы. Далее необходимо создать новый класс PDF и передать поток.
```java
@Test
void pdfTest() throws Exception {
    InputStream stream = cl.getResourceAsStream("pdf/junit-user-guide-5.8.2.pdf");
        PDF pdf = new PDF(stream);
}
```

#### XLS
[GitHub xls-test](https://github.com/codeborne/xls-test)  
Библиотека для чтения excel-файлов:
```groovy
// https://mvnrepository.com/artifact/com.codeborne/xls-test
implementation 'com.codeborne:xls-test:1.7.2'
```
Решение проблемы с версиями с pdf-test и xls-test:
```groovy
    testImplementation(
            'com.codeborne:pdf-test:2.0.0',
            'org.apache.poi:poi:5.4.1',
            'org.apache.poi:poi-ooxml:5.4.1'
    )

    testImplementation ('com.codeborne:xls-test:1.7.2') {
                exclude(group: 'org.apache.poi', module: 'poi')
                exclude(group: 'org.apache.poi', module: 'poi-ooxml')
            }
```
Элементы таблицы:
- листы — `getSheetAt();`
- строчки — `getRow();`
- столбцы — `getCell();`
- ячейка — пересечение строки и столбца.
```java
@Test
void xlsTest() throws Exception {
    InputStream stream = getClass().getClassLoader().getResourceAsStream("xls/file.xls");
    XLS xls = new XLS(stream);
 // Пример записи строки из первого листа, третьей строки и первого столбца в переменную (нумерация начинается с нуля):
    String value = xls.excel.getSheetAt(0).getRow(3).getCell(0).getStringCellValue();
}
```

#### CSV
Нужен `ClassLoader`
Библиотека для чтения csv-файлов:
```groovy
// https://mvnrepository.com/artifact/com.opencsv/opencsv
implementation 'com.opencsv:opencsv:5.12.0'
```
Код:
```java
@Test
    void csvTest() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            
            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(new String[] {"Selenide", "https://selenide.org"}, data.get(0));
            Assertions.assertThat(data).contains(new String[]{"---", "---"}, new String[]{"---", "---"});
        }
    }
```
**Пояснение кода:**
```java
try (InputStream stream = getClass().getClassLoader().getResourceAsStream("example.csv");
```
- `try-with-resources` — всё, что создано в круглых скобках, будет автоматически закрыто после выполнения блока.
- `getClass().getClassLoader()` — берёт загрузчик классов, который знает, где лежат ресурсы проекта.
- `getResourceAsStream("example.csv")` — открывает файл example.csv из папки resources и превращает его в поток байтов (`InputStream`).
```java
CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8)))
```
- Создаётся `CSVReader` — парсер CSV-файлов.
- Внутрь передаётся `InputStreamReader`, который превращает поток байтов в поток символов.
- Указывается кодировка `UTF-8`, чтобы правильно читать символы (особенно если в файле есть кириллица или специальные символы).
```java
List<String[]> data = csvReader.readAll();
```
- `readAll()` читает весь CSV-файл целиком и возвращает его как список массивов строк `List<String[]>`.
- Каждая строка CSV → один элемент списка.
- Каждая ячейка строки → элемент массива.
#### ZIP
Для работы с zip в Java уже есть библиотека: `java.util.zip`
```java
    @Test
void zipTest() throws Exception {
    ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/sample-zip-file.zip"));
    ZipInputStream zipInputStream = new ZipInputStream(classLoader.getResourceAsStream("zip/sample-zip-file.zip"));
    ZipEntry entry;
    while((entry = zipInputStream.getNextEntry()) != null) {
        Assertions.assertEquals("sample.txt", entry.getName());
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            // проверки
        }
    }
}
```
**Разбор кода:**
```java
ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/sample-zip-file.zip"));
```
- Создаётся объект `ZipFile`, который позволяет работать с ZIP-архивом, как с коллекцией файлов.
- Указываем путь к ZIP-файлу на диске — `src/test/resources/zip/sample-zip-file.zip`.
- С помощью `zipFile` мы потом будем доставать содержимое конкретных файлов из архива.
```java
ZipInputStream zipInputStream = new ZipInputStream(classLoader.getResourceAsStream("zip/sample-zip-file.zip"));
```
- Создаём поток для чтения архива в виде последовательности записей `ZipEntry`.
- Используется `classLoader.getResourceAsStream(...`), чтобы найти ZIP-файл в ресурсах (вместо указания абсолютного пути).
- `ZipInputStream` позволяет итерироваться по файлам внутри архива — получать их имена и метаданные.
```java
ZipEntry entry;
```
- `ZipEntry` — это класс, который описывает один файл или папку внутри ZIP-архива.  
  Он хранит:
- имя файла `entry.getName()`,
- размер `entry.getSize()`,
- другие метаданные.
```java
while((entry = zipInputStream.getNextEntry()) != null) {
```
- `while (...) != null` — перебираем все файлы в архиве, пока они не закончатся.
- `getNextEntry()` — берёт следующий файл из архива.
```java
Assertions.assertEquals("sample.txt", entry.getName());
```
- Проверяем, что имя текущего файла в архиве равно "sample.txt".
```java
try (InputStream inputStream = zipFile.getInputStream(entry)) {
```
- Открываем поток чтения содержимого конкретного файла `entry` из архива через `zipFile`
- Используем `try-with-resources`, чтобы автоматически закрыть поток после выхода из блока

#### JSON
Гугловская библиотека для чтения json:
```groovy
// https://mvnrepository.com/artifact/com.google.code.gson/gson
implementation 'com.google.code.gson:gson:2.13.1'
```
Еще одна библиотека для работы с json:
```groovy
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
implementation 'com.fasterxml.jackson.core:jackson-core:2.19.0'
```
```java
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("example.json"))) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);
            Assertions.assertEquals("example glossary", actual.get("title").getAsString());

            JsonObject inner = actual.get("glossary").getAsJsonObject();
            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
        }
    }
```

Пояснение:
```java
try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("example.json"))) {
```
- `try-with-resources` — создаётся `Reader`, который будет автоматически закрыт после выполнения блока.
- `classLoader.getResourceAsStream("example.json")` — открывает файл `example.json` из ресурсов
- `InputStreamReader` преобразует поток байтов в поток символов, чтобы его можно было читать как текст.
```java
JsonObject actual = gson.fromJson(reader, JsonObject.class);
```
- `gson.fromJson(reader, JsonObject.class)` — парсит JSON-файл и превращает его в объект типа `JsonObject` (дерево JSON-данных).
- В переменной `actual` теперь лежит весь JSON, как структура данных, к которой можно обращаться по ключам.
```java
Assertions.assertEquals("example glossary", actual.get("title").getAsString());
```
- Достаём значение по ключу `title` из верхнего уровня JSON:
- `actual.get("title")` → элемент JSON,
- `.getAsString()` → его строковое значение.
- Сравниваем с ожидаемым значением "`example glossary`".
```java
JsonObject inner = actual.get("glossary").getAsJsonObject();
```
- Извлекаем вложенный в `actual` объект по ключу `glossary` и приводим его к `JsonObject`.
- Теперь в переменной `inner` лежит внутренний объект JSON, с которым можно работать дальше.
```java
Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
```
- Из внутреннего объекта достаём поле `SortAs` и проверяем, что оно равно `SGML`.

**Модели для json.**  
`@SerializedName("ID") `- нужен для того, чтобы java прочла название с большой буквы
```json
{
  "ID": 234234,
  "glossary": {
    "SortAs": "SGML"
  }
}
```
```java
public class Glossary {
    @SerializedName("ID")
    private Integer id;
    private GlossaryInner glossary;

    public Integer getID() {
        return id;
    }
    public void setID(Integer id) {
        this.id = id;
    }

    public GlossaryInner getGlossary() {
        return glossary;
    }
    public void setGlossary(GlossaryInner glossary) {
        this.glossary = glossary;
    }
}

```
```java
public class GlossaryInner {
    @SerializedName("SortAs")
    private String sortAs;

    public String getSortAs() {
        return sortAs;
    }
    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }
}

```

### Домашнее задание для урока 9:
1. **Работа с zip**  
   1.1 Запаковать в zip архив несколько разных файлов - pdf, xlsx, csv  
   1.2 Положить его в ресурсы  
   1.3 Реализовать чтение и проверку содержимого каждого файла из архива

2. **Разбор JSON**  
   2.1 Реализовать разбор json файла библиотекой Jackson
   2.2 Придумать реальный объект и описать его в виде json (можешь Lombok использовать)
   2.3 В идеале json должен содержать массив
---