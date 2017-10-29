package mk.ukim.finki.aud5;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BinaryNumbers {
    private static final String fileName = "asd4.dat";

    private static void generateFile(int n) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            Random rnd = new Random();
            for (int i = 0; i < n; ++i)
                oos.writeInt(rnd.nextInt(1000));
        }
    }

    private static double findNumbersAverage() throws IOException {
        double avg = 0;
        int numbers = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            try {
                while (true) {
                    avg += ois.readInt();
                    ++numbers;
                }
            } catch (EOFException e) {
                System.out.println("All numbers are read. :)");
            }
        }
        return numbers == 0 ? 0.00 : avg / numbers;
    }

    public static void main(String[] args) throws IOException {
        try (Scanner intScanner = new Scanner(new InputStreamReader(System.in))) {
            generateFile(intScanner.nextInt());
            System.out.println("Average: " + findNumbersAverage());
        }
    }
}
