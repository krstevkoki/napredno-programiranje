package mk.ukim.finki.lab8;

abstract class TriviaQuestion {
    private String question;       // Actual question
    private String answer;        // Answer to question
    private int points;          // Point points of question

    public TriviaQuestion() {
        question = "";
        answer = "";
        points = 0;
    }

    public TriviaQuestion(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getPoints() {
        return points;
    }
}
