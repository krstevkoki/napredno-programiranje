package mk.ukim.finki.kol1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class DailyTemperatures {
    private List<Temperature> temperatures;

    public DailyTemperatures() {
        temperatures = new ArrayList<>();
    }

    void readTemperatures(InputStream inputStream) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bf.readLine()) != null && line.length() != 0) {
                String[] parts = line.split("\\s+");
                List<Double> temps = new ArrayList<>();
                for (int i = 1; i < parts.length; ++i)
                    temps.add(Double.parseDouble(parts[i].substring(0, parts[i].length() - 1)));
                if (parts[1].endsWith("C"))
                    temperatures.add(new Temperature(Integer.parseInt(parts[0]), 'C', temps));
                else
                    temperatures.add(new Temperature(Integer.parseInt(parts[0]), 'F', temps));
            }
        }
    }

    private void convertToCelsius(Temperature t) {
        if (t.getScale() == 'F') {
            t.setTemperatures(
                    t.getTemperatures().stream()
                            .map(temp -> ((temp - 32.0) * 5.0) / 9.0)
                            .collect(Collectors.toList())
            );
            t.setScale('C');
        }
    }

    private void convertToFahrenheit(Temperature t) {
        if (t.getScale() == 'C') {
            t.setTemperatures(
                    t.getTemperatures().stream()
                    .map(temp -> (temp * 9.0) / 5.0 + 32.0)
                    .collect(Collectors.toList())
            );
            t.setScale('F');
        }
    }

    private Double minTemp(Temperature t) {
        return t.getTemperatures().stream()
                .mapToDouble(Double::valueOf)
                .min()
                .orElse(Double.MIN_VALUE);
    }

    private Double maxTemp(Temperature t) {
        return t.getTemperatures().stream()
                .mapToDouble(Double::valueOf)
                .max()
                .orElse(Double.MAX_VALUE);
    }

    private Double averageTemp(Temperature t) {
        return t.getTemperatures().stream()
                .mapToDouble(Double::valueOf)
                .average()
                .orElse(Double.MIN_VALUE);
    }

    private int count(Temperature t) {
        return t.getTemperatures().size();
    }

    void writeDailyStats(OutputStream outputStream, char scale) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        Collections.sort(temperatures);
        for (Temperature temperature : temperatures) {
            if (scale == 'C')
                convertToCelsius(temperature);
            else
                convertToFahrenheit(temperature);
            Double maxTemp = maxTemp(temperature);
            Double minTemp = minTemp(temperature);
            Double averageTemp = averageTemp(temperature);
            Integer count = count(temperature);
            Integer day = temperature.getDay();
            writer.println(String.format("%3s: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c", day, count,
                    minTemp, scale, maxTemp, scale, averageTemp, scale));
        }
        writer.flush();
    }
}
