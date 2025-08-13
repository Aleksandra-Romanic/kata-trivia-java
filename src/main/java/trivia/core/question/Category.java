package trivia.core.question;

public enum Category {
  POP("Pop"),
  SCIENCE("Science"),
  SPORTS("Sports"),
  ROCK("Rock");

  private final String displayName;

  Category(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static Category currentCategory(int position) {
    int index = (position - 1) % 4;
    return switch (index) {
      case 0 -> POP;
      case 1 -> SCIENCE;
      case 2 -> SPORTS;
      default -> ROCK;
    };
  }

}
