public class Riddle {
    private String question;
    private String answer;

    public Riddle(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase();
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String attempt) {
        return attempt.equals(answer);
    }
}
