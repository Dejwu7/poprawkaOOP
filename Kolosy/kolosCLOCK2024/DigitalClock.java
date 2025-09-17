package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DigitalClock extends Clock {
    public enum TimeSet {
        TWENTY_FOUR,
        TWELVE
    }

    private TimeSet timeSet;
    private boolean isAM;

    public DigitalClock(TimeSet timeSet, City city) {
        super(city); //przekazujemy miasto do klasy Clock
        this.timeSet = timeSet;
        this.isAM = true;
    }

    // Dodanie metody pozwalającej ustawić AM lub PM
    public void setAM(boolean isAM) {
        this.isAM = isAM;
    }

    public TimeSet getTimeSet() {
        return timeSet;
    }

    @Override
    public void setTime(int hours, int minutes, int seconds) {
        if (timeSet == TimeSet.TWELVE) {
            // Jeśli ustawiamy 12-godzinny format, dostosowujemy godzinę do AM/PM
            if (isAM && hours == 12) {
                hours = 0;  // 12 AM jest reprezentowane jako 00 w LocalTime
            } else if (!isAM && hours < 12) {
                hours += 12;  // Dodaj 12 godzin, aby uzyskać popołudniowy czas (PM)
            }
        }

        super.setTime(hours, minutes, seconds);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter;

        if(timeSet == TimeSet.TWENTY_FOUR) {
            formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        }
        else {
            formatter = DateTimeFormatter.ofPattern("h:mm:ss  a");
        }

        return getLocalTime().format(formatter);
    }
}

