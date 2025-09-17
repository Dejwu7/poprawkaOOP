package org.example;

public class ThreadStateCycleTimedWaiting {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
    }
}

//Timed Waiting - gdy uśpiony wątek oczekuje określony czas na swoją
//dalszą prace np po wywołaniu metody sleep(time).