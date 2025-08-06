package trivia;

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
    if (position - 1 == 0) return POP;
    if (position - 1 == 4) return POP;
    if (position - 1 == 8) return POP;
    if (position - 1 == 1) return SCIENCE;
    if (position - 1 == 5) return SCIENCE;
    if (position - 1 == 9) return SCIENCE;
    if (position - 1 == 2) return SPORTS;
    if (position - 1 == 6) return SPORTS;
    if (position - 1 == 10) return SPORTS;
    return ROCK;
  }
}
