package mk.ukim.finki.aud7;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    private static class TreeNode<T extends Comparable<T>> {
        private T data;
        private TreeNode<T> leftLink;
        private TreeNode<T> rightLink;

        public TreeNode(T data, TreeNode<T> leftLink, TreeNode<T> rightLink) {
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
            TreeNode<?> that = (TreeNode<?>) o;
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

    public Tree() {
        root = null;
    }

    public void add(T item) {
        root = insertInSubTree(item, root);
        ++size;
    }

    public void showElements() {
        showElementsInSubTree(root, "inorder");
    }

    public void showElements(String mode) {
        showElementsInSubTree(root, mode);
    }

    public int size() {
        return size;
    }

    public boolean contains(T item) {
        return isInSubTree(item, root);
    }

    private static <T extends Comparable<T>> boolean isInSubTree(T item, TreeNode<T> subTreeRoot) {
        if (subTreeRoot == null)
            return false;
        else if (item.equals(subTreeRoot.data))
            return true;
        else if (item.compareTo(subTreeRoot.data) < 0)
            return isInSubTree(item, subTreeRoot.leftLink);
        return isInSubTree(item, subTreeRoot.rightLink);
    }


    private static <T extends Comparable<T>> TreeNode<T> insertInSubTree(T item, TreeNode<T> subTreeRoot) {
        if (subTreeRoot == null)
            return new TreeNode<>(item, null, null);
        if (item.compareTo(subTreeRoot.data) < 0) {
            subTreeRoot.leftLink = insertInSubTree(item, subTreeRoot.leftLink);
            return subTreeRoot;
        }
        subTreeRoot.rightLink = insertInSubTree(item, subTreeRoot.rightLink);
        return subTreeRoot;
    }

    private static <T extends Comparable<T>> void inOrderTraversal(TreeNode<T> subTreeRoot) {
        // inorder traversal
        if (subTreeRoot != null) {
            inOrderTraversal(subTreeRoot.leftLink);
            System.out.print(subTreeRoot);
            inOrderTraversal(subTreeRoot.rightLink);
        }
    }

    private static <T extends Comparable<T>> void preOrderTraversal(TreeNode<T> subTreeRoot) {
        // preorder traversal
        if (subTreeRoot != null) {
            System.out.print(subTreeRoot);
            preOrderTraversal(subTreeRoot.leftLink);
            preOrderTraversal(subTreeRoot.rightLink);
        }
    }

    private static <T extends Comparable<T>> void postOrderTraversal(TreeNode<T> subTreeRoot) {
        // postorder traversal
        if (subTreeRoot != null) {
            postOrderTraversal(subTreeRoot.leftLink);
            postOrderTraversal(subTreeRoot.rightLink);
            System.out.print(subTreeRoot);
        }
    }

    private static <T extends Comparable<T>> void showElementsInSubTree(TreeNode<T> subTreeRoot, String mode) {
        if (mode.trim().toLowerCase().equals("inorder"))
            inOrderTraversal(subTreeRoot);
        else if (mode.trim().toLowerCase().equals("preorder"))
            preOrderTraversal(subTreeRoot);
        else if (mode.trim().toLowerCase().equals("postorder"))
            postOrderTraversal(subTreeRoot);
        else throw new NoSuchModeException();

    }
}
