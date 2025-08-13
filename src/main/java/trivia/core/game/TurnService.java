package trivia.core.game;

import trivia.core.player.Player;
import trivia.core.question.Category;
import trivia.core.question.QuestionService;

public class TurnService {

  private final QuestionService questionService;

  public TurnService(QuestionService questionService) {
    this.questionService = questionService;
  }

  public void processTurn(Player player, int roll) {
    player.move(roll);
    printPlayerLocation(player);

    Category category = Category.currentCategory(player.getPosition());
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
