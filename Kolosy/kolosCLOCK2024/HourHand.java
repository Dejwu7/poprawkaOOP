package org.example;

import java.time.LocalTime;

public class HourHand extends ClockHand {
    private float angle;

    @Override
    public void setTime(LocalTime localTime) {
        int hours = localTime.getHour() % 12;  // Wartości w zakresie 0-11
        int minutes = localTime.getMinute();
        this.angle = (hours * 30) + (minutes * 0.5f);  // 30 stopni na godzinę, 0.5 stopnia na minutę
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-50\" " +
                "stroke=\"black\" stroke-width=\"4\" transform=\"rotate(%.2f)\" />", angle);
    }
}
