# qaguru-tests-37-IL
* [Урок 2 - Git](#"урок_2)
* [Урок 3 - Погружение в инструментарий](#"урок_3)

## Урок 2: Git <a name="урок_2"></a>
[2. Git. Github. Погружаемся. Дмитрий Тучс](https://school.qa.guru/pl/teach/control/lesson/view?id=343208838&editMode=0)

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
