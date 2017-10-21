package mk.ukim.finki.aud4;

public class AlienPack {
    private Alien[] aliens;

    public AlienPack(int numAliens) {
        this.aliens = new Alien[numAliens];
    }

    public AlienPack(Alien[] aliens) {
        this.aliens = aliens;
    }

    public void addAlien(Alien newAlien, int index) {
        if (index < aliens.length)
            aliens[index] = newAlien;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public Alien[] getAliens() {
        return aliens;
    }

    public int calculateDamage() {
        int damage = 0;
        for (Alien alien : aliens) {
            if (alien != null) {
                damage += alien.getDamage();
            }
        }
        return damage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Alien alien : aliens) {
            if (alien != null) {
                sb.append(alien);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
