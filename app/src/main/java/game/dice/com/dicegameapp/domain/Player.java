package game.dice.com.dicegameapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List<Game> games=new ArrayList<>();
	private boolean hasPlayed = false;
	
	public Player(String name){		
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addGame(Game game){
		this.games.add(game);
		hasPlayed = true;
	}

	public List<Game> getAllGames() {
		return games;
	}

	public boolean hasPlayed() {
		return hasPlayed;
	}
}
