package mk.ukim.finki.lab3;

class ItemOutOfStockException extends Exception {
    private Item item;

    public ItemOutOfStockException(Item item) {
        this.item = item;
    }
}
