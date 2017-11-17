package mk.ukim.finki.kol1;

import java.util.List;

interface TaskScheduler<T extends Task> {
    List<T> schedule(T[] tasks);
}
