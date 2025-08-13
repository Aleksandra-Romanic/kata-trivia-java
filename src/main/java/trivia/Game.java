package trivia;

// REFACTOR ME
public class Game implements IGame {

  private final PlayerManager playerManager;
  private final PenaltyBoxHandler penaltyBoxHandler;
  private final TurnManager turnManager;
  private final AnswerHandler answerHandler;

  public Game() {
    QuestionManager questionManager = new QuestionManager();
    this.playerManager = new PlayerManager();
    this.penaltyBoxHandler = new PenaltyBoxHandler();
    this.turnManager = new TurnManager(questionManager);
    this.answerHandler = new AnswerHandler(this.playerManager);
  }

  @Override
  public boolean add(String playerName) {
    return playerManager.addPlayer(playerName);
  }

  @Override
  public void roll(int roll) {
    Player player = playerManager.getCurrentPlayer();

    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    boolean getsOut = penaltyBoxHandler.handleJail(player, roll);
    if (getsOut) {
      turnManager.processTurn(player, roll);
    }
  }

  @Override
  public boolean handleCorrectAnswer() {
    return answerHandler.handleCorrectAnswer();
  }

  @Override
  public boolean wrongAnswer() {
    return answerHandler.handleWrongAnswer();
  }
}
