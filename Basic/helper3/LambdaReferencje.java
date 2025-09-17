package org.example;
//W java można pobrać referencję do metody za pomocą
//operatora podwójnego dwukropka i przypisać ją do zmiennej w celu
//późniejszego wywołania. Zmienna z metodą najczęściej będzie przypisywana
//do interfejsu funkcyjnego z java.util.functon.

import java.util.Objects;
import java.util.function.IntSupplier;

public class LambdaReferencje {
    public void test(IntSupplier intSupplier){
        System.out.println(intSupplier.getAsInt());
    }
    public static void main(String[] args) {
        Object obj = new Object();
        IntSupplier intSupplier = obj::hashCode; //referencja do metody hashCode, zwróci wartośc typu int
        System.out.println(intSupplier.getAsInt());
        LambdaReferencje lambdaReferencje = new LambdaReferencje();
        lambdaReferencje.test(intSupplier);
    }
}
