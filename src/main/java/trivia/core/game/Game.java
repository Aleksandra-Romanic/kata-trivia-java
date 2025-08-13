package trivia.core.game;

import trivia.adapter.AnswerHandler;
import trivia.core.player.PenaltyBoxHandler;
import trivia.core.player.Player;
import trivia.core.player.PlayerService;
import trivia.core.question.QuestionService;

// REFACTOR ME
public class Game implements IGame {

  private final PlayerService playerService;
  private final PenaltyBoxHandler penaltyBoxHandler;
  private final TurnService turnService;
  private final AnswerHandler answerHandler;

  public Game() {
    QuestionService questionService = new QuestionService();
    this.playerService = new PlayerService();
    this.penaltyBoxHandler = new PenaltyBoxHandler();
    this.turnService = new TurnService(questionService);
    this.answerHandler = new AnswerHandler(this.playerService);
  }

  @Override
  public boolean add(String playerName) {
    return playerService.addPlayer(playerName);
  }

  @Override
  public void roll(int roll) {
    Player player = playerService.getCurrentPlayer();

    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    boolean getsOut = penaltyBoxHandler.handleJail(player, roll);
    if (getsOut) {
      turnService.processTurn(player, roll);
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
