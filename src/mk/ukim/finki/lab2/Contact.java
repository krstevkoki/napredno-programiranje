package mk.ukim.finki.lab2;

import java.util.Date;

abstract class Contact {
    protected String date;

    public Contact(String date) {  // YYYY-MM-DD
        this.date = date;
    }

    public boolean isNewerThan(Contact c) {
        // convert Contact this to Date object
        int thisYear = Integer.parseInt(date.substring(0, 4));
        int thisMmonth = Integer.parseInt(date.substring(5, 7));
        int thisDay = Integer.parseInt(date.substring(8, 10));
        // convert Contact c to Date object
        int cYear = Integer.parseInt(c.date.substring(0, 4));
        int cMonth = Integer.parseInt(c.date.substring(5, 7));
        int cDay = Integer.parseInt(c.date.substring(8, 10));

        Date thisDate = new Date(thisYear, thisMmonth, thisDay);
        Date cDate = new Date(cYear, cMonth, cDay);

        return thisDate.compareTo(cDate) > 0;
    }

    public abstract String getType();
}
