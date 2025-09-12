package lesson9.model;

import java.util.List;

public class GameReviews {
  private List<GamesInner> games;

  public List<GamesInner> getGames() {
    return games;
  }

  public void setGames(List<GamesInner> games) {
    this.games = games;
  }
}