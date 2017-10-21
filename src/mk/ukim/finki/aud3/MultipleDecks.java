package mk.ukim.finki.aud3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MultipleDecks {
    private Deck[] decks;

    public MultipleDecks(int n) {
        decks = new Deck[n];
        for (int i = 0; i < n; ++i) {
            decks[i] = new Deck();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + Arrays.hashCode(this.decks);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        MultipleDecks other = (MultipleDecks) obj;
        return Arrays.equals(this.decks, other.decks);
    }

    @Override
    public String toString() {
        /*StringBuilder sb = new StringBuilder();
        for (Deck deck : decks) {
            sb.append(deck);
            sb.append("\n");
        }
        return sb.toString();*/
        // Java 8 [Streams]
        return Arrays.stream(decks).map(Deck::toString).collect(Collectors.joining("\n"));
    }
}
