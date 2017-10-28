package mk.ukim.finki.lab4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Scheduler<T> {
    private ArrayList<Timestamp<T>> timestamps;

    public Scheduler() {
        timestamps = new ArrayList<>();
    }

    public void add(Timestamp<T> t) {
        timestamps.add(t);
    }

    public boolean remove(Timestamp<T> t) {
        if (timestamps.contains(t)) {
            timestamps.remove(t);
            return true;
        } else
            return false;
    }

    public Timestamp<T> next() {
        Timestamp<T> now = new Timestamp<>(LocalDateTime.now(), null);
        Timestamp<T> currentMin = new Timestamp<>(null, null);
        Timestamp<T> lastMin = currentMin;
        boolean flag = true;
        for (int i = 0; i < timestamps.size(); ++i) {
            if (now.compareTo(timestamps.get(i)) <= 0) {
                if (flag) {
                    lastMin = currentMin = timestamps.get(i);
                    flag = false;
                } else {
                    currentMin = timestamps.get(i);
                    if (lastMin.compareTo(currentMin) <= 0)
                        currentMin = lastMin;
                    else
                        lastMin = currentMin;
                }
            }
        }
        return currentMin;
    }

    public Timestamp<T> last() {
        Timestamp<T> now = new Timestamp<>(LocalDateTime.now(), null);
        Timestamp<T> currentMax = new Timestamp<>(null, null);
        Timestamp<T> lastMax = currentMax;
        boolean flag = true;
        for (int i = 0; i < timestamps.size(); ++i) {
            if (now.compareTo(timestamps.get(i)) > 0) {
                if (flag) {
                    lastMax = currentMax = timestamps.get(i);
                    flag = false;
                } else {
                    currentMax = timestamps.get(i);
                    if (lastMax.compareTo(currentMax) > 0)
                        currentMax = lastMax;
                    else
                        lastMax = currentMax;
                }
            }
        }
        return currentMax;
    }

    public List<Timestamp<T>> getAll(LocalDateTime begin, LocalDateTime end) {
        ArrayList<Timestamp<T>> ts = new ArrayList<>();
        for (Timestamp<T> timestamp : timestamps) {
            if (begin.compareTo(timestamp.getTime()) < 0 && end.compareTo(timestamp.getTime()) > 0)
                ts.add(timestamp);
        }
        return ts;
    }
}
