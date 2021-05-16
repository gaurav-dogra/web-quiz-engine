package engine;

import engine.model.Completions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletionsRepository extends JpaRepository<Completions, Long> {
}
