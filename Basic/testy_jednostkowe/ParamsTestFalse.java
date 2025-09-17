import org.example.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParamsTestFalse {
    @Test
    public void analyzeCarByParams_carMillageNotOk_resultFalse(){
        //zaczynamy od konstrukcji elementów wejsciowych
        //given
        Integer threadThickness = 3;
        Integer fuelUsage = 5;
        Integer carMillage = 210000; //poza zakres
        Car car = new Car();

        //when - tu wykonujemy właściwą logikę podlegającą testowaniu
        boolean result = car.analyzeCarByParams(threadThickness, fuelUsage, carMillage);

        //then
        Assertions.assertFalse(result);
    }
}
