package com.vikash.threading;

public class OrderedPrinting {

    volatile int count = 1;

    public void printFirst() {

        synchronized (this) {
            System.out.println("First");
            count = 2;
            this.notifyAll();
        }
    }

    public void printSecond() throws InterruptedException {

        synchronized (this) {
            while (count != 2) {
                this.wait();
            }
            System.out.println("Second");
            count = 3;
            this.notifyAll();
        }
    }

    public void printThird() throws InterruptedException {

        synchronized (this) {
            while (count != 3) {
                this.wait();
            }
            System.out.println("Third");
            this.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        OrderedPrinting orderedPrinting = new OrderedPrinting();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                orderedPrinting.printFirst();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    orderedPrinting.printSecond();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    orderedPrinting.printThird();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
