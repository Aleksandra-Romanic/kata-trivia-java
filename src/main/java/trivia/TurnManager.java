package trivia;

public class TurnManager {

  private final QuestionManager questionManager;

  public TurnManager(QuestionManager questionManager) {
    this.questionManager = questionManager;
  }

  public void processTurn(Player player, int roll) {
    player.move(roll);
    System.out.println(player.getName()
        + "'s new location is "
        + player.getPosition());

    Category category = Category.currentCategory(player.getPosition());
    System.out.println("The category is " + category.getDisplayName());
    String question = questionManager.getNextQuestion(category);
    System.out.println(question);
  }
}
