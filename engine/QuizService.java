package engine;

import engine.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        return ServerResponseQuiz.valueOf(quizzes.get(id));
    }

    public List<ServerResponseQuiz> getAll() {
        List<ServerResponseQuiz> result = new ArrayList<>();
        for (Quiz quiz : quizzes.values()) {
            result.add(ServerResponseQuiz.valueOf(quiz));
        }
        return result;
    }

    public boolean quizExist(long id) {
        Quiz quiz = quizzes.get(id);
        return quiz != null;
    }

    public Reply solve(long id, int[] parameterAnswer) {
        Quiz quiz = quizzes.get(id);
        int[] databaseAnswer = quiz.getAnswer();

        Set<Integer> databaseAnswerSet = Arrays.stream(databaseAnswer)
                    .boxed()
                    .collect(Collectors.toSet());

        Set<Integer> parameterAnswerSet = Arrays.stream(parameterAnswer)
                    .boxed()
                    .collect(Collectors.toSet());

        if (databaseAnswerSet.equals(parameterAnswerSet)) {
            return Reply.CORRECT_ANSWER;
        } else {
            return Reply.INCORRECT_ANSWER;
        }
    }
}

