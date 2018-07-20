package game.dice.com.dicegameapp.view;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;

public class UserActivity extends AppCompatActivity {
    TextInputEditText input;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        input = findViewById(R.id.input);
        fab = findViewById(R.id.addName);

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
        GameController controller = new GameController();
        try {
            controller.createPlayer(name);
            finish();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Player name cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}
