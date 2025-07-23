package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
   List<Player> players = new ArrayList<>();
   int currentPlayerIndex = 0;

   boolean isGettingOutOfPenaltyBox;

   LinkedList<String> popQuestions = new LinkedList<>();
   LinkedList<String> scienceQuestions = new LinkedList<>();
   LinkedList<String> sportsQuestions = new LinkedList<>();
   LinkedList<String> rockQuestions = new LinkedList<>();

   public Game() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
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

   if (player.isInPenaltyBox()) {
      if (roll % 2 != 0) {
         isGettingOutOfPenaltyBox = true;
         System.out.println(
             players.get(currentPlayerIndex).getName() + " is getting out of the penalty box");
         player.move(roll);
         System.out.println(players.get(currentPlayerIndex).getName()
             + "'s new location is "
             + player.getPosition());
         System.out.println("The category is " + currentCategory());
         askQuestion();
      } else {
         System.out.println(player.getName() + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
      }

   } else {
      player.move(roll);
      System.out.println(player.getName()
          + "'s new location is "
          + player.getPosition());
      System.out.println("The category is " + currentCategory());
      askQuestion();
   }
}

   private void askQuestion() {
     switch (currentCategory()) {
       case "Pop" -> System.out.println(popQuestions.removeFirst());
       case "Science" -> System.out.println(scienceQuestions.removeFirst());
       case "Sports" -> System.out.println(sportsQuestions.removeFirst());
       case "Rock" -> System.out.println(rockQuestions.removeFirst());
       default -> throw new IllegalArgumentException("Unknown category: " + currentCategory());
     }
   }

   private String currentCategory() {
      int position = players.get(currentPlayerIndex).getPosition();
      if (position - 1 == 0) return "Pop";
      if (position - 1 == 4) return "Pop";
      if (position - 1  == 8) return "Pop";
      if (position - 1  == 1) return "Science";
      if (position - 1 == 5) return "Science";
      if (position - 1 == 9) return "Science";
      if (position - 1 == 2) return "Sports";
      if (position - 1 == 6) return "Sports";
      if (position - 1 == 10) return "Sports";
      return "Rock";
   }

   public boolean handleCorrectAnswer() {
      Player player = currentPlayer();
      if (player.isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            player.addCoins();
            System.out.println(player.getName()
                               + " now has "
                               + player.getCoins()
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            return winner;
         } else {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            return true;
         }
      } else {
         System.out.println("Answer was correct!!!!");
         player.addCoins();
         System.out.println(players.get(currentPlayerIndex).getName()
                            + " now has "
                            + player.getCoins()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayerIndex++;
         if (currentPlayerIndex == players.size()) currentPlayerIndex= 0;
         return winner;
      }
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
