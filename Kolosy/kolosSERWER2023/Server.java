package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {

    private ServerSocket serverSocket;
    private boolean oneUserLogged = false;
    private final DatabaseConnection dbConnection;

    public Server(DatabaseConnection dbConnection, int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.dbConnection = dbConnection;
        System.out.println("Server is running on port " + port);
    }

    public void start() throws IOException {
        while (true) {
            if (!oneUserLogged) {
                Socket socket = serverSocket.accept(); // Akceptowanie połączenia od administratora
                new ClientHandler(socket).start(); // Obsługa klienta w osobnym wątku
                oneUserLogged = true; // Ustawienie flagi po zaakceptowaniu klienta
            }
        }
    }

    //Klasa obsługująca połączenie klienta
    //Serwer powinien oczekiwać na przesłanie przez użytkownika
    //pliku graficznego png
    //Z Client jest metoda sendFile(String filePath)

    private class ClientHandler extends Thread {
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        }

        @Override
        public void run() {
            try {
                String imagePath = saveImage(inputStream);
                System.out.println("Zapisano obraz: " + imagePath);

                // Przekształć obraz (np. box blur)
                String transformedImagePath = transformImage(imagePath);
                System.out.println("Przekształcony obraz zapisany: " + transformedImagePath);

                // Wyślij przekształcony obraz do klienta
                sendImageToClient(transformedImagePath);
                System.out.println("Obraz wysłany do klienta.");

                // Tutaj możesz dodać informacje do bazy danych
                File savedImage = new File(imagePath);
                int imageSize = (int) savedImage.length(); // Rozmiar obrazu w bajtach
                int delay = 0; // Przykładowy czas opóźnienia (można dostosować)

                // Dodanie informacji do bazy danych
                insertEntry(imagePath, imageSize, delay);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        // Metoda do przekształcenia obrazu (np. zastosowanie box blur)
        private String transformImage(String imagePath) throws IOException {
            // Dodaj tu swoją logikę do przekształcenia obrazu, np. algorytm box blur
            // Zakładam, że przekształcony obraz zapisujesz w nowej ścieżce
            String transformedImagePath = imagePath.replace(".png", "_transformed.png");

            // Wstaw tutaj operacje na obrazie, które modyfikują obraz
            // np. użycie metody blurImageMethod()

            return transformedImagePath;
        }

        // Wysyła przekształcony obraz do klienta
        private void sendImageToClient(String imagePath) throws IOException {
            File imageFile = new File(imagePath);
            try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);  // Wysyłanie przekształconego obrazu do klienta
                }
            }
        }

        //Po otrzymaniu pliku należy zapisać go do katalogu images znajdującym
        //się w ścieżce uruchomienia programu. Jeśli katalogu nie ma, program
        //powinien go utworzyć. W nazwie pliku powinien znaleźć się
        //znacznik czasowy.
        private String createPathName(){
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
            String formattedDateTime = time.format(formatter);
            String dirPath = "resources" + File.separator + "images";

            File dir = new File(dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }

            String fullPathName = dirPath + File.separator + formattedDateTime + ".png";

            return fullPathName;
        }

        // Zrównoleglenie algorytmu box blur
        public int[][] blurImageMethod(int[][] image) throws InterruptedException {
            int numberOfThreads = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

            int rows = image.length;
            int cols = image[0].length;
            int[][] nImage = new int[rows][cols];

            // Podział obrazu na równe części między wątki
            int rowsPerThread = rows / numberOfThreads;

            for (int thread = 0; thread < numberOfThreads; thread++) {
                int startRow = thread * rowsPerThread;
                int endRow = (thread == numberOfThreads - 1) ? rows : startRow + rowsPerThread;

                // Tworzymy zadanie dla wątku
                executor.submit(() -> {
                    for (int i = startRow; i < endRow; i++) {
                        for (int j = 1; j < cols - 1; j++) {
                            // Box blur dla każdego piksela (omijamy krawędzie)
                            if (i > 0 && i < rows - 1) {
                                int sum = image[i - 1][j - 1] + image[i - 1][j] + image[i - 1][j + 1] +
                                        image[i][j - 1] + image[i][j] + image[i][j + 1] +
                                        image[i + 1][j - 1] + image[i + 1][j] + image[i + 1][j + 1];
                                nImage[i][j] = sum / 9;
                            }
                        }
                    }
                });
            }

            // Zakończenie wszystkich wątków
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            return nImage;
        }

        public void initializeDatabase(String path) throws IOException {

            // Sprawdzanie, czy plik index.db istnieje w katalogu images
            File sqliteDb = new File(path);
            if (!sqliteDb.exists()) {
                sqliteDb.createNewFile();
                System.out.println("Utworzono nowy plik bazy danych: " + path);
            };

            // i założyć w nim bazę danych SQLite
            dbConnection.connect(path);
            Connection connection = dbConnection.getConnection();

            if(connection != null){
                try {
                    Statement statement = connection.createStatement();

                    // Tworzenie nowej tabeli, jeśli nie istnieje
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS blur ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "path TEXT NOT NULL, " +
                            "size INTEGER NOT NULL, " +
                            "delay INTEGER NOT NULL); ";

                    statement.execute(createTableSQL);
                    System.out.println("Tabela blur została utworzona lub już istnieje.");

                } catch (SQLException e){
                    e.printStackTrace();
                } finally {
                    dbConnection.disconnect();
                }
            }
        }

        //Dla każdego przekonwertowanego obrazu należy wpis do tej
        //tabeli

        public void insertEntry(String imagePath, int imageSize, int delay) {

            Connection connection = dbConnection.getConnection();

            if (connection != null) {
                String insertSQL = "INSERT INTO blur (path, size, delay) VALUES (?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                    // Ustawianie wartości dla zapytania
                    preparedStatement.setString(1, imagePath);  // Ścieżka do pliku
                    preparedStatement.setInt(2, imageSize);     // Rozmiar pliku (np. w bajtach)
                    preparedStatement.setInt(3, delay);         // Ewentualne opóźnienie (w ms)

                    // Wykonanie zapytania
                    preparedStatement.executeUpdate();
                    System.out.println("Wpis został dodany do bazy danych.");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    dbConnection.disconnect();
                }
            }
        }

        private String saveImage(InputStream input) throws IOException {

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(createPathName());
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = input.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } finally {
                if (outputStream != null) {
                    outputStream.close(); // Upewnienie się, że strumień zostanie zamknięty
                }
            }
            return createPathName();
        }

        // Metoda do zamykania połączenia
        private void closeConnection() {
            try {
                oneUserLogged = false;
                socket.close();
                inputStream.close();
                System.out.println("Admin disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

//InputStream inputStream: Strumień danych, który pochodzi
// z połączenia socket. Ten strumień reprezentuje dane binarne,
// które klient wysyła – w tym przypadku plik graficzny.

//metoda saveImage = tworzenie strumienia wyjściowego,
//zapisanie do pliku. FileOutputStream jest używany do
// zapisywania danych binarnych do pliku. Dzięki temu każdy
// fragment pliku przesłany przez klienta będzie zapisywany
// bezpośrednio na dysku serwera.

//Bufor o rozmiarze 4096 bajtów (4 KB) jest tworzony,
// aby odczytywać dane ze strumienia w blokach, a nie po
// jednym bajcie.

//bytesRead: Liczba odczytanych bajtów z bufora.
// Jeżeli wynosi -1, oznacza to koniec strumienia (plik
// został w całości przesłany).