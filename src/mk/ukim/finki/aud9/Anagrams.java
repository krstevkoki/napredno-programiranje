package mk.ukim.finki.aud9;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private static void findAll(InputStream inputStream) {
        Scanner input = new Scanner(inputStream);
        Map<String, Set<String>> anagrams = new HashMap<>();

        while (input.hasNextLine()) {
            String word = input.nextLine();
            char[] wordArray = word.toCharArray();
            Arrays.sort(wordArray);
            String wordSorted = new String(wordArray);
            if (anagrams.containsKey(wordSorted)) {
                Set<String> words = anagrams.get(wordSorted);
                words.add(word);
            } else {
                Set<String> words = new TreeSet<>();
                words.add(word);
                anagrams.put(wordSorted, words);
            }
        }
        input.close();

        List<String> list = new ArrayList<>();
        for (Set<String> stringSet : anagrams.values()) {
            StringBuilder sb = new StringBuilder();
            if (stringSet.size() >= 5) {
                Iterator<String> it = stringSet.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    if (it.hasNext())
                        sb.append(" ");
                }
            }
            list.add(sb.toString());
        }

        Collections.sort(list);
        list.forEach(System.out::println);
    }

    private static void findAllFunc(InputStream inputStream) {
        Map<String, Set<String>> anagrams = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.toMap(  // keyMapper
                        word -> word.chars()
                                .sorted()
                                .collect(StringBuilder::new,  // [supplier]
                                        StringBuilder::append,  // (sb, char) -> sb.append(char) [accumulator]
                                        StringBuilder::append)  // [combiner]  (sb1, sb2) -> sb1.append(sb2)
                                .toString()
                        ,  // valueMapper
                        word -> {
                            Set<String> words = new TreeSet<>();  // create the Set
                            words.add(word);  // add the anagram
                            return words;
                        }
                        ,  // mergeFunction if key already exists
                        (oldSet, newSet) -> {
                            oldSet.addAll(newSet);  // add the new set to the old one
                            return oldSet;
                        }));

        anagrams.values().stream()
                .filter(set -> set.size() >= 5)
                .map(set -> set.stream()
                        .collect(Collectors.joining(" ")))
                .sorted()
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        // findAll(System.in);
        findAllFunc(System.in);
    }
}
