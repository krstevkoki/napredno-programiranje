package mk.ukim.finki.aud3;

public class DeckTester {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck.toString());
        deck.shuffle();
        System.out.println(deck.toString());
        PlayingCard card;
        while ((card = deck.dealCard()) != null) {
            System.out.println(card);
        }
    }
}
