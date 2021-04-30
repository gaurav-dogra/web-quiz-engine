package engine;

import engine.model.Quiz;

import java.util.List;

public class ServerResponseQuiz {
    private long id;
    private String title;
    private String text;
    private String[] options;


    public ServerResponseQuiz(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.text = quiz.getTitle();
        this.options = quiz.getOptions();
    }

    public ServerResponseQuiz() {
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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
