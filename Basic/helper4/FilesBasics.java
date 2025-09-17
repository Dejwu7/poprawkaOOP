package org.example;

import java.io.FileOutputStream;
import java.io.IOException;

//FileOutputSteam example
public class FilesBasics {
    public static void main(String[] args) throws IOException {
        //metoda read() zwraca typ int natomiast czyta jeden bajt
        //czyli liczbę z przedziału od 0 do 255
        //Jeżeli read() zwróci -1 to znaczy, że jest to koniec
        //pliku. W przypadku, gdy bajt nie może być odczytany,
        //zgloszony zostaje IOExeption
        //Metoda write() zapisuje bajt przekazanego argumentu
        //i ignoruje resztę. Jeśli pojawi się błąd poczas zapisu to pojawi się
        //wyjątek IOException
        //jeśli zamykamy strumień pliku, warto użyć
        //try-catch-finally
        char chars[] = {'Q', 'W', 'E', 'R', 'T', 'Y'}; //ciąg znaków, który zapiszemy do pliku
        FileOutputStream out = null;
        try { //w konstruktorze podajemy nazwę pliku, który jeśli nie istnieje, zostanie automatycznie utworzony
            out = new FileOutputStream("test.txt");
            for(char c : chars){
                out.write(c); //iterujemy po tablicy i zapisujemy znaki
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(out != null) out.close();
        }

    }
}