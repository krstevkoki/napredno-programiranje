package mk.ukim.finki.aud1;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverter {
    public static final int[] decimal = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    public static final String[] letters = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public static String toRoman(int num) {
        if (num < 1)
            return "Cannot represent numbers smaller that 1";
        else if (num > 3999)
            return "Cannot represent numbers bigger than 3999";
        else {
            StringBuilder roman = new StringBuilder();
            while (num != 0) {
                int maxFound = 0;
                for (int i = 0; i < decimal.length; ++i)
                    if (num >= decimal[i])
                        maxFound = i;
                roman.append(letters[maxFound]);
                num -= decimal[maxFound];
            }
            return roman.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n).forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}
