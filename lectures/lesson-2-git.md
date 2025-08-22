## Урок 2: Git
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