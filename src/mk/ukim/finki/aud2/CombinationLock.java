package mk.ukim.finki.aud2;

import java.util.Scanner;

public class CombinationLock {
    private int combination;
    private boolean isOpen;

    public CombinationLock(final int combination) {
        this.combination = combination;
        this.isOpen = false;
    }

    public boolean open(final int combination) {
        boolean isCorrect = this.combination == combination;
        if (isCorrect) {
            isOpen = true;
        }
        return isCorrect;
    }

    public boolean changeCombo(final int oldCombination, final int newCombination) {
        boolean isCorrectCombination = open(oldCombination);
        if (isCorrectCombination)
            this.combination = newCombination;
        isOpen = false;
        return isCorrectCombination;

    }

    public boolean isOpen() {
        return isOpen;
    }

    public static void main(String[] args) {
        Scanner intScanner = new Scanner(System.in);
        CombinationLock cl = new CombinationLock(intScanner.nextInt());
        System.out.println(cl.isOpen ? "UNLOCKED" : "LOCKED");

        System.out.println("Attempting to unlock the CombinationLock...");
        if (cl.open(5488)) {
            System.out.print("Access granted, [");
            System.out.print(cl.isOpen() ? "UNLOCKED" : "LOCKED");
            System.out.println("]");
        } else {
            System.out.print("Access denied, [");
            System.out.print(cl.isOpen() ? "UNLOCKED" : "LOCKED");
            System.out.println("]");
        }

        if (cl.changeCombo(intScanner.nextInt(), intScanner.nextInt()))
            System.out.println("Combination changed successfully");
        else
            System.out.println("Wrong combination");
        System.out.println(cl.isOpen() ? "UNLOCKED" : "LOCKED");

        if (cl.changeCombo(6586, intScanner.nextInt()))
            System.out.println("Combination changed successfully");
        else
            System.out.println("Wrong combination");
        System.out.println(cl.isOpen() ? "UNLOCKED" : "LOCKED");
    }
}
