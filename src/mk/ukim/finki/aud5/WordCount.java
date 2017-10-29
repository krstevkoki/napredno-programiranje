package mk.ukim.finki.aud5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordCount {
    private static final String str = "osodfosid fosdk fosdkf ksdlf sdklf\n" +
            ";lsjd;klsjd ;las\n" +
            "sidhfuihd uisfh \n" +
            "h     uisdhfuisdhfiusdh fuishd fiushdi ufhsduih fisduh ifhsd ihfsdihfsidufhsi h\n" +
            "hsidufhuisd hfuisdh uihsdf\n" +
            "hsudfhsduihfsui hif hsdui h\n" +
            "sdui    fhsuidhf isdhfiusdh fiusdhi fh\n" +
            "shduif hsuid  fhisdhifushi";

    private static void writeToFile(final String fileName) throws FileNotFoundException {
        try (PrintWriter in = new PrintWriter(new FileOutputStream(fileName))) {
            in.println(str);
        }
    }

    private static String processFile(final String fileName) throws IOException {
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = bf.readLine()) != null && !(line.length() == 0)) {
                ++lineCount;
                String[] words = line.split("\\s+");
                wordCount += words.length;
                charCount += line.length() + 1;
            }
        }
        return String.format("%d %d %d", charCount, wordCount, lineCount);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Arguments must be provided !!!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            writeToFile(arg);
            sb.append(processFile(arg));
        }
        System.out.println(sb.toString());
    }
}
