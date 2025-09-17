package org.example;

class TestNewThread extends Thread {
    //każdy wątek otrzyma inną wartość, na ile czasu będzie się usypiał
    private int sleepTime;

    public TestNewThread(String name, int sleepTime) {
        super(name);
        this.sleepTime = sleepTime;
    }

    //musimy przesłonić metodę run
    public void run(){
        //w momencie kiedy uruchomimy wątek za pomocą
        //metody start to ta metoda zacznie działać równolegle
        //do naszego głównego wątku
        for(int i=0; i<10; i++){
            System.out.println("Wątek" + this.getName() + "wartość i: " + i);
            //po każdym wywołaniu uśpimy wątek na sekundę
            try {
                this.sleep(this.sleepTime);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class ThreadExtendExample {
    public static void main(String[] args) {
        //Musimy tu stworzyć egzemplarz naszej nowej klasy TestNewThread
        TestNewThread thread1 = new TestNewThread("thread1", 700);
        thread1.start(); //automatycznie startuje osobny wątek

        //Tworzymy kolejny egzemplarz! Skoro możemy nadać wątkowi nazwę
        //będziemy wiedzieć który jest który
        TestNewThread thread2 = new TestNewThread("thread2", 1000);
        thread2.start();
    }
}
//wątek to wydzielony kawałek aplikacji, który
//działa niezależnie od głównego wątku programu
