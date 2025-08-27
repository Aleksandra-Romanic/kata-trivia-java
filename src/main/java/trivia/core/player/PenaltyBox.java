package trivia.core.player;

import java.util.HashSet;
import java.util.Set;

public class PenaltyBox {

  private final Set<Player> imprisonedPlayers = new HashSet<>();

  public void imprison(Player player) {
    imprisonedPlayers.add(player);
    System.out.println(player.getName() + " was sent to the penalty box");
  }

  public boolean hasImprisoned(Player player) {
    return imprisonedPlayers.contains(player);
  }

  public void tryToGetOut(Player player, int roll) {
    if (!hasImprisoned(player)) {
      return;
    }
    if (isOdd(roll)) {
      letOutPlayer(player);
    } else {
      System.out.println(player.getName() + " is not getting out of the penalty box");
    }
  }

  public void letOutPlayer(Player player) {
    System.out.println(player.getName() + " is getting out of the penalty box");
    imprisonedPlayers.remove(player);
  }

  private boolean isOdd(int number) {
    return number % 2 != 0;
  }
}
