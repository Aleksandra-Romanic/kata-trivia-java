package trivia;

// REFACTOR ME
public class Game implements IGame {

   private final QuestionManager questionManager;
   private final PlayerManager playerManager;
   private final PenaltyBoxHandler penaltyBoxHandler;

   public Game(){
      this.questionManager = new QuestionManager();
      this.playerManager = new PlayerManager();
      this.penaltyBoxHandler = new PenaltyBoxHandler();
   }

   public boolean add(String playerName) {
      return playerManager.addPlayer(playerName);
   }

   public void roll(int roll) {
      Player player = playerManager.getCurrentPlayer();
      System.out.println(playerManager.getCurrentPlayer().getName() + " is the current player");
      System.out.println("They have rolled a " + roll);
      boolean canPlay = penaltyBoxHandler.handleJail(player, roll);
      if (canPlay) {
         processTurn(player, roll);
      }
   }

   private void processTurn(Player player, int roll) {
      player.move(roll);
      System.out.println(player.getName()
          + "'s new location is "
          + player.getPosition());
      System.out.println("The category is " + currentCategory().getDisplayName());
      askQuestion();
   }

   private void askQuestion() {
      Category category = currentCategory();
      String question = questionManager.getNextQuestion(category);
      System.out.println(question);
   }

   private Category currentCategory() {
      int position = playerManager.getCurrentPlayer().getPosition();
      return Category.currentCategory(position);
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

   public void correctAnswer(){
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
