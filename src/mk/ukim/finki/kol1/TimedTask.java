package mk.ukim.finki.kol1;

class TimedTask implements Task {
    private final int time;

    public TimedTask(int time) {
        this.time = time;
    }

    @Override
    public int getOrder() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("TT -> %d", getOrder());
    }
}
