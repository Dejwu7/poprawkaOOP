package org.example;

import java.time.LocalTime;

public class SecondHand extends ClockHand {
    private float angle;

    @Override
    public void setTime(LocalTime localTime) {
        this.angle = localTime.getSecond() * 6;  // 6 stopni na sekundę
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-80\" " +
                "stroke=\"red\" stroke-width=\"1\" transform=\"rotate(%.2f)\" />", angle);
    }
}
