package game.dice.com.dicegameapp.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.GameController;
import game.dice.com.dicegameapp.utilities.MissingPlayerException;

public class MainActivity extends AppCompatActivity {
    GameController controller = new GameController();
    TextView nameTextView;
    Button playButton;
    TextView warningText;
    ImageView dice1;
    ImageView dice2;
    TextView messageText;
    FloatingActionButton fab;
    Resources res;
    TypedArray icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innitViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void innitViews(){
        res = getResources();
        icons = res.obtainTypedArray(R.array.dice_numbers);

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
                startActivityForResult(intent, 1);
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
        //Hiding views in case they were visible
        dice1.setVisibility(View.GONE);
        dice2.setVisibility(View.GONE);
        messageText.setVisibility(View.GONE);

        //Making visible all the views we need to play
        nameTextView.setVisibility(View.VISIBLE);
        nameTextView.setText(controller.getPlayerName()); //This can throw an error
        playButton.setVisibility(View.VISIBLE);
        warningText.setVisibility(View.GONE);
    }

    private void playGame(){
        try {
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

        //We're using the dice result (-1) to access the correct drawable of the array
        dice1.setImageDrawable(icons.getDrawable(diceValues[0]-1));
        dice2.setImageDrawable(icons.getDrawable(diceValues[1]-1));
    }

    private void changeMessage(boolean result){
        messageText.setVisibility(View.VISIBLE);
        if (result){
            messageText.setText(R.string.main_won);}
        else{
            messageText.setText(R.string.main_lost);}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                try {
                    changeToGameMode();
                } catch (MissingPlayerException e) {
                    e.printStackTrace();
                }
            }
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
