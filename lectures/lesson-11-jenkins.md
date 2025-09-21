## Урок 11: Jenkins. Создаем первую задачу
1. Практика. Создаем задачу (Job), связываем с репозиторием в Github, запускаем тесты удаленно.
2. Теория. Основы Jenkins. Основы CI/CD.

### Домашнее задание для урока 11:
1. Взять свой код для http://demoqa.com/automation-practice-form (урок 6-7)
2. Добавить аттачи для Allure – скриншот, page source, console.log и видео
3. Cделать сборку в Jenkins
4. В качестве ответа на нужно приложить ссылку на Allure-отчет в Jenkins (с видео)

## Урок 12 (10): Управляем параметрами в коде и в Jenkins
1. Передаем параметры в код из командной строки
2. Передаем параметры из Jenkins
### Домашнее задание для урока 10:
Доработать свой код:
1. Передать из дженкинса адрес удаленного браузера (selenoid)
2. Сделать возможность выбора браузера, версии и разрешения из сборки дженкинс  
https://jenkins.autotests.cloud/job/037-attanosolas-lesson9/
### Запуск локально при помощи переменных:
```bash
./gradlew clean test_for_jenkins -Dremote=https://ССЫЛКА_НА_СЕЛЕНОЙД -Dbrowser=chrome -DbrowserVersion=128.0 -DbrowserResolution=1280×720
```

## Урок 13 (11): Telegram-бот. Отправляем уведомления о результатах прохождении автотестов
1. Практика: Постобработка сборки. Настраиваем отправку уведомлений в Jenkins
2. Теория: Обзор возможностей Telegram API
### Домашнее задание для урока 13:
1. Создайте проект с любыми автотестами, либо возьмите уже созданный.
2. Создайте задачу в Jenkins
3. Зарегистрируйте бота в Telegram, создайте чат и добавьте бота в него.
4. Добавьте бота к вашему проекту.
Для выполнения домашнего задания нужно приложить:  
- ссылку на git-репозиторий.  
- ссылку на job в Jenkins.  
- скриншот из телеграм-чата с нотификацией.  

### Настройка для отправки соо в тг:
1. Создаешь бота и канал, куда добавляешь бота как админа
2. В браузере открываешь: https://api.telegram.org/bot{{TELEGRAM_BOT_TOKEN}}/getUpdates и берешь айди канала. Для этого в канал должно быть отправлено сообщение
3. Курл для проверки, может ли бот отправлять сообщения:
Шаблон
```bash
curl -X POST \
     -H 'Content-Type: application/json' \
     -d '{"chat_id": "123456789", "text": "This is a test from curl", "disable_notification": true}' \
     https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage
```
Работает у меня:
```bash
curl --location 'https://api.telegram.org/bot{{TELEGRAM_BOT_TOKEN}}/sendMessage' \
--header 'Content-Type: application/json' \
--data '{
"chat_id": "{{айди чата}}",
"text": "hello from postman",
"disable_notification": true
}'
```
### Отправка сообщения в тг локально:
1. Скачиваешь [allure-notifications-4.11.0.jar](https://github.com/qa-guru/allure-notifications)
2. Кидаешь в папку `notifications` в корне проекта
3. Там же создаешь `config.json`  
   Про `allureFolder`: Чтобы он появился надо выполнить `allureServe` и скопировать путь к отчету, он будет в `run` в виде `/var/folders/s1/ЧТО-ТО_НЕПОНЯТНОЕ/T/КУЧА_ЦИФР/allure-report`
```json
{
  "base": {
    "project": "наименование проекта - появляется в заголовке картинки в сообщении",
    "environment": "окружение, будет в сообщении как 'Рабочее окружение: demoqa' (прод, препод, дев)",
    "comment": "локальный коммент",
    "reportLink": "ссылка на то, где можно посмотреть отчет",
    "language": "язык сообщения",
    "allureFolder": "ссылка на папку с отчетом",
    "enableChart": true
  },
  "telegram": {
    "token": "токен бота",
    "chat": "токен канала",
    "replyTo": ""
  }
}
```
4. Делаешь команду:
```bash
java "-DconfigFile=notifications/config.json" -jar ./notifications/allure-notifications-4.11.0.jar
```
5. Готово, сообщение в канал отправлено
### Отправка сообщения в тг через Jenkins:
1. Первый Build Steps:
- Активные чекбоксы: `Create at Workspace:` и `Overwrite file`
- `File Path:` `notifications/config.json`
- `Text File Content:`
```json
  {
   "base": {
      "project": "${JOB_NAME}",
      "environment": "https://demoqa.com",
      "comment": "Привет из Jenkins: ${COMMENT}",
      "reportLink": "${BUILD_URL}"
      ",
      "language": "ru",
      "allureFolder": "allure-report",
      "enableChart": true
   },
   "telegram": {
      "token": "токен бота",
      "chat": "токен канала",
      "replyTo": ""
   }
}
```
2. Post-build actions - Post build task
- Первый скрипт: 
- Второй скрипт: `java "-DconfigFile=notifications/config.json" -jar ../allure-notifications-4.11.0.jar`
3. Файла `allure-notifications-4.11.0.jar` не должно быть в репозитории, так как он слишком тяжелый. Решение:
- Перед билд-таск, что создали в шаге два создать еще одну билд таск со скриптом:
```bash
cd ..
FILE=allure-notifications-4.11.0.jar
if [ ! -f "$FILE" ]; then
   wget https://github.com/qa-guru/allure-notifications/releases/download/4.11.0/allure-notifications-4.11.0.jar
fi
```