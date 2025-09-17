package org.example;

import java.util.ArrayList;
import java.util.Iterator;

class Car{
    private String name, manufacturer;
    private int year;

    public Car(String name, String manufacturer, int year) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                '}';
    }
}
public class ArrayListIteration {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Charger", "Dodge", 2020));
        cars.add(new Car("T", "Ford", 1920));
        cars.add(new Car("Viper", "Dodge", 1997));

        //mamy różne możliwości, jak iterować po tych elementach
        for(int i=0; i< cars.size(); i++){
            Car car = cars.get(i);
            System.out.println(car);
        }

        System.out.println("Pętla for each: ");
        for(Car car : cars){
            System.out.println(car);
        }
        System.out.println("Iterator: ");
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()){
            Car car = iterator.next();
            System.out.println(car);
        }
    }
}
