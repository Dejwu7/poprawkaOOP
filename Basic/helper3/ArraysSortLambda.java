package org.example;

import java.util.Arrays;

public class ArraysSortLambda {
    public static void main(String[] args) {
        String arrStr[] = {"Fort", "BMW", "Mercedes", "Dodge"};
        Arrays.sort(arrStr, (a,b) -> {return a.length() - b.length();});
        //od najkrótszego do najdłuższego
    }
}
