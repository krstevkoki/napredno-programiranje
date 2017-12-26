package mk.ukim.finki.kol2;

import java.util.*;

class Audition {
    private Map<String, Set<Participant>> participants;

    public Audition() {
        this.participants = new HashMap<>();
    }

    public void addParticpant(String city, String code, String name, int age) {
        this.participants.computeIfAbsent(city, value -> new HashSet<>());

        this.participants.computeIfPresent(city, (key, value) -> {
            value.add(new Participant(city, code, name, age));
            return value;
        });
    }

    public void listByCity(String city) {
        participants.get(city)
                .stream()
                .sorted(Comparator
                        .comparing(Participant::getName)
                        .thenComparing(Participant::getAge))
                .forEach(System.out::println);
    }
}
