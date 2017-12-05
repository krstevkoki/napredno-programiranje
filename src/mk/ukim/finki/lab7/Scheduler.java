package mk.ukim.finki.lab7;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

class Scheduler<T> {
    private TreeMap<Date, T> schedulers;

    public Scheduler() {
        schedulers = new TreeMap<>();
    }

    public void add(Date d, T t) {
        schedulers.put(d, t);
    }

    public boolean remove(Date d) {
        if (schedulers.containsKey(d)) {
            schedulers.remove(d);
            return true;
        }
        return false;
    }

    public T next() {
        return schedulers.get(schedulers.ceilingKey(new Date()));
    }

    public T last() {
        return schedulers.get(schedulers.floorKey(new Date()));
    }

    public ArrayList<T> getAll(Date begin, Date end) {
        ArrayList<T> temp = new ArrayList<>();
        Set<Date> keys = schedulers.navigableKeySet();
        keys.forEach(date -> {
            if (date.compareTo(begin) > 0&&date.compareTo(end) < 0)
                temp.add(schedulers.get(date));
        });
        return temp;
    }

    public T getFirst() {
        return schedulers.get(schedulers.firstKey());
    }

    public T getLast() {
        return schedulers.get(schedulers.lastKey());
    }
}
