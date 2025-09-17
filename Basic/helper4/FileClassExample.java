package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileClassExample {
    //klasa File umożliwia uzyskanie informacji o plikach i katalogach
    //metoda createNewFile() tworzy nowy plik
    //canRead() zwróci wartość true jeśli plik może być odczytany
    //canWrite() zwróci wartosc true jeśli plik może być zapisywany
    //isDirectory() zwróci wartosc true jeśli plik jest katalogiem
    //isFile() zwróci wartość true jeśli jest plikiem
    //getname(), getParent(), getPath(), getAbsolutePath()
    public static void main(String[] args)
        throws IOException {

        File file = new File("files\\testFile.txt");
        //sprawdzenie, czy plik istnieje
        if(!file.exists()){
            boolean fileCreated = file.createNewFile();
            System.out.println(fileCreated);
        } else {
            System.out.println("Plik istnieje");
            System.out.println("Czy można czytać plik: " + file.canRead());
            System.out.println("Czy można zapisać do pliku: " + file.canWrite());
            System.out.println("Czy to zwykły plik: " + file.isFile());
            System.out.println("Czy plik jest katalogiem: " + file.isDirectory());
            System.out.println("Nazwa pliku: " + file.getName());
            System.out.println("Katalog, w którym jest plik: " + file.getParent());
            System.out.println("Path: " + file.getPath());
            System.out.println("Czy jest to ukryty plik: " + file.isHidden());
            long lastModified = file.lastModified();
            System.out.println("Ostatnio zmodyfikowany: " + new Date(lastModified));
            System.out.println("Wielkość pliku: " + file.length());
        }
        //Odczytanie katalogu
        File filesDir = new File("files");
        System.out.println("\n Lista plików w katalogu:");
        if(filesDir.exists()){
            String files[] = filesDir.list(); //lista plików w tym katalogu
            for(String f : files){
                System.out.println(f);
            }
        }
        //Stworzenie nowego katalogu
        File dir = new File("files\\test_dir");
        if(!dir.exists()){
            dir.mkdir();
        }
        }
    }
