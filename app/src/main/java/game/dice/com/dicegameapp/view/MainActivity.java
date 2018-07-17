package game.dice.com.dicegameapp.view;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;

public class MainActivity extends AppCompatActivity {
    GameController gc = new GameController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.addUser);
        Button button = findViewById(R.id.play);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = gc.playGame();
                changeDice();
                changeMessage(result);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gc.hasPlayer()) {
            changeDisplay();
        }
    }

    private void changeDisplay(){
        //Find all relevant views
        TextView nameTextView = findViewById(R.id.name);
        Button button = findViewById(R.id.play);
        TextView textView = findViewById(R.id.warning);

        //Change visibilities and set text to Hello User
        nameTextView.setVisibility(View.VISIBLE);
        nameTextView.setText(gc.getPlayerName());
        button.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }

    private void changeDice(){
        //Find dice textviews
        TextView dice1 = findViewById(R.id.dice1);
        TextView dice2 = findViewById(R.id.dice2);
        dice1.setVisibility(View.VISIBLE);
        dice2.setVisibility(View.VISIBLE);
        //Get the dice values
        int[] diceValues = gc.getResultLastGame();
        //And change the textviews to those values
        dice1.setText(String.valueOf(diceValues[0]));
        dice2.setText(String.valueOf(diceValues[1]));
    }

    private void changeMessage(boolean result){
        TextView message = findViewById(R.id.message);
        message.setVisibility(View.VISIBLE);
        if (result){message.setText("You've won!");}
        else{message.setText("You've lost...");}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.results:
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
