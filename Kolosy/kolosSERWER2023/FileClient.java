package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileClient {

    private final Socket socket;
    private final OutputStream outputStream;

    public FileClient(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.outputStream = socket.getOutputStream();
    }

    public void sendFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buffer = new byte[4096];
        int bytesRead;

        // Wysyłanie pliku
        while ((bytesRead = fileInputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        System.out.println("File sent: " + filePath);
    }

    public void close() throws IOException {
        socket.close();
    }

    public static void main(String[] args) {
        try {
            FileClient client = new FileClient("localhost", 6000); // Połączenie z serwerem na porcie 6000
            client.sendFile("path_to_image.png"); // Przykład ścieżki do pliku PNG
            client.close(); // Zamknięcie połączenia
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
