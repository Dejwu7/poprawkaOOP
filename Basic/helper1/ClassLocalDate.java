package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ClassLocalDate {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        localDate = localDate.plusYears(10);
        localDate = localDate.minusMonths(3);
        localDate = localDate.plusDays(9);


        //jest jeszcze klasa LocalDateTime którą możemy formatować za
        //pomocą DateTimeFormatter!

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = dateTimeFormatter.format(localDateTime);
        System.out.println(str);

        //mierzenie czasu wykonywania programu
        //do tego przydaje sie System.currentTimeMillis()
        //musimy ją wywołać po jakims kodzie, który będzie wykonywany jakąś
        //jednostkę czasu. wtedy musimy odjąć te dwie wartości
        long start = System.currentTimeMillis();
        try {
            for(int a=10; a > 0; a--){
                System.out.println(new Date().getTime());
                Thread.sleep(5);
            }
        } catch (InterruptedException exeption){
            exeption.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long timeElapsed = end - start;
        System.out.println("Took time: " + timeElapsed);

        //porównywanie dat
        LocalDate date1 = LocalDate.of(2022, 12, 12);
        LocalDate date2 = LocalDate.of(2021, 11, 11);

        if(date1.isAfter(date2)){
            System.out.println("2022 > 2021");
        }
        if(date2.isBefore(date1)){
            System.out.println("2021 < 2022");
        }
        if(date2.isEqual(date1)){
            System.out.println("date2 = date1");
        }

    }
}
