package mk.ukim.finki.kol1;

class Time implements Comparable<Time> {
    private int hour;
    private int min;

    public Time(int hour, int min) throws InvalidTimeException {
        if (hour < 0 || hour > 23 || min < 0 || min > 59)
            throw new InvalidTimeException(String.format("%d %d", hour, min));
        this.hour = hour;
        this.min = min;
    }

    @Override
    public int compareTo(Time o) {
        int thisMin = this.hour * 60 + this.min;
        int oMin = o.hour * 60 + o.min;
        return Integer.compare(thisMin, oMin);
    }

    public String toString(TimeFormat format) {
        if (format.toString().equals(TimeFormat.FORMAT_24.toString()))
            return String.format("%2d:%02d", hour, min);
        String part = "AM";
        if (hour >= 0 && hour < 1)
            hour += 12;
        if (hour >= 12 && hour < 13)
            part = "PM";
        if (hour >= 13 && hour < 24) {
            part = "PM";
            hour -= 12;
        }
        return String.format("%2d:%02d %s", hour, min, part);
    }
}
