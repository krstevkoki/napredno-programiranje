package mk.ukim.finki.lab5;

import java.util.*;

class SuperString {
    private LinkedList<String> superString;
    private Stack<String> lastAdded;

    public SuperString() {
        superString = new LinkedList<>();
        lastAdded = new Stack<>();
    }

    public void append(String s) {
        superString.addLast(s);
        lastAdded.push(s);
    }

    public void insert(String s) {
        superString.addFirst(s);
        lastAdded.push(s);
    }

    public boolean contains(String s) {
        return this.toString().contains(s);
    }

    public void reverse() {
        Collections.reverse(superString);
        reverseStringInList(superString);
        LinkedList<String> temp = new LinkedList<>();
        while (!lastAdded.empty())
            temp.add(lastAdded.pop());
        reverseStringInList(temp);
        Iterator<String> iterator = temp.descendingIterator();
        while (iterator.hasNext())
            lastAdded.push(iterator.next());
    }

    private static void reverseStringInList(List<String> list) {
        for (int i = 0; i < list.size(); ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(i));
            list.set(i, sb.reverse().toString());
        }
    }

    public void removeLast(int k) {
        for (int i = 0; i < k; ++i)
            superString.remove(lastAdded.pop());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        superString.forEach(sb::append);
        return sb.toString();
    }
}
