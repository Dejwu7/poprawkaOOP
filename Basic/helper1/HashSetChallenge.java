package org.example;

import java.util.HashSet;
import java.util.Iterator;

class Car2{
    String name;

    public Car2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car2{" +
                "name='" + name + '\'' +
                '}';
    }
}
public class HashSetChallenge {
    public static void main(String[] args) {
        //1. Stwórz HashSet z typem String i dodaj do niego następujące imiona:
        //Ola, Kasia, Daniel, Ola, Kasia, Adam. Na koniec wyświetl wszystkie elementy
        //w konsoli.
        //2. Dodaj klasę Car z polem name typu String oraz konstruktorem,
        //getterem, setterem i metodą toString().
        //Stwórz HashSet z typem Car i dodaj dwa razy tą samą instancję dla
        //3 nazw aut: "Dodge", "Citroen", "Opel". Wyświetl rezultat w konsoli.
        HashSet<String> set = new HashSet<>();
        set.add("Ola");
        set.add("Kasia");
        set.add("Daniel");
        set.add("Ola");
        set.add("Kasia");
        set.add("Adam");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }
        HashSet<Car2> cars = new HashSet<>();
        Car2 car1 = new Car2("Dodge");
        cars.add(car1);
        cars.add(car1);
        Car2 car2 = new Car2("Citroen");
        cars.add(car2);
        cars.add(car2);
        Car2 car3 = new Car2("Opel");
        cars.add(car3);
        cars.add(car3);
        Iterator<Car2> carsIterator = cars.iterator();
        while (carsIterator.hasNext()){
            System.out.println(carsIterator.next());
        }
    }
}
