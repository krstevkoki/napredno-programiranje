package mk.ukim.finki.kol1;

class Time {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return String.format("%2d:%02d", hour, minute);
    }

    public String toStringAMPM() {
        String part = "AM";
        if (hour == 0)
            hour += 12;
        else if (hour == 12)
            part = "PM";
        else if (hour > 12) {
            part = "PM";
            hour -= 12;
        }
        return String.format("%2d:%02d %s", hour, minute, part);
    }
}
