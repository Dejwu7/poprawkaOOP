package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorMethods {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Dodge");
        arr.add("Pontiak");
        arr.add("Merc");

        //Dla każdego elementu wyświetla
        arr.iterator().forEachRemaining(element -> {
            System.out.println(element);
        });
        //Natomiast jeśli byśmy chcieli usunąć dany element
        Iterator<String> iterator = arr.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(str.equalsIgnoreCase("merc")){
                iterator.remove();//metode remove możemy zastosować tylko przy użyciu metody next
            }
        }
        System.out.println(arr);
    }
}
