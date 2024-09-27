package PigInterfaceBrenes;
import java.util.Scanner;

public class PigGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HumanPlayer player1 = new HumanPlayer();
        Player player2 = null;
        Player currentPlayer = player1;

        while (true) {
            System.out.println("Would you like to play against another human or a computer? (H, C)");
            String playerType = sc.next().toUpperCase();
            switch (playerType) {
                case "H":
                    player2 = new HumanPlayer();
                    player2.setTurn(false);
                    break;
                case "C":
                    player2 = new ComputerPlayer();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;

            }
            if (playerType.equalsIgnoreCase("H") || playerType.equalsIgnoreCase("C")) {
                break;
            }
        }
        while (currentPlayer.getTotalScore() < 100) {
            boolean quit = currentPlayer.timeToQuit();
            if (!quit && currentPlayer.isTurn()) {
                currentPlayer.rollDice();
                if (currentPlayer.d.getDie1Value() != 1 && currentPlayer.d.getDie2Value() != 1) {
                    currentPlayer.setRoundScore(currentPlayer.getRoundScore() + currentPlayer.d.sum());
                    System.out.println("Round Score: " + currentPlayer.getRoundScore());
                } else if (currentPlayer.d.getDie1Value() == 1 && currentPlayer.d.getDie2Value() == 1) {
                    currentPlayer.setTotalScore(0);
                    currentPlayer.setTurn(false);
                    System.out.println("Total Score: " + currentPlayer.getTotalScore() + "\n");
                } else if (currentPlayer.d.getDie1Value() == 1 || currentPlayer.d.getDie2Value() == 1) {
                    currentPlayer.setRoundScore(0);
                    currentPlayer.setTurn(false);
                    System.out.println("Total Score: " + currentPlayer.getTotalScore() + "\n");
                }
            } else{
                currentPlayer.setTotalScore(currentPlayer.getTotalScore() + currentPlayer.getRoundScore());
                System.out.println("Switching Players" + "\n");
                if (player1.isTurn()){
                    player1.setTurn(false);
                    currentPlayer = player2;
                    player2.setTurn(true);
                } else{
                    player2.setTurn(false);
                    currentPlayer = player1;
                    player1.setTurn(true);
                }
            }
            if (player1.getTotalScore() >= 100){
                System.out.println("Player 1 Wins!");
                break;
            } else if(player2.getTotalScore() >= 100){
                System.out.println("Player 2 Wins!");
                break;
            }
        }
    }
}

