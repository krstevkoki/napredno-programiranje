package mk.ukim.finki.kol1;

import java.util.List;

interface TaskScheduler<T> {
    List<T> schedule(T[] tasks);
}
