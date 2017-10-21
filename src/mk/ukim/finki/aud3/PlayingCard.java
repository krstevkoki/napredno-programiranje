package mk.ukim.finki.aud3;

public class PlayingCard {
    public enum TYPE {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUB
    }

    private TYPE type;
    private String rank;  // String for storing J, Q, K, A

    public PlayingCard(TYPE type, String rank) {
        this.type = type;
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + this.rank.hashCode();
        hash = hash * prime + ((this.type == null ? 0 : this.type.hashCode()));
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
        PlayingCard other = (PlayingCard) obj;
        return this.rank.equals(other.rank) && this.type == other.type && this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s \t %s", rank, type.toString());
    }
}