package mk.ukim.finki.kol2;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

class WeatherStation {
    private int days;
    private Set<Measurement> measurements;

    public WeatherStation(int days) {
        this.measurements = new TreeSet<>((o1, o2) -> {
            long difference = Math.abs(o1.getDate().until(o2.getDate(), ChronoUnit.SECONDS));
            if (difference < 150)
                return 0;
            return Comparator.comparing(Measurement::getDate).compare(o1, o2);
        });
        this.days = days;
    }

    public void addMeasurement(float temperature, float wind, float humidity, float visibility, LocalDateTime date) {
        Measurement m = new Measurement(temperature, wind, humidity, visibility, date);
        if (!measurements.add(m))
            return;
        Predicate<Measurement> oldMeasurement
                = measurement -> date.minusDays(days).isAfter(measurement.getDate());
        measurements.removeIf(oldMeasurement);
    }

    public int total() {
        return measurements.size();
    }


    public void status(LocalDateTime from, LocalDateTime to) {
        Predicate<Measurement> predicate =
                measurement -> measurement.getDate().compareTo(from) >= 0 && measurement.getDate().compareTo(to) <= 0;
        if (this.measurements.stream().noneMatch(predicate))
            throw new RuntimeException();
        this.measurements.stream()
                .filter(predicate)
                .forEach(System.out::println);
        double averageTemp = this.measurements.stream()
                .filter(predicate)
                .mapToDouble(Measurement::getTemperature)
                .average()
                .orElse(0d);
        System.out.printf("Average temperature: %.2f", averageTemp);
    }
}
