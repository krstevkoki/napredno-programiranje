package mk.ukim.finki.kol1;

import java.util.Random;

class Coin {
    private SIDE side;

    public SIDE flip() {
        Random random = new Random();
        boolean isHead = random.nextBoolean();
        if (isHead)
            return SIDE.HEAD;
        return SIDE.TAIL;
    }

    public SIDE getSide() {
        return side;
    }
}
