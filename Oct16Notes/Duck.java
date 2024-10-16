package Oct16Notes;

import java.util.ArrayList;

public class Duck {
    private String name;
    static int population = 0;
    private int duckNumber;

    public Duck(){
        population += 1;
        duckNumber = population;
        name = "Donald Duck";
    }

    public Duck(int age){
        population += 1;
        duckNumber = population;
        name = age > 5 ? "Old Duck" : "Young Duck";
    }

    public Duck(String name){
        this.name = name;
        population += 1;
        duckNumber = population;
        System.out.println("I am a Duck. Quack!");
    }

    public static int printPopulation(){
        return population;
    }

    public static void main(String[] args){
        Duck d = new Duck("Hoover");
        System.out.println(d.name + " " + d.duckNumber);
        Duck d2 = new Duck();
        System.out.println(d2.name + " " + d2.duckNumber);
    }

    class Flock{
        ArrayList<Duck> ducks = new ArrayList<>();

        public Flock(){
        }
        public void add(Duck d){
            ducks.add(d);
        }

        public Duck getDuck(){
            return null;
        }

        public Duck getDuck(int duckNumber){
            return null;
        }

        public Duck getDuck(String name){
            return null;
        }

    }
}


