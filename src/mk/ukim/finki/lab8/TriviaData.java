package mk.ukim.finki.lab8;

import java.util.ArrayList;

class TriviaData {
    private ArrayList<TriviaQuestion> triviaQuestions;

    public TriviaData() {
        triviaQuestions = new ArrayList<>();
    }

    public int numQuestions() {
        return triviaQuestions.size();
    }

    public TriviaQuestion getQuestion(int index) {
        TriviaQuestion tmp = triviaQuestions.get(index);
        removeQuestion(index);
        return tmp;
    }

    public void addQuestion(String question, String answer, int points, String questionType) {
        if (questionType.toUpperCase().trim().equals("FREEFORM"))
            triviaQuestions.add(new FreeFormTriviaQuestion(question, answer, points));
        else if (questionType.toUpperCase().trim().equals("TRUEFALSE"))
            triviaQuestions.add(new TrueFalseTriviaQuestion(question, answer, points));
        else throw new UnsupportedOperationException(questionType);
    }

    public void removeQuestion(int index) {
        triviaQuestions.remove(index);
    }

    public void showQuestion(int index) {
        TriviaQuestion question = triviaQuestions.get(index);
        System.out.println("Question " + (index + 1) + ".  " + question.getPoints() + " points.");
        System.out.println(question.getQuestion());
        if (question instanceof TrueFalseTriviaQuestion)
            System.out.println("Enter 'T' for true or 'F' for false.");
    }
}
