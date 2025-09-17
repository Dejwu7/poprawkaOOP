package org.example;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//aby móc odczytać/zapisać inne dane niż tekstowe
//czy pojedyncze bajty powinno się zastosować wyspecjalizowane
//klasy z metodami do odczytu i zapisu innych typów prostych

public class DataOutputStreamExample {
    public static void main(String[] args)
            throws IOException {
        //Dodane klasy wymagają zagnieżdżania strumieni
        //aby z nich skorzystać, np. można przy zapisie buforować dane aby
        //przyspieszyć działanie programu
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("files\\test.dat")
                    )
            ); //mamy zbuforowany sturmień do pliku binarnego, czyli możemy
            //zapisywać różnego rodzaju dane binarne
            out.writeUTF("Testowy strumień binarny");
            out.writeShort(32000);
            out.writeLong(12378387424775L);
            out.writeFloat(26.848f);
            out.writeDouble(234d);
            out.flush(); // wymuszenie zapisu z bufora
            //mamy plik, który możemy odczytać za pomocą DataInputStream
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(out != null) out.close();
        }
    }
}
