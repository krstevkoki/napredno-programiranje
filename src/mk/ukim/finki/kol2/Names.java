package mk.ukim.finki.kol2;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Names {
    private Map<String, Integer> names;

    public Names() {
        this.names = new TreeMap<>();
    }

    public void addName(String name) {
        this.names.putIfAbsent(name, (0));
        this.names.computeIfPresent(name, (key, value) -> {
            ++value;
            return value;
        });
    }

    public void printN(int n) {
        Predicate<Map.Entry<String, Integer>> predicate
                = entry -> entry.getValue() >= n;
        this.names.entrySet()
                .stream()
                .filter(predicate)
                .forEach(entry -> System.out.printf("%s (%d) %d\n", entry.getKey(), entry.getValue(),
                        countUniqueChars(entry.getKey())));
    }

    private int countUniqueChars(String name) {
        int counter = 0;
        Set<Character> hashSet = new HashSet<>();
        for (int i = 0; i < name.length(); ++i)
            if (hashSet.add(name.toLowerCase().charAt(i)))
                ++counter;
        return counter;
    }

    public String findName(int len, int x) {
        Predicate<String> smallerThan =
                string -> string.length() < len;
        List<String> list = names.keySet().stream()
                .filter(smallerThan)
                .collect(Collectors.toList());
        return list.get(x % list.size());
    }
}
