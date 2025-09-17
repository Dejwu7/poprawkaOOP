import org.example.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarServiceTest {

    @Test
    //przypadek, kiedy jako parametry przekazujemy przebieg pusty
    //nazwa testowanej metody_co testujemy_oczekiwany rezultat
    public void analyzeCarByParams_millageNull_throwsIllegalArgumentException(){

        //zaczynamy od konstrukcji elementów wejsciowych
        //given
        Integer threadThickness = 3;
        Integer fuelUsage = 5;
        Integer carMillage = null;
        Car car = new Car();

        //when - tu wykonujemy właściwą logikę podlegającą testowaniu

    //then
    //jeżeli carMillage bedzie null musimy otrzymać illegala
        Assertions.assertThrows(IllegalArgumentException.class, () -> car.analyzeCarByParams(threadThickness, fuelUsage, carMillage));
    }
    //sprawdzenie wyjątku
    // czy wywołanie x zwróci illegalargument
}

