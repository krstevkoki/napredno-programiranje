package mk.ukim.finki.aud4;

public class SnakeAlien extends Alien {
    public SnakeAlien(String name, int health) {
        super(name, health);
    }

    @Override
    public int getDamage() {
        return 10;
    }
}
