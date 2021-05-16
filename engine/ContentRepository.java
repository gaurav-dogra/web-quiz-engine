package engine;

import engine.model.Completions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Completions, Long> {
}
