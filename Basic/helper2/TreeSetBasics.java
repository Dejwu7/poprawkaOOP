package org.example;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetBasics {
    public static void main(String[] args) {
        //jest podobny do hashSet ale ma wobec niego ważną zaletę, zbiór
        //ten jest uporządkowany, gdyż elementy są przechowywane w strukturze drzewiastej.
        //Każdy element umieszczany jest zgodnie z kolejnością, inaczej mówiąc
        //są posortowane rosnąco
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(56);
        treeSet.add(4);
        treeSet.add(23);
        treeSet.add(1);

        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //iteracja od największej do najmniejszej
        Iterator<Integer> descendingIterator = treeSet.descendingIterator();
        while (descendingIterator.hasNext()){
            System.out.println(descendingIterator.next());
        }

    }
}