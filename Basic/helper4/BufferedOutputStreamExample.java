package org.example;

import java.io.*;

import static java.nio.file.Files.write;

public class BufferedOutputStreamExample {
    public static void main(String[] args)
            throws IOException {
        BufferedOutputStream out = null; //strumień zapisu
        String str = "Testowy string.";//dane do zapisu
        char chars[] = str.toCharArray();
        try {
            out = new BufferedOutputStream((
                    new FileOutputStream("files\\test.txt") //nazwa pliku, który chcemy odczytać
            ));
            for(char c: chars){
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) out.close();
        }

    }
}
