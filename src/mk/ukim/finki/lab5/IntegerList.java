package mk.ukim.finki.lab5;

import java.util.*;
import java.util.stream.Collectors;

class IntegerList {
    private List<Integer> integerList;

    public IntegerList() {
        this.integerList = new LinkedList<>();
    }

    public IntegerList(Integer[] a) {
        this.integerList = new LinkedList<>();
        List<Integer> tmp = Arrays.asList(a);
        integerList.addAll(tmp);
    }

    public void add(int el, int idx) {
        if (idx <= size())
            this.integerList.add(idx, el);
        else {
            List<Integer> temp = new LinkedList<>();
            temp.addAll(integerList);
            for (int i = integerList.size(); i < idx; ++i)
                temp.add(0);
            temp.add(idx, el);
            integerList = temp;
        }
    }

    private boolean notValidIndex(int index) {
        return index < 0 || index > size();
    }

    public int remove(int idx) {
        if (notValidIndex(idx)) throw new ArrayIndexOutOfBoundsException();
        return integerList.remove(idx);
    }

    public void set(int el, int idx) {
        if (notValidIndex(idx)) throw new ArrayIndexOutOfBoundsException();
        integerList.set(idx, el);
    }

    public int get(int idx) {
        if (notValidIndex(idx)) throw new ArrayIndexOutOfBoundsException();
        return integerList.get(idx);
    }

    public int size() {
        return integerList.size();
    }

    public int count(int el) {
        return Collections.frequency(integerList, el);
    }

    public void removeDuplicates() {
        Hashtable<Integer, Boolean> hashtable = new Hashtable<>();
        for (int i = integerList.size() - 1; i >= 0; --i) {
            if (hashtable.containsKey(get(i)))
                remove(i);
            else
                hashtable.put(get(i), true);
        }
    }

    public int sumFirst(int k) {
        return integerList.stream().mapToInt(Integer::intValue).limit(k).sum();
    }

    public int sumLast(int k) {
        LinkedList<Integer> tmp = new LinkedList<>();
        tmp.addAll(integerList);
        Collections.reverse(tmp);
        return tmp.stream().mapToInt(Integer::intValue).limit(k).sum();
    }

    public void shiftRight(int idx, int k) {
        if (notValidIndex(idx)) throw new ArrayIndexOutOfBoundsException();
        int newIndex = (idx + k) % size();
        if (newIndex != idx) {
            int temp = get(idx);
            remove(idx);
            add(temp, newIndex);
        }
    }

    public void shiftLeft(int idx, int k) {
        if (notValidIndex(idx)) throw new ArrayIndexOutOfBoundsException();
        int newIndex;
        if (idx - k < 0)
            newIndex = size() - (Math.abs(idx - k) % size());
        else
            newIndex = (idx - k) % size();
        if (newIndex != idx) {
            int temp = get(idx);
            remove(idx);
            add(temp, newIndex);
        }
    }

    public IntegerList addValue(int value) {
        IntegerList il = new IntegerList();
        il.integerList = this.integerList.stream().map(integer -> (integer + value)).collect(Collectors.toList());
        return il;
    }
}
