package PigBrenes;

/**
 * Computer Player class -- handles computer player decisions and mechanics
 */

public class ComputerPlayer {
    private int roundScore;
    private int totalScore;
    private PairOfDice d;
    private boolean computerTurn;

    /**
     * Default constructor for computer player
     */
    public ComputerPlayer() {
        roundScore = 0;
        totalScore = 0;
        d = new PairOfDice();
        computerTurn = false;
    }

    /**
     *
     * @return return computerPlayer's total game score
     */
    public int getTotalScore() {return totalScore;}

    /**
     *
     * @return boolean to know if it's computer's turn or not
     */
    public boolean getComputerTurn() {return computerTurn;}

    /**
     *
     * @param t used to set computerTurn truth value during gameplay
     */
    public void setComputerTurn(boolean t){
        computerTurn = t;
    }

    /**
     * Main gameplay function for computer player, handles given computer decisions and game dice interactions.
     */
    public void computerPlay(){
        while (roundScore < 20){
            d.roll();
            System.out.println("Computer roll: " + d);
            if(d.getDie1Value() != 1 && d.getDie2Value() != 1){
                roundScore += d.sum();
                System.out.println("Computer Round Score: " + roundScore);
            }
            if(d.getDie1Value() == 1 && d.getDie2Value() == 1){
                roundScore = 0;
                totalScore = 0;
                computerTurn = false;
                break;
            }
            if(d.getDie1Value() == 1 || d.getDie2Value() == 1){
                roundScore = 0;
                computerTurn = false;
                break;
            }
        }
        computerTurn = false;
        totalScore += roundScore;
        roundScore = 0;
        System.out.println("Computer Total Score: " + totalScore + "\n");
    }

    /**
     * Test bed for computerPlayer class
     * @param args default main arguments
     */
    public static void main(String[] args) {
        ComputerPlayer cp = new ComputerPlayer();
        while (cp.getTotalScore() < 100 && cp.getComputerTurn()){
            cp.computerPlay();
        }
    }
}
