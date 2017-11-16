package mk.ukim.finki.kol1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TimeTable {
    private List<Time> times;

    public TimeTable() {
        times = new ArrayList<>();
    }

    private void processParts(String[] parts) throws UnsupportedFormatException, InvalidTimeException {
        for (String part : parts) {
            String[] newParts = part.split(":");
            if (newParts.length == 1) {
                newParts = part.split("\\.");
                if (newParts.length == 1) throw new UnsupportedFormatException(part);
            }
            times.add(new Time(Integer.parseInt(newParts[0]), Integer.parseInt(newParts[1])));
        }
    }

    public void readTimes(InputStream inputStream) throws IOException, UnsupportedFormatException, InvalidTimeException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null && line.length() != 0) {
                String[] parts = line.split("\\s+");
                processParts(parts);
            }
        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        times.sort(Time::compareTo);
        for (Time time : times)
            writer.println(time.toString(format));
        writer.flush();
    }
}
