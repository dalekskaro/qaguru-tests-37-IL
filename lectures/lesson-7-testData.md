## Урок 7: Генерация тестовых данных
[7. Продолжаем разрабатывать автотесты. Генерация тестовых данных. Станислав Васенков](https://school.qa.guru/pl/teach/control/lesson/view?id=343208846&editMode=0)

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
