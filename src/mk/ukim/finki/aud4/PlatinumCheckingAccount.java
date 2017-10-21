package mk.ukim.finki.aud4;

public class PlatinumCheckingAccount extends Account implements InterestBearingAccount {
    public PlatinumCheckingAccount(String cardHolder, int cardNumber, double currentAmount) {
        super(cardHolder, cardNumber, currentAmount);
    }

    @Override
    public void addInterest() {
        addAmount(getCurrentAmount() * InterestCheckingAccount.INTEREST_RATE * 2);
    }
}
