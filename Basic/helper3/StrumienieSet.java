package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StrumienieSet {
    //konwersja streamu do kolekcji Set, gdzie mogą być tylko unikalne elementy
    public static void main(String[] args) {
        //np jeśli mamy listę liczb całkowitych, dodatnich i ujemnych,
        //jeżeli ją przefiltrujemy za pomocą metody filter
        //np aby liczby były >=0 albo >30 otrzymamy nowy zbiór
        //który możemy skonwertować za pomocą Collectors toSet
        //do kolekcji set, w której będą tylko unikalne elementy
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Citroen", 180, 200000, 3));
        cars.add(new Car("Merc", 250, 400000, 5));
        cars.add(new Car("Opel", 250, 5000000, 3));
        cars.add(new Car("BMW", 240, 300000, 5));
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Dodge", 250, 300000, 4));
        Set<Car> set = cars.stream().filter(car -> car.price < 400000)
                .collect(Collectors.toSet());
        set.forEach(car -> System.out.println(car));
        //unikalne obiekty będą tylko wtedy, jak w klasie Car zaimplementujemy
        //Comparator
        //metoda collect() pozwala również na zsumowanie wartości
        //Collectors.summingInt()
        int totalPrice = cars.stream().collect(Collectors.summingInt(car -> car.price));
        System.out.println(totalPrice);

        Map<String, Car> carsMap = cars.stream()
                .collect(Collectors.toMap(car -> car.name, car -> car));
        System.out.println(carsMap);

    }
}
