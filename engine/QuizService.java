package engine;

import engine.model.Quiz;

import java.util.HashMap;
import java.util.Map;

public class QuizService {
    private final Map<Long, Quiz> quizzes = new HashMap<>();

    public ServerResponseQuiz save(Quiz quiz) {
        long id = quizzes.size() + 1;
        quiz.setId(id);
        quizzes.put(id, quiz);
        return new ServerResponseQuiz(quizzes.get(id));
    }

    public ServerResponseQuiz get(long id) {
        Quiz quiz = quizzes.get(id);
        return new ServerResponseQuiz(quiz);
    }
}

