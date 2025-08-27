package trivia.core.player;

public class Player {

  private final String name;
  private int position = 1;
  private int coins = 0;

  public Player(String name) {
    this.name = name;
  }

  public void setPosition(int position) {
    this.position = position;
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