package org.example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

//operowanie strumieniem bajt po bajcie jest mało wydajne
//bazowe strumienie nie są buforowane, dlatego są powolne
//buforowane strumienie odczytują i zapisują
//dane do bufora i w odpowiednim momencie zapisują/odczytują
//cały bufor przyspieszając operacje na plikach
// o wiele lepiej jest zebrać określoną ilość danych
//i zapisać je za jednym razem
//operacje na bajtach danych: BufferedInputStream, BufferedOutputStream
//buforowanie strumienia tekstu: BufferedReader, BufferedWriter
public class BufferedInputStreamExample {
    public static void main(String[] args)
            throws IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(
                    new FileInputStream("test.txt") //nazwa pliku, który chcemy odczytać
            );
            //tak jak wcześniej, bajt po bajcie możemy odczytać plik
            int v = 0;
            while ((v = in.read()) != -1){
                System.out.println((char) v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) in.close();
        }

    }
}
