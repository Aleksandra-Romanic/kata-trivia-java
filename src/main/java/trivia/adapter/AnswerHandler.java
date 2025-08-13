package trivia.adapter;

import trivia.core.player.Player;
import trivia.core.player.PlayerService;

public class AnswerHandler {

  private final PlayerService playerService;

  public AnswerHandler(PlayerService playerService) {
    this.playerService = playerService;
  }

  public boolean handleCorrectAnswer() {
    Player player = playerService.getCurrentPlayer();

    if (player.isInPenaltyBox() && !player.isGettingOutOfPenaltyBox()) {
        playerService.nextPlayer();
        return true;
    }

      processCorrectAnswer(player);
      boolean playerWon = playerService.hasCurrentPlayerWon();
      playerService.nextPlayer();
      return !playerWon;
    }

  public boolean handleWrongAnswer() {
    Player player = playerService.getCurrentPlayer();
    System.out.println("Question was incorrectly answered");
    player.penalize();
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
