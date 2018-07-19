package game.dice.com.dicegameapp.application.dto;

import game.dice.com.dicegameapp.domain.Game;

public class GameDTO {
    private int dice1;
    private int dice2;

    public GameDTO(Game game){
        this.dice1 = game.getValueDices()[0];
        this.dice2 = game.getValueDices()[1];
    }

    private int getSumDices(){
        return dice1+dice2;
    }

    public boolean hasWon() {
        return getSumDices()==7;
    }

    public int[] getValueDices(){
        return new int[]{dice1, dice2};
    }
}
