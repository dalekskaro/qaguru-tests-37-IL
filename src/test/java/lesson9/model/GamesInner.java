package lesson9.model;

import java.util.List;

public class GamesInner {

  public int gameId;
  public String name;
  public List<ReviewInner> reviews;

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ReviewInner> getReviews() {
    return reviews;
  }

  public void setReviews(List<ReviewInner> reviews) {
    this.reviews = reviews;
  }
}
