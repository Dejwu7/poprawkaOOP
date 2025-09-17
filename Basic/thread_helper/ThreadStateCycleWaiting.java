package org.example;

class TestThread extends Thread {
    Thread mainThread; //inicjalizujemy to tu aby móc sprawdzić status wątku main

    public TestThread(String name, Thread mainThread){
        super(name);
        this.mainThread = mainThread;
    }

    public void run(){
        System.out.println(getName() + " started.");
        try{
            for(int i=0; i<3; i++){
                Thread.sleep(1000);
                System.out.println(mainThread.getState()); //stan glownego wątku main
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(getName() + " finished.");
    }
}

public class ThreadStateCycleWaiting {
    public static void main(String[] args)
    throws InterruptedException {

        //zawsze nasz program pracuje w jakimś jednym wątku (main)

        //dostęp do głównego wątku:
        Thread mainThread = Thread.currentThread();

        TestThread thread1 = new TestThread("thread1", mainThread);
        thread1.start(); //na tym etapie thread1 będzie się wywoływał przez 3 sekundy
        //teraz thread1 będzie się wykonywał RÓWNOLEGLE z wątkiem main

        thread1.join(); //nasz główny wątek main będzie czekał aż
        //wątek thread1 zakończy swoje działanie i dopiero wtedy
        //wywoła w terminalu info
        //wątek main, w momencie kiedy będzie czekał na inny wątek
        //będzie miał właśnie ten state waiting
        System.out.println("Main thread.");
    }
}

//waiting gdy wątek czeka, aż inny wątek wykona swoją pracę.
//Sytuacja ta powstaje z wykorzystaniem metody obj.wait()
// lub thread.join()