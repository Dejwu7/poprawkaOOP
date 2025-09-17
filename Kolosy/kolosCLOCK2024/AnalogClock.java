package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AnalogClock extends Clock{

    //napisz metodę toSvg, która przyjmie ścieżkę
    //do pliku i zapisze w nim kod svg przedstawiający
    //tarczę zegara bez wskazówek

    // Prywatna, finalna polimorficzna lista wskazówek zegara
    private final List<ClockHand> clockHands;

    public AnalogClock(City city) {
        super(city);

        // Inicjalizacja listy wskazówek z po jednej wskazówce każdej klasy
        this.clockHands = new ArrayList<>();
        this.clockHands.add(new HourHand());
        this.clockHands.add(new MinuteHand());
        this.clockHands.add(new SecondHand());
    }

    public List<ClockHand> getClockHands() {
        return clockHands;
    }

    public void setTime(LocalTime localTime) {
        // Ustaw czas dla każdej wskazówki w liście
        for (ClockHand hand : clockHands) {
            hand.setTime(localTime);
        }
    }


    public void toSvg(String path) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write("<html>\n");
            fileWriter.write("<body>\n");
            fileWriter.write("<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\">\n");
            fileWriter.write(" <circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" />\n");
            fileWriter.write("  <g text-anchor=\"middle\">\n");
            fileWriter.write("    <text x=\"0\" y=\"-80\" dy=\"6\">12</text>\n");
            fileWriter.write("    <text x=\"80\" y=\"0\" dy=\"4\">3</text>\n");
            fileWriter.write("    <text x=\"0\" y=\"80\" dy=\"6\">6</text>\n");
            fileWriter.write("    <text x=\"-80\" y=\"0\" dy=\"4\">9</text>\n");
            fileWriter.write("  </g>\n");

            // Dodanie wskazówek
            for (ClockHand clockHand : clockHands) {
                fileWriter.write(clockHand.toSvg());
            }

            fileWriter.write("</svg>\n");
            fileWriter.write("</body>\n");
            fileWriter.write("</html>");
        } catch (IOException e) {
            throw new IOException("Error writing SVG file", e);
        }
    }
}
