package org.example;

class Square<T extends Number>{
    private T wall;

    public Square(T wall) {
        this.wall = wall;
    }

    public T getWall() {
        return wall;
    }

    public void setWall(T wall) {
        this.wall = wall;
    }

    public double getSurfaceArea (){
        double wall = this.wall.doubleValue();
        return wall * wall;
    }
}
public class GenericTask1 {
    public static void main(String[] args) {
        Square<Integer> square = new Square<>(10);
        System.out.println(square.getSurfaceArea());
        Square<Float> floatSquare = new Square<>(5.7f);
        System.out.println(floatSquare.getSurfaceArea());
    }
}
