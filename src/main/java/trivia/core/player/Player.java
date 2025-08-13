package trivia.core.player;

public class Player {

  private final String name;
  private int position = 1;
  private int coins = 0;
  private static final int BOARD_SIZE = 12;
  private static final int WINNING_COINS = 6;
  private boolean inPenaltyBox = false;
  private boolean gettingOutOfPenaltyBox = false;

  public Player(String name) {
    this.name = name;
  }

  public void move(int roll) {
    position = ((position - 1 + roll) % BOARD_SIZE) + 1;
  }

  public void addCoins() {
    coins++;
  }

  public String getName() {
    return name;
  }

  public int getCoins() {
    return coins;
  }

  public int getPosition() {
    return position;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }

  public void sendToPenaltyBox() {
    inPenaltyBox = true;
  }

  public void releaseFromPenaltyBox() {
    this.gettingOutOfPenaltyBox = true;
  }

  public void keepInPenaltyBox() {
    this.gettingOutOfPenaltyBox = false;
  }

  public boolean isGettingOutOfPenaltyBox() {
    return gettingOutOfPenaltyBox;
  }

  public boolean hasWon() {
    return coins == WINNING_COINS;
  }

  public void penalize() {
    System.out.println(getName() + " was sent to the penalty box");
    sendToPenaltyBox();
  }
}



