package org.example;

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("bmw");
        arr.add("merc");
        arr.add(null);
        arr.add("pontiac");
        arr.add(null);
        //chcemy wyszczyścić kolekcję z null i merc
        arr.removeIf(a -> a == null || a.equalsIgnoreCase("merc"));
        System.out.println(arr);
    }
}
