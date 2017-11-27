package mk.ukim.finki.lab6;

import java.util.ArrayList;
import java.util.Iterator;

class SortedLinkedList<T extends Comparable<T>> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public SortedLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(T element) {
        Iterator<T> iterator = iteratorLR();
        while (iterator.hasNext())
            if (iterator.next().equals(element))
                return true;
        return false;
    }

    public void add(T element) {
        if (contains(element))
            return;
        else if (isEmpty())
            addFirst(element);
        else if (first.element.compareTo(element) > 0)
            addFirst(element);
        else if (last.element.compareTo(element) < 0)
            addLast(element);
        else {
            Node<T> tempFirst = first;
            while (tempFirst.element.compareTo(element) < 0)
                tempFirst = tempFirst.next;
            addBefore(element, tempFirst);
        }
        ++size;
    }

    private void addFirst(T element) {
        Node<T> ins = new Node<>(element, null, first);
        if (first == null)
            last = ins;
        else
            first.previous = ins;
        first = ins;
    }

    private void addLast(T element) {
        if (last == null)
            addFirst(element);
        else {
            Node<T> ins = new Node<>(element, last, null);
            last.next = ins;
            last = ins;
        }
    }

    private void addBefore(T element, Node<T> node) {
        Node<T> ins = new Node<>(element, node.previous, node);
        node.previous.next = ins;
        node.previous = ins;
    }

    public boolean remove(T element) {
        if (contains(element)) {
            Node<T> tempFirst = first;
            while (!(tempFirst.element.equals(element)))
                tempFirst = tempFirst.next;
            if (tempFirst.equals(first))
                removeFirst();
            else if (tempFirst.equals(last))
                removeLast();
            else {
                tempFirst.previous.next = tempFirst.next;
                tempFirst.next.previous = tempFirst.previous;
            }
            --size;
            return true;
        }
        return false;
    }

    private void removeFirst() {
        first = first.next;
        if (first == null)
            last = null;
        else
            first.previous = null;
    }

    private void removeLast() {
        if (last.equals(first))
            removeFirst();
        else {
            last = last.previous;
            last.next = null;
        }
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> tmp = new ArrayList<>();
        Iterator<T> iterator = iteratorLR();
        while (iterator.hasNext())
            tmp.add(iterator.next());
        return tmp;
    }

    @SuppressWarnings("unchecked")
    public void addAll(SortedLinkedList<? extends T> a) {
        Iterator<T> iterator = (Iterator<T>) a.iteratorLR();
        while (iterator.hasNext())
            add(iterator.next());
    }

    @SuppressWarnings("unchecked")
    public boolean containsAll(SortedLinkedList<? extends T> a) {
        Iterator<T> iterator = (Iterator<T>) a.iteratorLR();
        while (iterator.hasNext())
            if (!contains(iterator.next()))
                return false;
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "NONE";
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = iteratorLR();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext())
                sb.append(" ");
        }
        return sb.toString();
    }

    public String toStringRL() {
        if (isEmpty())
            return "NONE";
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = iteratorRL();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext())
                sb.append(" ");
        }
        return sb.toString();
    }

    public Iterator<T> iteratorLR() {
        return new IteratorLR();
    }

    public Iterator<T> iteratorRL() {
        return new IteratorRL();
    }

    /* ============ Inner Classes ============ */

    private class IteratorLR implements Iterator<T> {
        private SortedLinkedList<T>.Node<T> place;

        public IteratorLR() {
            place = first;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public T next() {
            T element = place.element;
            place = place.next;
            return element;
        }
    }

    private class IteratorRL implements Iterator<T> {
        private SortedLinkedList<T>.Node<T> place;

        public IteratorRL() {
            place = last;
        }

        @Override
        public boolean hasNext() {
            return place != null;
        }

        @Override
        public T next() {
            T element = place.element;
            place = place.previous;
            return element;
        }
    }

    private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        private T element;
        private Node<T> previous;
        private Node<T> next;

        public Node(T element, Node<T> previous, Node<T> next) {
            this.element = element;
            this.previous = previous;
            this.next = next;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            if (element != null ? !element.equals(node.element) : node.element != null) return false;
            if (previous != null ? !previous.equals(node.previous) : node.previous != null) return false;
            return next != null ? next.equals(node.next) : node.next == null;
        }

        @Override
        public int hashCode() {
            int result = element != null ? element.hashCode() : 0;
            result = 31 * result + (previous != null ? previous.hashCode() : 0);
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public int compareTo(Node<T> o) {
            return this.element.compareTo(o.element);
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
