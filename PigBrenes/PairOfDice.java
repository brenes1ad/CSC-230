package PigBrenes;

/**
 * Represents two dice with faces showing between 1 and max value
 */

public class PairOfDice {
    private final int MAX;
    private Die die1 = new Die();
    private Die die2 = new Die();

    /**
     * Creates default instance of PairOfDice with both dice having 6 faces.
     */
    public PairOfDice(){
        MAX = 6;
        die1.roll();
        die2.roll();
    }

    /**
     *
     * @param max number of faces on the die. Must be at least 4; otherwise a 6-sided die is created.
     */
    public PairOfDice(int max){
        if (max >= 4) {
            MAX = max;
        } else {
            MAX = 6;
        }
        die1 = new Die(max);
        die2 = new Die(max);
        die1.roll();
        die2.roll();
    }

    /**
     *
     * @return the sum of both die's face values
     */
    public int sum(){
        return die1.getFaceValue() + die2.getFaceValue();
    }

    public String toString() {
        return (die1.getFaceValue()) + " & " + (die2.getFaceValue());
    }

    /**
     * rolls both dice to produce random values
     */
    public void roll(){
        die1.roll();
        die2.roll();
    }

    /**
     *
     * @return current face value of die1
     */
    public int getDie1Value(){return die1.getFaceValue();}

    /**
     *
     * @return current face value of die2
     */
    public int getDie2Value(){return die2.getFaceValue();}

    /**
     * tests the PairOfDice class to make sure it works
     * @param args -- null argument list ignored
     */
    public static void main(String[] args) {
        PairOfDice p = new PairOfDice();
        System.out.println(p);
        System.out.println(p.sum());
        p.roll();
        System.out.println(p);
        System.out.println(p.sum());
        System.out.println("Die1 Get Function: " + p.getDie1Value());
    }
}
