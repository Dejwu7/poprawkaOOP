import org.example.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParamsTestLessThan {
    @Test
    public void analyzeCarByParams_carMillageLessThan0_resultFalse(){
        //zaczynamy od konstrukcji elementów wejsciowych
        //given
        Integer threadThickness = 3;
        Integer fuelUsage = 5;
        Integer carMillage = -1;
        Car car = new Car();

        //when - tu wykonujemy właściwą logikę podlegającą testowaniu

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> car.analyzeCarByParams(threadThickness, fuelUsage, carMillage));
    }
}
