package engine;

import engine.exceptions.BadRequestException;
import engine.exceptions.NotFoundException;
import engine.model.Quiz;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    @Autowired
    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @PostMapping("/api/quizzes")
    public ServerResponseQuiz createQuiz(@Valid @RequestBody Quiz quiz) throws NotFoundException {
        System.out.println("QuizController.createQuiz");
        quiz.setUser(getCurrentUser());
        return quizService.save(quiz);
    }

    private User getCurrentUser() throws NotFoundException {
        System.out.println("QuizController.getCurrentUser");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByEmail(currentPrincipalName);
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
        int[] answerParameter = answer.getAnswer() == null ? new int[]{} : answer.getAnswer();
        if (quizService.quizExist(id)) {
            return quizService.solve(id, answerParameter);
        } else {
            throw new NotFoundException(id + " Quiz does not exist");
        }
    }

    @PostMapping("/api/register")
    public void registerNewUser(@Valid @RequestBody User user) throws BadRequestException {
        System.out.println("QuizController.registerNewUser");
        if (userService.exist(user)) {
            throw new BadRequestException(user.getEmail() + " already exist");
        }
        userService.save(user);
    }
}
