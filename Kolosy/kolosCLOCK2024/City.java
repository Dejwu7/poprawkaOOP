package org.example;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class City {

    private static final String REGEX = ",";
    private String capital;
    private double summer_timezone;
    private double latitude;
    private double longitude;
    private LocalTime localTime;
    private LocalTime localMeanTime;

    public City(String capital, double summer_timezone, double latitude, double longitude) {
        this.capital = capital;
        this.summer_timezone = summer_timezone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCapital() {
        return capital;
    }

    public double getSummer_timezone() {
        return summer_timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public LocalTime getLocalMeanTime() {
        return localMeanTime;
    }

    // Metoda do konwersji współrzędnych geograficznych z formatu tekstowego na double
    private double parseCoordinate(String coordinate) {
        coordinate = coordinate.trim();
        char direction = coordinate.charAt(coordinate.length() - 1);
        double value = Double.parseDouble(coordinate.substring(0, coordinate.length() - 2).trim());

        // Ustal kierunek (N i E to dodatnie, S i W to ujemne)
        if (direction == 'S' || direction == 'W') {
            value = -value;
        }
        return value;
    }

    // Metoda do parsowania linii tekstowej z pliku CSV na obiekt City
    private City parseLine(String line) {
        String[] tempArr = line.split(REGEX);
        String capital = tempArr.length > 0 ? tempArr[0].trim() : "";
        double summer_timezone = tempArr.length > 1 ? Double.parseDouble(tempArr[1].trim()) : 0;
        double latitude = tempArr.length > 2 ? parseCoordinate(tempArr[2].trim()) : 0;
        double longitude = tempArr.length > 3 ? parseCoordinate(tempArr[3].trim()) : 0;

        return new City(capital, summer_timezone, latitude, longitude);
    }

    // Metoda do wczytywania danych z pliku CSV i tworzenia mapy miast
    public Map<String, City> parseFile(String path) throws IOException {
        HashMap<String, City> map = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine(); // Pomijanie nagłówka
            while ((line = reader.readLine()) != null) {
                City city = this.parseLine(line);
                if (city != null) {
                    map.put(city.getCapital(), city);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();
        }
        return map;
    }

    // Metoda obliczająca czas miejscowy (lokalny czas słoneczny)
    public LocalTime localMeanTime(LocalTime localTime) {
        this.localTime = localTime;
        double centralMeridian = this.summer_timezone * 15.0;
        double longitudeDiff = this.longitude - centralMeridian;
        int timeDifferenceMinutes = (int) (longitudeDiff * 4);
        this.localMeanTime = localTime.plusMinutes(timeDifferenceMinutes);

        return localMeanTime;
    }

    //komparator dwóch miast. Wynik sortowania ustawia na początku kolekcji
    //miasta, których różnica między czasem miejscowym
    //a czasem wynikającym ze strefy czasowej jest największa

    public LocalTime setGetDifferenceTime() {
        int diffHours = Math.abs(getLocalMeanTime().getHour() - getLocalTime().getHour());
        int diffMinutes = Math.abs(getLocalMeanTime().getMinute() - getLocalTime().getMinute());
        int diffSeconds = Math.abs(getLocalMeanTime().getSecond() - getLocalTime().getSecond());
        return LocalTime.of(diffHours, diffMinutes, diffSeconds);
    }


    public static List<City> worstTimeZoneFit(List<City> cities){
        Comparator<City> comp1 = (c1,c2) -> {
            LocalTime diff1 = c1.setGetDifferenceTime();
            LocalTime diff2 = c2.setGetDifferenceTime();
            if (diff1.isBefore(diff2)) return 1;
            if(diff1.isAfter(diff2)) return -1;
            else return 0;
        };
        cities.sort(comp1);
        return cities;
    }

    //napisz publiczną metodę statyczną generateAnalogClockSvg, która
    //przyjmie listę obiektów City oraz obiekt AnalogClock. Metoda powinna
    //założyć katalog o nazwie będącej wynikiem funkcji toString obiektu
    //zegara. W katalogu, dla każdego miasta z listy, należy utworzyć plik Svg
    //o nazwie odpowiadającej nazwie miasta. Pliki powinny zawierać
    //wynik działania metody toSvg zegara w kolejnych miastach.

    public static void generateAnalogClock(List<City> cities, AnalogClock analogClock){
        // Upewnienie się, że czas jest ustawiony przed wywołaniem `toString`
        analogClock.setTime(LocalTime.now());

        // Tworzenie katalogu na podstawie nazwy zegara
        String directoryName = analogClock.toString();
        File directory = new File("C:\\Users\\maria\\Desktop\\git\\kolokwium1\\src\\main\\resources\\" + directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        for (City city : cities) {
            // Ustawienie czasu na zegarze dla danego miasta
            LocalTime cityTime = city.localMeanTime(LocalTime.now());
            analogClock.setTime(cityTime);

            // Nazwa pliku SVG odpowiadająca nazwie miasta
            String fileName = city.getCapital() + ".svg";
            File svgFile = new File(directory, fileName);

            try (FileWriter fw = new FileWriter(svgFile)) {
                // Generowanie i zapisanie SVG do pliku
                analogClock.toSvg(svgFile.getPath());
                System.out.println("File " + fileName + " created for city " + city.getCapital());
            } catch (IOException e) {
                System.err.println("Failed to create file for city " + city.getCapital() + ": " + e.getMessage());
            }
        }
    }



}
