public class Riddle {
    private String riddle;
    private String answer;

    public Riddle(String riddle, String answer) {
        this.riddle = riddle;
        this.answer = answer.toLowerCase();
    }

    public String getRiddle() {
        return riddle;
    }

    public boolean checkAnswer(String attempt) {
        return attempt.equals(answer);
    }
}
