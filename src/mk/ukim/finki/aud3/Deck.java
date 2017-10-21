package mk.ukim.finki.aud3;

import java.util.Arrays;

public class Deck {
    private PlayingCard[] cards;
    private boolean[] isPicked;
    private int total;

    public Deck() {
        total = 0;
        cards = new PlayingCard[52];
        isPicked = new boolean[52];
        for (int i = 0; i < 13; ++i) {
            for (int j = 0; j < PlayingCard.TYPE.values().length; ++j) {
                if (i + 1 == 1)
                    cards[i + (13 * j)] = new PlayingCard(PlayingCard.TYPE.values()[j], "A");
                else if (i + 1 == 11)
                    cards[i + (13 * j)] = new PlayingCard(PlayingCard.TYPE.values()[j], "J");
                else if (i + 1 == 12)
                    cards[i + (13 * j)] = new PlayingCard(PlayingCard.TYPE.values()[j], "Q");
                else if (i + 1 == 13)
                    cards[i + (13 * j)] = new PlayingCard(PlayingCard.TYPE.values()[j], "K");
                else
                    cards[i + (13 * j)] = new PlayingCard(PlayingCard.TYPE.values()[j], Integer.toString(i + 1));
            }
        }
    }

    public boolean anyLeftInDeck() {
        return total < 52;
    }

    public PlayingCard dealCard() {
        if (!this.anyLeftInDeck())
            return null;
        int card = (int) (Math.random() * 52);
        if (!isPicked[card]) {
            ++total;
            isPicked[card] = true;
            return cards[card];
        }
        return dealCard();
    }

    public void shuffle() {
        for (int i = 0; i < 52; ++i)
            cards[i] = this.dealCard();
        this.isPicked = new boolean[52];
        total = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Deck other = (Deck) obj;
        return Arrays.equals(this.cards, other.cards);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + Arrays.hashCode(this.cards);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlayingCard card : cards) {
            sb.append(card);
            sb.append("\n");
        }
        return sb.toString();
    }
}
