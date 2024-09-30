package PigInterfaceBrenes;

/**
 * ComputerPlayer -- handles computer decision and has needed methods for gameplay
 */

public class ComputerPlayer implements Player{
    private int roundScore;
    private int totalScore;
    private boolean computerTurn;
    private boolean playing;

    /**
     * Default constructor for computer player
     */
    ComputerPlayer() {
        roundScore = 0;
        totalScore = 0;
        computerTurn = false;
    }

    /**
     *
     * @return uses given rules to determine if the computer should quit their turn
     */
    public boolean timeToQuit(){
        if (roundScore > 20){
             totalScore += roundScore;
             roundScore = 0;
             System.out.println("Total Score: " + totalScore + "\n");
             computerTurn = false;
            return true;
        }
        return false;
    }

    /**
     * Rolls the pair of dice shared for human and computer players
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
        return computerTurn;
    }

    /**
     *
     * @param t -- used to set player turn to True or False during gameplay
     */
    public void setTurn(boolean t){
         computerTurn = t;
    }

    /**
     *
     * @param s -- used to increment or reset round score based on gameplay interactions
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
     * @return -- private variable to tell if player is actively playing, used to help change activePlayer
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
}
