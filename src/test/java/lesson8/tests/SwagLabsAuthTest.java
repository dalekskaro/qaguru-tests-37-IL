package lesson8.tests;

import lesson8.pages.SwagLabsAuthPage;
import lesson8.pages.SwagLabsProductPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("homework-8")
public class SwagLabsAuthTest extends BaseTestForSwagLabs {
    SwagLabsAuthPage swagLabsAuthPage = new SwagLabsAuthPage();
    SwagLabsProductPage swagLabsProductPage = new SwagLabsProductPage();

    @ValueSource(strings = {
            "standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"
    })
    @ParameterizedTest(name = "Авторизация  в Swag Labs с логином {0}")
    @DisplayName("Аннотация @ValueSource")
    void sauceDemoAuthTest(String login) {
        swagLabsAuthPage.openAuthPage()
                .setLogin(login)
                .setHardcodePassword()
                .clickOnLoginButton();

        swagLabsProductPage.checkOnPage();
    }
}
