package trivia.core.game;

import trivia.adapter.AnswerHandler;
import trivia.core.player.PenaltyBox;
import trivia.core.player.Player;
import trivia.core.player.PlayerService;
import trivia.core.question.Category;
import trivia.core.question.QuestionService;

// REFACTOR ME
public class Game implements IGame {

  private static final int WINNING_COINS = 6;
  private final PlayerService players;
  private final PenaltyBox penaltyBox;
  private final Board board;
  private final AnswerHandler answerHandler;
  private final QuestionService questionService;


  public Game() {
    this.questionService = new QuestionService();
    this.players = new PlayerService();
    this.penaltyBox = new PenaltyBox();
    this.board = new Board();
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
      player.move(board, roll);
      displayQuestion(player);
    }
  }

  private void displayQuestion(Player player){
    Category category = board.getCategoryInPlace(player.getPosition());
    System.out.println("The category is " + category.getDisplayName());
    String question = questionService.getNextQuestion(category);
    System.out.println(question);
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