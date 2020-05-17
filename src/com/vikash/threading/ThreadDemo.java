package com.vikash.threading;

public class ThreadDemo implements Runnable
{
    String x, y;
    public void run()
    {
        for(int i = 0; i < 10; i++)
            synchronized(this)
            {
                x = "Hello";
                y = "Java";
                System.out.print(x + " " + y + " ");
            }
    }
    public static void main(String args[])
    {
        ThreadDemo run = new ThreadDemo ();
        Thread obj1 = new Thread(run);
        Thread obj2 = new Thread(run);
        obj1.start();
        obj2.start();
    }
}
