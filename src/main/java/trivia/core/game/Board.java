package trivia.core.game;

import static trivia.core.question.Category.POP;
import static trivia.core.question.Category.ROCK;
import static trivia.core.question.Category.SCIENCE;
import static trivia.core.question.Category.SPORTS;

import trivia.core.question.Category;

public class Board {

  private static final int SIZE = 12;

  public int nextPosition(int currentPosition, int roll) {
    return ((currentPosition - 1 + roll) % SIZE) + 1;
  }

  public Category getCategoryInPlace(int position) {
      int index = (position - 1) % 4;
      return switch (index) {
        case 0 -> POP;
        case 1 -> SCIENCE;
        case 2 -> SPORTS;
        default -> ROCK;
      };
    }
  }

