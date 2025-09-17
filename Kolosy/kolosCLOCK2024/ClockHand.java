package org.example;

import java.time.LocalTime;

public abstract class ClockHand {
    //Napisz klasę abstrakcyjną ClockHand reprezentującą wskazówkę zegrata tarczowego
    //Klasa powinna posiadać publiczne, abstrakcyjne metody
    //setTime która przyjmuje obiekt localTime
    //toSvg która zwraca napis zawierający znacznik svg wskazówki
    public abstract void setTime (LocalTime localTime);
    //jest abstrakcyjna to będzie nadpisywana
    public abstract String toSvg();

}
