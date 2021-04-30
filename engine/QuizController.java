package engine;

import engine.model.Quiz;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    private QuizService quizService = new QuizService();

    @GetMapping("/api/quiz")
    public Quiz getQuiz() {
        return new Quiz();
    }

    @PostMapping("/api/quiz")
    public Reply solveQuiz(@RequestParam int answer) {
        if (answer == 2) {
            return Reply.CORRECT_ANSWER;
        } else {
            return Reply.INCORRECT_ANSWER;
        }
    }

    @PostMapping("/api/quizzes")
    public ServerResponseQuiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.save(quiz);
    }

    @GetMapping("/api/quizzes/{id}")
    public ServerResponseQuiz getQuizById(@PathVariable long id) {
        return quizService.get(id);
    }

}
