package mk.ukim.finki.kol2;

import java.util.Set;
import java.util.TreeSet;

public class Block<T> {
    private int blockSize;
    private Set<T> elements;

    public Block(int blockSize) {
        this.blockSize = blockSize;
        this.elements = new TreeSet<>();
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void addElementInBlock(T element) {
        elements.add(element);
    }

    public void deleteElement(T element) {
        elements.remove(element);
    }

    public Set<T> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block<?> block = (Block<?>) o;
        if (blockSize != block.blockSize) return false;
        return elements != null ? elements.equals(block.elements) : block.elements == null;
    }

    @Override
    public int hashCode() {
        int result = blockSize;
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        elements.stream()
                .forEach(element -> {
                    sb.append(element);
                    sb.append(", ");
                });
        return sb.toString().substring(0, sb.toString().length() - 2);
    }
}
