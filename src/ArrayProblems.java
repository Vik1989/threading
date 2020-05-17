
import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

    public static void main(String[] args) {

        Integer[] array = {1, 2, 2, 1, 2, 1, 4};

        Map<Integer,Integer> integerMap = new HashMap<>();

        for(Integer i : array){

            if(integerMap.containsKey(i)){

                integerMap.put(i,integerMap.get(i)+1);
            }else {
                integerMap.put(i,1);
            }
        }


    }
}
