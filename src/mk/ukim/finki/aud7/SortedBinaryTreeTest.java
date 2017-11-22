package mk.ukim.finki.aud7;

public class SortedBinaryTreeTest {
    public static void main(String[] args) {
        SortedBinaryTree<Integer> sortedBinaryTreeInt = new SortedBinaryTree<>();
        sortedBinaryTreeInt.add(5);
        sortedBinaryTreeInt.add(3);
        sortedBinaryTreeInt.add(4);
        sortedBinaryTreeInt.add(1);
        sortedBinaryTreeInt.add(2);
        sortedBinaryTreeInt.add(7);
        sortedBinaryTreeInt.add(6);
        sortedBinaryTreeInt.add(9);
        sortedBinaryTreeInt.add(8);
        sortedBinaryTreeInt.showElements();
        System.out.println();
        sortedBinaryTreeInt.showElements("preorder");
        System.out.println();
        sortedBinaryTreeInt.showElements("postorder");
        System.out.println();
        System.out.println("Size: " + sortedBinaryTreeInt.size());
        System.out.println(sortedBinaryTreeInt.contains(5));
        System.out.println(sortedBinaryTreeInt.contains(10));

        SortedBinaryTree<Character> sortedBinaryTreeChar = new SortedBinaryTree<>();
        sortedBinaryTreeChar.add('F');
        sortedBinaryTreeChar.add('D');
        sortedBinaryTreeChar.add('E');
        sortedBinaryTreeChar.add('B');
        sortedBinaryTreeChar.add('C');
        sortedBinaryTreeChar.add('A');
        sortedBinaryTreeChar.add('J');
        sortedBinaryTreeChar.add('G');
        sortedBinaryTreeChar.add('K');
        sortedBinaryTreeChar.add('I');
        sortedBinaryTreeChar.showElements();
        System.out.println();
        sortedBinaryTreeChar.showElements("preorder");
        System.out.println();
        sortedBinaryTreeChar.showElements("postorder");
        System.out.println();
        System.out.println("Size: " + sortedBinaryTreeChar.size());
        System.out.println(sortedBinaryTreeChar.contains('G'));
        System.out.println(sortedBinaryTreeChar.contains('M'));
    }
}
