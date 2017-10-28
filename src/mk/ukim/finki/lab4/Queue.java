package mk.ukim.finki.lab4;

class Queue<T> {
    private Node<T> front, rear;
    private int length = 0;

    public Queue() {
        clear();
    }

    public void enqueue(T element) {
        Node<T> latest = new Node<>(element, null);
        if (rear == null)
            front = rear = latest;
        else {
            rear.setNext(latest);
            rear = latest;
        }
        ++length;
    }

    public T dequeue() throws EmptyQueueException {
        T element = front.getElement();
        front = front.getNext();
        if (front == null)
            rear = null;
        --length;
        return element;
    }

    public T peek() throws EmptyQueueException {
        if (length == 0)
            throw new EmptyQueueException();
        return front.getElement();
    }

    public T inspect() throws EmptyQueueException {
        if (length == 0)
            throw new EmptyQueueException();
        return rear.getElement();
    }

    public void clear() {
        front = rear = null;
        length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int count() {
        return length;
    }
}
