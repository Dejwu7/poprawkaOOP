package org.example;

import java.util.ArrayList;

class Person {
    protected String name;

    public Person(String name) {
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
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
public class ArrayListMethods {
    public static void main(String[] args) {
        Person p1 = new Person("Adam");
        Person p2 = new Person("Kasia");
        Person p3 = new Person("Rafał");
        Person p4 = new Person("Anna");

        ArrayList<Person> arr1 = new ArrayList<>();
        arr1.add(p1);
        arr1.add(p2);
        arr1.add(p3);
        arr1.add(p4);

        Person p5 = new Person("Bartek");
        Person p6 = new Person("Marek");
        ArrayList<Person> arr2 = new ArrayList<>();
        arr2.add(p5);
        arr2.add(p6);

        arr1.addAll(arr2);

        if(arr1.contains(p4)){
            System.out.println("Arr1 posiada p4.");
        }
        if(arr1.containsAll(arr2)){
            System.out.println("Posiada wszystkie elementy arr2");
        }
        Person p7 = new Person("Daniel");
        arr2.add(p7);
        if(arr1.containsAll(arr2)){
            System.out.println("Arr1 posiada elementy arr2");
        }
        else {
            System.out.println("arr1 nie posiada wszystkich elementów arr2");
        }
        arr2.remove(p7);
        Person people[] = new Person[arr1.size()];
        people = arr1.toArray(people);
        System.out.println(people.length);
        arr1.clear();
        if(arr1.isEmpty()){
            System.out.println("Pusto jak w tw glowie");
        }
    }
}
