## Урок 3: <a name="урок_3"></a>
[3. Погружаемся в инструментарий и библиотеки. Станислав Васенков и Дмитрий Тучс](https://school.qa.guru/pl/teach/control/lesson/view?id=343208839&editMode=0)
### Домашнее задание для урока 3:
Разработайте один автотест на проверку формы https://demoqa.com/automation-practice-form
- Необходимо **заполнить все поля формы** (input, textarea, загрузка картинки и тд), а не только обязательные.
- Рекомендуется **не переусложнять код** - не добавляйте генерацию рандомных значений, не добавляйте pageobjects. Это будет изучено в последующих занятиях.
### Хитрые моменты в форме и как их обойти:
#### Radio-button выбора Gender: Male, Female, Other
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
Код теста:
```java
$("#dateOfBirthInput").click();
$(".react-datepicker__year-select").$(byText("2000")).click();
$(".react-datepicker__month-select").$(byText("January")).click();
$(".react-datepicker__day--001").click();
```
#### Выбор из выпадающего списка после ввода в поле поиска Subjects
Код теста:
```java
$("#subjectsInput").setValue("Math");
$(byText("Maths")).click();
```
#### Чек-боксы выбора Hobbies: Sports, Reading, Music
Код теста:
```java
$("#hobbiesWrapper").$(byText("Reading")).click();
```
#### Загрузка файла Picture
Код теста:
Файл загружен в resources
```java
$("#uploadPicture").uploadFromClasspath("lesson3/knight.jpg");
```
#### Выбор значения из выпадающих списков State and City
Код теста:
```java
$("#state").click();
$(byText("NCR")).click();
$("#city").click();
$(byText("Delhi")).click();
```