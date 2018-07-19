package game.dice.com.dicegameapp.persistance;

import game.dice.com.dicegameapp.domain.Player;

public class PlayerRepository {
    private static Player player;

    public Player getPlayer() throws Exception {
        if (player == null)throw new Exception("Player not created");
        return player;
    }

    public void savePlayer(Player player) throws Exception {
        if (player == null)throw new Exception("Player not created");
        PlayerRepository.player = player;
    }
}
