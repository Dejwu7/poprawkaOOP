package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsLimit {
    //metoda limit() ogranicza ilość wyników
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Citroen", 180, 200000, 3));
        cars.add(new Car("Merc", 250, 400000, 5));
        cars.add(new Car("Opel", 250, 5000000, 3));
        cars.add(new Car("BMW", 240, 300000, 5));
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Dodge", 250, 300000, 4));

        List<Car> car3elements = cars.stream().filter(car -> car.price < 500000)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(car3elements);
    }
}
