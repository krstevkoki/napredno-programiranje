package mk.ukim.finki.aud7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrangeLetters {
    private static String rearrange(String str) {
        String[] parts = str.split("\\s+");
        return Arrays.stream(parts)
                .map(eachPart -> {
                    char[] word = eachPart.toCharArray();
                    Arrays.sort(word);
                    return new String(word);
                })
                .sorted()
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(rearrange(str));
    }
}
