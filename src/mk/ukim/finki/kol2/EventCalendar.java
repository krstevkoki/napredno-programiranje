package mk.ukim.finki.kol2;

import java.util.*;
import java.util.stream.IntStream;

class EventCalendar {
    private int year;
    private Map<String, Set<Event>> events;
    private Map<Integer, Integer> statisticsByMonth;

    public EventCalendar(int year) {
        this.year = year;
        this.events = new HashMap<>();
        this.statisticsByMonth = new TreeMap<>();
    }

    public void addEvent(String name, String location, Date date) throws WrongDateException {
        if (date.getYear() != (year - 1900))
            throw new WrongDateException(String.format("Wrong date: %s", date));
        this.statisticsByMonth.computeIfPresent(date.getMonth(), (key, value) -> ++value);
        this.statisticsByMonth.putIfAbsent(date.getMonth(), 1);
        // day.month.year
        String dateString = date.getDate() + "." + (date.getMonth() + 1) + "." + (date.getYear() + 1900);
        this.events.computeIfAbsent(dateString, value -> new TreeSet<>(
                Comparator.comparing(Event::getTime)
                        .thenComparing(Event::getName)
        ));
        this.events.computeIfPresent(dateString, (key, value) -> {
            value.add(new Event(name, location, date));
            return value;
        });
    }

    public void listEvents(Date date) {
        String dateString = date.getDate() + "." + (date.getMonth() + 1) + "." + (date.getYear() + 1900);
        if (!this.events.containsKey(dateString))
            System.out.println("No events on this day!");
        else
            this.events.get(dateString)
                    .stream()
                    .forEach(System.out::println);
    }

    public void listByMonth() {
        IntStream.range(0, 12)
                .forEach(i -> System.out.printf("%d : %d\n", i + 1, statisticsByMonth.getOrDefault(i, 0)));
    }
}
