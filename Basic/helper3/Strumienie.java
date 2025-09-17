package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Car implements Comparator<Car> {
    public String name;
    public int topSpeed;
    public int price;
    public int rating; // 1 - 5

    public Car(String name, int topSpeed, int price, int rating) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public int compare(Car o1, Car o2) {
        return o1.name.compareTo(o2.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", topSpeed=" + topSpeed +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}
//Strumienie pozwalają na łatwą iterację oraz manipulowanie elementami
//np.Kolekcji. Aby stworzyć strumień trzeba użyć metody stream() dostępnej w
//interfejsie Collection. Stream to interfejs generyczny mający
//typ danych ze strumienia.
public class Strumienie {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Asia");
        arr.add("Daniel");
        arr.add("Ola");
        arr.add("Zuza");
        //chcemy mieć wszystkie elementy, których liczba znaków jest
        //większa niż 3 ale mniejsza niż 5
        arr.stream().filter(str -> str.length() > 3 && str.length() < 5)
        .filter(str -> str.startsWith("A") || str.startsWith("P"))
                .forEach(str -> System.out.println(str));

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Dodge", 250, 300000, 4));
        cars.add(new Car("Citroen", 180, 200000, 3));
        cars.add(new Car("Merc", 250, 400000, 5));
        cars.add(new Car("Opel", 250, 5000000, 3));
        cars.add(new Car("BMW", 240, 300000, 5));

        cars.stream().filter(car -> car.rating > 3)
                .filter(car -> car.price > 200000 && car.price < 6000000)
                .forEach(car -> System.out.println(car));
        //Strumienie - metoda collect() do zebrania wyników filtrowania do
        //List i ArrayList. Dzięki temu przefilturejmy jedną kolekcję i uzyskamy
        //na wyjściu drugą

        List<Car> list = cars.stream().filter(car -> car.price < 400000)
                .collect(Collectors.toList());
        ArrayList<Car> carArrayList = new ArrayList<>(list);
        carArrayList.forEach(car -> System.out.println(car));
    }
}
