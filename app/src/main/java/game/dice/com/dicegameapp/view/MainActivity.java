package game.dice.com.dicegameapp.view;

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

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;

public class MainActivity extends AppCompatActivity {
    GameController gc = new GameController();
    TextView nameTextView;
    Button playButton;
    TextView warningText;
    TextView dice1;
    TextView dice2;
    TextView messageText;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innitViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeToGameMode();
    }

    private void innitViews(){
        fab = findViewById(R.id.addUser);
        nameTextView = findViewById(R.id.name);
        playButton = findViewById(R.id.play);
        warningText = findViewById(R.id.warning);
        dice1 = findViewById(R.id.item_dice1);
        dice2 = findViewById(R.id.dice2);
        messageText = findViewById(R.id.message);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserActivity.class);
                startActivity(intent);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame();
            }
        });
    }

    private void changeToGameMode(){
        innitViews();
        try {
            nameTextView.setVisibility(View.VISIBLE);
            nameTextView.setText(gc.getPlayerName());
            playButton.setVisibility(View.VISIBLE);
            warningText.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeDice(){
        innitViews();
        dice1.setVisibility(View.VISIBLE);
        dice2.setVisibility(View.VISIBLE);
        try {
            //Get the dice values
            int[] diceValues = gc.getResultLastGame();
            dice1.setText(String.valueOf(diceValues[0]));
            dice2.setText(String.valueOf(diceValues[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeMessage(boolean result){
        innitViews();
        messageText.setVisibility(View.VISIBLE);
        if (result){
            messageText.setText("You've won!");}
        else{
            messageText.setText("You've lost...");}
    }

    private void playGame(){
        try {
            boolean result = gc.playGame();
            changeDice();
            changeMessage(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
