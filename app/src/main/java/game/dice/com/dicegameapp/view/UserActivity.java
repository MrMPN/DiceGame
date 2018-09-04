package game.dice.com.dicegameapp.view;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;
import game.dice.com.dicegameapp.application.dto.PlayerDTO;
import game.dice.com.dicegameapp.utilities.MissingPlayerException;

public class UserActivity extends AppCompatActivity {
    TextInputEditText input;
    FloatingActionButton fab;
    TextView tooltip;
    GameController controller = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        innitViews();
        try{
            showTooltip();
        } catch (MissingPlayerException e) {
            e.printStackTrace();
        }
    }

    private void innitViews(){
        input = findViewById(R.id.input);
        fab = findViewById(R.id.addName);
        tooltip = findViewById(R.id.changeuser);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.VISIBLE);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPlayer();
            }
        });
    }

    private void createPlayer(){
        String name = input.getText().toString();
        try {
            controller.createPlayer(name);
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, R.string.user_cannnot_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private PlayerDTO checkPlayer() throws MissingPlayerException {
        return controller.getPlayer();
    }

    private void showTooltip() throws MissingPlayerException{
        PlayerDTO player = checkPlayer();
        tooltip.setVisibility(View.VISIBLE);
        tooltip.setText(String.format(getResources().getString(R.string.user_tooltip), player.getName()));
    }

}
