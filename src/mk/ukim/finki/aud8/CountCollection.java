package mk.ukim.finki.aud8;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CountCollection {
    private static int count(List<List<String>> collection, String strToCount) {
        int counter = 0;
        for (List<String> strings : collection)
            for (String string : strings)
                if (string.equals(strToCount))
                    ++counter;
        return counter;
    }

    private static long countFunc(List<List<String>> collection, String strToCount) {
        return collection.stream()
                .mapToLong(coll -> coll.stream()
                        .filter(str -> str.equals(strToCount))
                        .count())
                .sum();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<List<String>> c = new LinkedList<>();
        for (int i = 0; i < 3; ++i) {
            c.add(new LinkedList<>());
            for (int j = 0; j < 5; ++j)
                c.get(i).add(input.nextLine());
        }
        String strToCount = input.nextLine();
        input.close();
        /*int counter = count(c, strToCount);
        System.out.println(counter);*/
        long counter = countFunc(c, strToCount);
        System.out.println(counter);
        System.out.println(c);
    }
}
