package engine;

import engine.model.Quiz;

import java.util.Arrays;
import java.util.List;

public class ServerResponseQuiz {
    private long id;
    private String title;
    private String text;
    private List<String> options;

    public ServerResponseQuiz() {
    }

    public static ServerResponseQuiz valueOf(Quiz quiz) {
        ServerResponseQuiz serverResponseQuiz = new ServerResponseQuiz();
        serverResponseQuiz.id = quiz.getId();
        serverResponseQuiz.title = quiz.getTitle();
        serverResponseQuiz.text = quiz.getText();
        serverResponseQuiz.options = quiz.getOptions();
        return serverResponseQuiz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ServerResponseQuiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}
