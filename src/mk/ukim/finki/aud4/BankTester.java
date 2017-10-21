package mk.ukim.finki.aud4;

public class BankTester {
    public static void main(String[] args) {
        Bank bank = new Bank(5);
        InterestCheckingAccount ica = new InterestCheckingAccount("Kostadin Krstev", 11111, 14321);
        bank.addAccount(ica);
        System.out.println(bank.toString());

        PlatinumCheckingAccount pca = new PlatinumCheckingAccount("Kostadin Krstev", 22222, 10232);
        pca.withdraw(2403);
        bank.addAccount(pca);
        System.out.println(bank.toString());

        NonInterestCheckingAccount nca = new NonInterestCheckingAccount("Kostadin Krstev", 33333, 5683);
        nca.addAmount(9032);
        bank.addAccount(nca);
        System.out.println(bank.toString());

        System.out.println("Vkupna sostojba na smetkite: " + String.format("%.2f\n", bank.totalAssets()));

        bank.addInterest();
        System.out.println(bank.toString());

        System.out.println("Vkupna sostojba na smetkite: " + String.format("%.2f", bank.totalAssets()));
    }
}
