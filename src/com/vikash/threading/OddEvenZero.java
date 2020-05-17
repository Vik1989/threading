package com.vikash.threading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenZero {

    private final Integer n;

    Semaphore lockZero = new Semaphore(1);
    Semaphore lockOdd = new Semaphore(0);
    Semaphore lockEven = new Semaphore(0);

    public OddEvenZero(int i) {
        this.n = i;
    }

    public void printZero() throws InterruptedException {

        for(int i = 0; i <= n; ++i) {

            lockZero.acquire();

            System.out.print(0);
            if(i%2 != 0){
                lockEven.release();
            }else {
                lockOdd.release();
            }
        }

    }

    public void printOdd() throws InterruptedException {

        for(int i =1; i <= n; i=i+2) {
            lockOdd.acquire();
            System.out.print(i);
            lockZero.release();
        }

    }

    public void printEven() throws InterruptedException {

        for(int i =2; i <= n; i=i+2) {
            lockEven.acquire();
            System.out.print(i);
            lockZero.release();
        }
    }

    public static void main(String[] args) {

        OddEvenZero oddEvenZero = new OddEvenZero(5);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        oddEvenZero.printZero();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        oddEvenZero.printOdd();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        oddEvenZero.printEven();
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
