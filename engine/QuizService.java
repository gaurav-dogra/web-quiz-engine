package engine;

import engine.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final Map<Long, Quiz> quizzes = new HashMap<>();
    private QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public ServerResponseQuiz save(Quiz quiz) {
        Quiz quizDb = quizRepository.save(quiz);
        System.out.println("quizDb = " + quizDb);
        return ServerResponseQuiz.valueOf(quizDb);
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

//    public Reply solve(long id, int[] parameterAnswer) {
//        Quiz quiz = quizzes.get(id);
//        List<Integer> databaseAnswer = quiz.getAnswer();
//
//        Set<Integer> databaseAnswerSet = Arrays.stream(databaseAnswer)
//                    .boxed()
//                    .collect(Collectors.toSet());
//
//        Set<Integer> parameterAnswerSet = Arrays.stream(parameterAnswer)
//                    .boxed()
//                    .collect(Collectors.toSet());
//
//        if (databaseAnswerSet.equals(parameterAnswerSet)) {
//            return Reply.CORRECT_ANSWER;
//        } else {
//            return Reply.INCORRECT_ANSWER;
//        }
//    }
}

