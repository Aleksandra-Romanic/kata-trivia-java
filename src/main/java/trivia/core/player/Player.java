package trivia.core.player;

public class Player {

  private final String name;
  private int position = 1;
  private int coins = 0;
  private static final int BOARD_SIZE = 12;

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
}