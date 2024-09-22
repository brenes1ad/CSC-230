package PigBrenes;

import java.util.Scanner;

public class Pig {


    public static void main(String[] args) {
	// write your code here
        HumanPlayer human = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer();

        while(human.getTotalScore() < 100 || computer.getTotalScore() < 100) {
            if(human.getPlayerTurn()){
                human.play();
                if (!human.getPlayerTurn()){
                    computer.setComputerTurn(true);
                }
            }else {
                computer.computerPlay();
                if (!computer.getComputerTurn()){
                    human.setPlayerTurn(true);
                }
            }
            if (computer.getTotalScore() >= 100) {
                System.out.println("Computer wins!");
                break;
            } else if (human.getTotalScore() >= 100) {
                System.out.println("Human wins!");
                break;
            }
        }
        
    }
}
