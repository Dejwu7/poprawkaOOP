package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

class Animal2 {
    protected String name;

    public Animal2(String name) {
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
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal2 animal2 = (Animal2) obj;
        return this.name.equals(animal2.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    //po dopisaniu hashCode i equals pojawi sie tylko jeden tiger
}
public class HashSetBasics {
    //opiera się na obliczeniu unikalnego hasha (skrótu) z dodanego obiektu
    //i na tej podstawie określana jest jego kolejność w zbiorze. Atutem HashSet
    //jest fakt, że nie pozwala na ponowne dodanie takiego samego elementu do zbioru, wszystkie są unikalne
    //Sprawdzenie czy dany element jest unikalny opiera się na mentodzie equals(), która tylko
    //sprawdza referencję do obiektu, więc dwa obiekty z takimi samymi wartościami będą
    //różnymi elementami w HashSet, co nie jest odpowiednie.
    //Aby prawidłowo określić czy obiekty są takie same trzeba zaimplementować equals() i hashCode().
    //Jeżeli dwa obiekty są takie same według equals to hashCode musi dla nich
    //dać takie same wartości.

    public static void main(String[] args) {
        HashSet<Animal2> set = new HashSet<>();
        Animal2 a = new Animal2("Tiger");
        set.add(a);
        set.add(a); //hashset nam na to nie pozwoli
        set.add(new Animal2("Lion"));
        set.add(new Animal2("Dog"));
        set.add(new Animal2("Tiger")); //zostanie dodany, bo jest to osobny obiekt
        //chociaż ma taką samą nazwę
        Iterator<Animal2> iterator = set.iterator();
        while(iterator.hasNext()){
            Animal2 animal2 = iterator.next();
            System.out.println(animal2);
        }
        //nie chcemy, żeby nawet obiekty o tej samej nazwie zostały dodane
        //więc w klasie Animal2 musimy zaimplementować metodę
    }

}
