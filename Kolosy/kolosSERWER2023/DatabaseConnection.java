package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        private Connection connection;

        public Connection getConnection() {
            return connection;
        } //jak chcesz połączyć się ze swoją sqlite database kopiujesz link
        //tam gdzie zostało stworzone usersdb w main

        public void connect(String path){
            try {
                String url = "jdbc:sqlite:" + path;
                connection = DriverManager.getConnection(url);
                System.out.println("Jest połączenie");
            } catch (SQLException e){
                System.out.println("Error connecting to database");
                e.printStackTrace();
            }
        }

        public void disconnect(){
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                    System.out.println("Rozłączono z bazą");
                }
            } catch (SQLException e){
                System.out.println("Nie udało się rozłączyć z bazą");
                e.printStackTrace();
            }
        }
}
