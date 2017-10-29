package mk.ukim.finki.aud5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class FindOldest {
    private static final String str = "Кристијан 25\n" +
            "Дритон 39\n" +
            "Ристе 17\n" +
            "Лусијана 28\n" +
            "Бобан 7\n" +
            "Оливера 71\n" +
            "Ана 14\n" +
            "Димитар 56\n" +
            "Диме 11\n" +
            "Билјана 12";

    private static void writeToFile(final String fileName) throws FileNotFoundException {
        try (PrintWriter in = new PrintWriter(new FileOutputStream(new File(fileName)))) {
            in.print(str);
        }
    }

    private static String processFile(final String fileName) throws IOException {
        String maxName = null;
        int maxAge = Integer.MIN_VALUE;
        try (BufferedReader out = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = out.readLine()) != null && !(line.length() == 0)) {
                String[] parts = line.split("\\s+");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                if (maxAge <= age) {
                    maxAge = age;
                    maxName = name;
                }
            }
        }
        return String.format("%s %d", maxName, maxAge);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Arguments must be provided!");
            return;
        }
        for (String arg : args) {
            writeToFile(arg);
            System.out.println(processFile(arg));
        }
    }
}
