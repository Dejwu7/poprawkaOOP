package org.example;

import java.util.HashMap;
import java.util.Map;

class Animal {
    String name;

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
public class HashMapChallenge {
    public static void main(String[] args) {
        HashMap<String, Animal> map = new HashMap<>();
        String names[] = {"Kot", "Pies", "Koza", "Puma"};
        for(int i=0; i<names.length; i++){
            map.put(names[i], new Animal(names[i].toString()));
        }
        //Wyświetl w konsoli wynik z map dla klucza "Puma"
        Animal a = map.get("Puma");
        System.out.println(a);
        //Dodaj pętlę for each która przechodzi po map i pobiera
        //klucze oraz wartości a następnie wyświetla je w konsoli
        System.out.println("\n");
        for(Map.Entry<String, Animal> entry : map.entrySet()){
            String key = entry.getKey();
            Animal animal = entry.getValue();
            System.out.println(key + " - " + animal);
        }
    }
}
