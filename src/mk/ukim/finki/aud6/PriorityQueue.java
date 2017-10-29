package mk.ukim.finki.aud6;

import java.util.ArrayList;

class PriorityQueue<T> {
    private ArrayList<T> queue;
    private ArrayList<Integer> priorities;

    public PriorityQueue() {
        queue = new ArrayList<>();
        priorities = new ArrayList<>();
    }

    public void add(T element, int priority) {
        int i;
        for (i = 0; i < priorities.size(); ++i)
            if (priorities.get(i) < priority)
                break;
        queue.add(i, element);
        priorities.add(i, priority);
    }

    public T remove() {
        if (queue.isEmpty())
            return null;
        T element = queue.get(0);
        queue.remove(0);
        priorities.remove(0);
        return element;
    }
}
