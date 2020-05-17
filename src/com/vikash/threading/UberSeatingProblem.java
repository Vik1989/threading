package com.vikash.threading;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberSeatingProblem {

    int democrats = 0;
    int republicans = 0;

    CyclicBarrier barrier = new CyclicBarrier(4);

    Semaphore demsWaiting = new Semaphore(0);
    Semaphore repWaiting = new Semaphore(0);

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        UberSeatingProblem uberSeatingProblem = new UberSeatingProblem();
        Set<Thread> allThreads = new HashSet<>();

        for(int i = 0; i <10; i++) {
            Thread democratThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        uberSeatingProblem.seatDemocrats();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }

            });
            democratThread.setName("democratthread "+ (i + 1));
            allThreads.add(democratThread);
            Thread.sleep(50);
        }

        for(int i = 0; i <14; i++) {
            Thread republicanThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        uberSeatingProblem.seatRepublicans();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            republicanThread.setName("republicanThread "+ (i + 1));
            allThreads.add(republicanThread);
            Thread.sleep(20);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
           t.join();
        }
    }

    private void seatDemocrats() throws InterruptedException, BrokenBarrierException {

        boolean rideLeader = false;
        lock.lock();
        democrats++;

        if(democrats == 4){

            demsWaiting.release(3);
            democrats -= 4;
            rideLeader = true;

        }else if (democrats ==2 && republicans >= 2){

           demsWaiting.release(1);
           repWaiting.release(2);
           democrats -= 2;
           republicans -= 2;
           rideLeader = true;

        }else{
            lock.unlock();
            demsWaiting.acquire();
        }

        seated();
        barrier.await();

        if(rideLeader){
            drive();
            lock.unlock();
        }
    }

    private void seatRepublicans() throws InterruptedException, BrokenBarrierException {


        lock.lock();
        republicans++;
        boolean rideLeader = false;

        if(republicans == 4){

            repWaiting.release(3);
            republicans -= 4;
            rideLeader = true;

        }else if (republicans ==2 && democrats >= 2){

            repWaiting.release(1);
            demsWaiting.release(2);
            democrats -= 2;
            republicans -= 2;
            rideLeader = true;

        }else{
            lock.unlock();
            repWaiting.acquire();
        }

        seated();
        barrier.await();

        if(rideLeader){
            drive();
            lock.unlock();
        }

    }

    private void drive() {
        System.out.println("Drive now !!" + Thread.currentThread().getName());
        System.out.flush();
    }

    private void seated() {
        System.out.println("Seated " + Thread.currentThread().getName());
        System.out.flush();
    }
}
