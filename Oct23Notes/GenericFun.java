package Oct23Notes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import java.util.HashMap;
import java.util.Hashtable;

public class GenericFun {

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Double> arr = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            arr.add(rand.nextDouble());
        }

        for(Double d : arr){
            System.out.print(d + ", ");
        }
        System.out.println();

        Collections.sort(arr, new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                double d1 = o1;
                double d2 = o2;
                if (d1 > d2){
                    return -1;
                } else if (d1 < d2){
                    return 1;
                } else{
                    return 0;
                }
            }
        });

        for(Double d : arr){
            System.out.print(d + ", ");
        }
        System.out.println();

        HashMap<String, Integer> map = new HashMap<>();
        String []animals = {"dog", "cat", "horse", "pig", "bird"};
        int ID = 0;
        for (String animal : animals) {
            map.put(animal, ID++);
        }

        System.out.println("The value of 'dog' is:  " + map.get("dog"));
        System.out.println("The value of 'pig' is: " + map.get("pig"));
        System.out.println("'horse' is in the table: " + map.containsKey("horse"));
        System.out.println("'walrus' is in the table: " + map.containsKey("walrus"));
        System.out.println("'walrus's' value is:  " + map.get("walrus"));

        System.out.println("id 2 in map: " + map.containsValue(2));
        System.out.println("id 12 in map: " + map.containsValue(12));

        Hashtable<String, Integer> hash = new Hashtable<>();
        hash.put("Car", null);
    }

}
