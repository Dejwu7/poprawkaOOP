package org.example;

import java.util.ArrayList;

public class CountReduce {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Citroen", 180, 200000, 3));
        cars.add(new Car("Merc", 250, 400000, 5));
        cars.add(new Car("Opel", 250, 5000000, 3));
        cars.add(new Car("BMW", 240, 300000, 5));
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Dodge", 250, 300000, 4));

        int totalPrice = cars.stream().reduce(0,
                (subtotal, car) -> subtotal + car.getPrice(), Integer::sum);
    }
}
//metoda reduce() zredukuje zbiór do jednej wartości
//reduce oczekuje wartości początkowej np 0
//Wyrażenie lambda przyjmuje dwa argumenty:
//-subtotal, suma z poprzednich iteracji
//-element, aktualny element z iteracji
//int result = arr.stream()
// .reduce(0, (subtotal, element) -> subtotal + element);
//do reduce można przekazać referencję do metody np Integer::sum);