package trivia.core.game;

import trivia.adapter.AnswerHandler;
import trivia.core.player.PenaltyBox;
import trivia.core.player.Player;
import trivia.core.player.PlayerService;
import trivia.core.question.QuestionService;

// REFACTOR ME
public class Game implements IGame {

  private static final int WINNING_COINS = 6;
  private final PlayerService players;
  private final PenaltyBox penaltyBox;
  private final TurnService turnService;
  private final AnswerHandler answerHandler;


  public Game() {
    QuestionService questionService = new QuestionService();
    Board board = new Board();
    this.players = new PlayerService();
    this.penaltyBox = new PenaltyBox();
    this.turnService = new TurnService(questionService, board);
    this.answerHandler = new AnswerHandler(this.players, this.penaltyBox);
  }

  @Override
  public boolean add(String playerName) {
    players.addPlayer(playerName);
    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.count());

    return true;
  }

  @Override
  public void roll(int roll) {
    Player player = players.getCurrentPlayer();
    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (penaltyBox.hasImprisoned(player)) {
      penaltyBox.tryToGetOut(player, roll);
    }
    if (!penaltyBox.hasImprisoned(player)) {
      turnService.processTurn(player, roll);
    }
  }

  @Override
  public boolean handleCorrectAnswer() {
    Player player = players.getCurrentPlayer();
    answerHandler.handleCorrectAnswer();
    return !hasCurrentPlayerWon(player);
  }

  @Override
  public boolean wrongAnswer() {
    return answerHandler.handleWrongAnswer();
  }

  private boolean hasCurrentPlayerWon(Player player) {
    return player.getCoins() == WINNING_COINS;
  }
}