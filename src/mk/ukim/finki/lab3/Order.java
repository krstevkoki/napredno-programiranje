package mk.ukim.finki.lab3;

class Order {
    private Item[] items;
    private int[] counts;
    private boolean isLocked;

    public Order() {
        items = new Item[0];
        counts = new int[0];
        isLocked = false;
    }

    public int alredyInStock(Item item) {
        int index = -1;
        for (int i = 0; i < items.length; ++i) {
            if (items[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if (!isLocked) {
            if (count > 10)
                throw new ItemOutOfStockException(item);
            int index;
            if ((index = alredyInStock(item)) != -1) {
                items[index] = item;
                counts[index] = count;
            } else {
                Item[] tempItems = new Item[items.length + 1];
                int[] tempCounts = new int[counts.length + 1];
                System.arraycopy(items, 0, tempItems, 0, items.length);
                System.arraycopy(counts, 0, tempCounts, 0, counts.length);
                tempItems[items.length] = item;
                tempCounts[counts.length] = count;
                items = tempItems;
                counts = tempCounts;
            }
        } else
            throw new OrderLockedException();
    }

    public int getPrice() {
        int price = 0;
        for (int i = 0; i < items.length; ++i) {
            price += items[i].getPrice() * counts[i];
        }
        return price;
    }

    public void removeItem(int idx) throws OrderLockedException {
        if (!isLocked) {
            if (idx < 0 || idx >= items.length)
                throw new ArrayIndexOutOfBoundsException(idx);
            Item[] tempItems = new Item[items.length - 1];
            int[] tempCounts = new int[counts.length - 1];
            for (int i = 0, j = 0; i < items.length; ++i) {
                if (i != idx) {
                    tempItems[j] = items[i];
                    tempCounts[j++] = counts[i];
                }
            }
            items = tempItems;
            counts = tempCounts;
        } else
            throw new OrderLockedException();
    }

    public void lock() throws EmptyOrder {
        if (counts.length != 0)
            isLocked = true;
        else
            throw new EmptyOrder();
    }

    public void displayOrder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; ++i) {
            sb.append(String.format("%3s", i + 1));
            sb.append(".");
            sb.append(String.format("%-15s", items[i]));
            sb.append("x");
            sb.append(String.format("%2s", counts[i]));
            sb.append(String.format("%5s", items[i].getPrice() * counts[i]));
            sb.append("$");
            sb.append("\n");
        }
        sb.append(String.format("%-22s", "Total:"));
        sb.append(String.format("%5s", getPrice()));
        sb.append("$");
        System.out.println(sb.toString());
    }
}
