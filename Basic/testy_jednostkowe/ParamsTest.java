import org.example.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParamsTest {
    @Test
    public void analyzeCarByParams_allValuesOk_resultTrue(){
        //zaczynamy od konstrukcji elementów wejsciowych
        //given
        Integer threadThickness = 3;
        Integer fuelUsage = 5;
        Integer carMillage = 15000;
        Car car = new Car();

        //when - tu wykonujemy właściwą logikę podlegającą testowaniu
        boolean result = car.analyzeCarByParams(threadThickness, fuelUsage, carMillage);

        //then
        Assertions.assertEquals(result, true);
        //Dla boolean można też assertTrue(result);
    }
}
//Test dla przypadku, gdy samochód ma wszystkie przypadki poprawne
