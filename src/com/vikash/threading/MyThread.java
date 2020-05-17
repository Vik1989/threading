package com.vikash.threading;

public class MyThread extends Thread {
    MyThread() {
        System.out.print(" MyThread");
    }

    public void run() {
        System.out.print(" bar");
    }

    public void run(String s) {
        System.out.print(" baz");
    }
}

 class TestThreads {
    public static void main(String[] args) {
        Thread t = new MyThread() {
            public void run() {
                System.out.print(" foo");
            }
        };
        t.start();
    }
}
