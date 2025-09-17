package org.example;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable { //Runnable oznacza,
    //że może być uruchamiana w osobnym wątku, aby obsługiwać
    //klientów w aplikacji wielowątkowej.

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    // Konstruktor klasy ClientHandler
    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;

        // Pobieramy strumienie wejściowy i wyjściowy z gniazda
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // Tworzymy obiekt BufferedReader do odczytu danych od klienta
        reader = new BufferedReader(new InputStreamReader(input));

        // Tworzymy obiekt PrintWriter do wysyłania danych do klienta
        writer = new PrintWriter(output, true); // Flaga true włącza auto-flush
    }

    // Implementacja metody run() z interfejsu Runnable
    @Override
    public void run() {
        System.out.println("Client connected"); // Wypisuje informację o
        // połączeniu klienta

        String message;
        try {
            // Odczytuje wiadomości od klienta i
            // odsyła je z powrotem (tryb echo)
            while ((message = reader.readLine()) != null) {
                writer.println(message); // Odsłanie tej samej
                // wiadomości do klienta
            }
            socket.close(); // Zamyka połączenie po
            // zakończeniu komunikacji
        } catch (IOException e) {
            throw new RuntimeException(e); // Obsługa wyjątku,
            // gdy wystąpi problem z I/O
        }

        System.out.println("Client disconnected"); // Wypisuje
        // informację o zakończeniu połączenia z klientem
    }

}

