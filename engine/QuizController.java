package engine;

import engine.exceptions.NotFoundException;
import engine.model.Quiz;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    private final QuizService quizService = new QuizService();

    @PostMapping("/api/quizzes")
    public ServerResponseQuiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.save(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public ServerResponseQuiz getQuizById(@PathVariable long id) throws NotFoundException {
        ServerResponseQuiz serverResponseQuiz = quizService.getQuizById(id);
        if (serverResponseQuiz == null) {
            throw new NotFoundException(id + " Quiz does not exist");
        } else {
            return serverResponseQuiz;
        }
    }

    @GetMapping("/api/quizzes")
    public List<ServerResponseQuiz> getAll() {
        return quizService.getAll();
    }

}
