package game.dice.com.dicegameapp.application.dto;

import java.util.ArrayList;
import java.util.List;

import game.dice.com.dicegameapp.domain.Game;
import game.dice.com.dicegameapp.domain.Player;

public class PlayerDTO {
    private String name;
    private List<GameDTO> gamesDTO = new ArrayList<>();
    private boolean hasPlayed;

    public PlayerDTO(Player player){
        this.name = player.getName();
        for(Game game: player.getAllGames()){
            this.gamesDTO.add(new GameDTO(game));
        }
        this.hasPlayed = player.hasPlayed();
    }

    public String getName(){
        return name;
    }

    public List<GameDTO> getAllGames() {
        return gamesDTO;
    }

    public boolean hasPlayed(){
        return hasPlayed;
    }
}
