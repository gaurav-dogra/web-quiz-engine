package engine.repositoryServices;

import engine.CompletionsRepository;
import engine.model.Completions;
import engine.model.Quiz;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompletionsService {

    private final CompletionsRepository completionsRepository;

    @Autowired
    public CompletionsService(CompletionsRepository completionsRepository) {
        this.completionsRepository = completionsRepository;
    }

    public void save(Quiz quiz, LocalDateTime localDateTime, User user) {
        completionsRepository.save(new Completions(quiz, localDateTime, user));
    }

    public Page<Completions> getCompletions(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return completionsRepository.findAll(paging);
    }
}
