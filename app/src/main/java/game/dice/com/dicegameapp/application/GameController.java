package game.dice.com.dicegameapp.application;

import java.util.List;

import game.dice.com.dicegameapp.application.dto.PlayerDTO;
import game.dice.com.dicegameapp.domain.*;
import game.dice.com.dicegameapp.persistance.PlayerRepository;


public class GameController {

	private PlayerRepository repository = new PlayerRepository();


	public GameController() {
	}

	public void createPlayer(String name) throws Exception {
		Player player = new Player(name);
		repository.savePlayer(player);
	}

	public String getPlayerName() throws Exception {
		Player player = repository.getPlayer();
		return player.getName();
	}

	public boolean playGame() throws Exception {
		Player player = repository.getPlayer();
		Game game = new Game();
		boolean hasWon = game.playGame();
		player.addGame(game);
		return hasWon;
	}

	public PlayerDTO getPlayer() throws Exception {
		return new PlayerDTO(repository.getPlayer());
	}

	public int[] getResultLastGame() throws Exception{
		Player player = repository.getPlayer();
        List<Game> games = player.getAllGames();
        return games.get(games.size()-1).getValueDices();
    }
}
