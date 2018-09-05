package game.dice.com.dicegameapp.application.dto;

import java.util.ArrayList;
import java.util.List;

import game.dice.com.dicegameapp.domain.Game;
import game.dice.com.dicegameapp.domain.Player;
import game.dice.com.dicegameapp.utilities.NoGameException;

public class PlayerDTO {
    private String name;
    private List<GameDTO> gamesDTO = new ArrayList<>();

    public PlayerDTO(Player player){
        this.name = player.getName();
        try {
            for(Game game: player.getAllGames()){
                this.gamesDTO.add(new GameDTO(game));
            }
        } catch (Exception e) {
        }
    }

    public String getName(){
        return name;
    }

    public List<GameDTO> getAllGames() throws NoGameException {
        if (gamesDTO.isEmpty()){
            throw new NoGameException("No games have been played");
        }
        return gamesDTO;
    }

    public double getRanking() throws NoGameException {
        if (gamesDTO.isEmpty()){
            throw new NoGameException("Cannot get ranking, no games have been played.");
        }
        double wins = 0.0;
        for (GameDTO game : gamesDTO) {
            if (game.hasWon())
                wins++;
        }
        return wins / gamesDTO.size();
    }
}
