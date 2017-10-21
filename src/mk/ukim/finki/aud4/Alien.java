package mk.ukim.finki.aud4;

public abstract class Alien {
    private String name;
    private int health;

    public Alien(String name, int health) {
        this.health = health;
        this.name = name;
    }

    public abstract int getDamage();

    @Override
    public String toString() {
        return name + " " + health;
    }
}
