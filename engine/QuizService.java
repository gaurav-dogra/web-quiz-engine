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

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(long id) {
        return quizRepository.findById(id).orElseThrow();
    }

    public Page<Quiz> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        System.out.println("QuizService.getAll");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return quizRepository.findAll(paging);
    }

    public boolean quizExist(long id) {
        return quizRepository.findById(id)
                .isPresent();
    }

    public Reply solve(long id, int[] parameterAnswer) {
        System.out.println("id = " + id + ", parameterAnswer = " + Arrays.toString(parameterAnswer));
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        List<Integer> databaseAnswer = quiz.getAnswer();
        System.out.println("databaseAnswer = " + databaseAnswer);
        Set<Integer> databaseAnswerSet = new HashSet<>(databaseAnswer);

        Set<Integer> parameterAnswerSet = Arrays.stream(parameterAnswer)
                .boxed()
                .collect(Collectors.toSet());

        if (databaseAnswerSet.equals(parameterAnswerSet)) {
            System.out.println("Correct");
            return Reply.CORRECT_ANSWER;
        } else {
            System.out.println("Incorrect");
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

