package mk.ukim.finki.aud6;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

class Box<T> {
    private ArrayList<T> list;

    public Box() {
        list = new ArrayList<>();
    }

    public void add(T item) {
        list.add(item);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T drawItem() {
        if (isEmpty())
            throw new NoSuchElementException();
        Random rnd = new Random();
        return list.get(rnd.nextInt(list.size()));
    }
}
