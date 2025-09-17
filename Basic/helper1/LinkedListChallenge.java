package org.example;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

class Animal {
    protected String name;

    public Animal(String name) {
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
}
public class LinkedListChallenge {
    public static void main(String[] args) {
        LinkedList<Animal> animals = new LinkedList<>();
        LinkedList<String> elements = new LinkedList<>();
        elements.add("Dog");
        elements.add("Cat");
        elements.add("Tiger");
        elements.add("Lion");
        elements.add("Panda");
        for(int i=0; i<20; i++){
            Random rand = new Random();
            int n = rand.nextInt(5);
            animals.add(new Animal(elements.get(n)));
        }
        animals.removeFirst();
        animals.removeLast();
        animals.addFirst(new Animal("tiger"));
        animals.addLast(new Animal("lion"));
        ListIterator<Animal> listIterator = animals.listIterator();
        while ((listIterator.hasNext())){
            Animal a = listIterator.next();
            System.out.println(a.getName());
        }
        
    }
}
