package trivia;

import java.util.ArrayList;

import java.util.List;


// REFACTOR ME
public class Game implements IGame {
   List<Player> players = new ArrayList<>();
   int currentPlayerIndex = 0;

   private final QuestionManager questionManager = new QuestionManager();

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      Player player = currentPlayer();
      System.out.println(players.get(currentPlayerIndex).getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      handleJail(player, roll);
   }

   private void handleJail(Player player, int roll) {
      if (!player.isInPenaltyBox()) {
         processTurn(player, roll);
         return;
      }
      if (isOdd(roll)) {
         player.releaseFromPenaltyBox();
         System.out.println(
             players.get(currentPlayerIndex).getName() + " is getting out of the penalty box");
         processTurn(player, roll);
      } else {
         System.out.println(player.getName() + " is not getting out of the penalty box");
         player.keepInPenaltyBox();
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

   private boolean isOdd(int roll) {
      return roll % 2 != 0;
   }

   private void askQuestion() {
      Category category = currentCategory();
      String question = questionManager.getNextQuestion(category);
      System.out.println(question);
   }

   private Category currentCategory() {
      int position = currentPlayer().getPosition();
      return Category.currentCategory(position);
   }

   public boolean handleCorrectAnswer() {
      Player player = currentPlayer();
      if (player.isInPenaltyBox()) {
         if (player.isGettingOutOfPenaltyBox()) {
            correctAnswer();
            boolean winner = didPlayerWin();
            advanceToNextPlayer();
            return winner;
         } else {
            advanceToNextPlayer();
            return true;
         }
      } else {
         correctAnswer();
         boolean winner = didPlayerWin();
         advanceToNextPlayer();
         return winner;
      }
   }

   public void correctAnswer(){
      Player player = currentPlayer();
      System.out.println("Answer was correct!!!!");
      player.addCoins();
      System.out.println(player.getName()
          + " now has "
          + player.getCoins()
          + " Gold Coins.");
   }

   private void advanceToNextPlayer() {
      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) currentPlayerIndex= 0;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayerIndex).getName() + " was sent to the penalty box");
      currentPlayer().sendToPenaltyBox();

      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
      return true;
   }

   private boolean didPlayerWin() {
      return !(currentPlayer().getCoins() == 6);
   }
}
