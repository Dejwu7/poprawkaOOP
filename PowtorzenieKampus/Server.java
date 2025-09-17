package org.example.server;

import org.example.databasecreator.DatabaseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import static org.example.server.ChartGenerator.createChart;

public class Server {
    //port
    private int port = 1111;
    private final DatabaseConnection databaseConnection;

    //konstruktor ustawiający port

    public Server(int port, DatabaseConnection databaseConnection) {
        this.port = port;
        this.databaseConnection = databaseConnection;
    }

    private void start() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem.");
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //handleClient
    private void handleClient(Socket clientSocket){
        //try nowy BufferedReader nowy Inputstrim clientsocket get input
        //odbieramy nazwe uzytkownika czyli reader.readline bo jest w pierwszje
        //linijce
        //otrzymano nazwe użytkownika
        //Wczytujesz linijka po linijce
        //if byr rozłączasz
        //else rozbudowujesz baze
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))) {
                String username = reader.readLine();
                System.out.println("Otrzymano nazwę użytkownika: " + username);

                String line;
                int chartNumber = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("bye")) {
                        System.out.println("Klient zakończył przesyłanie danych.");
                        break;
                    }
                    System.out.println("Otrzymano linię z CSV: " + line);  // Odbieranie linii CSV
                    String base64Chart = Base64.getEncoder().encodeToString(createChart(line, chartNumber));
                    chartNumber++;

                    Connection connection = databaseConnection.getConnection();

                    // Zapis do bazy danych
                    String sql = "INSERT INTO usereeg (chart, chartNumber, username) VALUES (?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, base64Chart);  // Zapisany wykres w formacie base64
                        statement.setInt(2, chartNumber);     // Numer wykresu
                        statement.setString(3, username);     // Nazwa użytkownika
                        statement.executeUpdate();
                        System.out.println("Zarejestrowano nowy wykres dla użytkownika: " + username);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();  // Zamknięcie połączenia po zakończeniu przesyłania danych
                    System.out.println("Połączenie z klientem zamknięte.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//W projekcie pierwszym napisz aplikacje serwerową,
// która obsługuje wielu klientów.
//Dla pojedynczego klienta serwer
// pobiera informację o nazwie usera,
//Pamiętaj o utworzeniu bazy danych za pomocą klasy Creator.