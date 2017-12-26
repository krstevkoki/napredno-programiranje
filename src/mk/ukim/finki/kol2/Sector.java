package mk.ukim.finki.kol2;

class Sector {
    private String sectorCode;
    private int numSeats;
    private boolean[] takenSeats;
    private int sectorType;
    public int freeSeats;

    public Sector(String sectorCode, int numSeats) {
        this.sectorCode = sectorCode;
        this.numSeats = numSeats;
        this.takenSeats = new boolean[numSeats + 1];
        this.sectorType = 0;
        this.freeSeats = numSeats;
    }

    public String getSectorCode() {
        return sectorCode;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    private double calculatePercentage() {
        return 100 - (freeSeats / (double) numSeats * 100);
    }

    public void takeSeat(int seat, int type) throws SeatNotAllowedException, SeatTakenException {
        if (sectorType == 0)
            sectorType = type;
        if (type == 1) {
            if (takenSeats[seat])
                throw new SeatTakenException(String.format("%d", seat));
            if (sectorType == 2)
                throw new SeatNotAllowedException(String.format("%d", seat));
            takenSeats[seat] = true;
        } else if (type == 2) {
            if (takenSeats[seat])
                throw new SeatTakenException(String.format("%d", seat));
            if (sectorType == 1)
                throw new SeatNotAllowedException(String.format("%d", seat));
            takenSeats[seat] = true;
        } else {
            if (takenSeats[seat])
                throw new SeatTakenException(String.format("%d", seat));
            takenSeats[seat] = true;
        }
        --freeSeats;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t%d/%d\t\t%.1f%%", sectorCode, freeSeats, numSeats, calculatePercentage());
    }
}
