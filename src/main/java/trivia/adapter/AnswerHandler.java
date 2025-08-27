package trivia.adapter;

import trivia.core.player.PenaltyBox;
import trivia.core.player.Player;
import trivia.core.player.PlayerService;

public class AnswerHandler {

  private final PlayerService playerService;
  private final PenaltyBox penaltyBox;

  public AnswerHandler(PlayerService playerService, PenaltyBox penaltyBox) {
    this.playerService = playerService;
    this.penaltyBox = penaltyBox;
  }

  public void handleCorrectAnswer() {
    Player player = playerService.getCurrentPlayer();

    if (penaltyBox.hasImprisoned(player)) {
      playerService.nextPlayer();
      return;
    }
    processCorrectAnswer(player);
    playerService.nextPlayer();
  }

  public boolean handleWrongAnswer() {
    Player player = playerService.getCurrentPlayer();
    System.out.println("Question was incorrectly answered");
    penaltyBox.imprison(player);
    playerService.nextPlayer();
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
