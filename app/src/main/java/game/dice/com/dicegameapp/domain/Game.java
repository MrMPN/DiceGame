package game.dice.com.dicegameapp.domain;

public class Game {

	private Dice dice1=new Dice();
	private Dice dice2=new Dice();
	
	public Game(){
		
	}
	
	public boolean playGame() {
		dice1.rollDice();
		dice2.rollDice();
		return hasWon();
	}
	
	private int getSumDices(){
		return dice1.getValue()+dice2.getValue();	
	}
	
	private boolean hasWon() {
		return getSumDices()==7;
	}

	public int[] getValueDices(){
		return new int[]{dice1.getValue(), dice2.getValue()};
	}
}
