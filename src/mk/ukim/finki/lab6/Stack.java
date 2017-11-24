package mk.ukim.finki.lab6;

class Stack<T> {
    private Node<T> top;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T element) {
        top = new Node<>(element, top);
    }

    public T pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        T element = top.element;
        top = top.next;
        return element;
    }

    public T peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return top.element;
    }

    private class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}

class EmptyStackException extends Exception {
    public EmptyStackException() {
    }
}
