package org.example;

//Nowy wątek jest utworzony, ale jeszcze nie rozpoczął swojej pracy
//ponieważ nie była wywołana metoda start()

public class ThreadStateCycleNew {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Working thread");
            }
        });

        //thread.start();
        System.out.println(thread.getState()); //NEW
    }
}



//Życie wątku można podzielić na kilka etapów,
//odpowiadają statycznemu polu State(typ wyliczeniowy)
//NEW - nowo utworzony wątek, który jeszcze nie rozpoczął swojej pracy
//RUNNABLE - działający wątek albo gotowy na rozpoczęcie pracy, ale
//czekający na zasoby
//BLOCKED - oczekuje na zablokowany zasób aby uzyskać do niego dostęp.
//Odbywa się za pomocą monitorów służących do synchronizacji wątków
//WAITING - czeka na inny wątek aż zakończy swoją pracę bez limitu
//pracy
//TIMED_WAITING - uśpiony wątek oczekuje określony czas np. po wywołaniu
//metody sleep()
//TERMINATED - wątek zakończył swoją pracę