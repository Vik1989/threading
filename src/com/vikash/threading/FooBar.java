package com.vikash.threading;

public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws InterruptedException {

        boolean bar = false;

        ThreadMain threadMain = new ThreadMain(true);

        Thread thread1 = new Thread(() -> {
            while(true) {
                try {
                    threadMain.printBar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BarThread");

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    threadMain.printFoo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Foo-Thread");
        thread1.start();
        thread2.start();
    }
}

class ThreadMain{

    private boolean flag;
    Object lock = new Object();

    public ThreadMain(Boolean flag){
        this.flag = flag;
    }
    public void printFoo() throws InterruptedException {
        synchronized (lock){
            while( flag == true) {
                System.out.println("Waiting Foo");
                lock.wait();
            }
            System.out.println("Entered Foo method in Thread Main.");
            flag = true;
            lock.notifyAll();
        }

    }

    public void printBar() throws InterruptedException {
        synchronized (lock) {
            while (flag == false) {
                System.out.println("Waiting Bar");
                lock.wait();
            }
            System.out.println("Entered Bar method in Thread Main.");
            flag = false;
            lock.notifyAll();
        }
    }
}
