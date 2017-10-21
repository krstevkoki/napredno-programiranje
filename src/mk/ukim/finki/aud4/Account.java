package mk.ukim.finki.aud4;

public abstract class Account {
    private String cardHolder;
    private int cardNumber;
    private double currentAmount;

    public Account(String cardHolder, int cardNumber, double currentAmount) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.currentAmount = currentAmount;
    }

    public void addAmount(double amount) {
        this.currentAmount += amount;
    }

    public void withdraw(double amount) {
        if (amount <= this.currentAmount)
            this.currentAmount -= amount;
        else
            throw new RuntimeException();
    }

    @Override
    public String toString() {
        return cardHolder + "\t " + cardNumber + "\t" + currentAmount;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }
}
