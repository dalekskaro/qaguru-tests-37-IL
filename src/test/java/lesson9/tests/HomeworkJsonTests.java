package lesson9.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStreamReader;
import java.io.Reader;
import lesson9.model.GameReviews;
import lesson9.model.GamesInner;
import lesson9.model.ReviewInner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HomeworkJsonTests {

  private ClassLoader classLoader = FileParsingTest.class.getClassLoader();
  ObjectMapper objectMapper = new ObjectMapper();

  @Tag("homework-9")
  @ParameterizedTest(name = "Проверка json для игры {1} для отзыва с id={3}")
  @CsvSource(value = {
      "2077,Cyberpunk 2077,1,1,10,10/10 симуляторов таракана в голове,0,0",
      "64,Minecraft,2,12,3,спалила мама когда орал на того кто поломал мой дом,1,0",
      "64,Minecraft,2,13,10,я ценитель этого искусства как итальянский мрамор,1,1"
  })
  void jsonTest(
      int gameId, String name, int reviews, int reviewId,
      int score, String review, int gameList, int reviewList
  ) throws Exception {
    try (Reader reader = new InputStreamReader(
        classLoader.getResourceAsStream("lesson9/ reviews.json")
    )) {
      GameReviews gameReviews = objectMapper.readValue(reader, GameReviews.class);
      assertEquals(2,gameReviews.getGames().size(),"Размер массива games не равен ожидаемому");

      GamesInner gamesInner = gameReviews.getGames().get(gameList);
      assertEquals(gameId, gamesInner.getGameId(),"Значение gameId не соответствует ожидаемому");
      assertEquals(name, gamesInner.getName(),"Значение name не соответствует ожидаемому");
      assertEquals(reviews,gamesInner.getReviews().size(),"Размер массива reviews не равен ожидаемому");

      ReviewInner reviewInner = gamesInner.getReviews().get(reviewList);
      assertEquals(reviewId, reviewInner.getReviewId(),"Значение reviewId не соответствует ожидаемому");
      assertEquals(score, reviewInner.getScore(),"Значение score не соответствует ожидаемому");
      assertEquals(review, reviewInner.getReview(),"Значение review не соответствует ожидаемому");
    }
  }
}
