package mk.ukim.finki.aud6;

public class PriorityQueueTester {
    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.add("X", 10);
        pq.add("Y", 1);
        pq.add("Z", 3);
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
    }
}
