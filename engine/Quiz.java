package engine;

public class Quiz {
    private String title;
    private String text;
    private String[] options;

    public Quiz() {
        this.title = "The Java Logo";
        this.text = "What is depicted on the Java logo?";
        this.options = new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"};
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
