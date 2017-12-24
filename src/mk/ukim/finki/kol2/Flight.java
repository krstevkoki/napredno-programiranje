package mk.ukim.finki.kol2;

class Flight {
    private final static int MINUTES_PER_DAY = 1440;
    private final static int HOURS_PER_DAY = 24;
    private String from;
    private String to;
    private int time;
    private int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration + time;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    private String parseMinutesToHours(int min) {
        int h = (min / 60) % HOURS_PER_DAY;
        int m = min % 60;
        return String.format("%02d:%02d", h, m);
    }

    private String timeDifference(int time1, int time2) {
        int difference = Math.abs(time1 - time2);
        int h = (difference / 60) % HOURS_PER_DAY;
        int m = difference % 60;
        return String.format("%dh%02dm", h, m);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        if (time != flight.time) return false;
        if (duration != flight.duration) return false;
        if (from != null ? !from.equals(flight.from) : flight.from != null) return false;
        return to != null ? to.equals(flight.to) : flight.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + time;
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        if (duration > MINUTES_PER_DAY) {
            int daysPassed = duration / MINUTES_PER_DAY;
            return String.format("%s-%s %s-%s +%dd %s", from, to, parseMinutesToHours(time),
                    parseMinutesToHours(duration), daysPassed, timeDifference(time, duration));
        } else
            return String.format("%s-%s %s-%s %s", from, to, parseMinutesToHours(time), parseMinutesToHours(duration),
                    timeDifference(duration, time));
    }
}
