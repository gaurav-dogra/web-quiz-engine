package engine;

import engine.exceptions.NotFoundException;
import engine.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizService {
    private final Map<Long, Quiz> quizzes = new HashMap<>();

    public ServerResponseQuiz save(Quiz quiz) {
        long id = quizzes.size() + 1;
        quiz.setId(id);
        quizzes.put(id, quiz);
        return ServerResponseQuiz.valueOf(quiz);
    }

    public ServerResponseQuiz getQuizById(long id) {
        Quiz quiz = quizzes.get(id);
        if (quiz == null) {
            return null;
        }
        return ServerResponseQuiz.valueOf(quiz);
    }
}

