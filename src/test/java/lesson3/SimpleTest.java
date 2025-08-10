package lesson3;

import org.junit.jupiter.api.*;

public class SimpleTest {
    int result;

    private int getResult() {
        return 3;
    }

    @BeforeEach
    void beforeEach() {
        result = getResult();
    }

    @AfterEach
    void afterEach(){
        result=0;
    }

    @Test
    @DisplayName("Число 3 больше 2")
    void firstTest() {
        Assertions.assertTrue(result>2);
    }
}