package mk.ukim.finki.kol1;

class SpecialArchive extends Archive {
    private int maxOpen;
    private int timesOpened;

    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        timesOpened = 0;
    }

    public int getTimesOpened() {
        return timesOpened;
    }

    public void setTimesOpened(int timesOpened) {

        this.timesOpened = timesOpened;
    }

    public int getMaxOpen() {
        return maxOpen;
    }
}
