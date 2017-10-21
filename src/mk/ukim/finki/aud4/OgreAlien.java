package mk.ukim.finki.aud4;

public class OgreAlien extends Alien {
    public OgreAlien(String name, int health) {
        super(name, health);
    }

    @Override
    public int getDamage() {
        return 6;
    }
}
