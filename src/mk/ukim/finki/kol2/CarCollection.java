package mk.ukim.finki.kol2;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class CarCollection {
    private List<Car> cars;
    private static Comparator<Car> comparator =
            Comparator.comparing(Car::getPrice).thenComparing(Car::getPower);

    public CarCollection() {
        cars = new LinkedList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void sortByPrice(boolean ascending) {
        if (ascending)
            Collections.sort(cars, comparator);
        else
            Collections.sort(cars, comparator.reversed());  // descending ordering of the (cars) elements
    }

    public List<Car> filterByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(car -> car.getManufacturer().toLowerCase().compareTo(manufacturer.toLowerCase()) == 0)
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    public List<Car> getList() {
        return cars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        cars.stream()
                .forEach(car -> {
                    sb.append(car);
                    sb.append("\n");
                });
        return sb.toString();
    }
}
