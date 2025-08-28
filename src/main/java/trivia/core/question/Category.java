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

}
