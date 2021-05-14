package engine;

import engine.model.Content;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void save(Long questionId, LocalDateTime localDateTime, User user) {
        contentRepository.save(new Content(questionId, localDateTime, user));
    }

    public List<Content> findAll(User user) {
        List<Content> allCompletions = contentRepository.findAll();
        return allCompletions.stream()
                .filter(content -> content.getUser().getId() == user.getId())
                .sorted()
                .collect(Collectors.toList());
    }
}
