package PigInterfaceBrenes;
import java.util.Scanner;

/**
 * HumanPlayer -- handles human player decisions and holds needed methods for gameplay
 */

public class HumanPlayer implements Player {
    private int roundScore;
    private int totalScore;
    private boolean playerTurn;
    private Scanner scan;
    private boolean playing;

    /**
     * Default constructor for human player
     */
    public HumanPlayer(){
        roundScore = 0;
        totalScore = 0;
        playerTurn = true;
        scan = new Scanner(System.in);
    }


    /**
     *
     * @return Uses player input to determine if the human player would like to end their turn
     */
    public boolean timeToQuit(){
        System.out.println("Would you like to roll? (Y, N)");
        String ans = scan.nextLine().toLowerCase();
        switch(ans){
            case "y":
                return false;
            case "n":
                totalScore += roundScore;
                System.out.println("Total Score: " + totalScore + "\n");
                roundScore = 0;
                playerTurn = false;
                return true;
            default:
                System.out.println("Invalid Answer. Ending Turn");
                return true;
        }
    }

    /**
     * Rolls the pair of ice shared for human and computer players
     */
    public void rollDice() {
        d.roll();
        System.out.println(d.getDie1Value() + " & " + d.getDie2Value());
    }

    /**
     *
     * @return private variable determining state of player turn
     */
    public boolean isTurn(){
        return playerTurn;
    }

    /**
     *
     * @param t -- used to set player turn to True or False during gameplay
     */
    public void setTurn(boolean t){
        playerTurn = t;
    }

    /**
     *
     * @param s used to increment or reset round score based on gameplay interactions
     */
    public void setRoundScore(int s){
        roundScore = s;
    }

    /**
     *
     * @param s -- used to increment or reset total score based on gameplay interactions
     */
    public void setTotalScore(int s){
        totalScore = s;
    }

    /**
     *
     * @return private variable to tell if player is actively playing, used ot help change activePlayer
     */
    public boolean isPlaying(){return playing;}

    /**
     *
     * @param p -- used to change the activePlayer state
     */
    public void setPlaying(boolean p){playing = p;}

    /**
     *
     * @return total player score
     */
    public int getTotalScore() {return totalScore;}

    /**
     *
     * @return score for player during each round of gameplay
     */
    public int getRoundScore() {return roundScore;}


    /**
     * Helper method to test the timeToQuit function.
     * @param args default main method argument
     */
    public static void main(String[] args){
        HumanPlayer p = new HumanPlayer();
        p.timeToQuit();
    }
}
