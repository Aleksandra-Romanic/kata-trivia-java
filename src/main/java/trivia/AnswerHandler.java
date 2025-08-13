package trivia;

public class AnswerHandler {

  private final PlayerManager playerManager;

  public AnswerHandler(PlayerManager playerManager) {
    this.playerManager = playerManager;
  }

  public boolean handleCorrectAnswer() {
    Player player = playerManager.getCurrentPlayer();

    if (player.isInPenaltyBox() && !player.isGettingOutOfPenaltyBox()) {
        playerManager.nextPlayer();
        return true;
    }

      processCorrectAnswer(player);
      boolean playerWon = playerManager.hasCurrentPlayerWon();
      playerManager.nextPlayer();
      return !playerWon;
    }

  public boolean handleWrongAnswer() {
    Player player = playerManager.getCurrentPlayer();
    System.out.println("Question was incorrectly answered");
    player.penalize();
    playerManager.nextPlayer();
    return true;
  }

  private void processCorrectAnswer(Player player) {
    System.out.println("Answer was correct!!!!");
    player.addCoins();
    System.out.println(player.getName()
        + " now has "
        + player.getCoins()
        + " Gold Coins.");
  }
}
