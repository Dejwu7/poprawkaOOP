import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class ClientSendDataTest {

    @Parameterized.Parameter(0)
    public String testData;

    @Parameterized.Parameter(1)
    public String expectedImagePath;

    // Przygotowanie danych z CSV jako parametrów do testu
    @Parameterized.Parameters(name = "{index}: Test with data={0}, expectedImage={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1,2,3,4,5", "src/test/resources/expectedCharts/expectedChart1.png"},
                {"2,3,4,5,6", "src/test/resources/expectedCharts/expectedChart2.png"},
                {"3,4,5,6,7", "src/test/resources/expectedCharts/expectedChart3.png"}
        });
    }

    // Testowa metoda
    @Test
    public void testSendData() {
        // 1. Wyślij dane do serwera i otrzymaj wygenerowany obraz
        byte[] receivedImageBytes = sendData(testData);

        // 2. Wczytaj oczekiwany obraz
        BufferedImage expectedImage = loadImage(expectedImagePath);

        // 3. Wczytaj otrzymany obraz jako BufferedImage
        BufferedImage receivedImage = convertToBufferedImage(receivedImageBytes);

        // 4. Porównaj obrazy (pixel by pixel)
        assertTrue("Obrazy nie są identyczne", imagesAreEqual(expectedImage, receivedImage));
    }

    // Pomocnicza metoda do wysyłania danych do serwera
    private byte[] sendData(String data) {
        // Symulacja wysyłania danych do serwera, np. przez Socket lub HTTP
        // W tym przykładzie zwróć dane jako obraz PNG wygenerowany na serwerze
        // Na potrzeby tego testu, symulujemy wywołanie metody serwera, która generuje obraz
        return ServerSimulator.generateChart(data);  // metoda symulująca odpowiedź serwera
    }

    // Konwersja bajtów na BufferedImage
    private BufferedImage convertToBufferedImage(byte[] imageBytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            fail("Nie można skonwertować bajtów na BufferedImage: " + e.getMessage());
            return null;
        }
    }

    // Wczytanie oczekiwanego obrazu z pliku
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            fail("Nie można wczytać oczekiwanego obrazu z pliku: " + e.getMessage());
            return null;
        }
    }

    // Porównanie obrazów pixel by pixel
    private boolean imagesAreEqual(BufferedImage imgA, BufferedImage imgB) {
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        for (int x = 0; x < imgA.getWidth(); x++) {
            for (int y = 0; y < imgA.getHeight(); y++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }
}

