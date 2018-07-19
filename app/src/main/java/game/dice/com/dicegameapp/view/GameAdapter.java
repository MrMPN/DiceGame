package game.dice.com.dicegameapp.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.application.dto.GameDTO;
import game.dice.com.dicegameapp.domain.Game;

public class GameAdapter extends BaseAdapter {

    private Context context;
    private List<GameDTO> items;
    private TextView dice1;
    private TextView dice2;
    private TextView result;

    public GameAdapter (Context context, List<GameDTO> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public GameDTO getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.list_item, viewGroup, false);
        }

        return createLayout(view, i);
    }

    private View createLayout(View view, int i){
        // Get current item to be displayed
        GameDTO currentGame = getItem(i);

        //Get data
        int[] diceValues = currentGame.getValueDices();
        boolean hasWon = currentGame.hasWon();

        //Find the views we want to modify
        innitView(view);
        //And set the data
        setData(diceValues, hasWon);

        return view;
    }

    private void innitView (View view){
        dice1 = view.findViewById(R.id.item_dice1);
        dice2 = view.findViewById(R.id.item_dice2);
        result = view.findViewById(R.id.item_result);
    }

    private void setData(int[] diceValues, boolean hasWon){
        dice1.setText(String.valueOf(diceValues[0]));
        dice2.setText(String.valueOf(diceValues[1]));
        if (hasWon){result.setText("Won");}
        else{result.setText("Lost");}
    }
}
