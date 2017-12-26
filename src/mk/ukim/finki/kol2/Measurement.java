package mk.ukim.finki.kol2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Measurement {
    private float temperature;
    private float wind;
    private float humidity;
    private float visibility;
    private LocalDateTime date;

    public Measurement(float temperature, float wind, float humidity, float visibility, LocalDateTime date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWind() {
        return wind;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        System.out.println(that.date.getSecond() + " " + this.date.getSecond());
        return that.date.getSecond() - this.date.getSecond() >= 150;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT' uuu");
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature, wind, humidity, visibility,
                date.format(formatter));
    }
}
