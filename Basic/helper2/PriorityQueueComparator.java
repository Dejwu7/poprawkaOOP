package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

class AscendingComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer x, Integer y) {
        if(x==y) return 0;
        if(x > y) return 1; //rosnąca
        else return -1;
    } //można zrobić analogicznie żeby było malejąco
}

public class PriorityQueueComparator {
    public static void main(String[] args) {
        //można zmienić sposób sortowania dzięki wykorzystaniu klasy Comparator
        //przesłaniamy metodę compare, która przyjmuje dwie wartości integer

        PriorityQueue<Integer> test =
                new PriorityQueue<>(new AscendingComparator());
        test.add(99);
        test.add(56);
        test.add(88);
        test.add(14);
        while (!test.isEmpty()) {
            System.out.println(test.poll());
        }
    }
}
