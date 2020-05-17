package com.vikash.threading;

public class ThreadWaitTest
{
    public static void main(String [] args)
    {
        System.out.print("1 ");
        synchronized(args)
        {
            System.out.print("2 ");
            try
            {
                args.wait(); /* Line 11 */
            }
            catch(InterruptedException e){
                System.out.println("3");
            }finally {
                args.notifyAll();
            }
        }
        System.out.print("3 ");
    }
}
