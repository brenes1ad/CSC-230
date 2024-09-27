package PigInterfaceBrenes;
import PigBrenes.PairOfDice;

public interface Player {
    PairOfDice d = new PairOfDice();
    public boolean timeToQuit();
    public String toString();
    public int getRoundScore();
    public int getTotalScore();
    public void rollDice();
    public boolean isTurn();
    public void setTurn(boolean t);
    public void setRoundScore(int s);
    public void setTotalScore(int s);
}
