package PigBrenes;

public class ComputerPlayer {
    private int roundScore;
    private int totalScore;
    private PairOfDice d;
    private boolean computerTurn;

    public ComputerPlayer() {
        roundScore = 0;
        totalScore = 0;
        d = new PairOfDice();
        computerTurn = false;
    }

    public int getRoundScore() {return roundScore;}

    public int getTotalScore() {return totalScore;}

    public boolean getComputerTurn() {return computerTurn;}

    public void setComputerTurn(boolean t){
        computerTurn = t;
    }

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

    public static void main(String[] args) {
        ComputerPlayer cp = new ComputerPlayer();
        while (cp.getTotalScore() < 100 && cp.getComputerTurn()){
            cp.computerPlay();
        }
    }
}
