package org.example;

import java.io.*;

public class DataInputStreamExample {
    public static void main(String[] args)
            throws IOException {

        DataInputStream in = null;
        try {
            in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("files\\test.dat")
                    )
            );

            //out.writeUTF("Testowy strumień binarny");
            String str = in.readUTF();
            System.out.println(str);

            //out.writeShort(32000);
            short num = in.readShort();
            System.out.println(num);

            //out.writeLong(12378387424775L);
            long longNum = in.readLong();
            System.out.println(longNum);

            //out.writeFloat(26.848f);
            float floatNum = in.readFloat();
            System.out.println(floatNum);

            //out.writeDouble(234d);
            double doubleNum = in.readDouble();
            System.out.println(doubleNum);

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(in != null) in.close();
        }
    }
}
