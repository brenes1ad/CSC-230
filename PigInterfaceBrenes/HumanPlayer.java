package PigInterfaceBrenes;
import java.util.Scanner;
import PigBrenes.PairOfDice;



public class HumanPlayer implements Player {
    private int roundScore;
    private int totalScore;
    private boolean playerTurn;
    private Scanner scan;

    public HumanPlayer(){
        roundScore = 0;
        totalScore = 0;
        playerTurn = true;
        scan = new Scanner(System.in);
    }

    public int getTotalScore() {return totalScore;}

    public int getRoundScore() {return roundScore;}

    public boolean timeToQuit(){
        System.out.println("Would you like to roll again (Y, N)");
        String ans = scan.nextLine().toLowerCase();
        switch(ans){
            case "y":
                return false;
            case "n":
                totalScore += roundScore;
                System.out.println("Total Score: " + totalScore + "\n");
                roundScore = 0;
                return true;
            default:
                System.out.println("Invalid Answer. Ending Turn");
                return true;
        }
    }

    public void rollDice() {
        d.roll();
        System.out.println(d.getDie1Value() + " & " + d.getDie2Value());
    }

    public boolean isTurn(){
        return playerTurn;
    }
    public void setTurn(boolean t){
        playerTurn = t;
    }

    public void setRoundScore(int s){
        roundScore = s;
    }

    public void setTotalScore(int s){
        totalScore = s;
    }


    public static void main(String[] args){
        HumanPlayer p = new HumanPlayer();
        p.timeToQuit();
    }
}
