package com.vikash.threading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MorganStanleyTest {

    public static void main(String[] args) {

        List<Integer> px = new ArrayList<>();

        px.add(6);
        px.add(7);
        px.add(9);
        px.add(5);px.add(6);
        px.add(3);
        px.add(2);


        System.out.println(maxDifference(px));
    }

    public static int maxDifference(List<Integer> px) {
        // Write your code here
        int k = 1;
        int maxDiff = 0;
        int i = 1;

        while(i < px.size()){
            int tempDiff = 0 ;
            while(i-k>=0 && px.get(i) > px.get(i-k) ){
                tempDiff = px.get(i) - px.get(i-k);
                k++;
            }
            if(tempDiff > maxDiff)
                maxDiff = tempDiff;
            i++;
        }
        return maxDiff;
    }
}
