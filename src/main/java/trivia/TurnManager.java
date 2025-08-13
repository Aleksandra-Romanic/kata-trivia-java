package trivia;

public class TurnManager {

  private final QuestionManager questionManager;

  public TurnManager(QuestionManager questionManager) {
    this.questionManager = questionManager;
  }

  public void processTurn(Player player, int roll) {
    player.move(roll);
    printPlayerLocation(player);

    Category category = Category.currentCategory(player.getPosition());
    printCategory(category);

    String question = questionManager.getNextQuestion(category);
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
