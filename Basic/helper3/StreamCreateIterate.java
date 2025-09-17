package org.example;

import java.util.stream.Stream;

public class StreamCreateIterate {
    public static void main(String[] args) {
        Stream.iterate(1, n -> n + 1).limit(10)
                .forEach(s -> System.out.println(s));
        Stream.iterate(0, n -> n < 5, n -> n + 1);
    }
}
