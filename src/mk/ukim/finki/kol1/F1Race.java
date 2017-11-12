package mk.ukim.finki.kol1;

import java.io.*;
import java.util.ArrayList;

class F1Race {
    private ArrayList<F1Driver> drivers;

    public F1Race() {
        drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bf.readLine()) != null && line.length() != 0) {
                String[] parts = line.split(" ");
                String name = parts[0];
                ArrayList<String> laps = new ArrayList<>();
                for (int i = 1; i < parts.length; ++i)
                    laps.add(parts[i]);
                drivers.add(new F1Driver(name, laps));
            }
        }
    }

    private int parseMilliseconds(String lap) {
        String[] parts = lap.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60000 + seconds * 1000 + Integer.parseInt(parts[2]);
    }

    private void sortDrivers(ArrayList<F1Driver> drivers) {
        for (int i = 0; i < drivers.size() - 1; ++i) {
            for (int j = 0; j < drivers.size() - i - 1; ++j) {
                if (parseMilliseconds(drivers.get(j).getLaps().get(0)) > parseMilliseconds(drivers.get(j + 1).getLaps().get(0))) {
                    F1Driver temp = drivers.get(j);
                    drivers.set(j, drivers.get(j + 1));
                    drivers.set(j + 1, temp);
                }
            }
        }
    }

    public void printSorted(OutputStream outputStream) {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream))) {
            ArrayList<F1Driver> bestDrivers = new ArrayList<>();
            for (F1Driver driver : drivers) {
                int minMilliseconds = Integer.MAX_VALUE;
                int indexBestLap = -1;
                ArrayList<String> bestLapPerDriver = new ArrayList<>();
                for (int j = 0; j < driver.getLaps().size(); ++j) {
                    int milliseconds = parseMilliseconds(driver.getLaps().get(j));
                    if (minMilliseconds > milliseconds) {
                        minMilliseconds = milliseconds;
                        indexBestLap = j;
                    }
                }
                bestLapPerDriver.add(driver.getLaps().get(indexBestLap));
                bestDrivers.add(new F1Driver(driver.getName(), bestLapPerDriver));
            }
            sortDrivers(bestDrivers);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bestDrivers.size(); ++i) {
                if (i != bestDrivers.size() - 1)
                    sb.append(String.format("%d. %-9s %10s\n", i + 1, bestDrivers.get(i).getName(), bestDrivers.get(i).getLaps().get(0)));
                else
                    sb.append(String.format("%d. %-9s %10s", i + 1, bestDrivers.get(i).getName(), bestDrivers.get(i).getLaps().get(0)));
            }
            out.println(sb.toString());
        }
    }
}
