package org.example;

import java.util.Optional;

public class OptionalCreateOfNullableEmpty {
    public static void main(String[] args) {
        //Optional.of() tworzy Optional, który nie może być null
        Optional<String> opt1 = Optional.of("Test");
        System.out.println(opt1.get());

        Optional<String> opt3 = Optional.ofNullable("optional 3");
        System.out.println(opt3);

        Optional<String> opt4 = Optional.empty();
        System.out.println(opt4);

        Integer integer = Integer.valueOf(10);
        Optional<Integer> optInt = Optional.ofNullable(integer);
        if(optInt.isPresent()){
            System.out.println(optInt.get());
        } else  {
            System.out.println("optInt jest null");
        }

        Optional<Integer> opt = Optional.ofNullable(Integer.valueOf(20));
        Optional<Integer> opt2 = opt.map(i -> i*3);
        System.out.println(opt2.orElse(Integer.valueOf(5)));
    }
}
