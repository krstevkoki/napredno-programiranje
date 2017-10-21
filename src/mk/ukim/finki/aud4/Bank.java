package mk.ukim.finki.aud4;

import java.util.Arrays;

public class Bank {
    private Account[] accounts;
    private int totalAccounts;
    private int max;

    public Bank(int max) {
        this.max = max;
        this.totalAccounts = 0;
        this.accounts = new Account[max];
    }

    public void addAccount(Account account) {
        if (this.totalAccounts == this.accounts.length)
            this.accounts = Arrays.copyOf(this.accounts, 2 * this.max);
        this.accounts[this.totalAccounts++] = account;
    }

    public double totalAssets() {
        double sum = 0;
        for (Account account : accounts) {
            if (account != null)
                sum += account.getCurrentAmount();
        }
        return sum;
    }

    public void addInterest() {
        for (Account account : accounts) {
            if (account instanceof InterestBearingAccount) {
                InterestBearingAccount iba = (InterestBearingAccount) account;
                iba.addInterest();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Account account : accounts) {
            if (account != null) {
                sb.append(account);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
