package mk.ukim.finki.aud8;

import java.util.Collection;
import java.util.LinkedList;

public class ReverseList {
    private static <T> void printReverse(Collection<? extends T> collection) {
        int size = collection.size();
        Object[] array = collection.toArray();
        System.out.print('[');
        for (int i = size - 1; i >= 0; --i) {
            System.out.print(array[i]);
            if (i > 0)
                System.out.print(", ");
        }
        System.out.println(']');
    }

    private static <T> void reversePrint(Collection<? extends T> collection) {
        Object[] array = collection.toArray(new Object[collection.size()]);
        System.out.print('[');
        for (int i = array.length - 1; i >= 0; --i) {
            System.out.print(array[i]);
            if (i > 0)
                System.out.print(", ");
        }
        System.out.println(']');
    }

    public static void main(String[] args) {
        Collection<String> collection = new LinkedList<>();
        collection.add("asd");
        collection.add("qwe");
        collection.add("swk");
        collection.add("zxc");
        collection.add("qwe");
        System.out.println(collection);
        printReverse(collection);

        System.out.println();

        System.out.println(collection);
        reversePrint(collection);
    }
}
