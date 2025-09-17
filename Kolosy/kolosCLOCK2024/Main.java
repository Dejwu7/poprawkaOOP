package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Zadanie 2
        City warsaw = new City("Warszawa", 2, 52.2297, 21.0122);
        DigitalClock digitalClock = new DigitalClock(DigitalClock.TimeSet.TWELVE, warsaw);

        // Ustawienie na 12:00 PM
        digitalClock.setAM(false);
        digitalClock.setTime(12, 0, 0);
        System.out.println(digitalClock.toString()); // Powinno zwrócić 12:00:00 PM

        // Ustawienie na 12:00 AM
        digitalClock.setAM(true);
        digitalClock.setTime(12, 0, 0);
        System.out.println(digitalClock.toString()); // Powinno zwrócić 12:00:00 AM

        // Zadanie 3
        City city = new City(null, 0, 0, 0);
        try {
            System.out.println(city.parseFile("C:\\Users\\maria\\Desktop\\git\\kolokwium1\\src\\main\\resources\\strefy.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Zmiana miasta na Kijów
        City kyiv = new City("Kijów", 3, 50.4501, 30.5234);
        digitalClock.setCity(kyiv);
        System.out.println(digitalClock.toString());

        // Ścieżka do pliku CSV z danymi miast
        String csvFilePath = "C:\\Users\\maria\\Desktop\\git\\kolokwium1\\src\\main\\resources\\strefy.csv";

        // Tworzenie obiektu City, aby wczytać dane z pliku
        City cityLoader = new City(null, 0, 0, 0);

        try {
            // Wczytanie danych z pliku CSV do mapy miast
            Map<String, City> citiesMap = cityLoader.parseFile(csvFilePath);

            // Przekształcenie mapy na listę miast
            List<City> cities = new ArrayList<>(citiesMap.values());

            // Tworzenie obiektu AnalogClock
            City initialCity = new City("Warszawa", 2, 52.2297, 21.0122);
            AnalogClock analogClock = new AnalogClock(initialCity);

            // Wywołanie metody generateAnalogClock w celu wygenerowania plików SVG
            City.generateAnalogClock(cities, analogClock);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
