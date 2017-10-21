package mk.ukim.finki.lab1;

abstract class Transaction {
    private final long fromId;
    private final long toId;
    private final String description;
    private final String amount;

    public Transaction(long fromId, long toId, String description, String amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public abstract double getProvision();

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) fromId;
        hash = prime * hash + (int) toId;
        hash = prime * hash + description.hashCode();
        hash = prime * hash + amount.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FlatAmountProvisionTransaction) {
            FlatAmountProvisionTransaction tmp = (FlatAmountProvisionTransaction) obj;
            return this.fromId == tmp.getFromId() && this.toId == tmp.getToId()
                    && this.description.equals(tmp.getDescription()) && this.amount.equals(tmp.getAmount())
                    && this.hashCode() == tmp.hashCode();
        } else if (obj instanceof FlatPercentProvisionTransaction) {
            FlatPercentProvisionTransaction tmp = (FlatPercentProvisionTransaction) obj;
            return this.fromId == tmp.getFromId() && this.toId == tmp.getToId()
                    && this.description.equals(tmp.getDescription()) && this.amount.equals(tmp.getAmount())
                    && this.hashCode() == tmp.hashCode();
        } else
            return false;
    }
}
