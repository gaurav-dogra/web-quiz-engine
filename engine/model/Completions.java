package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Completions implements Comparable<Completions> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long completionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Quiz quiz;

    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Override
    public int compareTo(Completions o) {
        if (this.completedAt.isBefore(o.completedAt)) {
            return 1;
        } else if (this.completedAt.isAfter(o.completedAt)) {
            return -1;
        } else {
            return 0;
        }
    }

    public Completions(Quiz quiz, LocalDateTime completedAt, User user) {
        this.quiz = quiz;
        this.completedAt = completedAt;
        this.user = user;
    }

    public Completions() {
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
