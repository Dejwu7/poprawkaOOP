package org.example;

import java.util.Optional;

public class OptionalFilterOr {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable("tekst");
        opt.map(str -> str + " oraz informacje")
                .filter(str -> str.length() > 5)
                .or(() -> Optional.ofNullable("Nowy string"))
                .ifPresent(System.out::println);

        Integer integer = Integer.valueOf(10);
        Optional<Integer> optInt = Optional.ofNullable(integer);

        Integer result = optInt.filter(i -> i >0)
                .filter(i -> i<100)
                .map(i -> i *2)
                .filter(i -> i > 1000)
                .or(()->Optional.ofNullable(Integer.valueOf(1200)))
                .orElse(Integer.valueOf(2000));
        System.out.println(result);
    }
}
//metoda or podstawia domyślną wartość jeśli w którymś
//momencie pojawi się pusty Optional
//dzięki czemu będzie dalej kontynuowana praca