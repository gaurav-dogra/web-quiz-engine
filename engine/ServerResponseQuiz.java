package engine;

import engine.model.Quiz;

import java.util.List;

public class ServerResponseQuiz {
    private long id;
    private String title;
    private String text;
    private String[] options;

    public ServerResponseQuiz() {
    }

    public static ServerResponseQuiz valueOf(Quiz quiz) {
        ServerResponseQuiz serverResponseQuiz = new ServerResponseQuiz();
        serverResponseQuiz.id = quiz.getId();
        serverResponseQuiz.title = quiz.getTitle();
        serverResponseQuiz.text = quiz.getTitle();
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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
