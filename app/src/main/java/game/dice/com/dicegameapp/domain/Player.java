package game.dice.com.dicegameapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private List<Game> games=new ArrayList<>();
	
	public Player(String name){		
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addGame(Game game){
		this.games.add(game);
	}

	public List<Game> getAllGames() {
		return games;
	}
}
