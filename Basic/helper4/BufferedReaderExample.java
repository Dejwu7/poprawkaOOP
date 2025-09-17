package org.example;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args)
            throws IOException {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("files\\multiline.txt"));
            String str = null;
            while ((str = br.readLine()) != null){
                System.out.println(str);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (br != null) br.close();
        }
    }
}
