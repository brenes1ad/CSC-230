package DiceBrenes;

import java.util.ArrayList;

public class Handful {
    private ArrayList<Die> dice = new ArrayList<>();
    private final int FACES;

    public Handful(int number, int faces){
        FACES = faces;
        for(int i = 0; i < number; i++){
            dice.add(new Die(faces));
        }
    }
    public void rollHandfull(){
        int counter = 1;
        System.out.println("Rolling " + dice.size() + "d" + FACES);
        System.out.println("-------------------");
        for(Die d : dice) {
            d.roll();
            System.out.println("Die " + counter + " rolled: " + d);
            counter++;
        }
    }

    public static void main(String[] args){
        Handful handful = new Handful(96, 6);
        handful.rollHandfull();
    }
}
