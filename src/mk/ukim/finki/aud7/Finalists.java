package mk.ukim.finki.aud7;

import java.util.Set;

public class Finalists {
    public static void main(String[] args) {
        RandomPicker picker = new RandomPicker(30);
        Set<Integer> picked = picker.pick(3);
        System.out.println(picked);
    }
}
