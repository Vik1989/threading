package com.vikash.threading;

public class PrintAlternate {

    public Object lock = new Object();

    public PrintAlternate() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        synchronized (lock) {
            printFirst.run();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {

         Object object= new Object();

         PrintAlternate printAlternate = new PrintAlternate();

         Runnable runnable1 = () ->
                 System.out.println("First");


        Runnable runnable2 = ()->
                System.out.println("Second");


        Runnable runnable3 = () ->
                System.out.println("Third");


        printAlternate.first(runnable1);
        printAlternate.second(runnable2);
        printAlternate.third(runnable3);

    }
}
