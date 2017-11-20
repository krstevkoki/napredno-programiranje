package mk.ukim.finki.lab5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        for (int i = 0; i < superString.size() - 1; ++i) {
            if (i == 0)
                superString.addFirst(superString.getLast());
            else
                superString.add(i, superString.getLast());
            superString.removeLast();
        }
        reverseString(superString);
        LinkedList<String> temp = new LinkedList<>();
        while (!lastAdded.empty())
            temp.add(lastAdded.pop());
        reverseString(temp);
        Iterator<String> iterator = temp.descendingIterator();
        while (iterator.hasNext())
            lastAdded.push(iterator.next());
    }

    private static void reverseString(List<String> list) {
        for (int i = 0; i < list.size(); ++i) {
            int n = list.get(i).length();
            Stack<Character> characterStack = new Stack<>();
            char[] reversed = new char[n];
            for (int j = 0; j < n; ++j)
                characterStack.push(list.get(i).charAt(j));
            for (int j = 0; !characterStack.empty(); ++j)
                reversed[j] = characterStack.pop();
            list.set(i, String.valueOf(reversed));
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
