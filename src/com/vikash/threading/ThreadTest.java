package com.vikash.threading;

public class ThreadTest extends Thread
{
    public void run()
    {
        for(int i = 0; i < 3; i++)
        {
            System.out.println("A");
            System.out.println("B");
        }
    }
}
class ThreadDem extends Thread
{
    public void run()
    {
        for(int i = 0; i < 3; i++)
        {
            System.out.println("C");
            System.out.println("D");
        }
    }
    public static void main(String args[])
    {
        ThreadTest t1 = new ThreadTest();
        ThreadDem t2 = new ThreadDem();
        t1.start();
        t2.start();
    }
}
