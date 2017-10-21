package mk.ukim.finki.lab1;

class FlatAmountProvisionTransaction extends Transaction implements Parser {
    private String flatProvision;

    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatProvision) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatProvision = flatProvision;
    }

    public String getFlatProvision() {
        return flatProvision;
    }

    @Override
    public double getProvision() {
        return Parser.parseStringToDouble(flatProvision);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = super.hashCode();
        hash = hash * prime + flatProvision.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FlatAmountProvisionTransaction))
            return true;
        return super.equals(obj);
    }
}
