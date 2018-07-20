package game.dice.com.dicegameapp.persistance;

import android.support.annotation.NonNull;

import game.dice.com.dicegameapp.domain.Player;
import game.dice.com.dicegameapp.utilities.MissingPlayerException;

public class PlayerRepository {
    private static Player player;

    public Player getPlayer() throws MissingPlayerException {
        if (player == null)throw new MissingPlayerException("Played not created");
        return player;
    }

    public void savePlayer(@NonNull Player player) {
        PlayerRepository.player = player;
    }
}
