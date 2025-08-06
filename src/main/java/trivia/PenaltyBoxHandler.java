package trivia;

public class PenaltyBoxHandler {

  public boolean handleJail(Player player, int roll) {
    if (!player.isInPenaltyBox()) {
      return true;
    }
    if (isOdd(roll)) {
      player.releaseFromPenaltyBox();
      System.out.println(player.getName() + " is getting out of the penalty box");
      return true;
    } else {
      System.out.println(player.getName() + " is not getting out of the penalty box");
      player.keepInPenaltyBox();
      return false;
    }
  }

  private boolean isOdd(int number) {
    return number % 2 != 0;
  }
}
