package org.example;

import java.util.ArrayList;

class Pet {
    protected String name;
    int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class Lion extends Pet{
    public Lion(String name, int age) {
        super(name, age);
    }
}
class Tiger extends Pet {
    public Tiger(String name, int age) {
        super(name, age);
    }
}
class Zoo <T extends Pet> {
    private ArrayList<Pet> animals;

    public Zoo() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal (T animal){
        this.animals.add(animal);
    }
    public void printAnimals(){
        for(Pet e : animals){
            System.out.println(e);
        }
    }
}
public class GenericTask2 {
    public static void main(String[] args) {
        Zoo<Pet> zoo = new Zoo<>();
        zoo.addAnimal(new Tiger("Tiger #1", 5));
        zoo.addAnimal(new Lion("Lion #1", 10));
        zoo.addAnimal(new Tiger("Tiger #2", 7));
        zoo.printAnimals();;
    }
}
