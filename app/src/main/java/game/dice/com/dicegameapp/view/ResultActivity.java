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
import game.dice.com.dicegameapp.utilities.NoGameException;

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
            player = controller.getPlayer();
            setDisplay();
        } catch (MissingPlayerException e) {
            percent.setText(R.string.no_player_data);
        }
    }


    private void innitViews(){
        listView = findViewById(R.id.games);
        percent = findViewById(R.id.percent);
    }

    private void setAdapter() throws NoGameException {
        GameAdapter adapter = new GameAdapter(this, player.getAllGames());
        listView.setAdapter(adapter);
    }

    private void showInfo() throws NoGameException {
        NumberFormat format = NumberFormat.getPercentInstance();
        String percentage = format.format(player.getRanking());
        String percent_message = String.format(getResources().getString(R.string.won_percent), percentage);
        percent.setText(percent_message);
    }

    private void setDisplay(){
        try {
            setAdapter();
            showInfo();
        } catch (NoGameException e) {
            percent.setText(R.string.no_played_yet);
            e.printStackTrace();
        }
    }
}
