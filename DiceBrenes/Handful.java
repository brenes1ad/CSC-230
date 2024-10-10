package DiceBrenes;
import java.util.ArrayList;

/**
 * Represents a handful of dice.
 * ------------
 * Doesn't use inheritance or an interface because I don't feel like it needs it. This is a has-a relationship,
 * a handful has a die(many) which doesn't call for parent child relationships of the dice and handfuls. I could
 * see potentially using an interface since they both share roll and sum methods, but that seems to excess for
 * two methods, especially since the die class kind of acts as a driver for the handful and after you make the
 * handful class, you really don't need to make a singular die anymore.
 */
public class Handful {
    private ArrayList<Die> dice = new ArrayList<>();
    private final int FACES;

    /**
     *  The ArrayList and final int FACES are both set to private because neither of those should be accessed
     *  outside the class, they really shouldn't be accessed outside the couple methods written that use them.
     * @param number amount of dice wanted in handful
     * @param faces size of the dice, number of faces each die has
     */
    public Handful(int number, int faces){
        FACES = faces;
        for(int i = 0; i < number; i++){
            dice.add(new Die(faces));
        }
    }

    /**
     * Rolls each die in the handful and prints out the number of the die and its rolled value.
     * -----------------------------------------------------------
     * Without knowing the full intended use of this class and methods, I feel like it's practical to keep
     * the rolling method public so anyone/anywhere can roll and handful that can also be created by
     * anyone/anywhere. This is the primary function of the handful so it should be public.
     */
    public void rollHandful(){
        int counter = 1;
        System.out.println("Rolling " + dice.size() + "d" + FACES);
        System.out.println("-------------------");
        for(Die d : dice) {
            d.roll();
            System.out.println("Die " + counter + " rolled: " + d);
            counter++;
        }
    }

    /**
     * Same as the roll method, without knowing where and how this class would be used, it makes sense
     * that anyone/anywhere can get the sum of all the dice. Since this changes nothing about any values of
     * the dice or the handful, I wouldn't ever see a reason that it couldn't be public.
     *
     * @return sum of each face value of the dice in the handful.
     */
    public int sum(){
        int total = 0;
        for(Die d : dice) {
            total += d.getFaceValue();
        }
        return total;
    }

    /**
     * Simple class used to create handful, test the rolling method for actually rolling and for printing
     * accuracy and tests the sum method to insure it sums all the dice correctly.
     * @param args default parameters for main method
     */
    public static void main(String[] args){
        Handful handful = new Handful(6, 6);
        handful.rollHandful();
        System.out.println(handful.sum());
    }
}
