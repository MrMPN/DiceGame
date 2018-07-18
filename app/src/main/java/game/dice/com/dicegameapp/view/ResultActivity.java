package game.dice.com.dicegameapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameAdapter;
import game.dice.com.dicegameapp.application.GameController;

public class ResultActivity extends AppCompatActivity {
    GameController gc = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListView listView = findViewById(R.id.games);


        try {
            GameAdapter adapter = new GameAdapter(this, gc.getPlayer().getAllGames());
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //Formatting the percentage
            NumberFormat format = NumberFormat.getPercentInstance();
            String percentage = format.format(gc.getPlayerRanking());
            //Finding and setting text to textview
            TextView percent = findViewById(R.id.percent);
            percent.setText("You've won " + percentage + " of the time");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
