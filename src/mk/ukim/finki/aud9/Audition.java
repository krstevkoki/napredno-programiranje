package mk.ukim.finki.aud9;

import java.util.*;

class Audition {
    private Map<String, HashSet<Participant>> participants;

    public Audition() {
        participants = new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {
        Set<Participant> cityParticipants = participants.computeIfAbsent(city, value -> new HashSet<>());
        cityParticipants.add(new Participant(code, name, age));
    }

    public void listByCity(String city) {
        participants.get(city).stream()
                .sorted(Comparator
                        .comparing(Participant::getName)
                        .thenComparing(Participant::getAge))
                .forEach(System.out::println);
    }
}
