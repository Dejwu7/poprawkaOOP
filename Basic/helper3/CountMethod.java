package org.example;

import java.util.ArrayList;

public class CountMethod {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Citroen", 180, 200000, 3));
        cars.add(new Car("Merc", 250, 400000, 5));
        cars.add(new Car("Opel", 250, 5000000, 3));
        cars.add(new Car("BMW", 240, 300000, 5));
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Dodge", 250, 300000, 4));

       int num = (int) cars.stream().filter(car -> car.topSpeed >= 200).count();
        System.out.println(num);

    }
}
