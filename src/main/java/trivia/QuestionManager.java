package trivia;

import java.util.EnumMap;
import java.util.Map;

public class QuestionManager {

  private final Map<Category, QuestionList> questionsByCategory = new EnumMap<>(Category.class);

  public QuestionManager() {
    for (Category category : Category.values()) {
      QuestionList list = new QuestionList();
      for (int i = 0; i < 50; i++) {
        list.add(category.getDisplayName() + " Question " + i);
      }
      questionsByCategory.put(category, list);
    }
  }

  public String getNextQuestion(Category category) {
    return questionsByCategory.get(category).next();
  }
}
