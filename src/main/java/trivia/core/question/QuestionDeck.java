package trivia.core.question;

import java.util.EnumMap;
import java.util.Map;

public class QuestionDeck {

  private final Map<Category, QuestionList> questionsByCategory = new EnumMap<>(Category.class);

  public QuestionDeck(int numberOfQuestionsPerCategory) {
    for (Category category : Category.values()) {
      questionsByCategory.put(category, new QuestionList(category, numberOfQuestionsPerCategory));
    }
  }

  public String drawQuestion(Category category) {
    return questionsByCategory.get(category).draw();
  }
}