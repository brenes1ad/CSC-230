package Day5InClass;

/*
  LectureStuff.java for Monday 9/9/2024
  CSC230 F24
  Tony Brenes
 */

import java.util.ArrayList;
import java.util.Scanner;
public class LectureStuff {

    public static void main(String[] args){
        System.out.println("We Have " + args.length + " arguments");
        for (String arg : args) {
            System.out.println(arg);
        }

        int[] myVals = new int[10];
        System.out.println("We Have " + myVals.length + " elements");
        for (int i = 0; i < myVals.length; i++) {
            myVals[i] = i + 1;
        }
        for (int val : myVals) {
            System.out.println(val);
        }

        ArrayList<Integer> myArrList = new ArrayList<>();
        System.out.println("myArrList has " + myArrList.size() + " elements");
        for (int i = 0; i < 10; i++) {
            myArrList.add(i);
        }
        System.out.println("myArrList is: ");
        for (int i = 0; i < myArrList.size(); i++) {
            System.out.println(myArrList.get(i));
        }

        Scanner sc = new Scanner("1 2 3 4 YY5 6 7 8 9");
        while (sc.hasNext()){
            if (sc.hasNextInt())
                System.out.println(sc.nextInt());
            else {
                System.out.println("NOPE");
                sc.next();
            }
        }

        Scanner sc2 = new Scanner(System.in);
        while (sc2.hasNext()){
            System.out.println(sc2.next());
        }

    }

}
