package mk.ukim.finki.aud2;

import java.util.Scanner;
import java.util.stream.IntStream;

public class StringPrefix {
    public static boolean isPrefix(String str1, String str2) {
        if (str1.length() > str2.length())
            return false;
        for (int i = 0; i < str1.length(); ++i) {
            if (str1.charAt(i) != str2.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean isPrefixStream(String str1, String str2) {
        return str1.length() <= str2.length() && IntStream.range(0, str1.length()).allMatch(i -> str1.charAt(i) == str2.charAt(i));
    }

    public static void main(String[] args) {
        Scanner stringScanner = new Scanner(System.in);
        String str1 = stringScanner.nextLine();
        String str2 = stringScanner.nextLine();

        /*if (isPrefix(str1, str2))
            System.out.println(str1 + " is prefix for " + str2);
        else
            System.out.println(str1 + " is NOT prefix for " + str2);*/
        if (isPrefixStream(str1, str2))
            System.out.println(str1 + " is prefix for " + str2);
        else
            System.out.println(str1 + " is NOT prefix for " + str2);

    }
}
