package mk.ukim.finki.aud7;

public class TreeTester {
    public static void main(String[] args) {
        Tree<Integer> treeInt = new Tree<>();
        treeInt.add(5);
        treeInt.add(3);
        treeInt.add(4);
        treeInt.add(1);
        treeInt.add(2);
        treeInt.add(7);
        treeInt.add(6);
        treeInt.add(9);
        treeInt.add(8);
        treeInt.showElements();
        System.out.println();
        treeInt.showElements("preorder");
        System.out.println();
        treeInt.showElements("postorder");
        System.out.println();
        System.out.println("Size: " + treeInt.size());
        System.out.println(treeInt.contains(5));
        System.out.println(treeInt.contains(10));

        Tree<Character> treeChar = new Tree<>();
        treeChar.add('F');
        treeChar.add('D');
        treeChar.add('E');
        treeChar.add('B');
        treeChar.add('C');
        treeChar.add('A');
        treeChar.add('J');
        treeChar.add('G');
        treeChar.add('K');
        treeChar.add('I');
        treeChar.showElements();
        System.out.println();
        treeChar.showElements("preorder");
        System.out.println();
        treeChar.showElements("postorder");
        System.out.println();
        System.out.println("Size: " + treeChar.size());
        System.out.println(treeChar.contains('G'));
        System.out.println(treeChar.contains('M'));
    }
}
