package com.vikash.threading;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        int philosophers = 5;

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

        Semaphore[] forkSemaphore = new Semaphore[philosophers];

        int j = 0;

        for(Semaphore s : forkSemaphore){
            s = new Semaphore(1);
            forkSemaphore[j++] = s;
        }

        for (int i = 0; i < philosophers; i++) {
            int finalI = i;
            Thread eatThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        diningPhilosophers.activatePhilosopher(finalI,forkSemaphore);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            eatThread.start();
        }
    }

    void activatePhilosopher(int finalI, Semaphore[] forkSemaphore) throws InterruptedException {
        while(true){
            contemplate();
            eat(finalI,forkSemaphore);
        }
    }
    void contemplate() throws InterruptedException {
        Thread.sleep(random.nextInt(50));
    }
    public void eat(int i, Semaphore[] forkSemaphore){

        try {

            pickLeft(i,forkSemaphore);
            pickRight((i+1)%5,forkSemaphore);
            eatFood(i,forkSemaphore);
            keepLeft(i,forkSemaphore);
            keepRight((i+1)%5,forkSemaphore);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void pickRight(int i, Semaphore[] forkSemaphore) throws InterruptedException {

        forkSemaphore[i].acquire();
    }

    private void pickLeft(int i, Semaphore[] forkSemaphore) throws InterruptedException {
        forkSemaphore[i].acquire();
    }

    private void keepRight(int i, Semaphore[] forkSemaphore) {
        forkSemaphore[i].release();
    }

    private void keepLeft(int i, Semaphore[] forkSemaphore) {
        forkSemaphore[i].release();
    }

    private void eatFood(int i, Semaphore[] forkSemaphore) throws InterruptedException {
        System.out.println("Philosopher "+ i + "Eating now");
        Thread.sleep(20);
    }
}