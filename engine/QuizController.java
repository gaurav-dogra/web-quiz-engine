package engine;

import engine.exceptions.BadRequestException;
import engine.exceptions.NotFoundException;
import engine.model.Completions;
import engine.model.Quiz;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;
    private final ContentService contentService;

    @Autowired
    public QuizController(QuizService quizService, UserService userService, ContentService contentService) {
        this.quizService = quizService;
        this.userService = userService;
        this.contentService = contentService;
    }

    @PostMapping("/api/register")
    public void registerNewUser(@Valid @RequestBody User user) throws BadRequestException {
        System.out.println("QuizController.registerNewUser");
        if (userService.exist(user)) {
            throw new BadRequestException(user.getEmail() + " already exist");
        }
        userService.save(user);
    }

    @PostMapping("/api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        System.out.println("QuizController.createQuiz");
        quiz.setUser(getCurrentUser());
        return quizService.save(quiz);
    }

    private User getCurrentUser() {
        System.out.println("QuizController.getCurrentUser");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByEmail(currentPrincipalName);
    }

    @GetMapping("/api/quizzes")
    public Page<Quiz> getAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        System.out.println("QuizController.getAll");
        return quizService.getAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) throws NotFoundException {
        System.out.println("QuizController.getQuizById");
        if (quizService.quizExist(id)) {
            return quizService.getQuizById(id);
        } else {
            throw new NotFoundException(id + " Quiz does not exist");
        }
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Reply solve(@PathVariable long id, @RequestBody AnswerParameter answer) throws NotFoundException {
        System.out.println("QuizController.solve");
        int[] answerParameter = answer.getAnswer() == null ? new int[]{} : answer.getAnswer();
        if (quizService.quizExist(id)) {
            Reply reply = quizService.solve(id, answerParameter);
            if (reply.isSuccess()) {
                Quiz quiz = getQuizById(id);
                contentService.save(quiz, LocalDateTime.now(), getCurrentUser());
            }
            return reply;
        } else {
            throw new NotFoundException(id + " Quiz does not exist");
        }
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<HttpStatus> deleteQuiz(@PathVariable long id) {
        System.out.println("QuizController.deleteQuiz");
        if (quizService.quizExist(id)) {
            long ownerUserId = quizService.getOwnerUser(id).getId();
            long contextUserId = getCurrentUser().getId();
            if (ownerUserId == contextUserId) {
                quizService.deleteQuizById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/quizzes/completed")
    public Page<Completions> completions(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        System.out.println("QuizController.completions");
        return contentService.getCompletions(pageNo, pageSize, sortBy);
    }
}
