package mk.ukim.finki.lab4;

class Node<T> {
    private T element;
    private Node<T> next;

    public Node(T element, Node<T> successor) {
        this.element = element;
        this.next = successor;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
