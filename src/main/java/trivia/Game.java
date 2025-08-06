package trivia;

// REFACTOR ME
public class Game implements IGame {

  private final PlayerManager playerManager;
  private final PenaltyBoxHandler penaltyBoxHandler;
  private final TurnManager turnManager;

  public Game() {
    QuestionManager questionManager = new QuestionManager();
    this.playerManager = new PlayerManager();
    this.penaltyBoxHandler = new PenaltyBoxHandler();
    this.turnManager = new TurnManager(questionManager);
  }

  public boolean add(String playerName) {
    return playerManager.addPlayer(playerName);
  }

  public void roll(int roll) {
    Player player = playerManager.getCurrentPlayer();
    System.out.println(playerManager.getCurrentPlayer().getName() + " is the current player");
    System.out.println("They have rolled a " + roll);
     if (penaltyBoxHandler.handleJail(player, roll)) {
        turnManager.processTurn(player, roll);
     }
  }

  public boolean handleCorrectAnswer() {
    Player player = playerManager.getCurrentPlayer();
    if (player.isInPenaltyBox()) {
      if (player.isGettingOutOfPenaltyBox()) {
        correctAnswer();
        boolean winner = didPlayerWin();
        playerManager.nextPlayer();
        return winner;
      } else {
        playerManager.nextPlayer();
        return true;
      }
    } else {
      correctAnswer();
      boolean winner = didPlayerWin();
      playerManager.nextPlayer();
      return winner;
    }
  }

  public void correctAnswer() {
    Player player = playerManager.getCurrentPlayer();
    System.out.println("Answer was correct!!!!");
    player.addCoins();
    System.out.println(player.getName()
        + " now has "
        + player.getCoins()
        + " Gold Coins.");
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(playerManager.getCurrentPlayer().getName() + " was sent to the penalty box");
    playerManager.getCurrentPlayer().sendToPenaltyBox();
    playerManager.nextPlayer();
    return true;
  }

  private boolean didPlayerWin() {
    return !playerManager.hasCurrentPlayerWon();
  }
}
