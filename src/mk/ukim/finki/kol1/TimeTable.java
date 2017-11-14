package mk.ukim.finki.kol1;

import java.io.*;
import java.util.ArrayList;

class TimeTable {
    private ArrayList<Time> times;

    public TimeTable() {
        times = new ArrayList<>();
    }

    private void parseTime(String time) throws UnsupportedFormatException, InvalidTimeException {
        String[] parts = time.split(":");
        if (parts.length == 1) {
            parts = time.split("\\.");
            if (parts.length == 1)
                throw new UnsupportedFormatException(time);
        }
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        if ((hours < 0 || hours > 23) || (minutes < 0 || minutes > 59))
            throw new InvalidTimeException(time);
        times.add(new Time(hours, minutes));
    }

    public void readTimes(InputStream inputStream) throws IOException, UnsupportedFormatException, InvalidTimeException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bf.readLine()) != null && line.length() != 0) {
                String parts[] = line.split("\\s+");
                for (String part : parts)
                    parseTime(part);
            }
        }
    }

    private void sortTimes() {
        for (int i = 0; i < times.size() - 1; ++i) {
            for (int j = 0; j < times.size() - i - 1; ++j) {
                int min1 = times.get(j).getHour() * 60 + times.get(j).getMinute();
                int min2 = times.get(j + 1).getHour() * 60 + times.get(j + 1).getMinute();
                if (min1 > min2) {
                    Time temp = times.get(j);
                    times.set(j, times.get(j + 1));
                    times.set(j + 1, temp);
                }
            }
        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format) {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream));
        sortTimes();
        if (format.toString().equals("FORMAT_24")) {
            for (Time time : times)
                out.println(time.toString());
        } else {
            for (Time time : times)
                out.println(time.toStringAMPM());
        }
        out.flush();
    }
}
