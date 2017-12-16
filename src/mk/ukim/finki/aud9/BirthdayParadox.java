package mk.ukim.finki.aud9;

import java.util.*;

public class BirthdayParadox {
    private static int MAX_TRIALS = 5000;
    private final int maxPeople;

    public BirthdayParadox(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public static void setMaxTrials(int maxTrials) {
        MAX_TRIALS = maxTrials;
    }

    public Map<Integer, Double> simulate() {
        Map<Integer, Double> probabilities = new TreeMap<>();
        for (int i = 2; i <= maxPeople; ++i) {
            double probability = simulation(i, MAX_TRIALS);
            probabilities.put(i, probability);
        }
        return probabilities;
    }

    private double simulation(int numPeople, int maxTrials) {
        int positiveEvents = 0;
        for (int i = 0; i < maxTrials; ++i)
            if (singleExperiment(numPeople))
                ++positiveEvents;
        return positiveEvents / (double) maxTrials;
    }

    private boolean singleExperiment(int numPeople) {
        int sameBirthday = 0;
        Set<Integer> room = new HashSet<>();
        for (int i = 0; i < numPeople; ++i) {
            int birthday = new Random().nextInt(365) + 1;  // 1 - 365
            if (!room.add(birthday))
                ++sameBirthday;
        }
        return sameBirthday >= 1;
    }

    public static void main(String[] args) {
        BirthdayParadox birthdayParadox = new BirthdayParadox(50);
        Map<Integer, Double> probabilities = birthdayParadox.simulate();
        probabilities.forEach((key, value) -> System.out.printf("For %d people, the probability of two birthdays is " +
                "about %.3f \n", key, value));
    }
}
