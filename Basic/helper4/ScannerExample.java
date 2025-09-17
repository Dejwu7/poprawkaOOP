package org.example;

import java.util.Scanner;

public class ScannerExample {
    //odczyt danych wpisanych przez użytkownika
    //klasa scanner zawiera wiele metod do odczytu danych np:
    //nextInt(), nextFloat(), nextDouble(), nextByte(), nextLong() etc
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in /*terminal*/);

        //chcemy dodać liczby i to użytkownik zdefiniuje, ile liczb dodamy

        System.out.println("Wprowadź ilość liczb do dodania: ");
        int numNumbers = scanner.nextInt();

        int sum=0;
        for(int i=0; i< numNumbers; i++){
            System.out.println("Wprowadź kolejną liczbę całkowitą do dodania:");
            sum += scanner.nextInt();
        }
        System.out.println("Suma wszystkich liczb to: " + sum);
    }
}
