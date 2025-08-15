package lesson5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeworkDragAndDropTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @DisplayName("Drag&Drop с помощью Selenide.actions()")
    void dragAndDropWithActionsTest() throws InterruptedException {
        open("/drag_and_drop");
        // Проверяем, что А находится перед В
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        // Перенесите прямоугольник А на место В
        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(200, 0).release().perform();
        // Проверяем, что B находится перед A
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));

    }

    @Test
    @DisplayName("Drag&Drop с помощью .dragAndDrop($(to-element)")
    void dragAndDropWithDnDTest() {
        open("/drag_and_drop");
        // Проверяем, что А находится перед В
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        // В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
        $("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));
        // Проверяем, что B находится перед A
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
