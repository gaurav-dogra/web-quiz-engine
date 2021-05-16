package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Content implements Comparable<Content> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long completionId;
    @ManyToOne
    @JoinColumn(name = "quiz")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Quiz quiz;
    private LocalDateTime completedAt;
    @ManyToOne
    @JoinColumn(name = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public Content(Quiz quiz, LocalDateTime completedAt, User user) {
        this.quiz = quiz;
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


    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public Long getCompletionId() {
        return completionId;
    }

    public void setCompletionId(Long completionId) {
        this.completionId = completionId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
