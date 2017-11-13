package mk.ukim.finki.kol1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

class ArchiveStore {
    private ArrayList<Archive> archives;
    private StringBuilder logger;

    public ArchiveStore() {
        archives = new ArrayList<>();
        logger = new StringBuilder();
    }

    public void archiveItem(Archive item, Date date) {
        item.setDateArchived(date);
        archives.add(item);
        logger.append(String.format("Item %d archived at %s\n", item.getId(), printDate(item.getDateArchived())));
    }

    private int contains(int id) {
        for (int i = 0; i < archives.size(); ++i)
            if (archives.get(i).getId() == id)
                return i;
        return -1;
    }

    public void openItem(int id, Date date) throws NonExistingItemException {
        int index;
        if ((index = contains(id)) != -1) {
            Archive element = archives.get(index);
            if (element instanceof LockedArchive) {
                LockedArchive la = (LockedArchive) element;
                if (la.getDateToOpen().compareTo(date) <= 0)
                    logger.append(String.format("Item %d opened at %s\n", id, printDate(date)));
                else
                    logger.append(String.format("Item %d cannot be opened before %s\n", id, printDate(la.getDateToOpen())));
            }
            if (element instanceof SpecialArchive) {
                SpecialArchive sa = (SpecialArchive) element;
                if (sa.getTimesOpened() < sa.getMaxOpen()) {
                    logger.append(String.format("Item %d opened at %s\n", id, printDate(date)));
                    sa.setTimesOpened(sa.getTimesOpened() + 1);
                } else
                    logger.append(String.format("Item %d cannot be opened more than %d times\n", id, sa.getMaxOpen()));
            }
        } else throw new NonExistingItemException(String.format("Item with id %d doesn't exist", id));
    }

    public String getLog() {
        return logger.toString();
    }

    private String printDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}
