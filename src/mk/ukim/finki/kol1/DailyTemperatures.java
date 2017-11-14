package mk.ukim.finki.kol1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class DailyTemperatures {
    private ArrayList<Temperature> temperatures;

    public DailyTemperatures() {
        temperatures = new ArrayList<>();
    }

    void readTemperatures(InputStream inputStream) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bf.readLine()) != null && line.length() != 0) {
                String[] parts = line.split("\\s+");
                ArrayList<Double> temps = new ArrayList<>();
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
            for (int i = 0; i < t.getTemperatures().size(); ++i) {
                Double temperature = ((t.getTemperatures().get(i) - 32.0) * 5.0) / 9.0;
                t.getTemperatures().set(i, temperature);
            }
            t.setScale('C');
        }
    }

    private void convertToFahrenheit(Temperature t) {
        if (t.getScale() == 'C') {
            for (int i = 0; i < t.getTemperatures().size(); ++i) {
                Double temperature = (t.getTemperatures().get(i) * 9.0) / 5.0 + 32.0;
                t.getTemperatures().set(i, temperature);
            }
            t.setScale('F');
        }
    }

    private Double minTemp(Temperature t) {
        Double min = Double.MAX_VALUE;
        for (int i = 0; i < t.getTemperatures().size(); ++i)
            if (min > t.getTemperatures().get(i))
                min = t.getTemperatures().get(i);
        return min;
    }

    private Double maxTemp(Temperature t) {
        Double max = (double) Integer.MIN_VALUE;
        for (int i = 0; i < t.getTemperatures().size(); ++i)
            if (max < t.getTemperatures().get(i))
                max = t.getTemperatures().get(i);
        return max;
    }

    private Float averageTemp(Temperature t) {
        Double average = 0d;
        for (int i = 0; i < t.getTemperatures().size(); ++i)
            average += t.getTemperatures().get(i);
        return (float) (average / t.getTemperatures().size());
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
            Float averageTemp = averageTemp(temperature);
            Integer count = count(temperature);
            Integer day = temperature.getDay();
            writer.println(String.format("%3s: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c", day, count,
                    minTemp, scale, maxTemp, scale, averageTemp, scale));
        }
        writer.flush();
    }
}
