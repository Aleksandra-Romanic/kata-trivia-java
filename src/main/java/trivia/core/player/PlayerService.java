package trivia.core.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

  private final List<Player> players = new ArrayList<>();
  private int currentPlayerIndex = 0;

  public void addPlayer(String playerName) {
    players.add(new Player(playerName));
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  public void nextPlayer() {
    currentPlayerIndex++;
    if (currentPlayerIndex == players.size()) {
      currentPlayerIndex = 0;
    }
  }

  public int count() {
    return players.size();
  }
}