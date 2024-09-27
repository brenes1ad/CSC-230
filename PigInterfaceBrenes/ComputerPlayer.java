package PigInterfaceBrenes;
import PigBrenes.PairOfDice;

public class ComputerPlayer implements Player{
    private int roundScore;
    private int totalScore;
    private boolean computerTurn;

    ComputerPlayer() {
        roundScore = 0;
        totalScore = 0;
        computerTurn = false;
    }

    public int getTotalScore() {return totalScore;}

    public int getRoundScore() {return roundScore;}

    public boolean timeToQuit(){
        return roundScore >= 20;
    }

    public void rollDice() {
        d.roll();
        System.out.println(d.getDie1Value() + " & " + d.getDie2Value());
    }

    public boolean isTurn(){
        return computerTurn;
    }

    public void setTurn(boolean t){
         computerTurn = t;
    }

    public void setRoundScore(int s){
        roundScore = s;
    }

    public void setTotalScore(int s){
        totalScore = s;
    }
}
