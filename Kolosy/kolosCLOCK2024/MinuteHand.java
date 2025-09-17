package org.example;

import java.time.LocalTime;

public class MinuteHand extends ClockHand {
    private float angle;

    @Override
    public void setTime(LocalTime localTime) {
        this.angle = localTime.getMinute() * 6;  // 6 stopni na minutę
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-70\" " +
                "stroke=\"black\" stroke-width=\"2\" transform=\"rotate(%.2f)\" />", angle);
    }
}
