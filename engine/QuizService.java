package engine;

import engine.model.Quiz;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
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

    public List<ServerResponseQuiz> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Quiz> pageResult = quizRepository.findAll(paging);
        if (pageResult.hasContent()) {
            return pageResult.getContent()
                    .stream()
                    .map(ServerResponseQuiz::valueOf)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<ServerResponseQuiz>();
        }
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

    public User getOwnerUser(long id) {
            Quiz quiz = quizRepository.findById(id).orElseThrow();
            return quiz.getUser();
    }

    public void deleteQuizById(long id) {
        quizRepository.deleteById(id);
    }
}

