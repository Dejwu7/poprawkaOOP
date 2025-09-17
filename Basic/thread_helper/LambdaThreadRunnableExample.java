package org.example;

public class LambdaThreadRunnableExample {
    public static void main(String[] args) {

        Thread thread = new Thread( () -> {
            for(int i=0; i< 5; i++){
                System.out.println(i);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}

//Runnable to również interfejs funkcyjny, dzięki temu
// wątek można zapisać jako lambdę