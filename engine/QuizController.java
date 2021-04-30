package engine;

import engine.model.Quiz;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

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
        return new QuizService().save(quiz);
    }

}
