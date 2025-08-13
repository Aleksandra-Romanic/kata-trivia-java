package trivia.core.question;

import java.util.LinkedList;

public class QuestionList {

  private final LinkedList<String> questions = new LinkedList<>();

  public void add(String question) {
    questions.add(question);
  }

  public String next() {
    return questions.removeFirst();
  }
}
