package org.example;

import java.time.LocalTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ClassLocalTime {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        System.out.println("Godzina: " + localTime.getHour());
        System.out.println("Minuty: " + localTime.getMinute());
        System.out.println("Sekundy: " + localTime.getSecond());

        localTime = localTime.plusHours(2);
        localTime = localTime.minusMinutes(10);
        localTime = localTime.plusSeconds(35);
    }
}