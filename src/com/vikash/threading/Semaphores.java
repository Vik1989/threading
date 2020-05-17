package com.vikash.threading;

import java.util.concurrent.Semaphore;

public class Semaphores {

    public static void main(String[] args) throws InterruptedException {
        Semaphores.example1();
    }

    private static void example1() throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {

                    semaphore.acquire();
                    throw new RuntimeException("Exception thrown");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        });

        t1.start();
        // Wait for the bad thread to go belly-up
        Thread.sleep(1000);
        Thread t2 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
                System.out.println("Thread 2");
            }
        });
        t2.start();
        t1.join();
        t2.join();
    }
}
