package mk.ukim.finki.kol1;

import java.util.Date;

abstract class Archive {
    private int id;
    private Date dateArchived;

    public Archive(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDateArchived() {

        return dateArchived;
    }

    public void setDateArchived(Date dateArchived) {
        this.dateArchived = dateArchived;
    }
}
