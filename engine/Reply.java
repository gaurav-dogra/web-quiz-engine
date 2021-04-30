package engine;

public class Reply {
    private boolean success;
    private String feedback;
    public static final Reply CORRECT_ANSWER =
            new Reply(true, "Congratulations, you're right!");
    public static final Reply INCORRECT_ANSWER =
            new Reply(false, "Wrong answer! Please, try again.");
    ;

    public Reply(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public Reply() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
