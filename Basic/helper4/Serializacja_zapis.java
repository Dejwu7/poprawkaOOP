package org.example;

import java.io.*;

public class Serializacja_zapis {
    public static void main(String[] args)
        throws IOException {

        CarObject car1 = new CarObject("Ford", "Mustang", 1970, 200, 1);
        CarObject car2 = new CarObject("Dodge", "Charger", 1971, 220, 2);

        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("files\\cars.dat")
                )
        );
        out.writeObject(car1);
        out.writeObject(car2);
        out.flush();
        out.close();

    }
}
