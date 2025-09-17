package org.example.client;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    private final Socket socket;
    private final BufferedReader reader; //do odczytywania wiadomości wysłanych prez serwer
    private final PrintWriter writer; //do wysyłania wiadomości do serwera


    // Konstruktor łączący klienta z serwerem
    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);

        // Inicjalizacja strumieni do komunikacji z serwerem
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);
    }

    @Override
    public void run() {
        // Odbieranie wiadomości od serwera
        try {
            String message;
            while ((message = reader.readLine()) != null)
                System.out.println(message);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Funkcja wysyłająca dane z pliku CSV oraz nazwę użytkownika
    public void sendData(String name, String filepath) throws IOException {
        // Wysłanie nazwy użytkownika
        writer.println(name);

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filepath))) {
            String line;
            // Wysyłanie danych linia po linii
            while ((line = fileReader.readLine()) != null) {
                writer.println(line);  // Wysłanie linii z pliku CSV
                System.out.println(line);
                Thread.sleep(2000);    // Przerwa 2 sekundy między liniami
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wysłanie wiadomości o zakończeniu przesyłania
        writer.println("bye");
        System.out.println("Zakończono przesyłanie danych.");
    }

    // Zamykanie połączenia
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }

}

//W projekcie pierwszym napisz aplikację kliencką, która będzie
//wczytywać ze standardowego wejścia nazwę użytkownika oraz
// ścieżkę do pliku csv.
//Następnie wyśle na serwer informację o nazwie użytkownika,
// oraz zawartość pliku csv (w przykładzie są to tm00.csv i tm01.csv ) linia po linii.
// Po każdej wysłanej linii mają nastąpić 2 sekundy przerwy.
//Zakładamy, że podawane są unikatowe nazwy użytkowników.
//Po zakończeniu aplikacja wyśle wiadomość informującą serwer o
// zakończeniu przesyłania(np. bye).
//Wysyłanie wszystkich tych informacji odseparuj
// w oddzielnej funkcji: public void sendData(String name,
// String filepath)