package mk.ukim.finki.aud8;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LuckySuitor {
    private final List<Integer> positions;

    public LuckySuitor(int n) {
        this.positions = IntStream.rangeClosed(1, n)
                .boxed()  // mapToObj(Integer::new)
                .collect(Collectors.toList());
    }

    public Integer getWinner() {
        ListIterator<Integer> iterator = positions.listIterator();
        boolean toRight = true;
        while (positions.size() != 1) {
            for (int i = 0; i < 3; ++i) {
                if (iterator.hasNext() && toRight) {
                    iterator.next();
                    if (!iterator.hasNext()) {
                        toRight = false;
                        iterator.previous();
                    }
                } else {
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        if (!iterator.hasPrevious()) {
                            toRight = true;
                            iterator.next();
                        }
                    }
                }
            }
            iterator.remove();
        }
        return positions.get(0);
    }

    public static void main(String[] args) {
        LuckySuitor luckySuitor = new LuckySuitor(5);
        System.out.println("Winner: " + luckySuitor.getWinner());
    }
}
