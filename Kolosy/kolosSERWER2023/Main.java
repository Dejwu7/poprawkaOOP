package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    private Slider slider;
    private Label sliderRadius;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Interface");

        //Inicjalizacja elementów
        sliderRadius = new Label();
        slider = new Slider(1, 15, 1); // Ustawienie zakresu od 1 do 15

        //Konfiguracja suwaka
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(2);  // Główna jednostka na skali to 2 (co drugi znacznik)
        slider.setMinorTickCount(1); // Pomiędzy głównymi jednostkami
        slider.setBlockIncrement(2); // Ruch co 2 jednostki (nieparzyste liczby)

        // Zmuszenie suwaka do przyjmowania tylko wartości nieparzystych
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int roundedValue = (int) Math.round(newValue.doubleValue());
            if (roundedValue % 2 == 0) {
                roundedValue += 1; // Jeśli wartość jest parzysta, zaokrąglamy w górę do najbliższej nieparzystej liczby
            }
            slider.setValue(roundedValue); // Ustawienie wartości suwaka
            sliderRadius.setText("Promień: " + roundedValue); // Aktualizacja etykiety
        });

        //początkowy promień (na radiusLabel)
        sliderRadius.setText(String.valueOf(slider.getValue()));

        //Rozmieszczenie elementów na scenie + scena
        GridPane pane = new GridPane();
        pane.add(sliderRadius, 1, 2, 2, 1);
        pane.add(slider, 2, 3);

        Scene scene = new Scene(pane, 500, 300);  // Ustawienie rozmiaru okna aplikacji (500x300 pikseli)
        stage.setScene(scene);  // Dodanie sceny do okna aplikacji
        stage.show();  //
    }
    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}

//Serwer powinien posiadać zaimplementowany interfejs
//służący do ustawienia rozmiaru jądra splotu. Interfejs powinien
//posiadać dwa elementy:
//-suwak (slider) pozwalający modyfikować promień
//-etykieta(label) wyświetlająca promień filtra
//Promień filtra powinien przyjmować wyłącznie
//wartości nieparzyste z zakresu 1-15