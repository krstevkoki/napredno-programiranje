package mk.ukim.finki.kol1;

import java.util.*;

class Schedulers {
    public static <T extends Task> TaskScheduler<T> getOrdered() {
        // vashiot kod ovde (annonimous class)
        return new TaskScheduler<T>() {
            @Override
            public List<T> schedule(T[] tasks) {
                ArrayList<T> listTasks = new ArrayList<>();
                Collections.addAll(listTasks, tasks);
                listTasks.sort((o1, o2) -> {
                    Task t1 = (Task) o1;
                    Task t2 = (Task) o2;
                    return Integer.compare(t1.getOrder(), t2.getOrder());
                });
                return listTasks;
            }
        };
    }

    public static <T extends Task> TaskScheduler<T> getFiltered(int order) {
        // vashiot kod ovde (lambda expression)
        return tasks -> {
            ArrayList<T> listFilteredTasks = new ArrayList<>();
            for (T task : tasks) {
                Task t = (Task) task;
                if (t.getOrder() <= order)
                    listFilteredTasks.add(task);
            }
            return listFilteredTasks;
        };
    }
}
