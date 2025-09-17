package org.example;

public enum Operator {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private final String symbol; //to pole przechowuje symbol
    //danego operatora w postaci łańcucha znaków. Jest oznaczone final, co
    //oznacza, że jego wartość nie może zostać zmieniona po przypisaniu w
    //konstruktorze

    Operator(String symbol){
        this.symbol = symbol; //każdy element enum jest tworzony z konkretnym
        //symbolem, który jest przypisany podczas inicjalizacji
    }

    public String toString(){
        return symbol; //np ADDITION drukuje +
    }

}
