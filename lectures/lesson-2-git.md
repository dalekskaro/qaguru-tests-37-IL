## Урок 2: Git

Увидеть локальные настройки:
```bash
git config --list --local
git config --local user.name
git config --local user.email
```
Задать настройки локально (для одного проекта)
```bash
git config --local user.name "__"
git config --local user.email "__"
```
Увидеть глобальные настройки (для всех проектов):
```bash
git config --list –global
```
Инициализация проекта
```bash
git init
```
Посмотреть, какие проекты добавлены в гит
```bash
git status
```
Добавить файл в гит
```bash
git add *файл*
```
Добавить все файлы в гит, с учетом `.gitignore`
```bash
git add .
```
Убрать файл из гита
```bash
git rm --cached *файл*
```
Сделать коммит, -m - сообщение
```bash
git commit -m "сообщение"
```
Изменить последний коммит
```bash
git commit --amend -m "Новое название"
```
**Как сделать так, чтобы привязать уже сущствующий репозиторий в GitHub и локальный проект?**
1. Идешь в GitHub и создаешь проект, где Repository name = имени локального проекта
2. GitHub сам даст следующую комадну, она привяжет локальный и remote репозитории
```bash
git remote add origin git@github.com:dalekskaro/try_git_qa_guru_37.git
```
Пуш коммита в мастер: (-u = upstream = нужен для самого первого пуша)
```bash
git push -u origin master
```
Посмотреть все ветки
```bash
git branch
```
Создать новую ветку, где new-branch = название ветки
```bash
git branch new-branch
```
Переключиться на ветку, где new-branch = название ветки
```bash
git checkout new-branch
```
**Пошагово как через консоль закомитить и запушить все свои изменения:**
```bash
Делаешь изменения в коде
git add .
git commit -m "add 3 search tests"
git push -u origin new-branch
```
Обновить текущую ветку
```bash
git pull
```
**Решение конфликта:**
1. В мастере что-то изменилось
1. Сделала коммит в своей ветке (test-ya)
1. Перешла в master и сделала git pull
1. Перешла в свою ветку
1. master - `“Merge ‘master’ into ‘test-ya’”`