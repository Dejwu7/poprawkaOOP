package org.example;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListBasics {
    //arraylist jest bardzo dobra jeżeli chcemy mieć losowy dostęp
    //do elementów. Np jeśli bardzo często używamy metody get
    //LinkedList jest wolniejszy jeśli chodzi o losowy dostęp
    //ale bardzo szybki jeżeli chodzi o dodawanie losowe roznych elementow
    //ich zamiane itd
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("Adam");
        String p1 = "Ola";
        list.add(p1);
        list.addFirst("Rafał");
        list.addLast("Olek");
        System.out.println(list);
        System.out.println(list.getFirst()); //Rafał
        list.removeLast();
        list.removeFirst();
        System.out.println(list); //Adam, Ola
        list.add("Daniel");
        list.add("Kuba");
        list.add(p1);
        list.add("Ania");
        list.removeFirstOccurrence(p1);
        list.add(2, "Zenon");
        list.set(0, "Joanna"); //on skasuje ten co był pod 0
        ListIterator<String> iterator1 = list.listIterator();
        while ((iterator1.hasNext())){
            String str = iterator1.next();
            System.out.println(str);
        }
        //ListIterator może iterować też od końca
        ListIterator<String> iterator2 = list.listIterator(list.size());
        while(iterator1.hasPrevious()){
            String str = iterator2.previous();
            System.out.println(str);
        }
        //LinkedList dodawanie usuwanie aktualizacja elementu w trakcie iteracji
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
            if(str.equalsIgnoreCase("Ola")){
                iterator.set("Zuzia");
            }
            if(str.equalsIgnoreCase("Adam")){
                iterator.add("Karol");
            }
        }

    }
}
//linkedlist jest najlepsza do iteracji oraz do dodawania elementów na
//początku i na końcu
