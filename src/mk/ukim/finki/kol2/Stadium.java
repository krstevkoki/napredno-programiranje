package mk.ukim.finki.kol2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Stadium {
    private String name;
    private Map<String, Sector> sectors;

    public Stadium(String name) {
        this.name = name;
        this.sectors = new HashMap<>();
    }

    public void createSectors(String[] sectorNames, int[] sizes) {
        IntStream.range(0, sectorNames.length)
                .forEach(i -> this.sectors.put(sectorNames[i], new Sector(sectorNames[i], sizes[i])));
    }

    public void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        Sector sector = this.sectors.getOrDefault(sectorName, null);
        if (sector != null)
            sector.takeSeat(seat, type);
    }

    public void showSectors() {
        this.sectors.values()
                .stream()
                .sorted(Comparator
                        .comparing(Sector::getFreeSeats).reversed()
                        .thenComparing(Sector::getSectorCode))
                .forEach(System.out::println);
    }
}
