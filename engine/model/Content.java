package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Content implements Comparable<Content> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long completionId;
    private Long questionId;
    private LocalDateTime completedAt;
    @ManyToOne
    @JoinColumn(name = "solvedByUser")
    private User user;

    @Override
    public int compareTo(Content o) {
        if (this.completedAt.isBefore(o.completedAt)) {
            return 1;
        } else if (this.completedAt.isAfter(o.completedAt)) {
            return -1;
        } else {
            return 0;
        }
    }

    public Content(Long id, LocalDateTime completedAt, User user) {
        this.questionId = id;
        this.completedAt = completedAt;
        this.user = user;
    }

    public Content() {
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setQuestionId(Long id) {
        this.questionId = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public User getUser() {
        return user;
    }
}
