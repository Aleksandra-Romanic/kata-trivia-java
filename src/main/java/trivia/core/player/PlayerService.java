package trivia.core.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

  private final List<Player> players = new ArrayList<>();
  private int currentPlayerIndex = 0;

  public boolean addPlayer(String playerName) {
    players.add(new Player(playerName));
    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
    return true;
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

  public boolean hasCurrentPlayerWon() {
    return getCurrentPlayer().hasWon();
  }
}
