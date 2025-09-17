package org.example;

@FunctionalInterface
interface AdvancedMathOperations {
    int operate(int a, int b);
    default int power (int a, int b){
        return (int) Math.pow(a,b);
    }
    default int max (int a, int b){
        if ( a > b){
            return a;
        } if (b > a ){
            return b;
        } else {
            return  0;
        }
    }
    default int min (int a, int b){
        if ( a < b){
            return a;
        } if (b < a ){
            return b;
        } else {
            return  0;
        }
    }
    static int gcd(int a, int b) {
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }
}
 class AdvancedMathClass implements AdvancedMathOperations{
    @Override
    public int operate(int a, int b) {
        return a*b + 10;
    }
}
public class DefaultMethodsChallenge{
    public static void main(String[] args) {
        AdvancedMathClass advancedMathClass = new AdvancedMathClass();
        System.out.println(advancedMathClass.operate(10, 20));
    }
}
