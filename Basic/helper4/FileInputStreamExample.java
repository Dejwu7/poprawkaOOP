package org.example;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample {
    public static void main(String[] args)
            throws IOException {
        //teraz chcemy odczytać to, co zapisaliśmy do test
        FileInputStream in = null;
        try {
            in = new FileInputStream("test.txt");
            int num = 0;
            while ((num = in.read()) != -1) { //metoda read zwróci
                // nam jeden bajt informacji z InputStream
                // natomiast może również zwrócić -1 i to oznacza, że
                //jest to koniec pliku. Musimy się tu zabezpiecznyć,
                // dlatego sprawdzamy, czy num nie jest równe -1
                System.out.print((char) num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) in.close();
        }
    }
}
