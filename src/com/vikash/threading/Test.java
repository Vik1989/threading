package com.vikash.threading;

public class Test {

    public static void main(String[] args) {

            System.out.println("C");
            synchronized (args){
                System.out.println(2);
                try{
                    args.wait();
                }catch (InterruptedException e){

                }
            }
        System.out.println(3);
    }
}
