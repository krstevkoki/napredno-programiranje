package mk.ukim.finki.kol1;

import java.util.List;

class Temperature implements Comparable<Temperature> {
    private int day;
    private char scale;
    private List<Double> temperatures;

    public Temperature(int day, char scale, List<Double> temperatures) {
        this.day = day;
        this.scale = scale;
        this.temperatures = temperatures;
    }

    public int getDay() {
        return day;
    }

    public char getScale() {
        return scale;
    }

    public List<Double> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Double> temperatures) {
        this.temperatures = temperatures;
    }

    public void setScale(char scale) {
        this.scale = scale;
    }

    @Override
    public int compareTo(Temperature o) {
        return Integer.compare(this.day, o.getDay());
    }
}
