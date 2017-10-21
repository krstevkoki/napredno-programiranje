package mk.ukim.finki.aud4;

public class MarshmallowManAlien extends Alien {
    public MarshmallowManAlien(String name, int health) {
        super(name, health);
    }

    @Override
    public int getDamage() {
        return 1;
    }
}
