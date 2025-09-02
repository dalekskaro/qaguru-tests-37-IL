package lesson8.tests;

import lesson8.data.Tab;
import lesson8.pages.DemoQATabsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Tag("homework-8")
public class DemoQATabsTests extends BaseTestForDemoQA {
    DemoQATabsPage demoQATabsPage = new DemoQATabsPage();

    @EnumSource(Tab.class)
    @ParameterizedTest(name = "Проверка текста на вкладках")
    @DisplayName("Аннотация @EnumSource")
    void checkTextOnTabsTest(Tab tab) {
    demoQATabsPage.openPage()
            .clickOnTab(tab.name())
            .checkTextOnTab(tab.description);
    }
}
