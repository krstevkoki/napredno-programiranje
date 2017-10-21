package mk.ukim.finki.aud4;

public class AlienPackTester {
    public static void main(String[] args) {
        AlienPack apt = new AlienPack(5);
        System.out.println("Total damage: " + apt.calculateDamage());
        System.out.println(apt.toString());

        apt.addAlien(new SnakeAlien("Snakey", 100), 0);
        // System.out.println(apt.toString());

        apt.addAlien(new OgreAlien("Ogrey", 85), 1);
        // System.out.println(apt.toString());

        apt.addAlien(new MarshmallowManAlien("Marshy", 95), 2);
        // System.out.println(apt.toString());

        apt.addAlien(new SnakeAlien("Snakey2", 40), 3);
       // System.out.println(apt.toString());

        apt.addAlien(new OgreAlien("Ogrey2", 40), 4);
        System.out.println(apt.toString());

        System.out.print("Total damage: " + apt.calculateDamage());
    }
}
