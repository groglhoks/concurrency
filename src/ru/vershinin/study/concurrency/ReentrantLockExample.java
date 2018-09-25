package ru.vershinin.study.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    public static void main(String[] args) {

        CommonResource commonResource = new CommonResource();
        ReentrantLock reentrantLock = new ReentrantLock();

        for(int i=0; i<5; i++) {
            Thread thread = new Thread(new CountTread(commonResource, reentrantLock));
            thread.setName(String.valueOf(i));
            thread.start();
        }

    }

}

class  CommonResource {
    int x=0;
}

class CountTread implements Runnable {
    CommonResource commonResource;
    ReentrantLock reentrantLock;

    CountTread(CommonResource commonResource, ReentrantLock reentrantLock) {
        this.commonResource = commonResource;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {

        reentrantLock.lock();

        try {
            commonResource.x = 1;

            for (int i=0; i<=5; i++) {
                System.out.printf("thread: %s iter: %d \n", Thread.currentThread().getName(), i);
                Thread.sleep(100);
            }

        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            reentrantLock.unlock();
        }


    }
}