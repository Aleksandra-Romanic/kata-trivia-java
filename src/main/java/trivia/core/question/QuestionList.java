package trivia.core.question;

import java.util.LinkedList;

public class QuestionList {

  private final LinkedList<String> questions = new LinkedList<>();

  public QuestionList(Category category, int numberOfQuestions) {
    for (int i = 0; i < numberOfQuestions; i++) {
      questions.add(category.getDisplayName() + " Question " + i);
    }
  }

  public String draw() {
    return questions.removeFirst();
  }
}