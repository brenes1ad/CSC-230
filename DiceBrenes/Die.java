package DiceBrenes;

/**
 * Represents one die (singular of dice) with faces showing between 1 and max value
 * -----------------------------
 * Everything in this class has default protection(package protection) because I feel like
 * now that a handful class is written and functioning, there is no reason to ever use the die class on its own.
 * All the methods are used by the handful class so they can't have private status and that also means this
 * can't just be an abstract class for a handful, since the handful is a collection of dice objects.
 */
public class Die {
    private final int MAX;  // maximum face value
    private int faceValue;  // current value shown on die

    /**
     * @param max number of faces on the die. Must be at least 4; otherwise a 6-sided die is created.
     */
     Die(int max) {
        if (max >= 4) {
            MAX = max;
        } else {
            MAX = 6;
        }
        roll();
    }


    /**
     *
     * Roll die to produce random value.
     *
     * @return random value between 1 and max face value of die.
     */
     int roll () {
        faceValue = (int) (Math.random() * MAX) + 1;
        return faceValue;
    }

    public String toString() {
        return Integer.toString(faceValue);
    }

    /**
     *
     * @return face value of die
     */
     int getFaceValue() {return faceValue;}

    /**
     * main() tests a single die to make sure it works
     * @param args -- null argument list ignored
     */
    public static void main(String[] args) {
        Die die1 = new Die(6);
        System.out.println(die1);
    }
}
