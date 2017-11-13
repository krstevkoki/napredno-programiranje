package mk.ukim.finki.kol1;

class LoadedCoin extends Coin {
    private int probability;
    private int counter;

    public LoadedCoin(int probability) {
        super();
        this.probability = probability;
        counter = 0;
    }

    @Override
    public SIDE flip() {
        if (counter < (probability / 100.0) * 1000) {
            ++counter;
            return SIDE.HEAD;
        }
        ++counter;
        return SIDE.TAIL;
    }
}
