package game.dice.com.dicegameapp.application;

import java.util.List;

import game.dice.com.dicegameapp.application.dto.PlayerDTO;
import game.dice.com.dicegameapp.domain.*;
import game.dice.com.dicegameapp.persistance.PlayerRepository;
import game.dice.com.dicegameapp.utilities.MissingPlayerException;
import game.dice.com.dicegameapp.utilities.NoGameException;


public class GameController {

	private PlayerRepository repository = new PlayerRepository();


	public GameController() {
	}

	public void createPlayer(String name) throws IllegalArgumentException {
		Player player = new Player(name);
		repository.savePlayer(player);
	}

	public String getPlayerName() throws MissingPlayerException {
		Player player = repository.getPlayer();
		return player.getName();
	}

	public boolean playGame() throws MissingPlayerException {
		Player player = repository.getPlayer();
		Game game = new Game();
		boolean hasWon = game.playGame();
		player.addGame(game);
		return hasWon;
	}

	public PlayerDTO getPlayer() throws MissingPlayerException {
		return new PlayerDTO(repository.getPlayer());
	}

	public int[] getResultLastGame() throws MissingPlayerException, NoGameException {
		Player player = repository.getPlayer();
		List<Game> games;
		games = player.getAllGames();
		return games.get(games.size()-1).getValueDices();
    }
}
