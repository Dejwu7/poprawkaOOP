package org.example;


class SomeThread implements Runnable {

    //Wymusimy, aby dwa wątki wykonywały tę samą metodę

    public static synchronized void test(){
        while (true){

        }
    }

    @Override
    public void run() {
        test();
    }
}


public class ThreadStateCycleBlocked {
    public static void main(String[] args)
    throws InterruptedException {

        Thread thread1 = new Thread(new SomeThread());
        Thread thread2 = new Thread(new SomeThread());

        thread1.start(); //wejdzie w nieskończoną pętlę
        thread2.start();

        Thread.sleep(100);
        System.out.println(thread2.getState()); //BLOCKED
        //thread1 jest runnable

    }
}

//Wątek ma stan BLOCKED kiedy nie ma prawa do działania.
//Jest blokowany przez tzn monitor który zarządza
//synchronizacją wątków, gdyż chce się odwołać do kodu
//który jest używany przez inny wątek