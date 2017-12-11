package mk.ukim.finki.lab8;

import java.util.Random;
import java.util.Scanner;

public class TriviaGame {
    private TriviaData questions;  // Questions

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.addQuestion(
                "The possession of more than two sets of chromosomes is termed?", "polyploidy",
                3, "FreeForm");
        questions.addQuestion("Erling Kagge skied into the north pole alone on January 7, 1993.", "F",
                1, "TrueFalse");
        questions.addQuestion("1997 British band that produced 'Tub Thumper'", "Chumbawumba",
                2, "FreeForm");
        questions.addQuestion("I am the geometric figure most like a lost parrot", "polygon",
                2, "FreeForm");
        questions.addQuestion("Generics were introduced to Java starting at version 5.0.", "T",
                1, "TrueFalse");
    }

    // Main game loop
    public static void main(String[] args) {
        int score = 0;  // Overall score
        TriviaGame game = new TriviaGame();
        int totalQuestions = game.questions.numQuestions();         // Total number of questions
        int questionNumber = new Random().nextInt(totalQuestions);    // Random question we're asking
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (true) {
            // Show question
            game.questions.showQuestion(questionNumber);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            TriviaQuestion question = game.questions.getQuestion(questionNumber);
            if (question instanceof TrueFalseTriviaQuestion) {
                if (answer.toLowerCase().charAt(0) == question.getAnswer().toLowerCase().charAt(0)) {
                    System.out.println("That is correct! You get " + question.getPoints() + " points.");
                    score += question.getPoints();
                } else
                    System.out.println("Wrong, the correct answer is " + question.getAnswer());
            } else if (question instanceof FreeFormTriviaQuestion) {
                if (answer.toLowerCase().equals(question.getAnswer().toLowerCase())) {
                    System.out.println("That is correct! You get " + question.getPoints() + " points.");
                    score += question.getPoints();
                } else
                    System.out.println("Wrong, the correct answer is " + question.getAnswer());
            }
            System.out.println("Your score is " + score);
            --totalQuestions;
            if (totalQuestions <= 0)
                break;
            questionNumber = new Random().nextInt(totalQuestions);  // Random question we're asking
        }
        System.out.println("Game over! Thanks for playing!");
    }
}
