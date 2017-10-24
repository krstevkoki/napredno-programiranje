package mk.ukim.finki.lab3;

class PizzaItem implements Item {
    private String type;
    private static final String[] validPizzaItem = {"Standard", "Pepperoni", "Vegetarian"};
    private static final int[] pizzaItemPrices = {10, 12, 8};

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if (isValidType(type))
            this.type = type;
        else
            throw new InvalidPizzaTypeException();
    }

    private static boolean isValidType(String type) {
        return type.equals(validPizzaItem[0]) || type.equals(validPizzaItem[1]) || type.equals(validPizzaItem[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaItem))
            return false;
        PizzaItem pizzaItem = (PizzaItem) o;
        return type != null ? type.equals(pizzaItem.type) : pizzaItem.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public int getPrice() {
        int index = -1;
        for (int i = 0; i < pizzaItemPrices.length; ++i) {
            if (type.equals(validPizzaItem[i])) {
                index = i;
                break;
            }
        }
        return pizzaItemPrices[index];
    }

    @Override
    public String toString() {
        return type;
    }
}
