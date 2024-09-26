package PigInterfaceBrenes;
import java.util.Scanner;
import PigBrenes.PairOfDice;



public class HumanPlayer implements Player {
    private int roundScore;
    private int totalScore;
    private PairOfDice d;
    private boolean playerTurn;
    private Scanner scan;

    public HumanPlayer(){
        roundScore = 0;
        totalScore = 0;
        d = new PairOfDice();
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
                return true;
            default:
                System.out.println("Invalid Answer");
                return false;
        }
    }

    public static void main(String[] args){
        HumanPlayer p = new HumanPlayer();
        p.timeToQuit();
    }
}
