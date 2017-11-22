package mk.ukim.finki.aud7;

import javax.naming.OperationNotSupportedException;

public class SortedBinaryTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;
    private int size;

    public SortedBinaryTree() {
        root = null;
    }

    private static <T extends Comparable<T>> boolean isInSubTree(T item, BinaryTreeNode<T> subTreeRoot) {
        if (subTreeRoot == null)
            return false;
        else if (item.equals(subTreeRoot.data))
            return true;
        else if (item.compareTo(subTreeRoot.data) < 0)
            return isInSubTree(item, subTreeRoot.leftLink);
        return isInSubTree(item, subTreeRoot.rightLink);
    }

    private static <T extends Comparable<T>> BinaryTreeNode<T> insertInSubTree(T item, BinaryTreeNode<T> subTreeRoot) {
        if (subTreeRoot == null)
            return new BinaryTreeNode<>(item, null, null);
        if (item.compareTo(subTreeRoot.data) < 0) {
            subTreeRoot.leftLink = insertInSubTree(item, subTreeRoot.leftLink);
            return subTreeRoot;
        }
        subTreeRoot.rightLink = insertInSubTree(item, subTreeRoot.rightLink);
        return subTreeRoot;
    }

    private static <T extends Comparable<T>> void inOrderTraversal(BinaryTreeNode<T> subTreeRoot) {
        // inorder traversal
        if (subTreeRoot != null) {
            inOrderTraversal(subTreeRoot.leftLink);
            System.out.print(subTreeRoot);
            inOrderTraversal(subTreeRoot.rightLink);
        }
    }

    private static <T extends Comparable<T>> void preOrderTraversal(BinaryTreeNode<T> subTreeRoot) {
        // preorder traversal
        if (subTreeRoot != null) {
            System.out.print(subTreeRoot);
            preOrderTraversal(subTreeRoot.leftLink);
            preOrderTraversal(subTreeRoot.rightLink);
        }
    }

    private static <T extends Comparable<T>> void postOrderTraversal(BinaryTreeNode<T> subTreeRoot) {
        // postorder traversal
        if (subTreeRoot != null) {
            postOrderTraversal(subTreeRoot.leftLink);
            postOrderTraversal(subTreeRoot.rightLink);
            System.out.print(subTreeRoot);
        }
    }

    private static <T extends Comparable<T>> void showElementsInSubTree(BinaryTreeNode<T> subTreeRoot, String mode)
            throws OperationNotSupportedException {
        if (mode.trim().toLowerCase().equals("inorder"))
            inOrderTraversal(subTreeRoot);
        else if (mode.trim().toLowerCase().equals("preorder"))
            preOrderTraversal(subTreeRoot);
        else if (mode.trim().toLowerCase().equals("postorder"))
            postOrderTraversal(subTreeRoot);
        else throw new OperationNotSupportedException(mode);
    }

    public void add(T item) {
        root = insertInSubTree(item, root);
        ++size;
    }

    public void showElements() throws OperationNotSupportedException {
        showElementsInSubTree(root, "inorder");
    }

    public void showElements(String mode) throws OperationNotSupportedException {
        showElementsInSubTree(root, mode);
    }

    public int size() {
        return size;
    }

    public boolean contains(T item) {
        return isInSubTree(item, root);
    }

    private static class BinaryTreeNode<T extends Comparable<T>> {
        private T data;
        private BinaryTreeNode<T> leftLink;
        private BinaryTreeNode<T> rightLink;

        public BinaryTreeNode(T data, BinaryTreeNode<T> leftLink, BinaryTreeNode<T> rightLink) {
            this.data = data;
            this.leftLink = leftLink;
            this.rightLink = rightLink;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;
            if (data != null ? !data.equals(that.data) : that.data != null)
                return false;
            if (leftLink != null ? !leftLink.equals(that.leftLink) : that.leftLink != null)
                return false;
            return rightLink != null ? rightLink.equals(that.rightLink) : that.rightLink == null;
        }

        @Override
        public int hashCode() {
            int result = data != null ? data.hashCode() : 0;
            result = 31 * result + (leftLink != null ? leftLink.hashCode() : 0);
            result = 31 * result + (rightLink != null ? rightLink.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return data.toString() + " ";
        }
    }
}
