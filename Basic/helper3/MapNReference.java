package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapNReference {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Citroen", 180, 200000, 3));
        cars.add(new Car("Merc", 250, 400000, 5));
        cars.add(new Car("Opel", 250, 5000000, 3));
        cars.add(new Car("BMW", 240, 300000, 5));
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Dodge", 250, 300000, 4));
        List<Integer> carPrices = cars.stream()
                .filter(car -> car.price < 300000)
                .map(Car::getPrice).collect(Collectors.toList());
        System.out.println(carPrices);

        Car highestPrice = cars.stream()
                .max((car1, car2) -> car1.price > car2.price ? /*jeśli warunek jest spełniony*/ 1 : -1)
                .get();
        System.out.println(highestPrice);

        Car lowestPrice = cars.stream()
                .min((car1, car2) -> car1.price > car2.price ? 1 : -1)
                .get();
        System.out.println(lowestPrice);
    }
}
