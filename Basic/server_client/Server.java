package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> handlers = new ArrayList<>();

    //Konstruktor serwera
    public Server() throws IOException {
        serverSocket = new ServerSocket(3000); //Nasłuch na porcie

        //Tworzymy obiekt ServerSocket, który nasłuchuje na porcie
        //3000. Ten port identyfikuje aplikację na komputerze w sieci.
        //Jeśli utworzenie gniazda się nie powiedzie (np. port jest
        //zajęty), zostanie rzucony wyjątek.
    }

    // Metoda do nasłuchiwania na połączenia klientów
    public void listen() throws IOException {
        System.out.println("Server started");

        while (true) {
            Socket socket = serverSocket.accept();  // Akceptacja nowego klienta
            System.out.println("Client connected");

            ClientHandler handler = new ClientHandler(socket, this);  // Tworzenie handlera dla klienta
            Thread thread = new Thread(handler);  // Tworzenie nowego wątku dla klienta
            thread.start();  // Uruchomienie wątku
            handlers.add(handler);  // Dodanie handlera do listy aktywnych klientów
        }
    }

    // Metoda do usunięcia handlera, gdy klient się rozłączy
    public void removeHandler(ClientHandler handler) {
        handlers.remove(handler);
    }

    /*
    //Metoda nasłuchiwania na połączenia
    public void listen() throws IOException {
        System.out.println("Server started");
        Socket socket = serverSocket.accept(); //gdy klient spróbuje
        //się połączyć z serwerem, metoda accept() zwróci obiekt Socket,
        //który można wykorzystać do komunikacji z klientem
        System.out.println("Client connected");

        //Odebranie wiadomości
        InputStream input = socket.getInputStream(); //pobieram
        //strumień wejściowy z gniazda klienta
        BufferedReader reader = //pozwala na wygodne czytanie danych
                new BufferedReader(
                        new InputStreamReader(input)
                );
        String message;

        //Wysłanie wiadomości
        OutputStream output = socket.getOutputStream(); //pobiera strumień
        //wyjściowy z gniazda, które zostało utworzone po nawiązaniu połączenia
        //z klientem. Służy do wysyłania danych od serwera do klienta
        PrintWriter writer = new PrintWriter(output, true); //Obiekt PrintWriter służy do pisania
        // danych do strumienia w postaci tekstowej (np. linii tekstu).
        // Jest on owinięciem strumienia wyjściowego (OutputStream),
        // aby łatwiej było pisać dane tekstowe (stringi).
        //Flaga true w konstruktorze oznacza, że
        // auto-flush jest włączony. Oznacza to, że po każdej
        // operacji println() dane są natychmiast wysyłane do
        // klienta, bez konieczności ręcznego wywoływania flush().
        String response;
        writer.println("Hello!");
        while ((response = reader.readLine()) != null)
            writer.println(response);

        //zakończenie połączenia
        while ((message = reader.readLine())!=null){ //readLine() czeka na wiadomości od klienta
            System.out.println(message);
        }
        socket.close();
        serverSocket.close();
        System.out.println("System closed");
    }
    */

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.listen();
    }
}

//Serwer to program, który nasłuchuje na konkretnym porcie
//i czeka na połączenia od klientów.

//Klient to program, któy nawiązuje połączenie z serwerem
//Po nawiązaniu połączenia klient i serwer mogą wymieniać
//dane (zazwyczaj przez gniazda sieciowe)

//telnet localhost 3000