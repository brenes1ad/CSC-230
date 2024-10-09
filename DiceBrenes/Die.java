package DiceBrenes;

/**
 * Represents one die (singular of dice) with faces showing between 1 and max value
 */
public class Die {
    private final int MAX;  // maximum face value
    private int faceValue;  // current value shown on die

    /**
     * @param max number of faces on the die. Must be at least 4; otherwise a 6-sided die is created.
     */
    public Die(int max) {
        if (max >= 4) {
            MAX = max;
        } else {
            MAX = 6;
        }
        roll();
    }

    public Die(){
        MAX = 6;
        roll();
    }


    /**
     *
     * Roll die to produce random value.
     *
     * @return random value between 1 and max face value of die.
     */
    public int roll () {
        faceValue = (int) (Math.random() * MAX) + 1;
        return faceValue;
    }

    public String toString() {
        return Integer.toString(faceValue);
    }

    /**
     * main() tests a single die to make sure it works
     * @param args -- null argument list ignored
     */
    public static void main(String[] args) {
        Die die1 = new Die();
        System.out.println(die1);
    }
}
