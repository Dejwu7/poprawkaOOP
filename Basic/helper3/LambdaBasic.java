package org.example;

//Wyrażenia lambda są blokami kodu, które można przypisać do zmiennej i
//przekazywać między różnymi częściami programu jako argument w celu późniejszego
//wykorzystania. Z lambdami są związane również interfejscy, które
//posiadają wyłącznie jedną abstrakcyjną metodę, nazywa się je interfejsami funkcyjnymi.
//Wyrażenia lambda są szczególnie pomocne w pracy ze streamami oraz kolekcjami
//dzięki nim kod jest prostszy i czytelniejszy
//kilka instrukcji dla ciała wyrażenia lambda trzeba zawsze zapisywać w
//nawiasach klamrowych

interface Addition {
    int add(int a, int b);
}
//moglibyśmy nawet pozbyć się tych
//typów i zapisać je bardziej ogólnie
interface MathOperation <T> {
    T operate(T a, T b);
}
public class LambdaBasic {
    public void test(Addition addition){
        System.out.println(addition.add(100, 50));
    }
    public static void main(String[] args) {
        Addition addition = (int a, int b) -> a + b;
        System.out.println(addition.add(11, 6)); //17
        System.out.println(addition.add(20, 5)); //25
        //takie wrażenie możemy przekazać jako argument do metody

        LambdaBasic lambdaBasic = new LambdaBasic();
        lambdaBasic.test((a,b) -> a*2 + b);

        MathOperation<Integer> addition2 = (a,b) -> a + b;
        //w praktyce bez zmian, ale jest to bardziej uniwersalnie zapisane
        MathOperation<Float> addition3 = (a,b) -> a + b;
        System.out.println(addition3.operate(10.0f, 6.0f));
        MathOperation<Integer> substraction = (a,b) -> a - b;
        System.out.println(substraction.operate(100, 77)); //23

    }
}