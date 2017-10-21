package mk.ukim.finki.lab1;

class FlatPercentProvisionTransaction extends Transaction implements Parser {
    private int centsPerDollar;

    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDollar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDollar;
    }

    public int getCentsPerDollar() {
        return centsPerDollar;
    }

    @Override
    public double getProvision() {
        return (centsPerDollar / 100.0) * (int) Parser.parseStringToDouble(super.getAmount());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = super.hashCode();
        hash = prime * hash + centsPerDollar;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FlatPercentProvisionTransaction))
            return false;
        return super.equals(obj);
    }
}
