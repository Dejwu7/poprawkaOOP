package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Clock {
    private LocalTime localTime;
    private City city;

    //Inicjalizuję format dla czasu
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    //nie musi być tworzony na nowo dla każdej instancji clock


    public Clock(City city) {
        this.localTime = localTime;
        this.city = city;
    }

    public void setCurrentTime(){
        localTime = LocalTime.now();
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setTime(int hours, int minutes, int seconds){
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Godzina podana w złym zakresie");
        }
         if (minutes < 0 || minutes > 59) {
             throw new IllegalArgumentException("Minuty muszą być w poprawnym zakresie.");
         }
         if (seconds < 0 || seconds > 59) {
             throw new IllegalArgumentException("Sekundy muszą być w innym zakresie");
         }
        localTime = LocalTime.of(hours, minutes, seconds);
    }

    public void setCity(City newCity){
        double hourChange = newCity.getSummer_timezone() - city.getSummer_timezone();
        this.city = newCity; // metoda ta pozwoli zmienić miasto, którego czas reprezentuje zegar
        if(hourChange < 0){
            localTime = localTime.minusHours((long)Math.abs(hourChange)).minusMinutes((long)((Math.abs(hourChange) - Math.floor(Math.abs(hourChange))) * 60));
        } else {
            localTime = localTime.plusHours((long)hourChange).plusMinutes((long)((hourChange - Math.floor(hourChange)) * 60));
        }
    }

    @Override
    public String toString() {
        // Możesz zmodyfikować nazwę tak, aby była prostsza
        // Na przykład: "AnalogClock_" + godzina + "_" + minuta + "_" + sekunda
        if (this.localTime == null) {
            this.localTime = LocalTime.now();
        }
        return String.format("AnalogClock_%02d-%02d-%02d", localTime.getHour(), localTime.getMinute(), localTime.getSecond());
    }


}
