## Урок 5: <a name="урок_5"></a>
[5. Selenide #2. Алексей Виноградов](https://school.qa.guru/pl/teach/control/lesson/view?id=343208842&editMode=0)


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
