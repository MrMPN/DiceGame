package game.dice.com.dicegameapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;
import game.dice.com.dicegameapp.application.dto.PlayerDTO;
import game.dice.com.dicegameapp.utilities.MissingPlayerException;

public class ResultActivity extends AppCompatActivity {
    GameController controller = new GameController();
    PlayerDTO player;
    ListView listView;
    TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        innitViews();
        try {
            player = controller.getPlayer();   //Try to get Player
            setDisplay();              //If we got a player, set the display

        } catch (MissingPlayerException e) {
            percent.setText(R.string.no_player_data);   //If there's no player, show a message
        }
    }


    private void innitViews(){
        listView = findViewById(R.id.games);
        percent = findViewById(R.id.percent);
    }

    private void setAdapter(){
        GameAdapter adapter = new GameAdapter(this, player.getAllGames());
        listView.setAdapter(adapter);
    }

    private void showInfo(){
        NumberFormat format = NumberFormat.getPercentInstance();  //
        String percentage = format.format(player.getRanking()); //Formatting the percentage
        String percent_message = String.format(getResources().getString(R.string.won_percent), percentage);
        percent.setText(percent_message);
    }

    private void setDisplay(){
        if (player.hasPlayed()) {  //If he player has already played, start setting everything
            setAdapter();
            showInfo();
        }
        else{
            percent.setText(R.string.no_played_yet);  //If they haven't, just show a message
        }
    }
}
