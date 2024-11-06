package Nov6Notes;

import java.util.ArrayList;
import java.util.function.Consumer;

public class PlayWithLambdas {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.forEach(n -> System.out.println(n));
        System.out.println(list);
        ArrayList<Boolean> list2 = new ArrayList<>();
        list.forEach(n -> list2.add(n % 2 == 0));
        System.out.println(list2);

        list.removeIf(n -> n % 2 == 0);
        System.out.println(list);
    }

    static class ListEater implements Consumer<Integer> {
        public void accept(Integer i) {
            System.out.println("Eating Integer: " + i);
        }
    }
}
