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
    int index = (position - 1) % 12;
    if (index == 0) return POP;
    if (index == 4) return POP;
    if (index == 8) return POP;
    if (index == 1) return SCIENCE;
    if (index == 5) return SCIENCE;
    if (index == 9) return SCIENCE;
    if (index == 2) return SPORTS;
    if (index == 6) return SPORTS;
    if (index == 10) return SPORTS;
    return ROCK;
  }
}
