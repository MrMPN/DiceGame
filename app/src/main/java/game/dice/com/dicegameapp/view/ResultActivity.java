package game.dice.com.dicegameapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;
import game.dice.com.dicegameapp.application.dto.PlayerDTO;

public class ResultActivity extends AppCompatActivity {
    GameController gc = new GameController();
    PlayerDTO player;
    ListView listView;
    TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        percent = findViewById(R.id.percent);

        try {
            player = gc.getPlayer();

            if (player.hasPlayed()) {
                setAdapter();
                showInfo();
            }
            else{
                percent.setText("You haven't played yet");
            }

        } catch (Exception e) {
            e.printStackTrace();
            percent.setText("No player data");
        }

    }

    private void setAdapter(){
        listView = findViewById(R.id.games);
        try {
            PlayerDTO player = gc.getPlayer();
            GameAdapter adapter = new GameAdapter(this, player.getAllGames());
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showInfo(){
        try {
            //Formatting the percentage
            NumberFormat format = NumberFormat.getPercentInstance();
            String percentage = format.format(gc.getPlayerRanking());
            //Finding and setting text to textview
            percent = findViewById(R.id.percent);
            percent.setText("You've won " + percentage + " of the time");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
