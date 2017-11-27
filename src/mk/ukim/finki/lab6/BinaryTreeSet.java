package mk.ukim.finki.lab6;

class BinaryTreeSet<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;

    public BinaryTreeSet() {
        this.root = null;
    }

    private static <T extends Comparable<T>> BinaryTreeNode<T> insertInSubtree(T element, BinaryTreeNode<T> subTree) {
        if (subTree == null)
            return new BinaryTreeNode<>(element, null, null);
        else if (element.compareTo(subTree.data) < 0)
            subTree.left = insertInSubtree(element, subTree.left);
        else
            subTree.right = insertInSubtree(element, subTree.right);
        return subTree;
    }

    // finds and returns the node from the tree
    private static <T extends Comparable<T>> BinaryTreeNode<T> find(T element, BinaryTreeNode<T> subTree) {
        if (subTree == null)
            return null;
        if (subTree.data.compareTo(element) == 0 && !subTree.isDeleted)
            return subTree;
        else if (element.compareTo(subTree.data) < 0)
            return find(element, subTree.left);
        return find(element, subTree.right);
    }

    // checks if the given element is in the tree
    private static <T extends Comparable<T>> boolean contains(T element, BinaryTreeNode<T> subTree) {
        if (subTree == null)
            return false;
        if (subTree.data.compareTo(element) == 0 && !subTree.isDeleted)
            return true;
        else if (element.compareTo(subTree.data) < 0)
            return contains(element, subTree.left);
        return contains(element, subTree.right);
    }

    private static <T extends Comparable<T>> String printInOrder(BinaryTreeNode<T> subTree) {
        if (subTree != null) {
            if (!subTree.isDeleted)
                return printInOrder(subTree.left) + subTree + printInOrder(subTree.right);
            else
                return printInOrder(subTree.left) + printInOrder(subTree.right);
        }
        return "";
    }

    public void addElement(T element) {
        if (!contains(element))
            root = insertInSubtree(element, root);
    }

    public boolean contains(T element) {
        return contains(element, root);
    }

    public boolean removeElement(T element) {
        BinaryTreeNode<T> temp = find(element, root);
        if (temp == null)
            return false;
        temp.isDeleted = true;
        return true;
    }

    @Override
    public String toString() {
        if (root == null)
            return "EMPTY";
        String ret = printInOrder(root);
        return ret.substring(0, ret.length() - 1);
    }

    private static class BinaryTreeNode<T extends Comparable<T>> {
        private T data;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;
        private boolean isDeleted;  // marker used for removing nodes from the tree

        public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.isDeleted = false;
        }

        @Override
        public String toString() {
            return String.format("%s ", data);
        }
    }
}
