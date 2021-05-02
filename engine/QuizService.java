package engine;

import engine.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final Map<Long, Quiz> quizzes = new HashMap<>();
    private final QuizRepository quizRepository;

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
        Quiz quizDb = quizRepository.findById(id).orElseThrow();
        return ServerResponseQuiz.valueOf(quizDb);
    }

    public List<ServerResponseQuiz> getAll() {
        return quizRepository.findAll()
                .stream()
                .map(ServerResponseQuiz::valueOf)
                .collect(Collectors.toList());
    }

    public boolean quizExist(long id) {
        return quizRepository.findById(id)
                .isPresent();
    }

    public Reply solve(long id, int[] parameterAnswer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        List<Integer> databaseAnswer = quiz.getAnswer();

        Set<Integer> databaseAnswerSet = new HashSet<>(databaseAnswer);

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

