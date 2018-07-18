package game.dice.com.dicegameapp.application.dto;

import java.util.ArrayList;
import java.util.List;

import game.dice.com.dicegameapp.domain.Game;
import game.dice.com.dicegameapp.domain.Player;

public class PlayerDTO {
    private String name;
    private List<Game> games=new ArrayList<>();

    public PlayerDTO(Player player){
        this.name = player.getName();
        this.games = player.getAllGames();
    }

    public String getName(){
        return name;
    }
    public List<Game> getAllGames() {
        return games;
    }
}
