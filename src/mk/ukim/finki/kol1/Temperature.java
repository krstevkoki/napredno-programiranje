package mk.ukim.finki.kol1;

import java.util.ArrayList;

class Temperature implements Comparable {
    private int day;
    private char scale;
    private ArrayList<Double> temperatures;

    public Temperature(int day, char scale, ArrayList<Double> temperatures) {
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

    public ArrayList<Double> getTemperatures() {
        return temperatures;
    }

    public void setScale(char scale) {
        this.scale = scale;
    }

    @Override
    public int compareTo(Object o) {
        Temperature temperature = (Temperature) o;
        return Integer.compare(this.day, temperature.getDay());
    }
}
