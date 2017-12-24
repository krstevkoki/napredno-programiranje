package mk.ukim.finki.kol2;

import java.util.*;
import java.util.stream.IntStream;

class BlockContainer<T extends Comparable<T>> {
    private int numElements;
    private int numElementsPerBlock;
    private Map<Integer, Block<T>> blocks;

    public BlockContainer(int numElementsPerBlock) {
        this.numElementsPerBlock = numElementsPerBlock;
        this.numElements = 0;
        this.blocks = new HashMap<>();
    }

    public void add(T a) {
        if (numElements % numElementsPerBlock == 0) {  // create a new block
            ++numElements;
            int blockNumber = numElements / numElementsPerBlock + 1;
            if (numElementsPerBlock == 1)
                blockNumber -= 1;
            Block<T> block = new Block<>(numElementsPerBlock);
            block.addElementInBlock(a);
            blocks.put(blockNumber, block);
        } else {  // add the element to the old block
            int blockNumber = numElements / numElementsPerBlock + 1;
            blocks.computeIfPresent(blockNumber, (key, value) -> {
                value.addElementInBlock(a);
                return value;
            });
            ++numElements;
        }
    }

    private int computeBlockNumber() {
        if (numElements % numElementsPerBlock == 0)
            return numElements / numElementsPerBlock;
        else
            return numElements / numElementsPerBlock + 1;
    }

    public boolean remove(T a) {
        int numBlock = computeBlockNumber();
        --numElements;
        blocks.computeIfPresent(numBlock, (key, value) -> {
            value.deleteElement(a);
            return value;
        });
        int newNumBlock = computeBlockNumber();
        if (numBlock != newNumBlock)
            blocks.remove(numBlock);
        return true;
    }

    public void sort() {
        List<T> list = new LinkedList<>();
        blocks.values().stream()
                .flatMap(block -> block.getElements().stream())
                .forEach(list::add);
        Collections.sort(list);
        this.blocks.clear();
        numElements = 0;
        list.stream()
                .forEach(this::add);
    }

    @Override
    public String toString() {
        int numBlock = computeBlockNumber();
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(1, numBlock)
                .forEach(i -> {
                    sb.append("[");
                    sb.append(blocks.get(i));
                    sb.append("]");
                    if (i != numBlock)
                        sb.append(",");
                });
        return sb.toString();
    }
}
