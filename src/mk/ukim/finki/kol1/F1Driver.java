package mk.ukim.finki.kol1;

import java.util.ArrayList;

class F1Driver {
    private String name;
    private ArrayList<String> laps;

    public F1Driver(String name, ArrayList<String> laps) {
        this.name = name;
        this.laps = laps;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLaps() {
        return laps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(" ");
        for (String lap : getLaps()) {
            sb.append(lap);
            sb.append(" ");
        }
        return sb.toString();
    }
}
