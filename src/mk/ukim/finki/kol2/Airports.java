package mk.ukim.finki.kol2;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Airports {
    private Map<String, Set<Flight>> flightsByDepartureTime;
    private Map<String, TreeMap<String, Set<Flight>>> flightsByAirportCode;
    private Set<Airport> airports;
    private static Comparator<Flight> comparator = Comparator.comparing(Flight::getTime);

    public Airports() {
        this.flightsByDepartureTime = new HashMap<>();
        this.flightsByAirportCode = new HashMap<>();
        this.airports = new HashSet<>();
    }

    public void addAirport(String name, String country, String code, int passengers) {
        Airport a = new Airport(name, country, code, passengers);
        flightsByDepartureTime.computeIfAbsent(a.getCode(), key -> new TreeSet<>(comparator));
        flightsByAirportCode.computeIfAbsent(a.getCode(), key -> new TreeMap<>());
        airports.add(a);
    }

    public void addFlights(String from, String to, int time, int duration) {
        Flight f = new Flight(from, to, time, duration);
        flightsByDepartureTime.computeIfPresent(from, (key, value) -> {
            value.add(f);
            return value;
        });
        flightsByAirportCode.computeIfPresent(from, (key, value) -> {
            value.computeIfAbsent(to, keyFrom -> new TreeSet<>(comparator));
            value.computeIfPresent(to, (k, val) -> {
                val.add(f);
                return val;
            });
            return value;
        });
    }

    public void showFlightsFromAirport(String code) {
        if (airports.stream().anyMatch(a -> Objects.equals(a.getCode(), code))) {
            Airport target = airports.stream()
                    .filter(airport -> Objects.equals(airport.getCode(), code))
                    .collect(Collectors.toList())
                    .get(0);
            System.out.println(target);
            List<Flight> flights = flightsByAirportCode.getOrDefault(code, new TreeMap<>()).values()
                    .stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            IntStream.range(0, flights.size())
                    .forEach(i -> System.out.printf("%d. %s\n", i + 1, flights.get(i)));
        }
    }

    public void showDirectFlightsFromTo(String from, String to) {
        Supplier<TreeSet<Flight>> supplier = () -> new TreeSet<>(comparator);
        Set<Flight> flights = flightsByDepartureTime.getOrDefault(from, new TreeSet<>(comparator)).stream()
                .filter(flight -> Objects.equals(flight.getTo(), to))
                .collect(Collectors.toCollection(supplier));
        if (flights.isEmpty())
            System.out.printf("No flights from %s to %s\n", from, to);
        else flights.stream()
                .forEach(System.out::println);
    }

    public void showDirectFlightsTo(String to) {
        Supplier<TreeSet<Flight>> supplier = () -> new TreeSet<>(comparator.thenComparing(Flight::getFrom));

        Set<Flight> flights = flightsByDepartureTime.values()
                .parallelStream()
                .flatMap(Collection::stream)
                .filter(flight -> Objects.equals(flight.getTo(), to))
                .sorted(comparator.thenComparing(Flight::getFrom))
                .collect(Collectors.toCollection(supplier));
        if (flights.isEmpty())
            System.out.printf("No flights to %s\n", to);
        else flights.stream()
                .forEach(System.out::println);
    }
}
