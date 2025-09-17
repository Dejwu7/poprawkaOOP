package org.example;

@FunctionalInterface
interface MathInterface {
    int operate(int a, int b);

    default int add(int a, int b){
        return a + b;
    }

    default int substract(int a, int b){
        return a - b;
    }

    default int multiply(int a, int b){
        return a * b;
    }

    static int divade(int a, int b){
        return a/b;
    }
}
class MathClass implements MathInterface {
    @Override
    public int multiply(int a, int b) {
        System.out.println("MathClass.multiply()");
        return a * b;
    }

    @Override
    public int operate(int a, int b) {
        System.out.println("MathClass.operate()");
        return a + b;
    }
}

public class DefaultMethods {
    public static void main(String[] args) {
        MathInterface math = (int a, int b) -> a * b;
        System.out.println(math.operate(10, 30));
        System.out.println(MathInterface.divade(100,4)); //25
        System.out.println(math.add(10, 25));

        MathClass mathClass = new MathClass();
        System.out.println(mathClass.operate(100, 100));
        System.out.println(mathClass.multiply(20, 20));
    }
}
