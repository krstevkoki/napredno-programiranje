package mk.ukim.finki.aud6;

public class BoxTester {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.add("Kostadin");
        stringBox.add("Krstev");
        stringBox.add("Koki");
        stringBox.add("K");
        stringBox.add("Krstev");
        stringBox.add("Kostad");
        stringBox.add("Krs");
        stringBox.add("Kokii");
        System.out.println(stringBox.drawItem());

        Box<Integer> integerBox = new Box<>();
        integerBox.add(1);
        integerBox.add(2);
        integerBox.add(23);
        integerBox.add(45);
        integerBox.add(3);
        integerBox.add(6);
        integerBox.add(12);
        integerBox.add(453);
        integerBox.add(34);
        integerBox.add(756);
        integerBox.add(456);
        integerBox.add(8);
        integerBox.add(678);
        integerBox.add(456);
        integerBox.add(64);
        integerBox.add(89);
        integerBox.add(976);
        integerBox.add(-56);
        System.out.println(integerBox.drawItem());

    }
}
