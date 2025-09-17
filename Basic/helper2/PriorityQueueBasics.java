package org.example;

import java.util.PriorityQueue;

public class PriorityQueueBasics {
    public static void main(String[] args) {
        //pozwala na stworzenie kolejki ze względu na priorytet, a
        //nie ze względu na to kiedy zostały dodane dane
        //do kolejki. Dzięki temu pierwsze mogą być konsumowane
        //dane z wyższym priorytetem
        //wywołanie metody poll pobiera wartosc z kolejki i ją usuwa
        //wywołanie metody peek zostawia element w kolejce
        PriorityQueue<Integer> test = new PriorityQueue<>();
        test.add(99);
        test.add(56);
        test.add(88);
        test.add(14);
        while(!test.isEmpty()){
            System.out.println(test.poll());
        } //wyświetla rosnąco
        test.add(100);
        test.add(45);
        test.add(74);
        System.out.println(test.peek()); //45
        System.out.println(test.peek()); //45
    }
}
