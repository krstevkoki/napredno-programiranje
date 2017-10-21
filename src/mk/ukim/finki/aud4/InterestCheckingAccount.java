package mk.ukim.finki.aud4;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {
    public static final double INTEREST_RATE = 0.03;  // 3%

    public InterestCheckingAccount(String cardHolder, int cardNumber, double currentAmount) {
        super(cardHolder, cardNumber, currentAmount);
    }

    @Override
    public void addInterest() {
        addAmount(getCurrentAmount() * INTEREST_RATE);
    }
}
