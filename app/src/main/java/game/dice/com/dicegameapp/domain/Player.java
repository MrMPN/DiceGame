package game.dice.com.dicegameapp.domain;

import java.util.ArrayList;
import java.util.List;

import game.dice.com.dicegameapp.utilities.NoGameException;

public class Player {
	private String name;
	private List<Game> games=new ArrayList<>();
	
	public Player(String name) throws IllegalArgumentException{
		if (name.equals("")){
			throw new IllegalArgumentException();
		}
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addGame(Game game){
		this.games.add(game);
	}

	public List<Game> getAllGames() throws NoGameException {
		if (games.isEmpty()){
			throw new NoGameException("No games have been played");
		}
		return games;
	}
}
