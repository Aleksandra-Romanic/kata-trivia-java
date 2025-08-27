package trivia.core.game;

import trivia.core.player.Player;
import trivia.core.question.Category;
import trivia.core.question.QuestionService;

public class TurnService {

  private final QuestionService questionService;
  private final Board board;

  public TurnService(QuestionService questionService, Board board) {
    this.questionService = questionService;
    this.board = board;
  }

  public void processTurn(Player player, int roll) {
    int newPosition = board.nextPosition(player.getPosition(), roll);
    player.setPosition(newPosition);
    printPlayerLocation(player);

    Category category = board.getCategoryInPlace(newPosition);
    printCategory(category);

    String question = questionService.getNextQuestion(category);
    printQuestion(question);
  }

  private void printPlayerLocation(Player player) {
    System.out.println(player.getName()
        + "'s new location is "
        + player.getPosition());
  }

  private void printCategory(Category category) {
    System.out.println("The category is " + category.getDisplayName());
  }

  private void printQuestion(String question) {
    System.out.println(question);
  }
}
