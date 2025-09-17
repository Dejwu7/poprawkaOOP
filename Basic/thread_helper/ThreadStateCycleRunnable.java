package org.example;

//po wywołaniu metody start wątek przechodzi z stanu New na Runnable
public class ThreadStateCycleRunnable {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=10; i > 0; i--){
                    try{
                        Thread.sleep(10);
                        System.out.println(i);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        System.out.println(thread.getState());
    }
}

//Wątek w stanie Runnable
//działa albo jest gotowy do rozpoczęcia swojej pracy.