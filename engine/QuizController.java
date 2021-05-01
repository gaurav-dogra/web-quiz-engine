package engine;

import engine.exceptions.NotFoundException;
import engine.model.Quiz;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class QuizController {

    private final QuizService quizService = new QuizService();

    @PostMapping("/api/quizzes")
    public ServerResponseQuiz createQuiz(@Valid @RequestBody Quiz quiz) {
        System.out.println("QuizController.createQuiz");
        return quizService.save(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public ServerResponseQuiz getQuizById(@PathVariable long id) throws NotFoundException {
        System.out.println("QuizController.getQuizById");
        if (quizService.quizExist(id)) {
            return quizService.getQuizById(id);
        } else {
            throw new NotFoundException(id + " Quiz does not exist");
        }
    }

    @GetMapping("/api/quizzes")
    public List<ServerResponseQuiz> getAll() {
        System.out.println("QuizController.getAll");
        return quizService.getAll();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Reply solve(@PathVariable long id, @RequestBody AnswerParameter answer) throws NotFoundException {
        System.out.println("QuizController.solve");
        System.out.println("id = " + id);
        System.out.println("answer = " + Arrays.toString(answer.getAnswer()));
        if (quizService.quizExist(id)) {
            return quizService.solve(id, answer.getAnswer());
        } else {
            throw new NotFoundException(id + " Quiz does not exist");
        }
    }
}
