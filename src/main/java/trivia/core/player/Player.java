package trivia.core.player;

import trivia.core.game.Board;

public class Player {

  private final String name;
  private int position = 1;
  private int coins = 0;

  public Player(String name) {
    this.name = name;
  }

  public void move(Board board, int roll) {
    int newPosition = board.nextPosition(this.position, roll);
    this.position = newPosition;
    System.out.println(name + "'s new location is " + newPosition);
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