package mk.ukim.finki.lab8;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

/**
 * LocalTime API tests
 */
public class LocalTimeTest {
    public static void main(String[] args) {
        System.out.println(localTimeOfHourToMinute());
        System.out.println(localTimeOfHourToNanoSec());
        System.out.println(localTimeParse());
        System.out.println(localTimeWith());
        System.out.println(localTimePlus());
        System.out.println(localTimeMinus());
        System.out.println(localTimeMinusDuration());
        System.out.println(localDateIsBefore());
        System.out.println(localTimeTruncatedTo());
    }

    private static LocalTime localTimeOfHourToMinute() {
        /**
         * Create a {@link LocalTime} of 23:07 by using {@link LocalTime#of}
         */
        return LocalTime.of(23, 7);
    }

    private static LocalTime localTimeOfHourToNanoSec() {
        /**
         * Create a {@link LocalTime} of 23:07:03.1 by using {@link LocalTime#of}
         */
        return LocalTime.of(23, 7, 3, 100000000);
    }

    private static LocalTime localTimeParse() {
        /**
         * Create a {@link LocalTime} of 23:07:03.1 from String by using {@link LocalTime#parse}
         */
        return LocalTime.parse("23:07:03.1");
    }

    private static LocalTime localTimeWith() {
        LocalTime lt = DateAndTimes.LT_23073050;
        /**
         * Create a {@link LocalTime} from {@link lt} with hour 21
         * by using {@link LocalTime#withHour} or {@link LocalTime#with}
         */
        return lt.withHour(21);
    }

    private static LocalTime localTimePlus() {
        LocalTime lt = DateAndTimes.LT_23073050;
        /**
         * Create a {@link LocalTime} from {@link lt} with 30 minutes later
         * by using {@link LocalTime#plusMinutes} or {@link LocalTime#plus}
         */
        return lt.plusMinutes(30);
    }

    private static LocalTime localTimeMinus() {
        LocalTime lt = DateAndTimes.LT_23073050;
        /**
         * Create a {@link LocalTime} from {@link lt} with 3 hours before
         * by using {@link LocalTime#minusHours} or {@link LocalTime#minus}
         */
        return lt.minusHours(3);
    }


    private static LocalTime localTimeMinusDuration() {
        LocalTime lt = DateAndTimes.LT_23073050;
        /**
         * Define a {@link Duration} of 3 hours 30 minutes and 20.2 seconds
         * Create a {@link LocalTime} subtracting the duration from {@link lt} by using {@link LocalTime#minus}
         */
        return lt.minus(Duration.parse("PT3H30M20.2S"));
    }

    private static boolean localDateIsBefore() {
        LocalTime lt = DateAndTimes.LT_23073050;
        LocalTime lt2 = DateAndTimes.LT_12100000;
        /**
         * Check whether {@link lt2} is before {@link lt} or not
         * by using {@link LocalTime#isAfter} or {@link LocalTime#isBefore}
         */
        return lt2.isBefore(lt);
    }

    private static LocalTime localTimeTruncatedTo() {
        LocalTime lt = DateAndTimes.LT_23073050;
        /**
         * Create a {@link LocalTime} from {@link lt} truncated to minutes by using {@link LocalTime#truncatedTo}
         */
        return lt.truncatedTo(ChronoUnit.MINUTES);
    }

    private static class DateAndTimes {
        private static final LocalTime LT_23073050 = LocalTime.of(23, 7, 30, 500000000);
        private static final LocalTime LT_12100000 = LocalTime.of(12, 10);
    }
}
