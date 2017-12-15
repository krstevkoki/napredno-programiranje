package mk.ukim.finki.aud7;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomPicker {
    private final int total;

    public RandomPicker(int total) {
        this.total = total;
    }

    public Set<Integer> pick(int howMany) {
        /*List<Integer> picked = new ArrayList<>();
        while (picked.size() != howMany) {
            int selected = new Random().nextInt(total) + 1;
                if (!picked.contains(selected))
                    picked.add(selected);
        }*/
        Set<Integer> picked = new HashSet<>();
        while (picked.size() != howMany)
            picked.add(new Random().nextInt(total) + 1);
        return picked;
    }
}
