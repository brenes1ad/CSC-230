package Nov6Notes;

import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;

class Dog{
    static Random rand = new Random();
    static int number = 0;
    int age;
    int ID;
    Dog(){
        ID  = ++number;
        age = rand.nextInt(20);
    }

    public String toString(){
        return "(" + ID + " " + age + ")";
    }
}

public class SortWithLambdas {
    public static void main(String[] args) {
        ArrayList<Dog> dogs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dogs.add(new Dog());
        }
        System.out.println(dogs);
        dogs.sort((d1, d2) -> d1.age - d2.age);
        System.out.println(dogs);
    }

    static class DogComparator implements Comparator<Dog>{
        @Override
        public int compare(Dog o1, Dog o2) {
            return o1.age - o2.age;
        }
    }
}
