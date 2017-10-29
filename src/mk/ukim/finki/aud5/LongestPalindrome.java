package mk.ukim.finki.aud5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LongestPalindrome {
    private static final String fileName = "words.txt";

    private static boolean isPalindrome(String word) {
        for (int i = 0; i < word.length() / 2; ++i)
            if (word.charAt(i) != word.charAt(word.length() - i - 1))
                return false;
        return true;
    }

    private static String findLongest(InputStream fileName) throws IOException {
        String maxWord = null;
        ArrayList<String> palindromes = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(fileName))) {
            String line;
            while ((line = in.readLine()) != null && !(line.length() == 0)) {
                if (isPalindrome(line)) {
                    palindromes.add(line);
                    if (maxWord == null)
                        maxWord = line;
                    else if (line.length() > maxWord.length())
                        maxWord = line;
                }
            }
        }
        System.out.println("Palindromes: ");
        Collections.sort(palindromes);
        for (String palindrome : palindromes)
            System.out.print(palindrome + " ");
        System.out.println();
        return maxWord != null ? String.format("%s %d", maxWord, maxWord.length()) : "null 0";
    }

    public static void main(String[] args) {
        try {
            System.out.println("Max palindrome: " + findLongest(new FileInputStream(new File(fileName))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
