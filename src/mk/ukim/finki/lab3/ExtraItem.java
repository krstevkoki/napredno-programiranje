package mk.ukim.finki.lab3;

class ExtraItem implements Item {
    private String type;
    private static final String[] validExtraItems = {"Coke", "Ketchup"};
    private static final int[] extraItemPrices = {5, 3};

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if (isValidType(type))
            this.type = type;
        else
            throw new InvalidExtraTypeException();
    }

    private static boolean isValidType(String type) {
        return type.equals(validExtraItems[0]) || type.equals(validExtraItems[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExtraItem))
            return false;
        ExtraItem extraItem = (ExtraItem) o;
        return type != null ? type.equals(extraItem.type) : extraItem.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public int getPrice() {
        int index = -1;
        for (int i = 0; i < extraItemPrices.length; ++i) {
            if (type.equals(validExtraItems[i])) {
                index = i;
                break;
            }
        }
        return extraItemPrices[index];
    }

    @Override
    public String toString() {
        return type;
    }
}
