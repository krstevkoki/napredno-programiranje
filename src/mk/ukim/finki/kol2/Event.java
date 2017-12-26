package mk.ukim.finki.kol2;

import java.util.Date;

class Event {
    private final static String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
            "Nov", "Dec"};
    private String name;
    private String location;
    private Date time;

    public Event(String name, String location, Date time) {
        this.name = name;
        this.location = location;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%d %s, %d %02d:%02d at %s, %s", time.getDate(), MONTHS[time.getMonth()],
                time.getYear() + 1900, time.getHours(), time.getMinutes(), location, name);
    }
}
