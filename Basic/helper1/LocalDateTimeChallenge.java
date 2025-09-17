package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeChallenge {
    public static void main(String[] args) {
        /* 1. Stwórz instancję LocalDateTime i wywołaj now() aby otrzymać aktualną
        datę i czas.
         2. Zrób kolejną instancję i wywołaj of() aby przekazać dowolną
         datę i czas z przyszłości
         3. Wywołaj isAfter na drugiej instancji względem aktualnego czasu i jeśli zwróci
         true pokaż tekst w konsoli opisujący ze data jest z przyszłości*/
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime future = LocalDateTime.of(2029, 9, 3, 7, 27, 37);
        if(future.isAfter(localDateTime)){
            System.out.println("data jest git");
        }





    }
}
