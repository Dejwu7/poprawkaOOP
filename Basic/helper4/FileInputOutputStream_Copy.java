package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStream_Copy {
    public static void main(String[] args) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        try{
            in = new FileInputStream("test.txt");
            out = new FileOutputStream("copy.txt");

            int num = 0;
            while((num = in.read())!= -1) {
                out.write(num);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(in != null) in.close();
            if(out != null) out.close();
        }
    }
}
