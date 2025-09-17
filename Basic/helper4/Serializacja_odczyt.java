package org.example;

import java.io.*;

public class Serializacja_odczyt {
    public static void main(String[] args)
            throws IOException {
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("files\\cars.dat")));

        try {
            while(true){
                CarObject car = (CarObject) in.readObject();
                System.out.println(car);
            }

        } catch(EOFException e){
            //koniec pliku
        }
        catch(Exception e){
            e.printStackTrace();
        } finally {
            if(in != null) in.close();
        }
    }
}

