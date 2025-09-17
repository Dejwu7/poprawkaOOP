package org.example;
//umożliwia tworzenie kodu, który może być wykorzystany
//dla różnych typów, ma to znaczenie, gdyż często klasy bywają do siebie
//podobne, służą do wykonywania tych samych czynności, ale różnią się typami,
//z których korzystają
//Klasa generyczna posiada w nawiasach ostrych jeden lub więcej
//typów generycznych. Po przecinku podawane są kolejne typy generyczne

class Point<T> {
    private T x;
    private T y;

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Trio <T, U, V>{
    private T first;
    private U second;
    private V third;

    public Trio(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public V getThird() {
        return third;
    }

    public void setThird(V third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Trio{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}


public class GeneryczneWstep {
    public static void main(String[] args) {
        Point<Integer> pointInt = new Point<>(12, 30);
        System.out.println(pointInt.toString());
        Point<String> pointStr = new Point<>("X", "Y");
        System.out.println(pointStr);
        Trio<String, Integer, String> trio =
                new Trio<>("Adam", 10, "Kasia");
        System.out.println(trio);
    }
}
