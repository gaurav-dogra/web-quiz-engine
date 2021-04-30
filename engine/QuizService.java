package engine;

import engine.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizService {
    private final List<Quiz> quizzes = new ArrayList<>();

    public ServerResponseQuiz save(Quiz quiz) {
        quizzes.add(quiz);
        return new ServerResponseQuiz(quizzes);
    }
}
