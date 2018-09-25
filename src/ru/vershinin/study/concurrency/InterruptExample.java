package ru.vershinin.study.concurrency;

class JThread extends Thread {

    JThread(String name){
        super(name);
    }

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        try{
            while(!isInterrupted()){
                System.out.println("Loop " + counter++);
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(getName() + " has been interrupted");
        }

        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
public class InterruptExample {

    public static void main(String[] args) {

        System.out.println("Main thread started...");
        JThread t = new JThread("JThread");
        t.start();
        try{
            Thread.sleep(150);
            t.interrupt();

            Thread.sleep(150);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}