package game.dice.com.dicegameapp.application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import game.dice.com.dicegameapp.R;
import game.dice.com.dicegameapp.domain.Game;

public class GameAdapter extends BaseAdapter {

    private Context context;
    private List<Game> items;

    public GameAdapter (Context context, List<Game> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Game getItem(int i) {
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

        // Get current item to be displayed
        Game currentGame = getItem(i);

        //Get the textviews
        TextView dice1 = view.findViewById(R.id.item_dice1);
        TextView dice2 = view.findViewById(R.id.item_dice2);
        TextView result = view.findViewById(R.id.item_result);

        //Get data
        int[] diceValues = currentGame.getValueDices();
        boolean hasWon = currentGame.hasWon();

        //Set data
        dice1.setText(String.valueOf(diceValues[0]));
        dice2.setText(String.valueOf(diceValues[1]));
        if (hasWon){result.setText("Won");}
        else{result.setText("Lost");}

        return view;
    }
}
