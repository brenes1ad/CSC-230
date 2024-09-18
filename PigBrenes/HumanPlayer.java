package PigBrenes;
import java.util.Scanner;

public class HumanPlayer {
    private int roundScore;
    private int totalScore;
    private PairOfDice d;
    private Scanner scan;
    private boolean playerTurn;


    public HumanPlayer(){
        roundScore = 0;
        totalScore = 0;
        d = new PairOfDice();
        scan = new Scanner(System.in);
        playerTurn = true;
    }

    public int getRoundScore() {return roundScore;}

    public boolean getPlayerTurn() {return playerTurn;}

    public void play(){
        System.out.println("Would you like to roll? (Y, N)");
        String ans = scan.next().toLowerCase();
        switch(ans){
            case "y":
                d.roll();
                System.out.println(d);
                if(d.getDie1Value() != 1 && d.getDie2Value() != 1){
                    roundScore += d.sum();
                    System.out.println("Round Score: " + roundScore);
                }
                if(d.getDie1Value() == 1 && d.getDie2Value() == 1){
                    totalScore = 0;
                    playerTurn = false;
                    System.out.println("Total Score: " + totalScore);

                }
                if(d.getDie1Value() == 1 || d.getDie2Value() == 1){
                    roundScore = 0;
                    playerTurn = false;
                    System.out.println("Total Score: " + totalScore);

                }
                break;
            case "n":
                totalScore += roundScore;
                playerTurn = false;
                System.out.println("Total Score: " + totalScore);
                break;
            default:
                System.out.println("Invalid Choice");
                break;


        }
    }
    public static void main(String[] args) {
        HumanPlayer p = new HumanPlayer();
        while(p.getRoundScore() < 100 && p.playerTurn){
            p.play();
        }

    }
}
