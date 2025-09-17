package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

@FunctionalInterface
interface Calculation {
    double calculate(double left, double right, Operator operator); }

public class Calculator extends Application {
    private TextField leftField; //zmienna zadeklarowana na poziomie
    //klasy, czyli będzie dostępna w całej klasie Calculator
    private TextField rightField;
    private ChoiceBox<Operator> choiceBox; //lista rozwijana
    private TextField resultField;
    private Button solveButton;
    private Calculation calculation;
    public void setCalculation(Calculation calculation){
        this.calculation = calculation;
    }

    //Calculator dziedziczy po klasie Application,
    //która jest częścią frameworku JavaFX. Aby stworzyć
    //aplikację JavaFX, należy rozszerzyć klasę Application
    //i zaimplementować metodę start()

    private void solve(){
        double left = Double.parseDouble(leftField.getText());
        double right = Double.parseDouble(rightField.getText());
        Operator operator = choiceBox.getValue();
        try {
            double result = calculation.calculate(left, right, operator);
            resultField.setText(String.valueOf(result));
        } catch (ArithmeticException e){
            showError(e.getMessage());
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void init() throws Exception {
        super.init();

        // Ustawianie obliczeń za pomocą lambda
        this.setCalculation((left, right, operator) -> {
            return switch (operator) {
                case ADDITION -> left + right;
                case SUBTRACTION -> left - right;
                case MULTIPLICATION -> left * right;
                case DIVISION -> {
                    if (right == 0) throw new ArithmeticException("Cannot divide by zero");
                    yield left / right;
                }
            };
        });
    }

    @Override public void start(Stage stage) { //Stage to główne okno aplikacji,
        //w którym są wyświetlane wszystkie elementy GUI
        stage.setTitle("Calculator"); //ustawienie tytułu okna
        solveButton = new Button("Solve");
        solveButton.setOnAction(event -> solve()); //akcja, któa ma się wykonać po kliknięciu solveButton

        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(Operator.values());
        choiceBox.setValue(Operator.ADDITION); //ustawienie domyślnego operatora

        resultField = new TextField();
        resultField.setEditable(false);

        leftField = new TextField();
        rightField = new TextField();

       //rightField.setLayoutX(100); //Przesunięcie w poziomie o 100 px
       //rightField.setLayoutY(15); //Przesunięcie w pionie o 15 px

        GridPane pane = new GridPane(); //jeden z kontenerów układu
        //w JavaFX, któy pozwala na ułożenie elementów w formie siatki
        //o ustalonej liczbie wierszy i kolumn

        pane.addRow(0, leftField, choiceBox, rightField, new Label("="), resultField);
        pane.add(solveButton, 1, 1, 2, 1); 

        /*pane.add(leftField, 0, 0, 1, 1);
        pane.add(choiceBox, 1, 0, 1, 1);
        pane.add(rightField, 2, 0, 1, 1);*/
        //leftField znajduje się w kolumnie 0, wierszu 0
        //rightField znajduje się w kolumnie 1, wierszu 0
        //parametry colSpan i rowSpan w obu przypadkach wynoszą 1,
        //co oznacza, że każde pole tekstowe zajmuje tylko jedną komórkę siatki

        /*Pane pane = new Pane(); //kontener na elementy GUI. Ręczne ustawianie
        //pozycji elementów w jego wnętrzu
        pane.getChildren().add(leftField); //pole tekstowe zostaje
        //dodane do układu, dzięki czemu element zostanie wyświetlony w oknie
        //aplikacji
        pane.getChildren().add(rightField);*/

        Scene scene = new Scene(pane); //Scena, która zawiera dany układ
        stage.setScene(scene); // Scena jest ustawiana na oknie aplikacji (stage)
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}