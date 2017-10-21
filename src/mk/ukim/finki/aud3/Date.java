package mk.ukim.finki.aud3;

class Date {
    private final int days;
    private static final int FIRST_YEAR = 1800;
    private static final int LAST_YEAR = 2500;
    private static final int DAYS_IN_YEAR = 365;
    private static final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] daysTillFirstOfMonth;
    private static final int[] daysTillJan1;

    static {
        daysTillFirstOfMonth = new int[12];
        for (int i = 1; i < 12; ++i)
            daysTillFirstOfMonth[i] = daysOfMonth[i - 1] + daysTillFirstOfMonth[i - 1];
        int totalYears = LAST_YEAR - FIRST_YEAR + 1;
        daysTillJan1 = new int[totalYears];
        int currentYear = FIRST_YEAR;
        for (int i = 1; i < totalYears; ++i) {
            if (isLeapYear(currentYear))
                daysTillJan1[i] = daysTillJan1[i - 1] + DAYS_IN_YEAR + 1;
            else
                daysTillJan1[i] = daysTillJan1[i - 1] + DAYS_IN_YEAR;
            ++currentYear;
        }
    }

    private static boolean isLeapYear(final int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    public Date(final int days) {
        this.days = days;
    }

    public Date(final int days, final int month, final int year) {
        if ((days < 0 || days > 31) || (month < 0 || month > 12) || (year < 1800 || year > 2500))
            throw new RuntimeException();
        int d = 0;
        d += daysTillJan1[year - FIRST_YEAR];
        d += daysTillFirstOfMonth[month - 1];
        if (month > 2 && isLeapYear(year))
            ++d;
        d += days;
        this.days = d;
    }

    public int substract(final Date date) {
        int result = this.days - date.days;
        if (result >= 1)
            return this.days - date.days;
        throw new RuntimeException();
    }

    public Date increment(final int days) {
        return new Date(this.days + days);
    }

    public int getDays() {
        return days;
    }

    public static int[] getDaysOfMonth() {
        return daysOfMonth;
    }

    public static int getFirstYear() {
        return FIRST_YEAR;
    }

    public static int getLastYear() {
        return LAST_YEAR;
    }

    public static int getDaysInYear() {
        return DAYS_IN_YEAR;
    }

    public static int[] getDaysTillFirstOfMonth() {
        return daysTillFirstOfMonth;
    }

    public static int[] getDaysTillJan1() {
        return daysTillJan1;
    }

    @Override
    public boolean equals(final Object other) {
        Date date = (Date) other;
        return this.days == date.days;
    }

    @Override
    public String toString() {
        int d = days, month, year, i;
        for (i = 0; i < daysTillJan1.length; ++i)
            if (daysTillJan1[i] >= days)
                break;
        d -= daysTillJan1[i - 1];
        year = FIRST_YEAR + i - 1;
        if (isLeapYear(year))
            --d;
        for (i = 0; i < daysTillFirstOfMonth.length; ++i)
            if (daysTillFirstOfMonth[i] >= d && i != 0)
                break;
        month = i;
        if (month < 3 && isLeapYear(year))
            ++d;
        d -= daysTillFirstOfMonth[i - 1];
        if (d == 32) {  // special case 01.02.leapYear :(
            d = 1;
            month = 2;
        }
        return String.format("%02d.%02d.%04d", d, month, year);
    }
}
