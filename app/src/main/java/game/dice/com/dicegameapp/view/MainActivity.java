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
import game.dice.com.dicegameapp.utilities.MissingPlayerException;

public class MainActivity extends AppCompatActivity {
    GameController controller = new GameController();
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
        try {
            changeToGameMode();
        } catch (MissingPlayerException e) { //We do nothing
            e.printStackTrace();
        }
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

    private void changeToGameMode() throws MissingPlayerException {
        innitViews();
        nameTextView.setVisibility(View.VISIBLE);
        nameTextView.setText(controller.getPlayerName()); //This can throw an error
        playButton.setVisibility(View.VISIBLE);
        warningText.setVisibility(View.GONE);
    }

    private void playGame(){
        try {
            innitViews();
            boolean result = controller.playGame(); // This can throw an error
            int[] diceValues = controller.getResultLastGame(); // This can throw an error
            changeMessage(result);
            changeDice(diceValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeDice(int[] diceValues){
        dice1.setVisibility(View.VISIBLE);
        dice2.setVisibility(View.VISIBLE);

        dice1.setText(String.valueOf(diceValues[0]));
        dice2.setText(String.valueOf(diceValues[1]));
    }

    private void changeMessage(boolean result){
        messageText.setVisibility(View.VISIBLE);
        if (result){
            messageText.setText(R.string.main_won);}
        else{
            messageText.setText(R.string.main_lost);}
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
